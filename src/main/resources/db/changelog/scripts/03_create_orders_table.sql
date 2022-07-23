CREATE TABLE public.orders
(
    id serial NOT NULL,
    price double precision NOT NULL,
    number_of_products integer NOT NULL,
    user_id integer,
    PRIMARY KEY (id),
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.users (id)
);
