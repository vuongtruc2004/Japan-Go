CREATE TABLE image
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    img_path      VARCHAR(255) NULL,
    img_alt       VARCHAR(255) NULL,
    CONSTRAINT pk_image PRIMARY KEY (id)
);

CREATE TABLE kanji
(
    id                      INT          NOT NULL AUTO_INCREMENT,
    created_by              VARCHAR(255) NOT NULL,
    created_time            datetime     NOT NULL,
    modified_by             VARCHAR(255) NULL,
    modified_time           datetime     NULL,
    kanji_character         VARCHAR(255) NOT NULL,
    unicode                 VARCHAR(255) NOT NULL,
    grade                   INT          NULL,
    stroke_count            INT          NULL,
    frequency               INT          NULL,
    jlpt_level              INT          NULL,
    main_sino_vietnamese_id BIGINT       NULL,
    CONSTRAINT pk_kanji PRIMARY KEY (id)
);

CREATE TABLE kanji_kanji_meaning
(
    kanji_id         INT    NOT NULL,
    kanji_meaning_id BIGINT NOT NULL
);

CREATE TABLE kanji_kunyomi
(
    kanji_id   INT    NOT NULL,
    kunyomi_id BIGINT NOT NULL
);

CREATE TABLE kanji_onyomi
(
    kanji_id  INT    NOT NULL,
    onyomi_id BIGINT NOT NULL
);

CREATE TABLE kunyomi
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    reading_text  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_kunyomi PRIMARY KEY (id)
);

CREATE TABLE meaning
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    reading_text  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_meaning PRIMARY KEY (id)
);

CREATE TABLE onyomi
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    reading_text  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_onyomi PRIMARY KEY (id)
);

CREATE TABLE permission
(
    id            INT          NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    api_path      VARCHAR(255) NULL,
    api_method    VARCHAR(255) NULL,
    CONSTRAINT pk_permission PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id            INT          NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    role_name     VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE role_permission
(
    permission_id INT NOT NULL,
    role_id       INT NOT NULL
);

CREATE TABLE sino_vietnamese
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    reading_text  VARCHAR(255) NOT NULL,
    kanji_id      INT          NULL,
    CONSTRAINT pk_sino_vietnamese PRIMARY KEY (id)
);

CREATE TABLE sino_vietnamese_meaning
(
    id                 BIGINT       NOT NULL AUTO_INCREMENT,
    created_by         VARCHAR(255) NOT NULL,
    created_time       datetime     NOT NULL,
    modified_by        VARCHAR(255) NULL,
    modified_time      datetime     NULL,
    reading_text       VARCHAR(255) NOT NULL,
    sino_vietnamese_id BIGINT       NULL,
    CONSTRAINT pk_sino_vietnamese_meaning PRIMARY KEY (id)
);

CREATE TABLE teacher
(
    id            INT          NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    user_id       INT          NULL,
    CONSTRAINT pk_teacher PRIMARY KEY (id)
);

CREATE TABLE user
(
    id              INT          NOT NULL AUTO_INCREMENT,
    created_by      VARCHAR(255) NOT NULL,
    created_time    datetime     NOT NULL,
    modified_by     VARCHAR(255) NULL,
    modified_time   datetime     NULL,
    email           VARCHAR(255) NULL,
    password        VARCHAR(255) NULL,
    refresh_token   VARCHAR(255) NULL,
    first_name      VARCHAR(255) NULL,
    last_name       VARCHAR(255) NULL,
    account_type    VARCHAR(255) NULL,
    avatar_image_id BIGINT       NULL,
    role_id         INT          NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE kanji
    ADD CONSTRAINT uc_kanji_kanji_character UNIQUE (kanji_character);

ALTER TABLE kanji
    ADD CONSTRAINT uc_kanji_unicode UNIQUE (unicode);

ALTER TABLE kunyomi
    ADD CONSTRAINT uc_kunyomi_reading_text UNIQUE (reading_text);

ALTER TABLE meaning
    ADD CONSTRAINT uc_meaning_reading_text UNIQUE (reading_text);

ALTER TABLE onyomi
    ADD CONSTRAINT uc_onyomi_reading_text UNIQUE (reading_text);

ALTER TABLE teacher
    ADD CONSTRAINT uc_teacher_user UNIQUE (user_id);

ALTER TABLE user
    ADD CONSTRAINT uc_user_avatar_image UNIQUE (avatar_image_id);

ALTER TABLE kanji
    ADD CONSTRAINT FK_KANJI_ON_MAIN_SINO_VIETNAMESE FOREIGN KEY (main_sino_vietnamese_id) REFERENCES sino_vietnamese (id);

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

ALTER TABLE kanji_kanji_meaning
    ADD CONSTRAINT fk_kankanmea_on_kanji_entity FOREIGN KEY (kanji_id) REFERENCES kanji (id);

ALTER TABLE kanji_kanji_meaning
    ADD CONSTRAINT fk_kankanmea_on_kanji_meaning_entity FOREIGN KEY (kanji_meaning_id) REFERENCES meaning (id);

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