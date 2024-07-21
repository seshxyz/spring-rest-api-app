
CREATE TABLE filetable (
    id bigint primary key unique not null generated always as identity,
    title varchar,
    filecontent text,
    description varchar(255),
    createdat timestamp without time zone not null
);