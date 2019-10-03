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
	id 						INT NOT NULL UNIQUE AUTO_INCREMENT,
	ci_id					VARCHAR(255) UNIQUE NOT NULL,
	name 					VARCHAR(255) NOT NULL,
	registration_date 		TIMESTAMP NOT NULL,
	calibration_date 		DATE DEFAULT NULL,
	calibration_interval 	INT DEFAULT NULL,
	next_calibration_date 	DATE DEFAULT NULL,
	picture          		VARCHAR(255),
	description 			TEXT,
	notes					TEXT,
	owner					VARCHAR(255),
    entry_creator_id	 	INT,

	PRIMARY KEY (id),
    FOREIGN KEY (entry_creator_id) REFERENCES users (id)

	CREATE FULLTEXT INDEX multicolumn ON items (ci_id, name, description,notes, owner);
);

