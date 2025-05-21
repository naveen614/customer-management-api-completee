# Customer Management API

A Spring Boot RESTful API to manage customer data and calculate membership tiers based on annual spend.

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 In-Memory Database
- Spring Validation
- Springdoc OpenAPI (Swagger)
- JUnit 5 + Mockito
- Gradle

---

## 🚀 How to Build and Run

```bash
# Build the application
./gradlew clean build

# Run the application
./gradlew bootRun  OR  ./gradlew run


```

Application will start at: `http://localhost:8080`

---

## 🔁 API Endpoints

### ➕ Create a Customer
**POST** `/customers`

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "annualSpend": 1200,
  "lastPurchaseDate": "2024-05-01T10:00:00"
}
```

### 🔍 Get Customer by ID
**GET** `/customers/{id}`

### 🔍 Get Customer by Name
**GET** `/customers?name=John Doe`

### 🔍 Get Customer by Email
**GET** `/customers?email=john@example.com`

### ✏️ Update Customer
**PUT** `/customers/{id}`

```json
{
  "name": "Updated Name",
  "email": "updated@example.com",
  "annualSpend": 9500,
  "lastPurchaseDate": "2024-06-01T12:30:00"
}
```

### ❌ Delete Customer
**DELETE** `/customers/{id}`

---

## 🧮 Tier Calculation Logic

- **Silver**: `annualSpend < 1000`
- **Gold**: `annualSpend >= 1000` and last purchase within **12 months**
- **Platinum**: `annualSpend >= 10000` and last purchase within **6 months**

Tier is **not stored** in the DB and is dynamically computed on every fetch.

---

## 📘 Access H2 Database Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: *(empty)*

---

## 📄 API Documentation (Swagger UI)

- URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Raw Spec: [http://localhost:8080/openapi.yaml](http://localhost:8080/openapi.yaml)

---

