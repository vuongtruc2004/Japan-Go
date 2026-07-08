package org.japan.mapper.grammar;

import org.japan.dto.response.grammar.*;
import org.japan.entity.grammar.*;
import org.japan.entity.lesson.BookEntity;
import org.japan.entity.lesson.GrammarLessonEntity;
import org.japan.entity.lesson.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring",
        uses = {SentenceMapper.class}
)
public interface GrammarMapper {

    @Named("details")
    @Mapping(target = "bookTitle", source = "grammarLesson",
            qualifiedByName = "bookTitle")
    @Mapping(target = "lessonName", source = "grammarLesson",
            qualifiedByName = "lessonName")
    @Mapping(target = "lessonId", source = "grammarLesson",
            qualifiedByName = "lessonId")
    GrammarResponse mapEntityToResponseDetails(GrammarEntity entity);

    @Named("summary")
    @Mapping(target = "bookTitle", source = "grammarLesson",
            qualifiedByName = "bookTitle")
    @Mapping(target = "lessonName", source = "grammarLesson",
            qualifiedByName = "lessonName")
    @Mapping(target = "lessonId", source = "grammarLesson",
            qualifiedByName = "lessonId")
    @Mapping(target = "grammarMeaning", ignore = true)
    @Mapping(target = "grammarStructure", ignore = true)
    @Mapping(target = "grammarExample", ignore = true)
    @Mapping(target = "grammarNote", ignore = true)
    GrammarResponse mapEntityToResponseSummary(GrammarEntity entity);

    GrammarMeaningResponse mapEntityToResponseDetails(GrammarMeaningEntity entity);

    GrammarStructureResponse mapEntityToResponseDetails(GrammarStructureEntity entity);

    GrammarExampleResponse mapEntityToResponseDetails(GrammarExampleEntity entity);

    GrammarNoteResponse mapEntityToResponseDetails(GrammarNoteEntity entity);

    @Named("bookTitle")
    default String getBookTitleFromGrammarLesson(GrammarLessonEntity entity) {
        if (entity != null) {
            LessonEntity lesson = entity.getLesson();
            if (lesson != null) {
                BookEntity book = lesson.getBook();
                if (book != null) {
                    return book.getVietnameseTitle();
                }
            }
        }
        return null;
    }

    @Named("lessonName")
    default String getLessonNameFromGrammarLesson(GrammarLessonEntity entity) {
        if (entity != null) {
            LessonEntity lesson = entity.getLesson();
            if (lesson != null) {
                return lesson.getLessonName();
            }
        }
        return null;
    }

    @Named("lessonId")
    default Long getLessonIdFromGrammarLesson(GrammarLessonEntity entity) {
        if (entity != null) {
            LessonEntity lesson = entity.getLesson();
            if (lesson != null) {
                return lesson.getId();
            }
        }
        return null;
    }
}
