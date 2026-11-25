ALTER TABLE doctor_schedule
    ADD COLUMN is_active BIT(1) NOT NULL DEFAULT b'1';