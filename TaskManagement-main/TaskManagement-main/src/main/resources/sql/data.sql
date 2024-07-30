INSERT INTO tasks (created_at, deadline, priority, is_completed, title, content)
VALUES
    (CURRENT_TIMESTAMP(), parsedatetime('12-10-2024', 'dd-MM-yyyy'), 'HIGH', false, 'Task with 10 days deadline', 'Content for the task');

INSERT INTO tasks (created_at, duration_in_hour,  priority, is_completed, title, content)
VALUES
    (CURRENT_TIMESTAMP(), 3, 'HIGH', false, 'Task with 3 hours duration', 'Content for the task');

-- Insert a task with a specific datetime deadline
INSERT INTO tasks (created_at, deadline, priority, is_completed, title, content)
VALUES
    ('2024-04-09 10:00:00',  parsedatetime('12-02-2024', 'dd-MM-yyyy'), 'HIGH', false, 'Task 1', 'Content for Task 1');

-- Insert a task with subtasks
INSERT INTO tasks (id,created_at,  deadline, priority, is_completed, title, content)
VALUES
    (99,CURRENT_TIMESTAMP(), parsedatetime('20-10-2024', 'dd-MM-yyyy'), 'HIGH', false, 'Task with 10 days deadline', 'Content for the task');

INSERT INTO subtasks (is_completed, title, task_id)
VALUES
    (false, 'Subtask 1', 99),
    (false, 'Subtask 2', 99),
    (false, 'Subtask 3', 99);
