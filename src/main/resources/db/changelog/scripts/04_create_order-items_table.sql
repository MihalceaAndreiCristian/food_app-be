CREATE TABLE public.order_items
(
    order_id integer,
    item_id integer,
    CONSTRAINT "orderId" FOREIGN KEY (order_id)
        REFERENCES public.orders (id),
    CONSTRAINT "itemId" FOREIGN KEY (item_id)
        REFERENCES public.items (id)
);
