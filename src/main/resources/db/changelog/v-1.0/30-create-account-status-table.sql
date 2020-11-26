CREATE TABLE IF NOT EXISTS `myapp`.`account_status` (
`idaccount_status` INT NOT NULL AUTO_INCREMENT,
`status` VARCHAR(45) NOT NULL,
PRIMARY KEY (`idaccount_status`),
UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE);