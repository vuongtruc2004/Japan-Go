package com.japan_go_be.infrastructure.mappers.kanji;

import com.japan_go_be.infrastructure.entities.kanji.KanjiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface KanjiMapper {
    List<KanjiEntity> findAllKanjiWithSinoVietnameseByKanjiCharacterIn(@Param("kanjiCharacters") Set<String> kanjiCharacters);
}
