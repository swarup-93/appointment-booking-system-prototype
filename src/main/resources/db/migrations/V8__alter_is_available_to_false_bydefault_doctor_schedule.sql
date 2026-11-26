ALTER TABLE doctor_schedule
    MODIFY COLUMN is_available BIT(1) NOT NULL DEFAULT b'1';