package org.japan.mapper.grammar;

import org.japan.dto.response.sentence.SentenceResponse;
import org.japan.entity.grammar.SentenceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SentenceMapper {
    SentenceResponse mapEntityToResponseDetails(SentenceEntity entity);
}
