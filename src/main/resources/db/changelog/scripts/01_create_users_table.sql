CREATE TABLE public.users
(
    id serial NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    role character varying(255) NOT NULL,
    created_at date NOT NULL,
    address character varying(255),
    is_closed boolean default false,
    PRIMARY KEY (id),
    CONSTRAINT uniq_username UNIQUE (username)
);