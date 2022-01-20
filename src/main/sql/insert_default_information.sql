INSERT INTO rd_role (name) values ('parent');
INSERT INTO rd_role (name) values ('student');

INSERT INTO rd_permission (name) values ('AUTH');
INSERT INTO rd_permission (name) values ('GENERATE DIARY');

INSERT INTO rd_permission (name) values ('SAVE_READING_TASK');
INSERT INTO rd_permission (name) values ('SAVE_WRITING');
INSERT INTO rd_permission (name) values ('SAVE_AUTHOR');

INSERT INTO rd_permission (name) values ('SAVE_READING_SESSION');
INSERT INTO rd_permission (name) values ('SAVE_REPORT');
INSERT INTO rd_permission (name) values ('DISCONNECT');
INSERT INTO rd_permission (name) values ('CONNECT');

INSERT INTO rd_role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO rd_role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO rd_role_permission (role_id, permission_id) VALUES (2, 1);
INSERT INTO rd_role_permission (role_id, permission_id) VALUES (2, 2);

INSERT INTO rd_role_permission (role_id, permission_id) VALUES (1, 3);
INSERT INTO rd_role_permission (role_id, permission_id) VALUES (1, 4);
INSERT INTO rd_role_permission (role_id, permission_id) VALUES (1, 5);

INSERT INTO rd_role_permission (role_id, permission_id) VALUES (2, 6);
INSERT INTO rd_role_permission (role_id, permission_id) VALUES (2, 7);
INSERT INTO rd_role_permission (role_id, permission_id) VALUES (2, 8);
INSERT INTO rd_role_permission (role_id, permission_id) VALUES (2, 9);
