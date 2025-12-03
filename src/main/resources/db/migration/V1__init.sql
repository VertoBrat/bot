-- ======================================
-- CREATE TABLE: topic
-- ======================================
CREATE TABLE topic (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- ======================================
-- CREATE TABLE: message
-- ======================================
CREATE TABLE message (
    id BIGSERIAL PRIMARY KEY,
    content TEXT NOT NULL
);

-- ======================================
-- CREATE TABLE: topic_message
-- связь один topic → много message
-- ======================================
CREATE TABLE topic_message (
    topic_id BIGINT NOT NULL,
    message_id BIGINT NOT NULL UNIQUE,

    CONSTRAINT fk_topic
        FOREIGN KEY (topic_id)
        REFERENCES topic (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_message
        FOREIGN KEY (message_id)
        REFERENCES message (id)
        ON DELETE CASCADE
);
