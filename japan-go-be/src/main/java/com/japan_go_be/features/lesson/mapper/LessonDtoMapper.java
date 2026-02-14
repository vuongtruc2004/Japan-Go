package com.japan_go_be.features.lesson.mapper;

import com.japan_go_be.features.kanji.dto.response.KanjiPageResponse;
import com.japan_go_be.features.kanji.mapper.KanjiDtoMapper;
import com.japan_go_be.features.lesson.constant.LessonTypeEnum;
import com.japan_go_be.features.lesson.dto.response.KanjiLessonResponse;
import com.japan_go_be.features.lesson.dto.response.LessonDetailsResponse;
import com.japan_go_be.features.lesson.entity.KanjiLessonEntity;
import com.japan_go_be.features.lesson.entity.LessonEntity;
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
