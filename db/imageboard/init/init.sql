drop table if exists theme_table CASCADE;
CREATE TABLE IF NOT EXISTS theme_table
(
    id int PRIMARY KEY generated always as identity,
    name varchar(255) NOT NULL,
    description varchar
);

drop table if exists board_table CASCADE;
CREATE TABLE IF NOT EXISTS board_table
(
    id VARCHAR(10) PRIMARY KEY,
    name varchar(255) NOT NULL,
    description varchar,
    theme_id int,
    banner bytea NOT NULL,
    created_date timestamp DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (theme_id) REFERENCES theme_table(id)
);

drop table if exists thread_table CASCADE;
CREATE TABLE IF NOT EXISTS thread_table
(
    id int PRIMARY KEY generated always as identity,
    board_id varchar(10) NOT NULL,
    name varchar(255) NOT NULL,
    created_date timestamp DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (board_id) REFERENCES board_table(id)
);

drop table if exists message_table CASCADE;
CREATE TABLE IF NOT EXISTS message_table
(
    id int PRIMARY KEY generated always as identity,
    thread_id int NOT NULL,
    body varchar(15000) NOT NULL,
    created_date timestamp DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (thread_id) REFERENCES thread_table(id) ON DELETE CASCADE
);

drop table if exists reply_table CASCADE;
CREATE TABLE IF NOT EXISTS reply_table
(
    id int PRIMARY KEY generated always as identity,
    reply_id int NOT NULL,
    msg_id int NOT NULL,

    FOREIGN KEY (reply_id) REFERENCES message_table(id) ON DELETE CASCADE,
    FOREIGN KEY (msg_id) REFERENCES message_table(id) ON DELETE CASCADE
);

drop table if exists attachment_table CASCADE;
CREATE TABLE IF NOT EXISTS attachment_table
(
    id int PRIMARY KEY generated always as identity,
    msg_id int NOT NULL,
    attachment bytea NOT NULL,

    FOREIGN KEY (msg_id) REFERENCES message_table(id) ON DELETE CASCADE
);