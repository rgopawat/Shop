CREATE TABLE `SHOP_INFORMATION` (
  `SHOP_NAME` varchar(50) NOT NULL,
  `SHOP_ADDRESS` varchar(100) default NULL,
  `SHOP_POSTCODE` INTEGER(15),
  `LATITUDE` varchar(50) default NULL,
  `LONGITUDE` varchar(50) default NULL,
  PRIMARY KEY  (`SHOP_NAME`)
);