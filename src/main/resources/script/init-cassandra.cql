CREATE TABLE ProductPrice (
  productPriceId bigint PRIMARY KEY,
  productId bigint,
  price double,
  timestamp bigint,
  siteId varchar
);

create index on ProductPrice (productId);