package com.japan_go_be.features.lesson.helper;

import com.japan_go_be.common.constant.message.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.common.util.StringUtil;
import com.japan_go_be.features.grammar.constant.GrammarPattern;
import com.japan_go_be.features.grammar.entity.*;
import com.japan_go_be.features.lesson.constant.GrammarComponentEnum;
import com.japan_go_be.features.lesson.entity.GrammarLessonEntity;
import com.japan_go_be.features.sentence.entity.SentenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

@Component
@RequiredArgsConstructor
public class GrammarLessonMarkdownImporter {

    private final StringUtil stringUtil;
    private final I18nService i18nService;

    public GrammarLessonEntity importGrammarLessonFromNotion(String markdown) {
        List<String> lines = Arrays.stream(markdown
                        .replace("\r\n", "\n")
                        .replace('\r', '\n')
                        .split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        Matcher matcher = GrammarPattern.LESSON_HEADER.matcher(lines.getFirst());
        if (!matcher.matches()) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                    i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
            );
        }
        GrammarLessonEntity grammarLesson = GrammarLessonEntity.builder()
                .grammarLessonTitle(matcher.group(1))
                .build();
        enrichGrammarsToGrammarLesson(grammarLesson, lines);
        return grammarLesson;
    }

    private void enrichGrammarsToGrammarLesson(GrammarLessonEntity grammarLesson, List<String> lines) {
        GrammarEntity grammar = null;
        GrammarMeaningEntity grammarMeaning = null;
        GrammarStructureEntity structure = null;
        GrammarExampleEntity example = null;
        GrammarNoteEntity grammarNote = null;

        GrammarComponentEnum componentEnum = GrammarComponentEnum.NONE;
        SentenceEntity sentence = null;

        for (int lineNum = 1; lineNum < lines.size(); lineNum++) {
            String currentLine = lines.get(lineNum);

            Matcher matcher = GrammarPattern.GRAMMAR_HEADER.matcher(currentLine);
            if (matcher.matches()) {
                grammarMeaning = new GrammarMeaningEntity();
                structure = new GrammarStructureEntity();
                example = new GrammarExampleEntity();
                grammarNote = new GrammarNoteEntity();

                grammar = GrammarEntity.builder()
                        .grammarTitle(matcher.group(1))
                        .grammarMeaning(grammarMeaning)
                        .grammarStructure(structure)
                        .grammarExample(example)
                        .grammarNote(grammarNote)
                        .build();

                grammar.setGrammarLesson(grammarLesson);
                grammarLesson.getGrammars().add(grammar);
                continue;
            }

            if (grammar == null) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
                );
            }

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
                grammar.setGrammarExample(example);
                componentEnum = GrammarComponentEnum.EXAMPLE;

            } else if (currentLine.matches(GrammarPattern.ADDITIONAL_NOTE_HEADER.pattern())) {
                grammarNote.setGrammar(grammar);
                grammar.setGrammarNote(grammarNote);
                componentEnum = GrammarComponentEnum.NOTE;
            }

            matcher = GrammarPattern.ORDERED_ITEM.matcher(currentLine);
            if (matcher.matches()) {
                String japaneseRaw;
                String japaneseHtml;
                switch (componentEnum) {
                    case MEANING:
                        japaneseRaw = stringUtil.removeAllSubstring(matcher.group(2), "**");
                        japaneseHtml = stringUtil.replaceAllSubstringWithHtmlTag(
                                matcher.group(2),
                                "**",
                                "<strong>",
                                "</strong>");
                        sentence = SentenceEntity.builder()
                                .japaneseRaw(japaneseRaw)
                                .japaneseHtml(japaneseHtml)
                                .grammarMeaning(grammarMeaning)
                                .build();
                        grammarMeaning.getSentences().add(sentence);
                        break;
                    case STRUCTURE:
                        sentence = SentenceEntity.builder()
                                .japaneseRaw(matcher.group(2))
                                .grammarStructure(structure)
                                .build();
                        structure.getSentences().add(sentence);
                        break;
                    case EXAMPLE:
                        japaneseRaw = stringUtil.removeAllSubstring(matcher.group(2), "**");
                        japaneseHtml = stringUtil.replaceAllSubstringWithHtmlTag(
                                matcher.group(2),
                                "**",
                                "<span class='highlight'>",
                                "</span>");
                        sentence = SentenceEntity.builder()
                                .japaneseRaw(japaneseRaw)
                                .japaneseHtml(japaneseHtml)
                                .grammarExample(example)
                                .build();
                        example.getSentences().add(sentence);
                        break;
                    case NOTE:
                        japaneseRaw = stringUtil.removeAllSubstring(matcher.group(2), "**");
                        japaneseHtml = stringUtil.replaceAllSubstringWithHtmlTag(
                                matcher.group(2),
                                "**",
                                "<strong>",
                                "</strong>");
                        sentence = SentenceEntity.builder()
                                .japaneseRaw(japaneseRaw)
                                .japaneseHtml(japaneseHtml)
                                .grammarNote(grammarNote)
                                .build();
                        grammarNote.getSentences().add(sentence);
                        break;
                    default:
                }
            }

            matcher = GrammarPattern.ARROW_LINE.matcher(currentLine);
            if (matcher.matches()) {
                if (sentence == null) {
                    throw new FileNotValidException(
                            i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                            i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
                    );
                }
                switch (componentEnum) {
                    case MEANING, EXAMPLE, NOTE:
                        sentence.setVietnameseRaw(matcher.group(1));
                        break;
                    default:
                }
            }
        }
    }
}

