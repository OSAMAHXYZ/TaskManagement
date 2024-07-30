-- Create the sequence
CREATE SEQUENCE tasks_seq START WITH 1 INCREMENT BY 1;

-- Create the tasks table
CREATE TABLE tasks (
    id BIGINT DEFAULT NEXT VALUE FOR tasks_seq PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    duration_in_hour INTEGER,
    deadline DATE,
    priority VARCHAR(10),
    is_completed BOOLEAN NOT NULL,
    title VARCHAR(300) NOT NULL,
    content VARCHAR(255)
);

-- Create the sequence for subtasks
CREATE SEQUENCE subtasks_seq START WITH 1 INCREMENT BY 1;

-- Create the subtasks table
CREATE TABLE subtasks (
    id BIGINT DEFAULT NEXT VALUE FOR subtasks_seq PRIMARY KEY,
    is_completed BOOLEAN NOT NULL,
    title VARCHAR(300)  NULL,
    task_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE ON UPDATE CASCADE
);