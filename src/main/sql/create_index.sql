CREATE INDEX rd_reading_task_parent_id ON rd_reading_task USING HASH(parent_id);
CREATE INDEX rd_reading_task_student_id ON rd_reading_task USING HASH(student_id);