create table if not exists socks (
    color varchar(6),
    cotton_part integer CHECK(cotton_part >= 0 AND cotton_part  <= 100),
    quantity integer NOT NULL CHECK(quantity >= 0),
    PRIMARY KEY (color, cotton_part)
);

GO