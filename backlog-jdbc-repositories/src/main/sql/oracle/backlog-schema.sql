CREATE TABLE BL_STORIES (
STORYID NUMBER NOT NULL,
TITLE VARCHAR2(128),
CLOSED DATE,
OPENED DATE,
OWNER VARCHAR(32),
PRIORITY NUMBER,
PROJECTID NUMBER,
STATE VARCHAR2(32),
STORY VARCHAR2(1000),
POINTS NUMBER(3),
PRIMARY KEY(STORYID)
);

CREATE INDEX  "BL_STORIES_OWNER" ON  "BL_STORIES" ("OWNER");
CREATE INDEX  "BL_STORIES_OPENED" ON  "BL_STORIES" ("OPENED");
CREATE INDEX  "BL_STORIES_POINTS" ON  "BL_STORIES" ("POINTS");
CREATE INDEX  "BL_STORIES_PROJECTID" ON  "BL_STORIES" ("PROJECTID");
CREATE INDEX  "BL_STORIES_PROJ_PRIORITY" ON  "BL_STORIES" ("PROJECTID", "PRIORITY");
CREATE INDEX  "BL_STORIES_PRIORITY" ON  "BL_STORIES" ("PRIORITY");
CREATE INDEX  "BL_STORIES_STATE" ON  "BL_STORIES" ("STATE");

CREATE SEQUENCE "BL_STORY_SEQ"  MINVALUE 1 INCREMENT BY 1 START WITH 1;

CREATE TABLE BL_PROJECTS (
  PROJECTID VARCHAR2(32) NOT NULL,
  TITLE VARCHAR2(128),
        DESCRIPTION VARCHAR2(1000),
        PRIMARY KEY(PROJECTID)
);

CREATE INDEX "BL_PROJECTS_TITLE" ON "BL_PROJECTS" ("TITLE");


CREATE TABLE BL_GROUPS (
  GROUPID VARCHAR2(32) NOT NULL,
  TITLE VARCHAR2(128),
  DESCRIPTION VARCHAR2(1000),
  PRIMARY KEY(GROUPID)
);

CREATE TABLE BL_GROUP_MEMBERS (
  GROUPID VARCHAR2(32) NOT NULL,
  MEMBERID VARCHAR2(32),
  PRIMARY KEY(GROUPID,MEMBERID)
);

CREATE TABLE BL_PERSONS (
  "PERSONID" VARCHAR2(32),
  "NAME" VARCHAR2(64),
  PRIMARY KEY("PERSONID");
);