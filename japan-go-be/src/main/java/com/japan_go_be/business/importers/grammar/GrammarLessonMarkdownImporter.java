package com.japan_go_be.business.importers.grammar;

import com.japan_go_be.business.exception.FileNotValidException;
import com.japan_go_be.business.helpers.grammar.GrammarHelper;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.constants.grammar.GrammarPattern;
import com.japan_go_be.contract.constants.lesson.GrammarComponentEnum;
import com.japan_go_be.contract.constants.lesson.LessonTypeEnum;
import com.japan_go_be.contract.constants.message.FileMessage;
import com.japan_go_be.contract.utils.StringUtil;
import com.japan_go_be.infrastructure.entities.grammar.*;
import com.japan_go_be.infrastructure.entities.lesson.GrammarLessonEntity;
import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
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
    private final GrammarHelper grammarHelper;

    public LessonEntity getLessonFromMarkdown(String markdown) {
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
        LessonEntity lesson = LessonEntity.builder()
                .lessonName(matcher.group(1))
                .lessonType(LessonTypeEnum.GRAMMAR)
                .build();
        GrammarLessonEntity grammarLesson = getGrammarLessonFromMarkdown(lines);
        lesson.setGrammarLesson(grammarLesson);
        return lesson;
    }

    private GrammarLessonEntity getGrammarLessonFromMarkdown(List<String> lines) {
        GrammarLessonEntity grammarLesson = new GrammarLessonEntity();
        GrammarEntity grammar = null;
        GrammarMeaningEntity grammarMeaning = null;
        GrammarStructureEntity structure = null;
        GrammarExampleEntity example = null;
        GrammarNoteEntity grammarNote = null;

        SentenceEntity sentence = null;

        GrammarComponentEnum currentGrammarComponent = GrammarComponentEnum.NONE;

        int numOfLines = lines.size();
        int currentLineNum = 1;

        while (currentLineNum < numOfLines) {
            String currentLine = lines.get(currentLineNum);

            Matcher matcher = GrammarPattern.GRAMMAR_HEADER.matcher(currentLine);
            if (matcher.matches()) {
                grammarMeaning = new GrammarMeaningEntity();
                structure = new GrammarStructureEntity();
                example = new GrammarExampleEntity();
                grammarNote = new GrammarNoteEntity();

                String grammarTitle = matcher.group(1).replaceFirst(GrammarPattern.ROMAN_NUMERAL.pattern(), "");
                System.out.println(grammarTitle);
                grammar = GrammarEntity.builder()
                        .grammarTitle(grammarTitle)
                        .grammarTitleFurigana(grammarHelper.getGrammarTitleFurigana(grammarTitle))
                        .grammarTitleRomaji(grammarHelper.getGrammarTitleRomaji(grammarTitle))
                        .grammarMeaning(grammarMeaning)
                        .grammarStructure(structure)
                        .grammarExample(example)
                        .grammarNote(grammarNote)
                        .build();

                grammar.setGrammarLesson(grammarLesson);
                grammarLesson.getGrammars().add(grammar);
                currentLineNum++;
                continue;
            }

            if (grammar == null) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
                );
            }

            currentGrammarComponent = getCurrentGrammarComponent(currentLine, currentGrammarComponent);

            matcher = GrammarPattern.ORDERED_ITEM.matcher(currentLine);
            if (matcher.matches()) {
                StringBuilder orderItem = new StringBuilder(matcher.group(2));

                currentLineNum++;

                while (currentLineNum < numOfLines && notMatchAnyOf(lines.get(currentLineNum)) && !lines.get(currentLineNum).equals("---")) {
                    orderItem.append("\n").append(lines.get(currentLineNum));
                    currentLineNum++;
                }

                String japaneseRaw;
                String japaneseHtml;
                switch (currentGrammarComponent) {
                    case MEANING:
                        japaneseRaw = stringUtil.removeAllSubstring(orderItem.toString(), "**");
                        sentence = SentenceEntity.builder()
                                .japaneseRaw(japaneseRaw)
                                .grammarMeaning(grammarMeaning)
                                .build();
                        grammarMeaning.getSentences().add(sentence);
                        break;
                    case STRUCTURE:
                        japaneseRaw = stringUtil.removeAllSubstring(orderItem.toString(), "~~");
                        japaneseHtml = stringUtil.replaceAllSubstringWithHtmlTag(
                                orderItem.toString(),
                                "~~",
                                "<del>",
                                "</del>");
                        sentence = SentenceEntity.builder()
                                .japaneseRaw(japaneseRaw)
                                .japaneseHtml(japaneseHtml)
                                .grammarStructure(structure)
                                .build();
                        structure.getSentences().add(sentence);
                        break;
                    case EXAMPLE:
                        japaneseRaw = stringUtil.removeAllSubstring(orderItem.toString(), "**");
                        japaneseHtml = stringUtil.replaceAllSubstringWithHtmlTag(
                                orderItem.toString(),
                                "**",
                                "<span class='grammar-highlight'>",
                                "</span>");
                        sentence = SentenceEntity.builder()
                                .japaneseRaw(japaneseRaw)
                                .japaneseHtml(japaneseHtml)
                                .grammarExample(example)
                                .build();
                        example.getSentences().add(sentence);
                        break;
                    case NOTE:
                        japaneseRaw = stringUtil.removeAllSubstring(orderItem.toString(), "**");
                        sentence = SentenceEntity.builder()
                                .japaneseRaw(japaneseRaw)
                                .grammarNote(grammarNote)
                                .build();
                        grammarNote.getSentences().add(sentence);
                        break;
                    default:
                }
            } else {
                matcher = GrammarPattern.ARROW_LINE.matcher(currentLine);
                if (matcher.matches()) {
                    StringBuilder orderItem = new StringBuilder(matcher.group(1));

                    currentLineNum++;

                    while (currentLineNum < numOfLines && notMatchAnyOf(lines.get(currentLineNum)) && !lines.get(currentLineNum).equals("---")) {
                        orderItem.append("\n").append(lines.get(currentLineNum));
                        currentLineNum++;
                    }

                    if (sentence == null) {
                        throw new FileNotValidException(
                                i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                                i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
                        );
                    }
                    switch (currentGrammarComponent) {
                        case MEANING, EXAMPLE, NOTE:
                            sentence.setVietnameseRaw(orderItem.toString());
                            break;
                        default:
                    }
                } else {
                    currentLineNum++;
                }
            }
        }
        return grammarLesson;
    }

    private GrammarComponentEnum getCurrentGrammarComponent(String currentLine, GrammarComponentEnum currentGrammarComponent) {
        if (currentLine.matches(GrammarPattern.MEANING_HEADER.pattern())) {
            currentGrammarComponent = GrammarComponentEnum.MEANING;

        } else if (currentLine.matches(GrammarPattern.STRUCTURE_HEADER.pattern())) {
            currentGrammarComponent = GrammarComponentEnum.STRUCTURE;

        } else if (currentLine.matches(GrammarPattern.EXAMPLE_HEADER.pattern())) {
            currentGrammarComponent = GrammarComponentEnum.EXAMPLE;

        } else if (currentLine.matches(GrammarPattern.ADDITIONAL_NOTE_HEADER.pattern())) {
            currentGrammarComponent = GrammarComponentEnum.NOTE;
        }
        return currentGrammarComponent;
    }

    private boolean notMatchAnyOf(String line) {
        return !line.matches(GrammarPattern.LESSON_HEADER.pattern()) &&
                !line.matches(GrammarPattern.GRAMMAR_HEADER.pattern()) &&
                !line.matches(GrammarPattern.MEANING_HEADER.pattern()) &&
                !line.matches(GrammarPattern.STRUCTURE_HEADER.pattern()) &&
                !line.matches(GrammarPattern.EXAMPLE_HEADER.pattern()) &&
                !line.matches(GrammarPattern.ADDITIONAL_NOTE_HEADER.pattern()) &&
                !line.matches(GrammarPattern.ORDERED_ITEM.pattern()) &&
                !line.matches(GrammarPattern.ARROW_LINE.pattern());
    }

}

