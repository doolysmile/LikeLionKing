DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- POST DATA
CREATE TABLE posts
(
    postSeq     bigint          NOT NULL AUTO_INCREMENT,     -- post PK
    userSeq     bigint          ,
    views       int             DEFAULT 0,
    likes       int             DEFAULT 0,
    title       varchar(200)    ,
    content     varchar(500)    ,
    boardType   int             DEFAULT 0,
    creatAt     datetime        DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (postSeq)
);

CREATE TABLE users
(
    userSeq  bigint    NOT NULL AUTO_INCREMENT,     -- user PK
    loginId  varchar(200)  NOT NULL,
    loginPw  varchar(500)  NOT NULL,
    PRIMARY KEY (userSeq)
);