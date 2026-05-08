package org.japan.persistence.mybatis.kanji;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.japan.entity.kanji.KanjiEntity;
import org.japan.persistence.mybatis.kanji.param.UpdateMainSinoVietnameseParam;

import java.util.List;
import java.util.Set;

@Mapper
public interface KanjiMapper {
    List<KanjiEntity> findAllKanjiWithSinoVietnameseByKanjiCharacterIn(
            @Param("kanjiCharacters") Set<String> kanjiCharacters
    );

    int updateMainSinoVietnamese(@Param("items") List<UpdateMainSinoVietnameseParam> items);
}
