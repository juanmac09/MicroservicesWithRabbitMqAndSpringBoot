# 🛠️ Sistema Distribuido de Pedidos – Microservicios con Spring Boot + RabbitMQ + Docker

## 🚀 Requisitos para ejecutar el proyecto

Antes de ejecutar este sistema distribuido basado en microservicios, asegúrate de tener instalado lo siguiente:

- 🐳 **Docker** y **Docker Compose**
- ☕ **Java 21** (solo si deseas ejecutar los servicios localmente sin Docker)
- 🧰 **Maven** (opcional, si deseas compilar los servicios manualmente)

> ⚠️ **Recomendación**: Se recomienda usar Docker para facilitar la ejecución en cualquier entorno.

---

## 💪 Pasos para correr el proyecto

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/tu-repo.git
cd tu-repo
```

---

### 2️⃣ Crear el archivo `.env`

Antes de iniciar los servicios, crea un archivo `.env` en la raíz del proyecto:

```bash
cp .env.example .env
```

Puedes editar los valores si deseas personalizar puertos, contraseñas u otras configuraciones.

---

### 3️⃣ Verificar la estructura del proyecto

La estructura de carpetas debe verse así:

```
.
├── order-service/
├── inventory-service/
├── delivery-service/
├── docker-compose.yml
├── .env
├── .env.example
├── README.md
```

Cada carpeta representa un microservicio independiente, basado en **Spring Boot**, con integración a **RabbitMQ** y su propia base de datos **MySQL**.

---

### 4️⃣ Levantar todos los servicios

Ejecuta el siguiente comando para construir y levantar los servicios:

```bash
docker-compose --env-file .env up --build
```

Este comando:

- 🔨 Compila los microservicios
- 🚀 Levanta los servicios en los siguientes puertos:
  - **Order Service:** `http://localhost:8080`
  - **Inventory Service:** `http://localhost:8081`
  - **Delivery Service:** `http://localhost:8082`
- 🐇 Inicia **RabbitMQ**:
  - Broker en `localhost:5672`
  - UI en `http://localhost:15672`
- 🗄️ Crea tres instancias de **MySQL** (una por servicio)

---

### 5️⃣ Probar los endpoints con Swagger

Una vez que todos los servicios estén corriendo, accede a sus interfaces de documentación Swagger UI:

- 📟 **Order Service:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- 📦 **Inventory Service:** [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- 🚚 **Delivery Service:** [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

Desde allí puedes realizar pruebas interactivas a cada API REST.

---

## 🗃️ Registros por defecto precargados

Al levantar el sistema, cada base de datos carga datos de prueba automáticamente. Esto permite realizar pruebas sin necesidad de insertar registros manualmente:

### 📟 Order Service

```sql
INSERT INTO orders (order_uuid, status) VALUES 
  ('uuid-001', 'CREATED'),
  ('uuid-002', 'INVENTORY_VALID'),
  ('uuid-003', 'DELIVERY_CREATED');
```

---

### 📦 Inventory Service

```sql
INSERT INTO products (item_sku, item_name, quantity) VALUES
  ('SKU-001', 'Producto A', 10),
  ('SKU-002', 'Producto B', 3),
  ('SKU-003', 'Producto C', 0);
```

---

### 🚚 Delivery Service

```sql
INSERT INTO deliveries (order_uuid, delivery_status, address) VALUES
  ('uuid-001', 'PENDING', 'Calle 123 #45-67'),
  ('uuid-002', 'IN_TRANSIT', 'Carrera 11 #23-45'),
  ('uuid-003', 'DELIVERED', 'Av. Siempre Viva 742');
```

---

## ✅ ¡Listo!

Ya tienes el sistema distribuido corriendo con Docker y listo para realizar pruebas completas del flujo de pedidos, inventario y entregas 🚚📦📟

---
