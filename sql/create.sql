USE cardb;
CREATE TABLE users
(
    id          INT NOT NULL AUTO_INCREMENT UNIQUE,
    username    VARCHAR(255),
    password    VARCHAR(255),
    email 		VARCHAR(255),
    phone_no 	INT,
    full_name 	VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE items(
	id 						INT UNIQUE NOT NULL,
	name 					VARCHAR(255) NOT NULL,
	registration_date 		DATE NOT NULL,
	calibration_date 		DATE,
	calibration_interval 	INT,
	next_calibration_date 	DATE,
	picture          		VARCHAR(255),
	description 			TEXT,
	notes					TEXT,
	owner					VARCHAR(255),
    item_creator_id 		INT,

	PRIMARY KEY (id),
    FOREIGN KEY (item_creator_id) REFERENCES users (id)
);

