-- drop database yogafire;
-- create database yogafire;
-- use yogafire;  

CREATE TABLE user
(
  user_id     VARCHAR(30) NOT NULL DEFAULT 'undefined' COMMENT '회원 아이디',
  password    VARCHAR(30) NOT NULL COMMENT '회원 비밀번호',
  name        VARCHAR(30) NOT NULL COMMENT '회원 이름',
  birthday    VARCHAR(10) NOT NULL COMMENT '회원 생년월일',
  email       VARCHAR(50) NOT NULL COMMENT '회원 이메일',
  phone       VARCHAR(20) NOT NULL COMMENT '전화번호',
  nickname    VARCHAR(30) NOT NULL COMMENT '닉네임',
  create_date DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  delete_yn   VARCHAR(1)  NOT NULL DEFAULT 'N' COMMENT '탈퇴여부',
  height 	  INT   	  NOT NULL COMMENT '키',
  age 		  INT 		  NOT NULL COMMENT '나이',	
  weight      INT         NOT NULL COMMENT '체중',
  sex         INT         NOT NULL COMMENT '성별',
  PRIMARY KEY (user_id)
) COMMENT '회원 테이블';

ALTER TABLE user
  ADD CONSTRAINT UQ_user_id UNIQUE (user_id);

ALTER TABLE user
  ADD CONSTRAINT UQ_email UNIQUE (email);

ALTER TABLE user
  ADD CONSTRAINT UQ_nickname UNIQUE (nickname);

CREATE TABLE video
(
  video_key    INT           NULL COMMENT '영상 고유 번호',
  video_id     VARCHAR(100)  NOT NULL COMMENT '유튜브 video id',
  video_title  VARCHAR(1000) NOT NULL COMMENT '영상 제목',
  area         VARCHAR(1000) NOT NULL COMMENT '운동 부위',
  channel_name VARCHAR(100)  NOT NULL COMMENT '채널명',
  center_name  VARCHAR(100)  NOT NULL DEFAULT '요가원' COMMENT '요가원 이름(채널명과 동일)',
  view_cnt     INT           NOT NULL DEFAULT 0 COMMENT '조회수',
  reg_date     DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '영상 등록일',
  v_comment_id INT           NOT NULL DEFAULT 0 COMMENT '영상 댓글 고유 번호',
  session_id   VARCHAR(30)   NOT NULL COMMENT '세션에서 가져온 회원 아이디',
  like_yn      BOOLEAN       NOT NULL DEFAULT false COMMENT '찜 여부 확인',
  PRIMARY KEY (video_id)
) COMMENT '영상 테이블';

ALTER TABLE video
  ADD CONSTRAINT UQ_video_key UNIQUE (video_key);
  
ALTER TABLE video
  ADD CONSTRAINT UQ_video_id UNIQUE (video_id);

CREATE TABLE video_comment
(
  v_comment_id      INT           NOT NULL AUTO_INCREMENT COMMENT '영상 댓글 고유 번호',
  v_comment_content VARCHAR(1000) NULL     COMMENT '영상 댓글 내용',
  v_comment_reg     DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '영상 댓글 등록일',
  delete_yn         VARCHAR(1)    NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
  user_id           VARCHAR(30)   NOT NULL COMMENT '회원 아이디',
  video_id          VARCHAR(100)  NOT NULL COMMENT '유튜브 video id',
  PRIMARY KEY (v_comment_id)
) COMMENT '영상 댓글 테이블';

ALTER TABLE video_comment
  ADD CONSTRAINT UQ_v_comment_id UNIQUE (v_comment_id);

CREATE TABLE video_like
(
  like_yn      VARCHAR(1),
  session_id   VARCHAR(30) NOT NULL COMMENT '회원 아이디',
  video_id	   VARCHAR(100) NOT NULL COMMENT '영상 url'
) COMMENT '찜 테이블';


