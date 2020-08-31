CREATE TABLE `subscription` (
  `SUBSCRIBER_NAME` varchar(100) NOT NULL,
  `DATE_SUBSCRIBED` varchar(100) DEFAULT NULL,
  `DATE_RETURNED` varchar(100) DEFAULT NULL,
  `BOOK_ID` varchar(50) DEFAULT NULL,
  KEY `fk_book_id_subs_idx` (`BOOK_ID`),
  CONSTRAINT `fk_book_id_subs` FOREIGN KEY (`BOOK_ID`) REFERENCES `book` (`BOOK_ID`)
) ;
