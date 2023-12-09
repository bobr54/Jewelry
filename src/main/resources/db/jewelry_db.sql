-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema jewelry_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jewelry_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jewelry_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `jewelry_db` ;

-- -----------------------------------------------------
-- Table `jewelry_db`.`gems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jewelry_db`.`gems` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `clarity` DOUBLE NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `weight` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jewelry_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jewelry_db`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(1000) NOT NULL,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ow0gan20590jrb00upg3va2fn` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jewelry_db`.`necklaces`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jewelry_db`.`necklaces` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK61643ag33hi12wf6nx8wmb7cr` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK61643ag33hi12wf6nx8wmb7cr`
    FOREIGN KEY (`user_id`)
    REFERENCES `jewelry_db`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jewelry_db`.`necklace_gems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jewelry_db`.`necklace_gems` (
  `necklace_id` BIGINT NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `gem_id` BIGINT NOT NULL,
  PRIMARY KEY (`necklace_id`, `gem_id`),
  INDEX `FKail8scl3e8iqu1gk9ys3gqn7w` (`gem_id` ASC) VISIBLE,
  CONSTRAINT `FK3arqqqmw98s06vsjlxi556o6q`
    FOREIGN KEY (`necklace_id`)
    REFERENCES `jewelry_db`.`necklaces` (`id`),
  CONSTRAINT `FKail8scl3e8iqu1gk9ys3gqn7w`
    FOREIGN KEY (`gem_id`)
    REFERENCES `jewelry_db`.`gems` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
