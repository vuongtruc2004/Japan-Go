CREATE TABLE cards
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    front         MEDIUMTEXT            NOT NULL,
    grammar_id    BIGINT                NULL,
    position      INT                   NOT NULL,
    deck_id       BIGINT                NULL,
    CONSTRAINT pk_cards PRIMARY KEY (id)
);

CREATE TABLE decks
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    title         VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_decks PRIMARY KEY (id)
);

CREATE TABLE folder_lesson
(
    folder_id BIGINT NOT NULL,
    lesson_id BIGINT NOT NULL
);

CREATE TABLE folders
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_by           VARCHAR(255)          NOT NULL,
    created_time         datetime              NOT NULL,
    modified_by          VARCHAR(255)          NULL,
    modified_time        datetime              NULL,
    folder_name          VARCHAR(255)          NOT NULL,
    parent_folder_id     BIGINT                NULL,
    is_pinned_to_sidebar BIT(1)                NULL,
    CONSTRAINT pk_folders PRIMARY KEY (id)
);

CREATE TABLE grammar_examples
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_examples PRIMARY KEY (id)
);

CREATE TABLE grammar_lessons
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_lessons PRIMARY KEY (id)
);

CREATE TABLE grammar_meanings
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_meanings PRIMARY KEY (id)
);

CREATE TABLE grammar_notes
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_notes PRIMARY KEY (id)
);

CREATE TABLE grammar_structures
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_grammar_structures PRIMARY KEY (id)
);

CREATE TABLE grammars
(
    id                     BIGINT AUTO_INCREMENT NOT NULL,
    created_by             VARCHAR(255)          NOT NULL,
    created_time           datetime              NOT NULL,
    modified_by            VARCHAR(255)          NULL,
    modified_time          datetime              NULL,
    grammar_title          VARCHAR(255)          NOT NULL,
    grammar_title_furigana VARCHAR(255)          NULL,
    grammar_title_romaji   VARCHAR(255)          NULL,
    translation            VARCHAR(255)          NULL,
    jlpt_level             INT                   NULL,
    grammar_meaning_id     BIGINT                NULL,
    grammar_structure_id   BIGINT                NULL,
    grammar_example_id     BIGINT                NULL,
    grammar_note_id        BIGINT                NULL,
    grammar_lesson_id      BIGINT                NULL,
    CONSTRAINT pk_grammars PRIMARY KEY (id)
);

CREATE TABLE images
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    img_path      VARCHAR(255)          NULL,
    img_alt       VARCHAR(255)          NULL,
    CONSTRAINT pk_images PRIMARY KEY (id)
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

CREATE TABLE kanji_lessons
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    CONSTRAINT pk_kanji_lessons PRIMARY KEY (id)
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

CREATE TABLE kanji_pages
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_by      VARCHAR(255)          NOT NULL,
    created_time    datetime              NOT NULL,
    modified_by     VARCHAR(255)          NULL,
    modified_time   datetime              NULL,
    main_kanji_id   BIGINT                NULL,
    kanji_lesson_id BIGINT                NULL,
    CONSTRAINT pk_kanji_pages PRIMARY KEY (id)
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

CREATE TABLE lessons
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
    CONSTRAINT pk_lessons PRIMARY KEY (id)
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

CREATE TABLE permissions
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    api_path      VARCHAR(255)          NULL,
    api_method    VARCHAR(255)          NULL,
    CONSTRAINT pk_permissions PRIMARY KEY (id)
);

CREATE TABLE role_permission
(
    permission_id BIGINT NOT NULL,
    role_id       BIGINT NOT NULL
);

CREATE TABLE roles
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    role_name     VARCHAR(255)          NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE sentences
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_by           VARCHAR(255)          NOT NULL,
    created_time         datetime              NOT NULL,
    modified_by          VARCHAR(255)          NULL,
    modified_time        datetime              NULL,
    japanese_raw         MEDIUMTEXT            NULL,
    vietnamese_raw       MEDIUMTEXT            NULL,
    english_raw          MEDIUMTEXT            NULL,
    japaneses_html       MEDIUMTEXT            NULL,
    vietnamese_html      MEDIUMTEXT            NULL,
    english_html         MEDIUMTEXT            NULL,
    grammar_meaning_id   BIGINT                NULL,
    grammar_structure_id BIGINT                NULL,
    grammar_example_id   BIGINT                NULL,
    grammar_note_id      BIGINT                NULL,
    CONSTRAINT pk_sentences PRIMARY KEY (id)
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

CREATE TABLE study_session_cards
(
    id                        BIGINT AUTO_INCREMENT NOT NULL,
    created_by                VARCHAR(255)          NOT NULL,
    created_time              datetime              NOT NULL,
    modified_by               VARCHAR(255)          NULL,
    modified_time             datetime              NULL,
    study_session_id          BIGINT                NULL,
    card_id                   BIGINT                NULL,
    order_index               INT                   NULL,
    study_session_card_status VARCHAR(255)          NULL,
    CONSTRAINT pk_study_session_cards PRIMARY KEY (id)
);

CREATE TABLE study_sessions
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_by           VARCHAR(255)          NOT NULL,
    created_time         datetime              NOT NULL,
    modified_by          VARCHAR(255)          NULL,
    modified_time        datetime              NULL,
    deck_id              BIGINT                NULL,
    study_session_status VARCHAR(255)          NULL,
    is_shuffled          BIT(1)                NULL,
    current_round        INT                   NULL,
    current_index        INT                   NULL,
    started_time         datetime              NULL,
    completed_time       datetime              NULL,
    CONSTRAINT pk_study_sessions PRIMARY KEY (id)
);

CREATE TABLE teachers
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255)          NOT NULL,
    created_time  datetime              NOT NULL,
    modified_by   VARCHAR(255)          NULL,
    modified_time datetime              NULL,
    user_id       BIGINT                NULL,
    CONSTRAINT pk_teachers PRIMARY KEY (id)
);

CREATE TABLE users
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
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE vocabularies
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
    CONSTRAINT pk_vocabularies PRIMARY KEY (id)
);

ALTER TABLE grammars
    ADD CONSTRAINT uc_grammars_grammar_example UNIQUE (grammar_example_id);

ALTER TABLE grammars
    ADD CONSTRAINT uc_grammars_grammar_meaning UNIQUE (grammar_meaning_id);

ALTER TABLE grammars
    ADD CONSTRAINT uc_grammars_grammar_note UNIQUE (grammar_note_id);

ALTER TABLE grammars
    ADD CONSTRAINT uc_grammars_grammar_structure UNIQUE (grammar_structure_id);

ALTER TABLE kanji
    ADD CONSTRAINT uc_kanji_kanji_character UNIQUE (kanji_character);

ALTER TABLE kanji_meaning
    ADD CONSTRAINT uc_kanji_meaning_reading_text UNIQUE (reading_text);

ALTER TABLE kanji
    ADD CONSTRAINT uc_kanji_unicode UNIQUE (unicode);

ALTER TABLE kunyomi
    ADD CONSTRAINT uc_kunyomi_reading_text UNIQUE (reading_text);

ALTER TABLE lessons
    ADD CONSTRAINT uc_lessons_grammar_lesson UNIQUE (grammar_lesson_id);

ALTER TABLE lessons
    ADD CONSTRAINT uc_lessons_kanji_lesson UNIQUE (kanji_lesson_id);

ALTER TABLE onyomi
    ADD CONSTRAINT uc_onyomi_reading_text UNIQUE (reading_text);

ALTER TABLE teachers
    ADD CONSTRAINT uc_teachers_user UNIQUE (user_id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_avatar_image UNIQUE (avatar_image_id);

ALTER TABLE cards
    ADD CONSTRAINT FK_CARDS_ON_DECK FOREIGN KEY (deck_id) REFERENCES decks (id);

ALTER TABLE cards
    ADD CONSTRAINT FK_CARDS_ON_GRAMMAR FOREIGN KEY (grammar_id) REFERENCES grammars (id);

ALTER TABLE folders
    ADD CONSTRAINT FK_FOLDERS_ON_PARENT_FOLDER FOREIGN KEY (parent_folder_id) REFERENCES folders (id);

ALTER TABLE grammars
    ADD CONSTRAINT FK_GRAMMARS_ON_GRAMMAR_EXAMPLE FOREIGN KEY (grammar_example_id) REFERENCES grammar_examples (id);

ALTER TABLE grammars
    ADD CONSTRAINT FK_GRAMMARS_ON_GRAMMAR_LESSON FOREIGN KEY (grammar_lesson_id) REFERENCES grammar_lessons (id);

ALTER TABLE grammars
    ADD CONSTRAINT FK_GRAMMARS_ON_GRAMMAR_MEANING FOREIGN KEY (grammar_meaning_id) REFERENCES grammar_meanings (id);

ALTER TABLE grammars
    ADD CONSTRAINT FK_GRAMMARS_ON_GRAMMAR_NOTE FOREIGN KEY (grammar_note_id) REFERENCES grammar_notes (id);

ALTER TABLE grammars
    ADD CONSTRAINT FK_GRAMMARS_ON_GRAMMAR_STRUCTURE FOREIGN KEY (grammar_structure_id) REFERENCES grammar_structures (id);

ALTER TABLE kanji
    ADD CONSTRAINT FK_KANJI_ON_MAIN_SINO_VIETNAMESE FOREIGN KEY (main_sino_vietnamese_id) REFERENCES sino_vietnamese (id);

ALTER TABLE kanji_pages
    ADD CONSTRAINT FK_KANJI_PAGES_ON_KANJI_LESSON FOREIGN KEY (kanji_lesson_id) REFERENCES kanji_lessons (id);

ALTER TABLE kanji_pages
    ADD CONSTRAINT FK_KANJI_PAGES_ON_MAIN_KANJI FOREIGN KEY (main_kanji_id) REFERENCES kanji (id);

ALTER TABLE lessons
    ADD CONSTRAINT FK_LESSONS_ON_GRAMMAR_LESSON FOREIGN KEY (grammar_lesson_id) REFERENCES grammar_lessons (id);

ALTER TABLE lessons
    ADD CONSTRAINT FK_LESSONS_ON_KANJI_LESSON FOREIGN KEY (kanji_lesson_id) REFERENCES kanji_lessons (id);

ALTER TABLE sentences
    ADD CONSTRAINT FK_SENTENCES_ON_GRAMMAR_EXAMPLE FOREIGN KEY (grammar_example_id) REFERENCES grammar_examples (id);

ALTER TABLE sentences
    ADD CONSTRAINT FK_SENTENCES_ON_GRAMMAR_MEANING FOREIGN KEY (grammar_meaning_id) REFERENCES grammar_meanings (id);

ALTER TABLE sentences
    ADD CONSTRAINT FK_SENTENCES_ON_GRAMMAR_NOTE FOREIGN KEY (grammar_note_id) REFERENCES grammar_notes (id);

ALTER TABLE sentences
    ADD CONSTRAINT FK_SENTENCES_ON_GRAMMAR_STRUCTURE FOREIGN KEY (grammar_structure_id) REFERENCES grammar_structures (id);

ALTER TABLE sino_vietnamese_meaning
    ADD CONSTRAINT FK_SINO_VIETNAMESE_MEANING_ON_SINO_VIETNAMESE FOREIGN KEY (sino_vietnamese_id) REFERENCES sino_vietnamese (id);

ALTER TABLE sino_vietnamese
    ADD CONSTRAINT FK_SINO_VIETNAMESE_ON_KANJI FOREIGN KEY (kanji_id) REFERENCES kanji (id);

ALTER TABLE study_sessions
    ADD CONSTRAINT FK_STUDY_SESSIONS_ON_DECK FOREIGN KEY (deck_id) REFERENCES decks (id);

ALTER TABLE study_session_cards
    ADD CONSTRAINT FK_STUDY_SESSION_CARDS_ON_CARD FOREIGN KEY (card_id) REFERENCES cards (id);

ALTER TABLE study_session_cards
    ADD CONSTRAINT FK_STUDY_SESSION_CARDS_ON_STUDY_SESSION FOREIGN KEY (study_session_id) REFERENCES study_sessions (id);

ALTER TABLE teachers
    ADD CONSTRAINT FK_TEACHERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_AVATAR_IMAGE FOREIGN KEY (avatar_image_id) REFERENCES images (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE vocabularies
    ADD CONSTRAINT FK_VOCABULARIES_ON_KANJI_PAGE FOREIGN KEY (kanji_page_id) REFERENCES kanji_pages (id);

ALTER TABLE folder_lesson
    ADD CONSTRAINT fk_folles_on_folder_entity FOREIGN KEY (folder_id) REFERENCES folders (id);

ALTER TABLE folder_lesson
    ADD CONSTRAINT fk_folles_on_lesson_entity FOREIGN KEY (lesson_id) REFERENCES lessons (id);

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
    ADD CONSTRAINT fk_rolper_on_permission_entity FOREIGN KEY (permission_id) REFERENCES permissions (id);

ALTER TABLE role_permission
    ADD CONSTRAINT fk_rolper_on_role_entity FOREIGN KEY (role_id) REFERENCES roles (id);