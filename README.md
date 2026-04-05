# Repository REST Resource - User Management API

A Spring Boot REST API application for managing users and their addresses, built with Spring Data REST for automatic CRUD operations and HATEOAS support.

## 🚀 Features

- **RESTful API** with automatic CRUD operations via Spring Data REST
- **User Management** - Create, read, update, delete users
- **Address Management** - Manage multiple addresses per user
- **H2 In-Memory Database** - Perfect for development and testing
- **HATEOAS Support** - Hypermedia-driven REST API
- **Lombok Integration** - Reduced boilerplate code
- **Log4j2 Logging** - Comprehensive logging configuration
- **Java 21** - Modern Java features and performance

## 🏗️ Architecture

### Entity Relationships
```
User (1) ←→ (*) Address
```
- One user can have multiple addresses
- Bidirectional relationship with cascade operations
- Automatic foreign key management

### Technology Stack
- **Spring Boot 4.0.5** - Application framework
- **Spring Data JPA** - Data persistence layer
- **Spring Data REST** - Automatic REST API generation
- **H2 Database** - In-memory database
- **Lombok** - Code generation
- **Log4j2** - Logging framework
- **Gradle** - Build tool
- **Java 21** - Programming language

## 📋 Prerequisites

- **Java 21** or higher
- **Gradle 7.0+** (or use included wrapper)
- **Git** (for cloning)

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <your-repository-url>
cd RepositoryRestResource
```

### 2. Build the Project
```bash
.\gradlew.bat clean build
```

### 3. Run the Application
```bash
.\gradlew.bat bootRun
```

The application will start on `http://localhost:8080`

## 🎯 Usage

### API Endpoints

#### Users API
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/users` | Get all users |
| `GET` | `/users/{id}` | Get user by ID |
| `POST` | `/users` | Create new user |
| `PUT` | `/users/{id}` | Update user |
| `PATCH` | `/users/{id}` | Partial update user |
| `DELETE` | `/users/{id}` | Delete user |
| `GET` | `/users/search/findByFirstName?firstName={name}` | Find user by first name |

#### Addresses API
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/addresses` | Get all addresses |
| `GET` | `/addresses/{id}` | Get address by ID |
| `POST` | `/addresses` | Create new address |
| `PUT` | `/addresses/{id}` | Update address |
| `DELETE` | `/addresses/{id}` | Delete address |

### 📝 API Examples

#### Create a User with Address
```bash
curl -X POST "http://localhost:8080/users" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "age": 30,
    "address": [
      {
        "street": "123 Main St",
        "city": "Springfield",
        "state": "IL",
        "zipCode": "62701",
        "country": "USA"
      }
    ]
  }'
```

#### Get All Users
```bash
curl -X GET "http://localhost:8080/users"
```

#### Find User by First Name
```bash
curl -X GET "http://localhost:8080/users/search/findByFirstName?firstName=John"
```

#### Create Multiple Addresses for a User
```bash
curl -X POST "http://localhost:8080/users" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Smith",
    "age": 25,
    "address": [
      {
        "street": "456 Oak Ave",
        "city": "Austin",
        "state": "TX", 
        "zipCode": "73301",
        "country": "USA"
      },
      {
        "street": "789 Pine St",
        "city": "Dallas",
        "state": "TX",
        "zipCode": "75001", 
        "country": "USA"
      }
    ]
  }'
```

## 🗄️ Database

### H2 Console Access
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(leave empty)*

### Database Schema

#### Users Table
| Column | Type | Description |
|--------|------|-------------|
| `id` | BIGINT | Primary key (auto-generated) |
| `first_name` | VARCHAR | User's first name |
| `last_name` | VARCHAR | User's last name |
| `age` | INTEGER | User's age |

#### Address Table
| Column | Type | Description |
|--------|------|-------------|
| `id` | BIGINT | Primary key (auto-generated) |
| `street` | VARCHAR | Street address |
| `city` | VARCHAR | City |
| `state` | VARCHAR | State/Province |
| `zip_code` | VARCHAR | ZIP/Postal code |
| `country` | VARCHAR | Country |
| `user_id` | BIGINT | Foreign key to users table |

## ⚙️ Configuration

### Application Properties (YAML)
```yaml
spring:
  application:
    name: RepositoryRestResource
  
  # H2 Database Configuration
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  
  # JPA/Hibernate Configuration
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  
  # H2 Console
  h2:
    console:
      enabled: true
      path: /h2-console
```

### Sample Data
The application automatically creates sample users on startup:
- **John Doe** (age 30) - 123 Main St, Springfield, IL
- **Jane Smith** (age 25) - 456 Oak Ave, Shelbyville, TN

## 🔧 Development

### Project Structure
```
src/
├── main/
│   ├── java/com/example/repositoryrestresource/
│   │   ├── entity/
│   │   │   ├── User.java          # User entity
│   │   │   └── Address.java       # Address entity
│   │   ├── repository/
│   │   │   ├── UserRepository.java    # User repository
│   │   │   └── AddressRepository.java # Address repository
│   │   ├── DataInitializer.java   # Sample data loader
│   │   └── RepositoryRestResourceApplication.java
│   └── resources/
│       └── application.yml        # Configuration
└── test/
    └── java/com/example/repositoryrestresource/
        └── RepositoryRestResourceApplicationTests.java
```

### Building
```bash
# Clean and build
.\gradlew.bat clean build

# Run tests
.\gradlew.bat test

# Build without tests
.\gradlew.bat build -x test
```

### Running in Development
```bash
# Run with live reload
.\gradlew.bat bootRun

# Run with debug
.\gradlew.bat bootRun --debug-jvm
```

## 📊 HATEOAS Response Format

Spring Data REST automatically provides HATEOAS links in responses:

```json
{
  "_embedded": {
    "users": [
      {
        "firstName": "John",
        "lastName": "Doe", 
        "age": 30,
        "address": [
          {
            "id": 1,
            "street": "123 Main St",
            "city": "Springfield",
            "state": "IL",
            "zipCode": "62701",
            "country": "USA"
          }
        ],
        "_links": {
          "self": {
            "href": "http://localhost:8080/users/1"
          },
          "user": {
            "href": "http://localhost:8080/users/1"
          },
          "address": {
            "href": "http://localhost:8080/users/1/address"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/users"
    },
    "profile": {
      "href": "http://localhost:8080/profile/users"
    },
    "search": {
      "href": "http://localhost:8080/users/search"
    }
  }
}
```

## 🐛 Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   ```bash
   # Change port in application.yml
   server:
     port: 8081
   ```

2. **Java version issues**
   ```bash
   # Check Java version
   java -version
   # Should be Java 21 or higher
   ```

3. **Database connection issues**
   - Check H2 console at http://localhost:8080/h2-console
   - Verify JDBC URL: `jdbc:h2:mem:testdb`

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📞 Support

For support and questions:
- Create an issue in the repository
- Check the H2 console for database inspection
- Review application logs for detailed error information
