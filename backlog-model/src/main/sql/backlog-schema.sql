create table backlog (project varchar2(64), storyNumber number, title varchar2(64), story varchar2(1000), addedBy varchar2(128), addedOn date);
create sequence backlogStorySequence ; 