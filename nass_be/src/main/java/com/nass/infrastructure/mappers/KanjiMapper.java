package com.nass.infrastructure.mappers;

import com.nass.infrastructure.entities.kanji.KanjiEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KanjiMapper {
    KanjiEntity findKanjiById(Integer id);
}
