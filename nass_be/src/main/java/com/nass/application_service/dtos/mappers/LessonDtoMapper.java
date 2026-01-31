package com.nass.application_service.dtos.mappers;

import com.nass.application_service.dtos.responses.kanji.KanjiPageResponse;
import com.nass.application_service.dtos.responses.lesson.KanjiLessonResponse;
import com.nass.application_service.dtos.responses.lesson.LessonDetailsResponse;
import com.nass.contract.enums.lesson.LessonTypeEnum;
import com.nass.infrastructure.entities.lesson.KanjiLessonEntity;
import com.nass.infrastructure.entities.lesson.LessonEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonDtoMapper {

    private final ModelMapper modelMapper;
    private final KanjiDtoMapper kanjiDtoMapper;

    public LessonDetailsResponse lessonEntityToLessonDetailsResponse(LessonEntity lessonEntity) {
        LessonDetailsResponse lessonDetailsResponse = modelMapper.map(lessonEntity, LessonDetailsResponse.class);
        if (lessonEntity.getLessonType().equals(LessonTypeEnum.KANJI)) {
            lessonDetailsResponse.setKanjiLesson(kanjiLessonEntityToKanjiLessonResponse(lessonEntity.getKanjiLesson()));
        }
        return lessonDetailsResponse;
    }

    public KanjiLessonResponse kanjiLessonEntityToKanjiLessonResponse(KanjiLessonEntity kanjiLessonEntity) {
        KanjiLessonResponse kanjiLessonResponse = modelMapper.map(kanjiLessonEntity, KanjiLessonResponse.class);
        List<KanjiPageResponse> kanjiPageResponses = kanjiLessonEntity.getKanjiPages().stream()
                .map(kanjiDtoMapper::kanjiPageEntityToKanjiPageResponse).toList();
        kanjiLessonResponse.setKanjiPages(kanjiPageResponses);
        return kanjiLessonResponse;
    }
}
