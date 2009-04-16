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
