package org.japan.dto.mapper.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.constants.lesson.LessonTypeEnum;
import org.japan.dto.mapper.GrammarDtoMapper;
import org.japan.dto.mapper.KanjiDtoMapper;
import org.japan.dto.response.grammar.GrammarResponse;
import org.japan.dto.response.kanji.KanjiPageResponse;
import org.japan.dto.response.lesson.GrammarLessonResponse;
import org.japan.dto.response.lesson.KanjiLessonResponse;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.entity.lesson.GrammarLessonEntity;
import org.japan.entity.lesson.KanjiLessonEntity;
import org.japan.entity.lesson.LessonEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonDtoMapper {

    private final KanjiDtoMapper kanjiDtoMapper;
    private final GrammarDtoMapper grammarDtoMapper;
    private final BookDtoMapper bookDtoMapper;

    public LessonResponse lessonEntityToLessonResponseDetails(LessonEntity lessonEntity) {
        LessonResponse lessonResponse = lessonEntityToLessonResponseSummary(lessonEntity);

        if (lessonEntity.getLessonType() == LessonTypeEnum.KANJI) {
            lessonResponse.setKanjiLesson(kanjiLessonEntityToKanjiLessonResponseDetails(lessonEntity.getKanjiLesson()));
        } else if (lessonEntity.getLessonType() == LessonTypeEnum.GRAMMAR) {
            lessonResponse.setGrammarLesson(grammarLessonEntityToGrammarLessonResponseDetails(lessonEntity.getGrammarLesson()));
        }

        return lessonResponse;
    }

    public LessonResponse lessonEntityToLessonResponseSummary(LessonEntity lessonEntity) {
        LessonResponse lessonResponse = LessonResponse.builder()
                .id(lessonEntity.getId())
                .lessonName(lessonEntity.getLessonName())
                .description(lessonEntity.getDescription())
                .lessonType(lessonEntity.getLessonType())
                .book(bookDtoMapper.mapBookEntityToBookResponseSummary(lessonEntity.getBook()))
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
