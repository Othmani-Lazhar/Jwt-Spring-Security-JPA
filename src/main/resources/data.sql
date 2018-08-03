INSERT INTO USER (USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, IS_ACTIVE, LAST_ISSUE_DATE) VALUES (1, 'admin', '$admin', 'admin', 'admin', 'admin@admin.com', 1, STR_TO_DATE('01-01-2016', '%d-%m-%Y'));
INSERT INTO USER (USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, IS_ACTIVE, LAST_ISSUE_DATE) VALUES (2, 'user', '$user', 'user', 'user', 'enabled@user.com', 1, STR_TO_DATE('01-01-2017','%d-%m-%Y'));
INSERT INTO USER (USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, IS_ACTIVE, LAST_ISSUE_DATE) VALUES (3, 'disabled', 'disabled', 'user', 'user', 'disabled@user.com', 0, STR_TO_DATE('01-01-2018','%d-%m-%Y'));


INSERT INTO AUTHORITY (AUTHORITY_ID, AUTHORITY_ROLE) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_ID, AUTHORITY_ROLE) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);
