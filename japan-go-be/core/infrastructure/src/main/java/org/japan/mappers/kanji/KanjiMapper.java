package org.japan.mappers.kanji;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.japan.entity.kanji.KanjiEntity;

import java.util.List;
import java.util.Set;

@Mapper
public interface KanjiMapper {
    List<KanjiEntity> findAllKanjiWithSinoVietnameseByKanjiCharacterIn(@Param("kanjiCharacters") Set<String> kanjiCharacters);
}
