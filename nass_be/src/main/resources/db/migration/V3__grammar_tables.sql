CREATE TABLE additional_note
(
    id            INT          NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    CONSTRAINT pk_additional_note PRIMARY KEY (id)
);

CREATE TABLE example
(
    id            INT          NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    CONSTRAINT pk_example PRIMARY KEY (id)
);

CREATE TABLE grammar
(
    id                 INT          NOT NULL AUTO_INCREMENT,
    created_by         VARCHAR(255) NOT NULL,
    created_time       datetime     NOT NULL,
    modified_by        VARCHAR(255) NULL,
    modified_time      datetime     NULL,
    grammar_title      VARCHAR(255) NULL,
    lesson_id          INT          NULL,
    grammar_meaning_id INT          NULL,
    structure_id       INT          NULL,
    example_id         INT          NULL,
    additional_note_id INT          NULL,
    CONSTRAINT pk_grammar PRIMARY KEY (id)
);

CREATE TABLE grammar_meaning
(
    id                     INT          NOT NULL AUTO_INCREMENT,
    created_by             VARCHAR(255) NOT NULL,
    created_time           datetime     NOT NULL,
    modified_by            VARCHAR(255) NULL,
    modified_time          datetime     NULL,
    vietnamese_translation VARCHAR(255) NULL,
    CONSTRAINT pk_grammar_meaning PRIMARY KEY (id)
);

CREATE TABLE lesson
(
    id            INT          NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    lesson_title  VARCHAR(255) NULL,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE sentence
(
    id                 BIGINT       NOT NULL AUTO_INCREMENT,
    created_by         VARCHAR(255) NOT NULL,
    created_time       datetime     NOT NULL,
    modified_by        VARCHAR(255) NULL,
    modified_time      datetime     NULL,
    vietnamese         VARCHAR(255) NULL,
    japanese           VARCHAR(255) NULL,
    english            VARCHAR(255) NULL,
    grammar_meaning_id INT          NULL,
    structure_id       INT          NULL,
    example_id         INT          NULL,
    additional_note_id INT          NULL,
    CONSTRAINT pk_sentence PRIMARY KEY (id)
);

CREATE TABLE structure
(
    id            INT          NOT NULL AUTO_INCREMENT,
    created_by    VARCHAR(255) NOT NULL,
    created_time  datetime     NOT NULL,
    modified_by   VARCHAR(255) NULL,
    modified_time datetime     NULL,
    CONSTRAINT pk_structure PRIMARY KEY (id)
);

ALTER TABLE grammar
    ADD CONSTRAINT uc_grammar_additional_note UNIQUE (additional_note_id);

ALTER TABLE grammar
    ADD CONSTRAINT uc_grammar_example UNIQUE (example_id);

ALTER TABLE grammar
    ADD CONSTRAINT uc_grammar_grammar_meaning UNIQUE (grammar_meaning_id);

ALTER TABLE grammar
    ADD CONSTRAINT uc_grammar_structure UNIQUE (structure_id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_ADDITIONAL_NOTE FOREIGN KEY (additional_note_id) REFERENCES additional_note (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_EXAMPLE FOREIGN KEY (example_id) REFERENCES example (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_GRAMMAR_MEANING FOREIGN KEY (grammar_meaning_id) REFERENCES grammar_meaning (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_LESSON FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE grammar
    ADD CONSTRAINT FK_GRAMMAR_ON_STRUCTURE FOREIGN KEY (structure_id) REFERENCES structure (id);

ALTER TABLE sentence
    ADD CONSTRAINT FK_SENTENCE_ON_ADDITIONAL_NOTE FOREIGN KEY (additional_note_id) REFERENCES additional_note (id);

ALTER TABLE sentence
    ADD CONSTRAINT FK_SENTENCE_ON_EXAMPLE FOREIGN KEY (example_id) REFERENCES example (id);

ALTER TABLE sentence
    ADD CONSTRAINT FK_SENTENCE_ON_GRAMMAR_MEANING FOREIGN KEY (grammar_meaning_id) REFERENCES grammar_meaning (id);

ALTER TABLE sentence
    ADD CONSTRAINT FK_SENTENCE_ON_STRUCTURE FOREIGN KEY (structure_id) REFERENCES structure (id);
