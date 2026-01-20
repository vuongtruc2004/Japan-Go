package com.nass.application_service.importers;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.ReplaceHelper;
import com.nass.contract.constants.GrammarPattern;
import com.nass.contract.enums.GrammarComponentEnum;
import com.nass.infrastructure.entities.grammar.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

@Slf4j
@Component
@RequiredArgsConstructor
public class LessonMarkdownImporter {

    private final ReplaceHelper replaceHelper;

    public LessonEntity importLessonFromNotion(String markdown) {
        List<String> lines = Arrays.stream(markdown
                        .replace("\r\n", "\n")
                        .replace('\r', '\n')
                        .split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        Matcher matcher = GrammarPattern.LESSON_HEADER.matcher(lines.getFirst());
        if (!matcher.matches()) {
            throw new FileNotValidException("File not valid format");
        }
        LessonEntity lesson = LessonEntity.builder()
                .lessonTitle(matcher.group(1))
                .build();
        enrichGrammarsToLesson(lesson, lines);
        return lesson;
    }

    private void enrichGrammarsToLesson(LessonEntity lesson, List<String> lines) {
        if (lesson == null) throw new FileNotValidException("Invalid markdown format!");

        GrammarEntity grammar = null;
        GrammarMeaningEntity grammarMeaning = null;
        StructureEntity structure = null;
        ExampleEntity example = null;
        AdditionalNote additionalNote = null;

        GrammarComponentEnum componentEnum = GrammarComponentEnum.NONE;
        SentenceEntity sentence = null;

        for (int lineNum = 1; lineNum < lines.size(); lineNum++) {
            String currentLine = lines.get(lineNum);

            Matcher matcher = GrammarPattern.GRAMMAR_HEADER.matcher(currentLine);
            if (matcher.matches()) {
                grammarMeaning = new GrammarMeaningEntity();
                structure = new StructureEntity();
                example = new ExampleEntity();
                additionalNote = new AdditionalNote();

                grammar = GrammarEntity.builder()
                        .grammarTitle(matcher.group(1))
                        .grammarMeaning(grammarMeaning)
                        .structure(structure)
                        .example(example)
                        .additionalNote(additionalNote)
                        .lesson(lesson)
                        .build();

                lesson.getGrammars().add(grammar);
                continue;
            }

            if (grammar == null) throw new FileNotValidException("Invalid markdown format!");

            if (currentLine.matches(GrammarPattern.MEANING_HEADER.pattern())) {
                grammarMeaning.setGrammar(grammar);
                grammar.setGrammarMeaning(grammarMeaning);
                componentEnum = GrammarComponentEnum.MEANING;

            } else if (currentLine.matches(GrammarPattern.STRUCTURE_HEADER.pattern())) {
                structure.setGrammar(grammar);
                grammar.setGrammarMeaning(grammarMeaning);
                componentEnum = GrammarComponentEnum.STRUCTURE;

            } else if (currentLine.matches(GrammarPattern.EXAMPLE_HEADER.pattern())) {
                example.setGrammar(grammar);
                grammar.setExample(example);
                componentEnum = GrammarComponentEnum.EXAMPLE;

            } else if (currentLine.matches(GrammarPattern.ADDITIONAL_NOTE_HEADER.pattern())) {
                additionalNote.setGrammar(grammar);
                grammar.setAdditionalNote(additionalNote);
                componentEnum = GrammarComponentEnum.ADDITIONAL_NOTE;
            }

            matcher = GrammarPattern.TRANSLATION_QUOTE.matcher(currentLine);
            if (matcher.matches()) {
                grammarMeaning.setVietnameseTranslation(matcher.group(1));
                continue;
            }
            matcher = GrammarPattern.ORDERED_ITEM.matcher(currentLine);
            if (matcher.matches()) {
                switch (componentEnum) {
                    case MEANING:
                        sentence = SentenceEntity.builder()
                                .vietnamese(matcher.group(2))
                                .grammarMeaning(grammarMeaning)
                                .build();
                        grammarMeaning.getSentences().add(sentence);
                        break;
                    case STRUCTURE:
                        sentence = SentenceEntity.builder()
                                .vietnamese(matcher.group(2))
                                .structure(structure)
                                .build();
                        structure.getSentences().add(sentence);
                        break;
                    case EXAMPLE:
                        String japanese = replaceHelper.replaceAllSubstringWithHtmlTag(
                                matcher.group(2),
                                "**",
                                "<span class='highlight'>",
                                "</span>");
                        sentence = SentenceEntity.builder()
                                .japanese(japanese)
                                .example(example)
                                .build();
                        example.getSentences().add(sentence);
                        break;
                    case ADDITIONAL_NOTE:
                        sentence = SentenceEntity.builder()
                                .vietnamese(matcher.group(2))
                                .additionalNote(additionalNote)
                                .build();
                        additionalNote.getSentences().add(sentence);
                        break;
                    default:
                }
            }

            matcher = GrammarPattern.ARROW_LINE.matcher(currentLine);
            if (matcher.matches()) {
                if (sentence == null) throw new FileNotValidException("Invalid markdown format!");
                sentence.setVietnamese(matcher.group(1));
            }
        }
    }
}

