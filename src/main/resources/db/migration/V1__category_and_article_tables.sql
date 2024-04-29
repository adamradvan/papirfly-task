CREATE TABLE IF NOT EXISTS pfy_category
(
    id          UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,

    category_id BIGSERIAL,
    name        VARCHAR(64)   NOT NULL,
    description VARCHAR(2048) NOT NULL
);

CREATE TABLE IF NOT EXISTS pfy_article
(
    id          UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,

    article_id  BIGSERIAL     NOT NULL,
    name        VARCHAR(64)   NOT NULL,
    description VARCHAR(2048) NOT NULL,
    price       NUMERIC       NOT NULL,
    currency    TEXT,
    categories  UUID[] DEFAULT '{}'::UUID[]
);
