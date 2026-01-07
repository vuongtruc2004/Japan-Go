package com.nass.infrastructure.entities.base;

import com.nass.contract.enums.VocabularyTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseVocabularyEntity<TKey> extends BaseEntity<TKey> {
    String kanji;
    String sinoVietnamese;
    String furigana;
    String vietnamese;
    String note;

    @Enumerated(EnumType.STRING)
    VocabularyTypeEnum vocabularyType;
}
