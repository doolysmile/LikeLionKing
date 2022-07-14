DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- POST DATA
CREATE TABLE posts
(
    postSeq  bigint    NOT NULL AUTO_INCREMENT,     -- post PK
    title    varchar(200)  NOT NULL,
    content  varchar(500)  NOT NULL,
    PRIMARY KEY (postSeq)
);

CREATE TABLE users
(
    userSeq  bigint    NOT NULL AUTO_INCREMENT,     -- user PK
    loginId  varchar(200)  NOT NULL,
    loginPw  varchar(500)  NOT NULL,
    PRIMARY KEY (userSeq)
);