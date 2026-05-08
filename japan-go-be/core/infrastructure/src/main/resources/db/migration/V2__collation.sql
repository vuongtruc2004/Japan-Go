ALTER TABLE kanji
    MODIFY kanji_character VARCHAR(20)
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_0900_bin;
ALTER TABLE onyomi
    MODIFY reading_text VARCHAR(255)
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_0900_bin;
ALTER TABLE kunyomi
    MODIFY reading_text VARCHAR(255)
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_0900_bin;
ALTER TABLE kanji_meaning
    MODIFY reading_text VARCHAR(255)
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_0900_bin;
ALTER TABLE sino_vietnamese
    MODIFY reading_text VARCHAR(255)
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_0900_bin;
ALTER TABLE sino_vietnamese_meaning
    MODIFY reading_text VARCHAR(255)
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_0900_bin;