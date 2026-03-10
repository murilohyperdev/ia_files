-- Create merchant table
CREATE TABLE t_merchant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    document VARCHAR(20),
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    logo_url VARCHAR(500),
    is_active BOOLEAN DEFAULT true,
    plan_type VARCHAR(50) DEFAULT 'basic',
    settings JSONB DEFAULT '{}',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Indexes
CREATE INDEX idx_merchant_slug ON t_merchant(slug);
CREATE INDEX idx_merchant_active ON t_merchant(is_active) WHERE deleted_at IS NULL;
CREATE INDEX idx_merchant_plan ON t_merchant(plan_type);
