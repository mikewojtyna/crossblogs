--liquibase formatted sql"

--changeset kshah:addFulltextSearch
CREATE FULLTEXT INDEX IDX_ARTICLE_TITLE on Article (title)