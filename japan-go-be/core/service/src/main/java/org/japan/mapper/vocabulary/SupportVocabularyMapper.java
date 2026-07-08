package org.japan.mapper.vocabulary;

import org.japan.dto.response.vocabulary.SupportVocabularyResponse;
import org.japan.entity.vocabulary.SupportVocabularyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupportVocabularyMapper {
    SupportVocabularyResponse mapEntityToResponseDetails(SupportVocabularyEntity entity);
}
