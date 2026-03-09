# spring-boot-dispatcher
Warehouse Management System (WMS) Integration Layer for Odoo 18

---
# 1. Overview
The **WMS Service Layer** acts as a middleware between the **Odoo 18 
ERP backend** and the **Web / Mobile Warehouse Applications**.

Its primary purpose is to:
* Provide a scalable **API layer** for warehouse operations
* Isolate business logic from Odoo
* Improve performance and security
* Support mobile barcode-driven warehouse workflows
* Enable advanced WMS features not available natively in Odoo

The service layer exposes REST APIs used by:
* Web Admin Application
* Mobile Warehouse Application
* External Integration Systems

```
                Mobile App / Web App
                         |
                    API Gateway
                         |
                WMS Service Layer
                         |
                  Odoo Integration
                         |
                      Odoo 18
                         |
                     PostgreSQL
```

---
# 2. Key Responsibilities
The WMS Service Layer performs the following functions:

### 1. API Gateway
Provides REST endpoints for:
* Warehouse configuration
* Location management
* Product master management
* Inventory operations
* Warehouse tasks

### 2. Business Logic Engine
Implements WMS-specific logic such as:
* Directed putaway
* Pick path optimization
* Inventory allocation
* Replenishment
* Task generation

### 3. Integration Layer
Handles communication with Odoo via:
* JSON-RPC
* Custom REST APIs
* Webhooks
* Scheduled synchronization

### 4. Data Validation
Ensures:
* Location capacity rules
* Hazard restrictions
* UOM conversions
* Inventory consistency

### 5. Performance Optimization
Uses:
* Redis caching
* Async processing
* Event-driven updates

---

# 5. Core Modules
The WMS service layer supports the following modules.

### Warehouse Management
* Create warehouse
* Configure docks
* Configure cross-docking zones
* Configure transit locations

### Location Management
Supports **hierarchical warehouse locations**.

Example hierarchy:
```
Warehouse
  └ Zone
      └ Aisle
          └ Rack
              └ Level
                  └ Bin
```

Location codes example:

```
WH01-A01-R02-L03-B04
```

Capabilities:
* Bulk location generation
* Bin capacity rules
* Hazard restrictions
* Temperature zones

---

### Product Master Management
Manages warehouse product attributes.

Features:
* SKU management
* Product variants
* Multiple barcodes
* UOM conversions
* Hazard classification
* ABC classification
* Shelf life rules

---

### Inventory Management
Handles real-time inventory tracking.
Features:
* Inventory by location
* Lot & batch tracking
* Serial tracking
* Inventory adjustments
* Inventory reservations

---

### Receiving
Supports inbound warehouse operations.

Typical workflow:
```
ASN → Receiving → Quality Check → Putaway
```

Capabilities:
* ASN scanning
* Barcode validation
* Lot capture
* Putaway task creation

---

### Putaway
Automatically determines optimal storage location.
Putaway rules consider:
* Product dimensions
* Bin capacity
* Hazard compatibility
* ABC velocity

---

### Picking
Supports multiple picking strategies.
Types:
* Single order picking
* Batch picking
* Wave picking
* Zone picking

---

### Packing
Handles packing operations before shipment.
Features:
* Package creation
* Weight verification
* Label printing
* Shipment preparation

---

### Shipping
Manages outbound shipments.

Steps:
```
Picking → Packing → Shipment → Dispatch
```

Features:
* Shipment confirmation
* Carrier integration
* Dispatch tracking

---
### Cycle Counting
Supports inventory auditing.

Types:
* Random counting
* Scheduled counting
* ABC-based counting

---

# 6. API Design Principles
The service layer exposes **REST APIs** following standard principles.

### Base URL
```
/api/v1
```

### Example Endpoint
```
POST /api/v1/products
GET  /api/v1/products/{id}
PUT  /api/v1/products/{id}
DELETE /api/v1/products/{id}
```

### Response Format
```
{
  "status": "success",
  "message": "Product created successfully",
  "data": {}
}
```

---

# 7. Authentication & Security
Security is implemented using **JWT-based authentication**.

### Authentication Flow
```
Client → Login API
      → Receive JWT token
      → Use token for API calls
```

Example header:
```
Authorization: Bearer <JWT_TOKEN>
```

Role-based access control (RBAC) is implemented.
Roles:
* Admin
* Warehouse Manager
* Supervisor
* Operator
* Auditor

---

# 8. Odoo Integration

The WMS service layer integrates with Odoo for:

* Product synchronization
* Inventory updates
* Purchase order receiving
* Sales order fulfillment

Integration methods:

### JSON-RPC

```
/jsonrpc
```

### Custom REST APIs

```
/odoo/api/products
/odoo/api/locations
/odoo/api/inventory
```

### Webhooks

Used for:

* Order creation
* Stock changes
* Product updates

---

# 9. Event Driven Architecture

The WMS supports event-based processing.

Events include:

```
InventoryUpdated
PutawayTaskCreated
PickTaskCreated
ShipmentDispatched
CycleCountCompleted
```

Message brokers supported:

* Kafka
* RabbitMQ

---

# 10. Performance Strategy

Warehouse systems require **very fast response times**.

Optimizations include:

### Caching

Redis used for:

* Product master
* Location hierarchy
* Inventory snapshot

### Bulk APIs

Used for:

* Barcode scanning
* Inventory adjustments
* Task creation

### Async Processing

Background jobs for:

* Inventory reconciliation
* Reporting
* Sync with Odoo

---

# 11. Logging & Monitoring

The system includes centralized logging.

Tools supported:

* ELK Stack
* Grafana
* Prometheus

Logs include:

* API requests
* Integration calls
* Inventory transactions

---

# 12. Deployment

The WMS service can run using Docker containers.

Example:

```
docker build -t wms-service .
docker run -p 8080:8080 wms-service
```

Production environments may use:

* Kubernetes
* AWS ECS
* Azure AKS

---

# 13. Future Enhancements

Planned enterprise features include:

* AI-driven slotting optimization
* Labor management
* Dock scheduling
* Yard management
* Robotics integration
* Voice picking
* Digital twin warehouse simulation

---

# 14. Contribution Guidelines

Before contributing:

1. Follow coding standards
2. Write unit tests
3. Update API documentation
4. Ensure backward compatibility

---

# 15. License

This project is proprietary and intended for internal warehouse management system development.

---


24. Cycle Counting APIs

Total: 6 APIs

Method	Endpoint
POST	/cycle-count/create
POST	/cycle-count/start
POST	/cycle-count/scan

POST | /cycle-count/confirm
GET | /cycle-count/tasks
GET | /cycle-count/history

25. Reporting APIs

Total: 8 APIs

Method	Endpoint
GET	/reports/inventory
GET	/reports/stock-aging
GET	/reports/warehouse-utilization
GET	/reports/product-velocity
GET	/reports/abc-analysis
GET	/reports/reorder-alerts
GET	/reports/receiving
GET	/reports/shipping
