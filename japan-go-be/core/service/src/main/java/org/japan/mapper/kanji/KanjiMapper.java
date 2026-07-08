package org.japan.mapper.kanji;

import org.japan.dto.response.kanji.KanjiResponse;
import org.japan.entity.kanji.KanjiEntity;
import org.japan.entity.kanji.KanjiMeaningEntity;
import org.japan.entity.kanji.KunyomiEntity;
import org.japan.entity.kanji.OnyomiEntity;
import org.japan.entry.KanjiDicEntry;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {SinoVietnameseMapper.class}
)
public interface KanjiMapper {
    // mapper method
    @Named("idAndCharacter")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "kanjiCharacter", target = "kanjiCharacter")
    @Mapping(source = "id", target = "id")
    KanjiResponse mapEntityToIdAndKanjiCharacter(KanjiEntity kanjiEntity);

    @Named("details")
    @Mapping(target = "mainSinoVietnamese", source = "mainSinoVietnamese.readingText")
    @Mapping(target = "sinoVietnameseList", source = "sinoVietnameseList")
    @Mapping(target = "onyomiList", source = "onyomiList")
    @Mapping(target = "kunyomiList", source = "kunyomiList")
    @Mapping(target = "kanjiMeaningList", source = "kanjiMeaningList")
    KanjiResponse mapEntityToResponseDetails(KanjiEntity kanjiEntity);

    @Named("summary")
    @Mapping(target = "mainSinoVietnamese", source = "mainSinoVietnamese.readingText")
    @Mapping(target = "sinoVietnameseList", ignore = true)
    @Mapping(target = "onyomiList", ignore = true)
    @Mapping(target = "kunyomiList", ignore = true)
    @Mapping(target = "kanjiMeaningList", ignore = true)
    KanjiResponse mapEntityToResponseSummary(KanjiEntity kanjiEntity);

    @Mapping(target = "mainSinoVietnamese", ignore = true)
    @Mapping(target = "sinoVietnameseList", ignore = true)
    @Mapping(target = "onyomiList", ignore = true)
    @Mapping(target = "kunyomiList", ignore = true)
    @Mapping(target = "kanjiMeaningList", ignore = true)
    @Mapping(target = "kanjiPages", ignore = true)
    KanjiEntity mapEntryToEntity(KanjiDicEntry entry);

    // default method
    default List<String> mapOnyomiListToReadingTextList(List<OnyomiEntity> onyomiList) {
        if (onyomiList == null) return List.of();
        return onyomiList
                .stream()
                .map(OnyomiEntity::getReadingText)
                .toList();
    }

    default List<String> mapKunyomiListToReadingTextList(List<KunyomiEntity> kunyomiList) {
        if (kunyomiList == null) return List.of();
        return kunyomiList
                .stream()
                .map(KunyomiEntity::getReadingText)
                .toList();
    }

    default List<String> mapKanjiMeaningListToReadingTextList(List<KanjiMeaningEntity> kanjiMeaningList) {
        if (kanjiMeaningList == null) return List.of();
        return kanjiMeaningList
                .stream()
                .map(KanjiMeaningEntity::getReadingText)
                .toList();
    }
}
