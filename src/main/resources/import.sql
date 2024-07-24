INSERT INTO users (id, name, email, password, created_at, modified_at, last_login, is_active, role)
VALUES
    ('1a950b23-d098-4ea0-83f3-2563ad4bfab8', 'Juan Gomez', 'juan.perez@gmail.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE, 'USER'),

INSERT INTO phones (id, number, city_code, country_code, user_id)
VALUES
    ('a1b2c3d4-e5f6-7g8h-9i0j-klmnopqrstuv', '3104206988', '1', '57', '1a950b23-d098-4ea0-83f3-2563ad4bfab8'),
