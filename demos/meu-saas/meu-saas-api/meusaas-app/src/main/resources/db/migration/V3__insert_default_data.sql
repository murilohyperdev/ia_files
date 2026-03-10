-- Insert default rules
INSERT INTO t_rule (id, code, name, module) VALUES
    (gen_random_uuid(), 'USER_CREATE', 'Criar usuários', 'USER'),
    (gen_random_uuid(), 'USER_READ', 'Visualizar usuários', 'USER'),
    (gen_random_uuid(), 'USER_UPDATE', 'Editar usuários', 'USER'),
    (gen_random_uuid(), 'USER_DELETE', 'Remover usuários', 'USER'),
    (gen_random_uuid(), 'GROUP_CREATE', 'Criar grupos', 'GROUP'),
    (gen_random_uuid(), 'GROUP_READ', 'Visualizar grupos', 'GROUP'),
    (gen_random_uuid(), 'GROUP_UPDATE', 'Editar grupos', 'GROUP'),
    (gen_random_uuid(), 'GROUP_DELETE', 'Remover grupos', 'GROUP'),
    (gen_random_uuid(), 'MERCHANT_READ', 'Visualizar merchant', 'MERCHANT'),
    (gen_random_uuid(), 'MERCHANT_UPDATE', 'Editar merchant', 'MERCHANT'),
    (gen_random_uuid(), 'ADMIN_ACCESS', 'Acesso administrativo', 'SYSTEM');

-- Insert default merchant
INSERT INTO t_merchant (id, name, slug, email, is_active, plan_type)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Default Merchant',
    'default',
    'admin@minhaempresa.com',
    true,
    'enterprise'
);

-- Insert default admin group for merchant
INSERT INTO t_group (id, merchant_id, name, description, is_system)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'Acesso total ao sistema',
    true
);

-- Associate all rules to admin group
INSERT INTO t_group_rule (group_id, rule_id)
SELECT '00000000-0000-0000-0000-000000000001', id FROM t_rule;

-- Insert default admin user (password: Admin@123)
-- BCrypt hash generated for "Admin@123"
INSERT INTO t_user (id, merchant_id, name, email, password_hash, is_active, email_verified_at)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@minhaempresa.com',
    '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/X4.VTtYLgVJay2Kbu',
    true,
    CURRENT_TIMESTAMP
);

-- Associate admin user to admin group
INSERT INTO t_user_group (user_id, group_id)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    '00000000-0000-0000-0000-000000000001'
);
