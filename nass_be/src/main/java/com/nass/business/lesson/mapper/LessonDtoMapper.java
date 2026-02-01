package com.nass.business.lesson.mapper;

import com.nass.business.kanji.mapper.KanjiDtoMapper;
import com.nass.business.lesson.dto.response.KanjiLessonResponse;
import com.nass.business.lesson.dto.response.LessonDetailsResponse;
import com.nass.business.lesson.constant.LessonTypeEnum;
import com.nass.business.lesson.entity.KanjiLessonEntity;
import com.nass.business.lesson.entity.LessonEntity;
import com.nass.business.kanji.dto.response.KanjiPageResponse;
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
