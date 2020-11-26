CREATE TABLE IF NOT EXISTS `myapp`.`accounts` (
`idaccounts_table` INT NOT NULL AUTO_INCREMENT,
`data` VARCHAR(245) NULL,
`idaccount_status` INT NOT NULL,
PRIMARY KEY (`idaccounts_table`),
INDEX `idaccount_status_idx` (`idaccount_status` ASC) VISIBLE,
CONSTRAINT `idaccount_status`
FOREIGN KEY (`idaccount_status`)
REFERENCES `myapp`.`account_status` (`idaccount_status`)
ON DELETE CASCADE
ON UPDATE CASCADE);
