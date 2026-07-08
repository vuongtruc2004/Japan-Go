package org.japan.mapper.kanji;

import org.japan.dto.response.kanji.SinoVietnameseResponse;
import org.japan.entity.kanji.SinoVietnameseEntity;
import org.japan.entity.kanji.SinoVietnameseMeaningEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SinoVietnameseMapper {
    @Mapping(target = "sinoVietnameseMeaningList",
            source = "sinoVietnameseMeaningList",
            qualifiedByName = "readingTextList")
    SinoVietnameseResponse mapEntityToResponseDetails(SinoVietnameseEntity entity);

    @Named("readingTextList")
    default List<String> mapSinoVietnameseMeaningListToReadingTextList(List<SinoVietnameseMeaningEntity> sinoVietnameseMeaningEntityList) {
        if (sinoVietnameseMeaningEntityList == null) return List.of();
        return sinoVietnameseMeaningEntityList
                .stream()
                .map(SinoVietnameseMeaningEntity::getReadingText)
                .toList();
    }
}
