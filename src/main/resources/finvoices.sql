buyerPartyDetails has one-to-many relation with invoiceDetails.
buyerPartyDetails has one-to-one relation with buyerPostalAddressDetails.
definitionDetails has oone to many relation with definitionDetails



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
  `DateInvoice` VARCHAR(50),
  `invoiceDueDate` VARCHAR(50),
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

