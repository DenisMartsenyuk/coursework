CREATE OR REPLACE FUNCTION rd_check_update_id() RETURNS TRIGGER AS $$
BEGIN
    IF OLD.id != NEW.id THEN
        RAISE EXCEPTION 'Can not update id column in this table.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER a_check_update_id_author
    BEFORE UPDATE
    ON rd_author
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_diary
    BEFORE UPDATE
    ON rd_diary
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_diary_file
    BEFORE UPDATE
    ON rd_diary_file
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_parent_student
    BEFORE UPDATE
    ON rd_parent_student
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_permission
    BEFORE UPDATE
    ON rd_permission
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_reading_session
    BEFORE UPDATE
    ON rd_reading_session
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_reading_task
    BEFORE UPDATE
    ON rd_reading_task
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_report
    BEFORE UPDATE
    ON rd_report
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_report_diary
    BEFORE UPDATE
    ON rd_report_diary
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_role
    BEFORE UPDATE
    ON rd_role
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_role_permission
    BEFORE UPDATE
    ON rd_role_permission
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_user
    BEFORE UPDATE
    ON rd_user
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_user_role
    BEFORE UPDATE
    ON rd_user_role
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();

CREATE TRIGGER a_check_update_id_writing
    BEFORE UPDATE
    ON rd_writing
    FOR EACH ROW
EXECUTE PROCEDURE rd_check_update_id();