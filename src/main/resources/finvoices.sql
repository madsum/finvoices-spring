



buyerPartyDetails has one-to-many relation with invoiceDetails.
buyerPartyDetails has one-to-one relation with buyerPostalAddressDetails.
definitionDetails has oone to many relation with definitionDetails

// Upper case table below.

CREATE TABLE `buyerPartyDetails` (
  `BuyerPartyDetails_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `BuyerPartyIdentifier` VARCHAR(100) NOT NULL,
  `BuyerOrganisationName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`BuyerPartyDetails_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


CREATE TABLE `buyerPostalAddressDetails` (
  `BuyerPartyDetails_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `BuyerStreetName` VARCHAR(100),
  `BuyerTownName` VARCHAR(100),
  `BuyerPostCodeIdentifier` VARCHAR(100),
  PRIMARY KEY (`BuyerPartyDetails_id`),
  CONSTRAINT `FK_BuyerPostalAddressDetails` FOREIGN KEY (`BuyerPartyDetails_id`) REFERENCES `buyerPartyDetails` (`BuyerPartyDetails_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


CREATE TABLE  `invoiceDetails` (
  `InvoiceDetails_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `InvoiceTypeCode` VARCHAR(100),
  `InvoiceTypeText` VARCHAR(100),
  `InvoiceNumber` VARCHAR(100),
  `OriginCode` VARCHAR(100),
  `SellersBuyerIdentifier` VARCHAR(100),
  `AgreementIdentifier` VARCHAR(100),
  `InvoiceTotalVatIncludedAmount` VARCHAR(50),  
  `InvoiceFreeText` VARCHAR(1000),
  `InvoiceDate` MEDIUMTEXT,
  `InvoiceDueDate` MEDIUMTEXT,
  `BuyerPartyDetails_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`InvoiceDetails_id`),
  CONSTRAINT `FK_InvoiceDetails` FOREIGN KEY (`BuyerPartyDetails_id`) 
  REFERENCES `buyerPartyDetails` (`BuyerPartyDetails_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;


CREATE TABLE  `definitionDetails` (
  `DefinitionDetails_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `definitionHeaderText_attribute` VARCHAR(100),
  `DefinitionHeaderText_value` VARCHAR(100),
  `DefinitionValue` VARCHAR(100),
  `InvoiceDetails_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`DefinitionDetails_id`),
  CONSTRAINT `FK_DefinitionDetails` FOREIGN KEY (`InvoiceDetails_id`) 
  REFERENCES `invoiceDetails` (`InvoiceDetails_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;



__________________________________________________________________________


// small case table below.


CREATE TABLE `buyerPartyDetails` (
  `buyerPartyDetails_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `buyerPartyIdentifier` VARCHAR(100) NOT NULL,
  `buyerOrganisationName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`buyerPartyDetails_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


CREATE TABLE `buyerPostalAddressDetails` (
  `buyerPartyDetails_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `buyerStreetName` VARCHAR(100),
  `buyerTownName` VARCHAR(100),
  `buyerPostCodeIdentifier` VARCHAR(100),
  PRIMARY KEY (`buyerPartyDetails_id`),
  CONSTRAINT `FK_buyerPostalAddressDetails` FOREIGN KEY (`buyerPartyDetails_id`) REFERENCES `buyerPartyDetails` (`buyerPartyDetails_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;



CREATE TABLE  `invoiceDetails` (
  `invoiceDetails_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `invoiceTypeCode` VARCHAR(100),
  `invoiceTypeText` VARCHAR(100),
  `invoiceNumber` VARCHAR(100),
  `originCode` VARCHAR(100),
  `sellersBuyerIdentifier` VARCHAR(100),
  `agreementIdentifier` VARCHAR(100),
  `invoiceTotalVatIncludedAmount` VARCHAR(50),  
  `invoiceFreeText` VARCHAR(1000),
  `invoiceDate` MEDIUMTEXT,
  `invoiceDueDate` MEDIUMTEXT,
  `buyerPartyDetails_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`invoiceDetails_id`),
  CONSTRAINT `FK_InvoiceDetails` FOREIGN KEY (`buyerPartyDetails_id`) 
  REFERENCES `buyerPartyDetails` (`buyerPartyDetails_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;


CREATE TABLE  `definitionDetails` (
  `definitionDetails_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `definitionHeaderText_attribute` VARCHAR(100),
  `definitionHeaderText_value` VARCHAR(100),
  `definitionValue` VARCHAR(100),
  `invoiceDetails_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`definitionDetails_id`),
  CONSTRAINT `FK_DefinitionDetails` FOREIGN KEY (`invoiceDetails_id`) 
  REFERENCES `invoiceDetails` (`invoiceDetails_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;





// Exmaple table bellow

// one to many table
CREATE TABLE  `stock_daily_record` (
  `RECORD_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PRICE_OPEN` FLOAT(6,2) DEFAULT NULL,
  `PRICE_CLOSE` FLOAT(6,2) DEFAULT NULL,
  `PRICE_CHANGE` FLOAT(6,2) DEFAULT NULL,
  `VOLUME` BIGINT(20) UNSIGNED DEFAULT NULL,
  `DATE` DATE NOT NULL,
  `BuyerPartyDetails_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RECORD_ID`),
  CONSTRAINT `FK_test` FOREIGN KEY (`BuyerPartyDetails_id`) 
  REFERENCES `BuyerPartyDetails` (`BuyerPartyDetails_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;




CREATE TABLE `stock` (
  `STOCK_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `STOCK_CODE` VARCHAR(10) NOT NULL,
  `STOCK_NAME` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`STOCK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


// one to one

CREATE TABLE  `stock_detail` (
 `STOCK_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
 `COMP_NAME` VARCHAR(100) NOT NULL,
 `COMP_DESC` VARCHAR(255) NOT NULL,
 `REMARK` VARCHAR(255) NOT NULL,
 `LISTED_DATE` DATE NOT NULL,
 PRIMARY KEY (`STOCK_ID`),
 CONSTRAINT `FK_STOCK_ID` FOREIGN KEY (`STOCK_ID`) REFERENCES `stock` (`STOCK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;





















