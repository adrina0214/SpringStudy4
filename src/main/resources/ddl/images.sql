USE edu;

CREATE OR REPLACE TABLE images 
(
	`no` 		INT 				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`file` 	LONGBLOB			NOT NULL,
	`type` 	VARCHAR(100) 	NOT NULL,
	`size` 	long 				NOT NULL,
	`del` 	BOOLEAN 			NOT NULL DEFAULT(0)
);

#INSERT INTO images (`file`, `type`, `size`) VALUE (#{file}, #{type}, #{size});

SELECT * FROM images;
