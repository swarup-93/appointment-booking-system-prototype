CREATE TABLE patients
(
    id            BINARY(16)   NOT NULL,
    user_id       BINARY(16)   NOT NULL,
    date_of_birth date         NOT NULL,
    gender        VARCHAR(30)  NOT NULL,
    address       VARCHAR(255) NULL,
    created_at    datetime(6)  NOT NULL,
    updated_at    datetime(6)  NOT NULL,
    CONSTRAINT pk_patients PRIMARY KEY (id)
);

ALTER TABLE patients
    ADD CONSTRAINT uc_patients_user UNIQUE (user_id);

ALTER TABLE patients
    ADD CONSTRAINT FK_PATIENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);