package com.japan_go_be.features.lesson.mapper;

import com.japan_go_be.features.kanji.dto.response.KanjiPageResponse;
import com.japan_go_be.features.kanji.mapper.KanjiDtoMapper;
import com.japan_go_be.features.lesson.constant.LessonTypeEnum;
import com.japan_go_be.features.lesson.dto.response.GrammarLessonResponse;
import com.japan_go_be.features.lesson.dto.response.KanjiLessonResponse;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import com.japan_go_be.features.lesson.entity.GrammarLessonEntity;
import com.japan_go_be.features.lesson.entity.KanjiLessonEntity;
import com.japan_go_be.features.lesson.entity.LessonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonDtoMapper {

    private final KanjiDtoMapper kanjiDtoMapper;

    public LessonResponse lessonEntityToLessonResponseDetails(LessonEntity lessonEntity) {
        LessonResponse lessonResponse = lessonEntityToLessonResponseSummary(lessonEntity);

        if (lessonEntity.getLessonType() == LessonTypeEnum.KANJI) {
            lessonResponse.setKanjiLesson(kanjiLessonEntityToKanjiLessonResponseDetails(lessonEntity.getKanjiLesson()));
        } else if (lessonEntity.getLessonType() == LessonTypeEnum.GRAMMAR) {
            lessonResponse.setGrammarLesson(grammarLessonEntityToGrammarLessonResponseSummary(lessonEntity.getGrammarLesson()));
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

    public GrammarLessonResponse grammarLessonEntityToGrammarLessonResponseSummary(GrammarLessonEntity grammarLessonEntity) {
        return GrammarLessonResponse.builder()
                .id(grammarLessonEntity.getId())
                .grammarLessonTitle(grammarLessonEntity.getGrammarLessonTitle())
                .build();
    }
}
