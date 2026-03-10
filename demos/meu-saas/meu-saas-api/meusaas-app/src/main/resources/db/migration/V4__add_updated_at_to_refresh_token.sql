-- Add updated_at column to t_refresh_token table
ALTER TABLE t_refresh_token ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
