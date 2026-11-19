ALTER TABLE doctor_application
    ADD COLUMN revocation_reason VARCHAR(500) NULL,
    ADD COLUMN revoked_at DATETIME(6) NULL;

ALTER TABLE doctors
    ADD COLUMN is_active BOOLEAN NOT NULL DEFAULT TRUE;