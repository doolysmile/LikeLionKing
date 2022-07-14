DROP TABLE IF EXISTS posts CASCADE;

-- POST DATA
CREATE TABLE posts
(
    postSeq  bigint    NOT NULL AUTO_INCREMENT,     -- post PK
    title    varchar(200)  NOT NULL,
    content  varchar(500)  NOT NULL,
    PRIMARY KEY (postSeq)
);