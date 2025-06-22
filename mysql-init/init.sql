-- =====================
-- Base de datos: order_service
-- =====================
CREATE DATABASE IF NOT EXISTS order_service;
USE order_service;

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_uuid VARCHAR(100) NOT NULL UNIQUE,
    status ENUM('CREATED', 'INVENTORY_VALID', 'INVENTORY_FAILED', 'DELIVERY_CREATED', 'FAILED') NOT NULL DEFAULT 'CREATED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    item_sku VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    event_type VARCHAR(100),
    payload TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

-- Registros de ejemplo
INSERT INTO orders (order_uuid, status) VALUES 
  ('uuid-001', 'CREATED'),
  ('uuid-002', 'INVENTORY_VALID'),
  ('uuid-003', 'DELIVERY_CREATED');

INSERT INTO order_items (order_id, item_sku, quantity) VALUES
  (1, 'SKU-001', 2),
  (2, 'SKU-002', 1),
  (3, 'SKU-003', 5);

INSERT INTO order_events (order_id, event_type, payload) VALUES
  (1, 'ORDER_CREATED', '{"item":"SKU-001"}'),
  (2, 'INVENTORY_VALIDATED', '{"item":"SKU-002"}'),
  (3, 'DELIVERY_CREATED', '{"item":"SKU-003"}');


-- =====================
-- Base de datos: inventory_service
-- =====================
CREATE DATABASE IF NOT EXISTS inventory_service;
USE inventory_service;

CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_sku VARCHAR(50) NOT NULL UNIQUE,
    item_name VARCHAR(100),
    quantity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS inventory_events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_uuid VARCHAR(100) NOT NULL,
    item_sku VARCHAR(50) NOT NULL,
    event_type ENUM('inventory_valid', 'inventory_failed') NOT NULL,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Registros de ejemplo
INSERT INTO products (item_sku, item_name, quantity) VALUES
  ('SKU-001', 'Producto A', 10),
  ('SKU-002', 'Producto B', 3),
  ('SKU-003', 'Producto C', 0);

INSERT INTO inventory_events (order_uuid, item_sku, event_type, message) VALUES
  ('uuid-001', 'SKU-001', 'inventory_valid', 'Stock disponible'),
  ('uuid-002', 'SKU-002', 'inventory_valid', 'Confirmado'),
  ('uuid-003', 'SKU-003', 'inventory_failed', 'Sin stock');


-- =====================
-- Base de datos: delivery_service
-- =====================
CREATE DATABASE IF NOT EXISTS delivery_service;
USE delivery_service;

CREATE TABLE IF NOT EXISTS deliveries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_uuid VARCHAR(100) NOT NULL UNIQUE,
    delivery_status ENUM('PENDING', 'IN_TRANSIT', 'DELIVERED') NOT NULL DEFAULT 'PENDING',
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS delivery_events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_uuid VARCHAR(100) NOT NULL,
    event_type ENUM('delivery_created', 'delivery_failed') NOT NULL,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Registros de ejemplo
INSERT INTO deliveries (order_uuid, delivery_status, address) VALUES
  ('uuid-001', 'PENDING', 'Calle 123 #45-67'),
  ('uuid-002', 'IN_TRANSIT', 'Carrera 11 #23-45'),
  ('uuid-003', 'DELIVERED', 'Av. Siempre Viva 742');

INSERT INTO delivery_events (order_uuid, event_type, message) VALUES
  ('uuid-001', 'delivery_created', 'Preparando envío'),
  ('uuid-002', 'delivery_created', 'En ruta'),
  ('uuid-003', 'delivery_created', 'Entregado con éxito');
