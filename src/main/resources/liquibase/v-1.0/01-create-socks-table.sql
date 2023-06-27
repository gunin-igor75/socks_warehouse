create table if not exists socks (
    color varchar(6) NOT NULL,
    cotton_part integer CHECK(cotton_part >= 0 AND cotton_part  <= 100) NOT NULL,
    quantity integer NOT NULL CHECK(quantity >= 0),
    PRIMARY KEY (color, cotton_part)
);

GO