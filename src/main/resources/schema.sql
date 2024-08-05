DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS user_auth;

/**********************************/
/* �e�[�u����: ������� */
/**********************************/
CREATE TABLE company(
		id          SERIAL,
		name        VARCHAR(255) NOT NULL,
		industry    TEXT,
		priority    INTEGER,
		zipcode     CHARACTER(8) NOT NULL,
		address     VARCHAR(255) NOT NULL,
		phone       VARCHAR(14)  NOT NULL,
		url         VARCHAR(255),
		cordination VARCHAR(255)
);

/**********************************/
/* �e�[�u����: �S���ҘA���� */
/**********************************/
CREATE TABLE contact(
		id         SERIAL,
		company_id INTEGER      NOT NULL,
		name       VARCHAR(100) NOT NULL,
		phone      VARCHAR(14)  NOT NULL,
		email      VARCHAR(255) NOT NULL
);

/**********************************/
/* �e�[�u����: �F�؏�� */
/**********************************/
CREATE TABLE user_auth(
		id       SERIAL,
		name     VARCHAR(100) NOT NULL,
		email    VARCHAR(255) NOT NULL,
		password VARCHAR(255) NOT NULL
);

ALTER TABLE user_auth ADD CONSTRAINT IDX_user_auth_PK PRIMARY KEY (id);

ALTER TABLE company ADD CONSTRAINT IDX_company_PK PRIMARY KEY (id);

ALTER TABLE contact ADD CONSTRAINT IDX_contact_PK PRIMARY KEY (id);
ALTER TABLE contact ADD CONSTRAINT IDX_contact_FK FOREIGN KEY (company_id) REFERENCES company (id);
