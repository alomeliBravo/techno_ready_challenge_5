-- ==========================
-- Clients
-- ==========================
INSERT INTO client (id, name, age, email, address) VALUES (1, 'Alice Smith', 28, 'alice@example.com', '123 Main St');
INSERT INTO client (id, name, age, email, address) VALUES (2, 'Bob Johnson', 35, 'bob@example.com', '456 Oak Ave');
INSERT INTO client (id, name, age, email, address) VALUES (3, 'Charlie Brown', 42, 'charlie@example.com', '789 Pine Rd');

-- ==========================
-- Items
-- ==========================
INSERT INTO item (id, name, description, price) VALUES (1, 'Laptop', 'Dell XPS 13', 1200.0);
INSERT INTO item (id, name, description, price) VALUES (2, 'Smartphone', 'Samsung Galaxy S24', 900.0);
INSERT INTO item (id, name, description, price) VALUES (3, 'Headphones', 'Sony WH-1000XM5', 350.0);

-- ==========================
-- Orders
-- ==========================
INSERT INTO orders (id, client_id, item_id, purchase_date, total)
VALUES (1, 1, 2, '2025-10-21', 900.0);

INSERT INTO orders (id, client_id, item_id, purchase_date, total)
VALUES (2, 2, 1, '2025-10-21', 1200.0);

INSERT INTO orders (id, client_id, item_id, purchase_date, total)
VALUES (3, 3, 3, '2025-10-21', 350.0);
