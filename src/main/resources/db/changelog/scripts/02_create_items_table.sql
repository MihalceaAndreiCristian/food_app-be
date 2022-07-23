CREATE TABLE public.items
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    price double precision NOT NULL,
    size character varying(255) NOT NULL,
    category character varying(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "unique" UNIQUE (name, price, size)
);