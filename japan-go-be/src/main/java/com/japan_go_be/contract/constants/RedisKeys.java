package com.japan_go_be.contract.constants;

public final class RedisKeys {
    private static final String KANJI_JLPT_LEVEL_KEY = "japan-go:kanji:jlpt-level:%s";

    private RedisKeys() {
    }

    public static String getKanjiJlptLevelKey(Integer jlptLevel) {
        return KANJI_JLPT_LEVEL_KEY.formatted(jlptLevel);
    }
}
