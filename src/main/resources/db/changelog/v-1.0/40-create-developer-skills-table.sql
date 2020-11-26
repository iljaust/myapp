CREATE TABLE IF NOT EXISTS `myapp`.`developer_skills` (
`id_developer_skills` INT NOT NULL,
`developer_id` INT NULL,
`skill_id` INT NULL,
PRIMARY KEY (`id_developer_skills`),
UNIQUE INDEX `id_developer_skills_UNIQUE` (`id_developer_skills` ASC) VISIBLE,
INDEX `developer_id_idx` (`developer_id` ASC) VISIBLE,
INDEX `skill_id_idx` (`skill_id` ASC) VISIBLE,
CONSTRAINT `developer_id`
FOREIGN KEY (`developer_id`)
REFERENCES `myapp`.`developers` (`developers_id`)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT `skill_id`
FOREIGN KEY (`skill_id`)
REFERENCES `myapp`.`skills` (`id`)
ON DELETE CASCADE
ON UPDATE CASCADE);