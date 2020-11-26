CREATE TABLE IF NOT EXISTS `myapp`.`developers` (
`developers_id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(265) NULL,
`account_id` INT NULL,
PRIMARY KEY (`developers_id`),
UNIQUE INDEX `developers_id_UNIQUE` (`developers_id` ASC) VISIBLE,
UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC) VISIBLE,
CONSTRAINT `account_id`
FOREIGN KEY (`account_id`)
REFERENCES `myapp`.`accounts` (`idaccounts_table`)
ON DELETE CASCADE
ON UPDATE CASCADE);
