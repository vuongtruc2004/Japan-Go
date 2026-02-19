CREATE TABLE folder
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_by       VARCHAR(255)          NOT NULL,
    created_time     datetime              NOT NULL,
    modified_by      VARCHAR(255)          NULL,
    modified_time    datetime              NULL,
    folder_name      VARCHAR(255)          NOT NULL,
    parent_folder_id BIGINT                NULL,
    CONSTRAINT pk_folder PRIMARY KEY (id)
);

CREATE TABLE folder_lesson
(
    folder_id BIGINT NOT NULL,
    lesson_id BIGINT NOT NULL
);

CREATE TABLE grammar
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_by           VARCHAR(255)          NOT NULL,
    created_time         datetime              NOT NULL,
    modified_by          VARCHAR(255)          NULL,
    modified_time        datetime              NULL,
    grammar_title        VARCHAR(255)          NULL,
    grammar_meaning_id   BIGINT                NULL,
    grammar_structure_id BIGINT                NULL,
    grammar_example_id   BIGINT                NULL,
    grammar_note_id      BIGINT                NULL,
    grammar_lesson_id    BIGINT                NULL,
    CONSTRAINT pk_grammar PRIMARY KEY (id)
);

CREATE TABLE grammar_example
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_example PRIMARY KEY (id)
);

CREATE TABLE grammar_lesson
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_by           VARCHAR(255)          NOT NULL,
    created_time         datetime              NOT NULL,
    modified_by          VARCHAR(255)          NULL,
    modified_time        datetime              NULL,
    grammar_lesson_title VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_grammar_lesson PRIMARY KEY (id)
);

CREATE TABLE grammar_meaning
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_meaning PRIMARY KEY (id)
);

CREATE TABLE grammar_note
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_note PRIMARY KEY (id)
);

CREATE TABLE grammar_structure
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_structure PRIMARY KEY (id)
);

CREATE TABLE image
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    img_path      VARCHAR(255)          NULL,
    img_alt       VARCHAR(255)          NULL,
    CONSTRAINT pk_image PRIMARY KEY (id)
);

CREATE TABLE kanji
(
    id                      BIGINT AUTO_INCREMENT NOT NULL,
    created_by              VARCHAR(255)          NOT NULL,
    created_time            datetime              NOT NULL,
    modified_by             VARCHAR(255)          NULL,
    modified_time           datetime              NULL,
    kanji_character         VARCHAR(255)          NOT NULL,
    unicode                 VARCHAR(255)          NOT NULL,
    kanji_vg                LONGTEXT              NULL,
    grade                   INT                   NULL,
    stroke_count            INT                   NULL,
    frequency               INT                   NULL,
    jlpt_level              INT                   NULL,
    main_sino_vietnamese_id BIGINT                NULL,
    CONSTRAINT pk_kanji PRIMARY KEY (id)
);

CREATE TABLE kanji_kanji_meaning
(
    kanji_id         BIGINT NOT NULL,
    kanji_meaning_id BIGINT NOT NULL
);

CREATE TABLE kanji_kunyomi
(
    kanji_id   BIGINT NOT NULL,
    kunyomi_id BIGINT NOT NULL
);

CREATE TABLE kanji_lesson
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_kanji_lesson PRIMARY KEY (id)
);

CREATE TABLE kanji_meaning
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    reading_text  VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_kanji_meaning PRIMARY KEY (id)
);

CREATE TABLE kanji_onyomi
(
    kanji_id  BIGINT NOT NULL,
    onyomi_id BIGINT NOT NULL
);

CREATE TABLE kanji_page
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_by      VARCHAR(255)          NOT NULL,
    created_time    datetime              NOT NULL,
    modified_by     VARCHAR(255)          NULL,
    modified_time   datetime              NULL,
    main_kanji_id   BIGINT                NULL,
    kanji_lesson_id BIGINT                NULL,
    CONSTRAINT pk_kanji_page PRIMARY KEY (id)
);

CREATE TABLE kunyomi
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    reading_text  VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_kunyomi PRIMARY KEY (id)
);

CREATE TABLE lesson
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_by        VARCHAR(255)          NOT NULL,
    created_time      datetime              NOT NULL,
    modified_by       VARCHAR(255)          NULL,
    modified_time     datetime              NULL,
    lesson_name       VARCHAR(255)          NOT NULL,
    `description`     VARCHAR(255)          NULL,
    lesson_type       VARCHAR(255)          NULL,
    grammar_lesson_id BIGINT                NULL,
    kanji_lesson_id   BIGINT                NULL,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE onyomi
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    reading_text  VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_onyomi PRIMARY KEY (id)
);

CREATE TABLE permission
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    api_path      VARCHAR(255)          NULL,
    api_method    VARCHAR(255)          NULL,
    CONSTRAINT pk_permission PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    role_name     VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE role_permission
(
    permission_id BIGINT NOT NULL,
    role_id       BIGINT NOT NULL
);

CREATE TABLE sentence
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_by           VARCHAR(255)          NOT NULL,
    created_time         datetime              NOT NULL,
    modified_by          VARCHAR(255)          NULL,
    modified_time        datetime              NULL,
    japanese_raw         VARCHAR(255)          NULL,
    vietnamese_raw       VARCHAR(255)          NULL,
    english_raw          VARCHAR(255)          NULL,
    japaneses_html       VARCHAR(255)          NULL,
    vietnamese_html      VARCHAR(255)          NULL,
    english_html         VARCHAR(255)          NULL,
    grammar_meaning_id   BIGINT                NULL,
    grammar_structure_id BIGINT                NULL,
    grammar_example_id   BIGINT                NULL,
    grammar_note_id      BIGINT                NULL,
    CONSTRAINT pk_sentence PRIMARY KEY (id)
);

CREATE TABLE sino_vietnamese
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    reading_text  VARCHAR(255)          NOT NULL,
    kanji_id      BIGINT                NULL,
    CONSTRAINT pk_sino_vietnamese PRIMARY KEY (id)
);

CREATE TABLE sino_vietnamese_meaning
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_by         VARCHAR(255)          NOT NULL,
    created_time       datetime              NOT NULL,
    modified_by        VARCHAR(255)          NULL,
    modified_time      datetime              NULL,
    reading_text       VARCHAR(255)          NOT NULL,
    sino_vietnamese_id BIGINT                NULL,
    CONSTRAINT pk_sino_vietnamese_meaning PRIMARY KEY (id)
);

CREATE TABLE teacher
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    user_id       BIGINT                NULL,
    CONSTRAINT pk_teacher PRIMARY KEY (id)
);

CREATE TABLE user
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_by      VARCHAR(255)          NOT NULL,
    created_time    datetime              NOT NULL,
    modified_by     VARCHAR(255)          NULL,
    modified_time   datetime              NULL,
    email           VARCHAR(255)          NULL,
    password        VARCHAR(255)          NULL,
    refresh_token   VARCHAR(255)          NULL,
    first_name      VARCHAR(255)          NULL,
    last_name       VARCHAR(255)          NULL,
    account_type    VARCHAR(255)          NULL,
    avatar_image_id BIGINT                NULL,
    role_id         BIGINT                NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE vocabulary
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    japanese      VARCHAR(255)          NOT NULL,
    reading       VARCHAR(255)          NOT NULL,
    meaning       VARCHAR(255)          NULL,
    note          VARCHAR(255)          NULL,
    kanji_page_id BIGINT                NULL,
    CONSTRAINT pk_vocabulary PRIMARY KEY (id)
);

ALTER TABLE grammar
    ADD CONSTRAINT uc_grammar_grammar_example UNIQUE (grammar_example_id);

ALTER TABLE grammar
    ADD CONSTRAINT uc_grammar_grammar_meaning UNIQUE (grammar_meaning_id);

ALTER TABLE grammar
    ADD CONSTRAINT uc_grammar_grammar_note UNIQUE (grammar_note_id);

ALTER TABLE grammar
    ADD CONSTRAINT uc_grammar_grammar_structure UNIQUE (grammar_structure_id);

ALTER TABLE kanji
    ADD CONSTRAINT uc_kanji_kanji_character UNIQUE (kanji_character);

ALTER TABLE kanji_meaning
    ADD CONSTRAINT uc_kanji_meaning_reading_text UNIQUE (reading_text);

ALTER TABLE kanji
    ADD CONSTRAINT uc_kanji_unicode UNIQUE (unicode);

ALTER TABLE kunyomi
    ADD CONSTRAINT uc_kunyomi_reading_text UNIQUE (reading_text);

ALTER TABLE lesson
    ADD CONSTRAINT uc_lesson_grammar_lesson UNIQUE (grammar_lesson_id);

ALTER TABLE lesson
    ADD CONSTRAINT uc_lesson_kanji_lesson UNIQUE (kanji_lesson_id);

ALTER TABLE onyomi
    ADD CONSTRAINT uc_onyomi_reading_text UNIQUE (reading_text);

ALTER TABLE teacher
    ADD CONSTRAINT uc_teacher_user UNIQUE (user_id);

ALTER TABLE user
    ADD CONSTRAINT uc_user_avatar_image UNIQUE (avatar_image_id);

ALTER TABLE folder
    ADD CONSTRAINT FK_FOLDER_ON_PARENT_FOLDER FOREIGN KEY (parent_folder_id) REFERENCES folder (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_GRAMMAR_EXAMPLE FOREIGN KEY (grammar_example_id) REFERENCES grammar_example (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_GRAMMAR_LESSON FOREIGN KEY (grammar_lesson_id) REFERENCES grammar_lesson (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_GRAMMAR_MEANING FOREIGN KEY (grammar_meaning_id) REFERENCES grammar_meaning (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_GRAMMAR_NOTE FOREIGN KEY (grammar_note_id) REFERENCES grammar_note (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_GRAMMAR_STRUCTURE FOREIGN KEY (grammar_structure_id) REFERENCES grammar_structure (id);

ALTER TABLE kanji
    ADD CONSTRAINT FK_KANJI_ON_MAIN_SINO_VIETNAMESE FOREIGN KEY (main_sino_vietnamese_id) REFERENCES sino_vietnamese (id);

ALTER TABLE kanji_page
    ADD CONSTRAINT FK_KANJI_PAGE_ON_KANJI_LESSON FOREIGN KEY (kanji_lesson_id) REFERENCES kanji_lesson (id);

ALTER TABLE kanji_page
    ADD CONSTRAINT FK_KANJI_PAGE_ON_MAIN_KANJI FOREIGN KEY (main_kanji_id) REFERENCES kanji (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_GRAMMAR_LESSON FOREIGN KEY (grammar_lesson_id) REFERENCES grammar_lesson (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_KANJI_LESSON FOREIGN KEY (kanji_lesson_id) REFERENCES kanji_lesson (id);

ALTER TABLE sentence
    ADD CONSTRAINT FK_SENTENCE_ON_GRAMMAR_EXAMPLE FOREIGN KEY (grammar_example_id) REFERENCES grammar_example (id);

ALTER TABLE sentence
    ADD CONSTRAINT FK_SENTENCE_ON_GRAMMAR_MEANING FOREIGN KEY (grammar_meaning_id) REFERENCES grammar_meaning (id);

ALTER TABLE sentence
    ADD CONSTRAINT FK_SENTENCE_ON_GRAMMAR_NOTE FOREIGN KEY (grammar_note_id) REFERENCES grammar_note (id);

ALTER TABLE sentence
    ADD CONSTRAINT FK_SENTENCE_ON_GRAMMAR_STRUCTURE FOREIGN KEY (grammar_structure_id) REFERENCES grammar_structure (id);

ALTER TABLE sino_vietnamese_meaning
    ADD CONSTRAINT FK_SINO_VIETNAMESE_MEANING_ON_SINO_VIETNAMESE FOREIGN KEY (sino_vietnamese_id) REFERENCES sino_vietnamese (id);

ALTER TABLE sino_vietnamese
    ADD CONSTRAINT FK_SINO_VIETNAMESE_ON_KANJI FOREIGN KEY (kanji_id) REFERENCES kanji (id);

ALTER TABLE teacher
    ADD CONSTRAINT FK_TEACHER_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_AVATAR_IMAGE FOREIGN KEY (avatar_image_id) REFERENCES image (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ROLE FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE vocabulary
    ADD CONSTRAINT FK_VOCABULARY_ON_KANJI_PAGE FOREIGN KEY (kanji_page_id) REFERENCES kanji_page (id);

ALTER TABLE folder_lesson
    ADD CONSTRAINT fk_folles_on_folder_entity FOREIGN KEY (folder_id) REFERENCES folder (id);

ALTER TABLE folder_lesson
    ADD CONSTRAINT fk_folles_on_lesson_entity FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE kanji_kanji_meaning
    ADD CONSTRAINT fk_kankanmea_on_kanji_entity FOREIGN KEY (kanji_id) REFERENCES kanji (id);

ALTER TABLE kanji_kanji_meaning
    ADD CONSTRAINT fk_kankanmea_on_kanji_meaning_entity FOREIGN KEY (kanji_meaning_id) REFERENCES kanji_meaning (id);

ALTER TABLE kanji_kunyomi
    ADD CONSTRAINT fk_kankun_on_kanji_entity FOREIGN KEY (kanji_id) REFERENCES kanji (id);

ALTER TABLE kanji_kunyomi
    ADD CONSTRAINT fk_kankun_on_kunyomi_entity FOREIGN KEY (kunyomi_id) REFERENCES kunyomi (id);

ALTER TABLE kanji_onyomi
    ADD CONSTRAINT fk_kanony_on_kanji_entity FOREIGN KEY (kanji_id) REFERENCES kanji (id);

ALTER TABLE kanji_onyomi
    ADD CONSTRAINT fk_kanony_on_onyomi_entity FOREIGN KEY (onyomi_id) REFERENCES onyomi (id);

ALTER TABLE role_permission
    ADD CONSTRAINT fk_rolper_on_permission_entity FOREIGN KEY (permission_id) REFERENCES permission (id);

ALTER TABLE role_permission
    ADD CONSTRAINT fk_rolper_on_role_entity FOREIGN KEY (role_id) REFERENCES `role` (id);