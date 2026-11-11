CREATE TABLE doctors
(
    id               BINARY(16)     NOT NULL,
    user_id          BINARY(16)     NOT NULL,
    specialization   VARCHAR(100)   NOT NULL,
    qualification    VARCHAR(150)   NOT NULL,
    licence_number   VARCHAR(100)   NOT NULL,
    experience_year  INT            NOT NULL DEFAULT 0,
    consultancy_fees DECIMAL(10, 2) NOT NULL,
    created_at       datetime(6)    NOT NULL,
    updated_at       datetime(6)    NOT NULL,
    CONSTRAINT pk_doctors PRIMARY KEY (id)
);

ALTER TABLE doctors
    ADD CONSTRAINT uc_doctors_user UNIQUE (user_id);

ALTER TABLE doctors
    ADD CONSTRAINT FK_DOCTORS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE doctors
    ADD CONSTRAINT chk_doctor_experience
        CHECK (experience_year >= 0);

CREATE TABLE doctor_schedule
(
    id                    BINARY(16)  NOT NULL,
    doctor_id             BINARY(16)  NOT NULL,
    available_date        date        NOT NULL,
    start_time            time        NOT NULL,
    end_time              time        NOT NULL,
    slot_duration_minutes INT         NOT NULL,
    is_available          BIT(1)      NOT NULL DEFAULT 0,
    created_at            datetime(6) NOT NULL,
    updated_at            datetime(6) NOT NULL,
    CONSTRAINT pk_doctorschedule PRIMARY KEY (id)
);

ALTER TABLE doctor_schedule
    ADD CONSTRAINT FK_DOCTORSCHEDULE_ON_DOCTOR FOREIGN KEY (doctor_id) REFERENCES doctors (id);

ALTER TABLE doctor_schedule
    ADD CONSTRAINT chk_schedule_time
        CHECK (start_time < end_time);

ALTER TABLE doctor_schedule
    ADD CONSTRAINT chk_slot_duration
        CHECK (slot_duration_minutes > 0);

CREATE TABLE doctor_application
(
    id                          BINARY(16)   NOT NULL,
    user_id                     BINARY(16)   NOT NULL,
    medical_registration_number VARCHAR(100) NOT NULL,
    specialization              VARCHAR(100) NOT NULL,
    qualification               VARCHAR(150) NOT NULL,
    experience_year             INT          NOT NULL DEFAULT 0,
    application_status          VARCHAR(30)  NOT NULL DEFAULT 'PENDING',
    rejection_reason            VARCHAR(500) NULL,
    reviewed_by                 BINARY(16)   NULL,
    reviewed_at                 datetime(6)  NULL,
    created_at                  datetime(6)  NOT NULL,
    updated_at                  datetime(6)  NOT NULL,
    CONSTRAINT pk_doctorapplication PRIMARY KEY (id)
);

ALTER TABLE doctor_application
    ADD CONSTRAINT FK_DOCTORAPPLICATION_ON_REVIEWED_BY FOREIGN KEY (reviewed_by) REFERENCES users (id);

ALTER TABLE doctor_application
    ADD CONSTRAINT FK_DOCTORAPPLICATION_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE doctor_application
    ADD CONSTRAINT chk_application_experience
        CHECK (experience_year >= 0);