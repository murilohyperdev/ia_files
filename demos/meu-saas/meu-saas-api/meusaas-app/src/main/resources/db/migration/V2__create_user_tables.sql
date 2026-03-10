-- Create rule table (permissions)
CREATE TABLE t_rule (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    module VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create group table (roles/profiles)
CREATE TABLE t_group (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    merchant_id UUID NOT NULL REFERENCES t_merchant(id),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    is_system BOOLEAN DEFAULT false,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(merchant_id, name)
);

-- Create group_rule association table
CREATE TABLE t_group_rule (
    group_id UUID NOT NULL REFERENCES t_group(id) ON DELETE CASCADE,
    rule_id UUID NOT NULL REFERENCES t_rule(id) ON DELETE CASCADE,
    PRIMARY KEY (group_id, rule_id)
);

-- Create user table
CREATE TABLE t_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    merchant_id UUID NOT NULL REFERENCES t_merchant(id),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(500),
    phone VARCHAR(20),
    is_active BOOLEAN DEFAULT true,
    email_verified_at TIMESTAMP,
    last_login_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    UNIQUE(merchant_id, email)
);

-- Create user_group association table
CREATE TABLE t_user_group (
    user_id UUID NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
    group_id UUID NOT NULL REFERENCES t_group(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, group_id)
);

-- Create refresh_token table
CREATE TABLE t_refresh_token (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
    token VARCHAR(500) NOT NULL UNIQUE,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    revoked_at TIMESTAMP
);

-- Indexes
CREATE INDEX idx_user_merchant ON t_user(merchant_id);
CREATE INDEX idx_user_email ON t_user(email);
CREATE INDEX idx_user_active ON t_user(is_active) WHERE deleted_at IS NULL;
CREATE INDEX idx_group_merchant ON t_group(merchant_id);
CREATE INDEX idx_refresh_token_user ON t_refresh_token(user_id);
CREATE INDEX idx_refresh_token_expires ON t_refresh_token(expires_at);
