package com.japan_go_be.business.dto.mappers;

import com.japan_go_be.business.dto.responses.grammar.GrammarResponse;
import com.japan_go_be.business.dto.responses.kanji.KanjiPageResponse;
import com.japan_go_be.business.dto.responses.lesson.GrammarLessonResponse;
import com.japan_go_be.business.dto.responses.lesson.KanjiLessonResponse;
import com.japan_go_be.business.dto.responses.lesson.LessonResponse;
import com.japan_go_be.contract.constants.lesson.LessonTypeEnum;
import com.japan_go_be.infrastructure.entities.lesson.GrammarLessonEntity;
import com.japan_go_be.infrastructure.entities.lesson.KanjiLessonEntity;
import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonDtoMapper {

    private final KanjiDtoMapper kanjiDtoMapper;
    private final GrammarDtoMapper grammarDtoMapper;

    public LessonResponse lessonEntityToLessonResponseDetails(LessonEntity lessonEntity) {
        LessonResponse lessonResponse = lessonEntityToLessonResponseSummary(lessonEntity);

        if (lessonEntity.getLessonType() == LessonTypeEnum.KANJI) {
            lessonResponse.setKanjiLesson(kanjiLessonEntityToKanjiLessonResponseDetails(lessonEntity.getKanjiLesson()));
        } else if (lessonEntity.getLessonType() == LessonTypeEnum.GRAMMAR) {
            lessonResponse.setGrammarLesson(grammarLessonEntityToGrammarLessonResponseDetails(lessonEntity.getGrammarLesson()));
        }

        if (!lessonEntity.getFolders().isEmpty()) {

        }

        return lessonResponse;
    }

    public LessonResponse lessonEntityToLessonResponseSummary(LessonEntity lessonEntity) {
        LessonResponse lessonResponse = LessonResponse.builder()
                .id(lessonEntity.getId())
                .lessonName(lessonEntity.getLessonName())
                .description(lessonEntity.getDescription())
                .lessonType(lessonEntity.getLessonType())
                .createdTime(lessonEntity.getCreatedTime())
                .modifiedTime(lessonEntity.getModifiedTime())
                .build();
        if (lessonEntity.getLessonType() == LessonTypeEnum.KANJI) {
            lessonResponse.setPageCount((long) lessonEntity.getKanjiLesson().getKanjiPages().size());
        } else if (lessonEntity.getLessonType() == LessonTypeEnum.GRAMMAR) {
            lessonResponse.setPageCount((long) lessonEntity.getGrammarLesson().getGrammars().size());
        }
        return lessonResponse;
    }

    public KanjiLessonResponse kanjiLessonEntityToKanjiLessonResponseDetails(KanjiLessonEntity kanjiLessonEntity) {
        KanjiLessonResponse kanjiLessonResponse = KanjiLessonResponse.builder()
                .id(kanjiLessonEntity.getId())
                .build();

        List<KanjiPageResponse> kanjiPageResponses = kanjiLessonEntity.getKanjiPages().stream()
                .map(kanjiDtoMapper::kanjiPageEntityToKanjiPageResponse).toList();

        kanjiLessonResponse.setKanjiPages(kanjiPageResponses);

        return kanjiLessonResponse;
    }

    public GrammarLessonResponse grammarLessonEntityToGrammarLessonResponseDetails(GrammarLessonEntity grammarLessonEntity) {
        GrammarLessonResponse grammarLessonResponse = GrammarLessonResponse.builder()
                .id(grammarLessonEntity.getId())
                .build();

        List<GrammarResponse> grammarResponses = grammarLessonEntity.getGrammars()
                .stream()
                .map(grammarDtoMapper::grammarEntityToGrammarResponseDetails)
                .toList();

        grammarLessonResponse.setGrammars(grammarResponses);
        return grammarLessonResponse;
    }
}
