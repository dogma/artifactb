INSERT INTO BL_STORIES (STORYID,TITLE,CLOSED,OPENED,OWNER,PRIORITY,PROJECTID,STATE,STORY,POINTS)
  VALUES (1,'Title1',CURDATE(),CURDATE(),'nominalOwner',10,'project1','new','This is the story of a lovely lady',40);

INSERT INTO BL_STORIES (STORYID,TITLE,CLOSED,OPENED,OWNER,PRIORITY,PROJECTID,STATE,STORY,POINTS)
  VALUES (2,'Title2',CURDATE(),CURDATE(),'nominalOwner',10,'project1','new','This is the story of a lovely lady',40);

INSERT INTO BL_STORIES (STORYID,TITLE,CLOSED,OPENED,OWNER,PRIORITY,PROJECTID,STATE,STORY,POINTS)
  VALUES (3,'Title3',null,CURDATE(),'nominalOwner',10,'project1','new','This is the story of a lovely lady',40);

INSERT INTO BL_STORIES (STORYID,TITLE,CLOSED,OPENED,OWNER,PRIORITY,PROJECTID,STATE,STORY,POINTS)
  VALUES (4,'Title4',null,CURDATE(),'nominalOwner',10,'project2','new','This is the story of a lovely lady',40);


INSERT INTO BL_PROJECTS (PROJECTID,TITLE,DESCRIPTION,CREATED,STATE,OWNER)
  VALUES ('project1','Project 1','Project 1 Description',CURDATE(),'new','nominalOwner');

INSERT INTO BL_PROJECTS (PROJECTID,TITLE,DESCRIPTION,CREATED,STATE,OWNER)
  VALUES ('project2','Project 2','Project 2 Description',CURDATE(),'inprogress','nominalOwner');

INSERT INTO BL_PROJECTS (PROJECTID,TITLE,DESCRIPTION,CREATED,STATE,OWNER)
  VALUES ('project3','Project 3','Project 3 Description',CURDATE(),'abandoned','nominalOwner');

INSERT INTO BL_PROJECTS (PROJECTID,TITLE,DESCRIPTION,CREATED,STATE,OWNER)
  VALUES ('project4','Project 4','Project 4 Description',CURDATE(),'closed','otherOwner');

INSERT INTO BL_GROUPS (GROUPID, TITLE, DESCRIPTION) VALUES ('home','Home','The home group');
INSERT INTO BL_GROUPS (GROUPID, TITLE, DESCRIPTION) VALUES ('work','Work','The work group');

INSERT INTO BL_GROUP_MEMBERS (GROUPID, MEMBERID) VALUES ('home','testa');
INSERT INTO BL_GROUP_MEMBERS (GROUPID, MEMBERID) VALUES ('home','testb');
INSERT INTO BL_GROUP_MEMBERS (GROUPID, MEMBERID) VALUES ('work','testa');
INSERT INTO BL_GROUP_MEMBERS (GROUPID, MEMBERID) VALUES ('work','testc');

INSERT INTO BL_PERSONS (PERSONID,NAME) VALUES ('testa','Test Person A');
INSERT INTO BL_PERSONS (PERSONID,NAME) VALUES ('testb','Test Person B');
INSERT INTO BL_PERSONS (PERSONID,NAME) VALUES ('testc','Test Person C');

INSERT INTO BL_INTERATION (ITERATIONID, PROJECTID, STARTDATE, ACTIVEDAYS, COMPLETEDATE, TITLE, STATE)
  VALUES (1,'project1',CURDATE(),10,null,'Sprint 1','complete');
INSERT INTO BL_INTERATION (ITERATIONID, PROJECTID, STARTDATE, ACTIVEDAYS, COMPLETEDATE, TITLE, STATE)
  VALUES (2,'project1',CURDATE(),10,null,'Sprint 2','complete');
INSERT INTO BL_INTERATION (ITERATIONID, PROJECTID, STARTDATE, ACTIVEDAYS, COMPLETEDATE, TITLE, STATE)
  VALUES (3,'project1',CURDATE(),10,null,'Sprint 3','running');
INSERT INTO BL_INTERATION (ITERATIONID, PROJECTID, STARTDATE, ACTIVEDAYS, COMPLETEDATE, TITLE, STATE)
  VALUES (4,'project1',CURDATE(),10,null,'Sprint 4','new');

INSERT INTO BL_ITERATION_TASK (ITERATIONID, TASKID, STORYID, TITLE, DESCRIPTION, STATE, POINTS, STARTDATE, COMPLETEDDATE, ASSIGNED)
        VALUES (1,1,1,'Task 1', 'Task 1 Description', 'complete', 20, CURDATE(), null, 'testa');
INSERT INTO BL_ITERATION_TASK (ITERATIONID, TASKID, STORYID, TITLE, DESCRIPTION, STATE, POINTS, STARTDATE, COMPLETEDDATE, ASSIGNED)
        VALUES (1,2,1,'Task 2', 'Task 2 Description', 'inprogress', 10, CURDATE(), null, 'testa');
INSERT INTO BL_ITERATION_TASK (ITERATIONID, TASKID, STORYID, TITLE, DESCRIPTION, STATE, POINTS, STARTDATE, COMPLETEDDATE, ASSIGNED)
        VALUES (1,3,1,'Task 3', 'Task 3 Description', 'new', 5, CURDATE(), null, 'testb');
INSERT INTO BL_ITERATION_TASK (ITERATIONID, TASKID, STORYID, TITLE, DESCRIPTION, STATE, POINTS, STARTDATE, COMPLETEDDATE, ASSIGNED)
        VALUES (1,4,2,'Task 4', 'Task 4 Description', 'new', 20, CURDATE(), null, 'testb');