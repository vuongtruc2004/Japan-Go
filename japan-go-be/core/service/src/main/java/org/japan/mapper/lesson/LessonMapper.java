package org.japan.mapper.lesson;

import org.japan.dto.response.lesson.LessonResponse;
import org.japan.entity.lesson.KanjiLessonEntity;
import org.japan.entity.lesson.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {BookMapper.class, KanjiLessonMapper.class, GrammarLessonMapper.class}
)
public interface LessonMapper {
    @Mapping(target = "grammarLesson", ignore = true)
    @Mapping(target = "pageCount", source = "kanjiLesson")
    LessonResponse mapEntityToResponseDetailsKanji(LessonEntity entity);

    @Mapping(target = "kanjiLesson", ignore = true)
    @Mapping(target = "pageCount", ignore = true)
    LessonResponse mapEntityToResponseDetailsGrammar(LessonEntity entity);

    @Mapping(target = "pageCount", ignore = true)
    @Mapping(target = "grammarLesson", ignore = true)
    @Mapping(target = "kanjiLesson", ignore = true)
    @Mapping(target = "book", ignore = true)
    LessonResponse mapEntityToResponseSummary(LessonEntity entity);

    default Integer getPageCountFromKanjiLesson(KanjiLessonEntity kanjiLessonEntity) {
        if (kanjiLessonEntity == null || kanjiLessonEntity.getKanjiPages() == null) {
            return 0;
        }
        return kanjiLessonEntity.getKanjiPages().size();
    }
}
