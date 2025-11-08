# Library Management System (LMS) - Backend

A robust and scalable Library Management System built with Spring Boot that helps manage books, members, and library operations efficiently.

## 🚀 Features

- **Book Management**
  - Add, update, and remove books from the library
  - Track book availability status
  - Categorize books by different genres/categories
  - Search and filter books

- **Member Management**
  - Register new members
  - Update member information
  - Track member status
  - Manage member profiles

- **Issue & Return System**
  - Issue books to members
  - Process book returns
  - Track due dates
  - Manage issue records

- **Reporting System**
  - Generate various reports
  - Track book circulation
  - View category-wise book distribution
  - Monitor member activities

## 🛠️ Tech Stack

- **Framework:** Spring Boot 3.5.4
- **Language:** Java 21
- **Database:** SQL (configured via DbConfig)
- **API Documentation:** Available via Spring Documentation
- **Architecture:** MVC Pattern with DAO layer

## 📦 Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── lms/
│               └── lms_backend/
│                   ├── config/         # Configuration files
│                   ├── constant/       # Enum constants
│                   ├── controller/     # REST API endpoints
│                   ├── dao/            # Data Access Objects
│                   ├── exception/      # Custom exceptions
│                   ├── model/          # Data models
│                   ├── service/        # Business logic
│                   └── utilities/      # Utility classes
```

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven
- SQL Database

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/lms-backend.git
   ```

2. Navigate to the project directory:
   ```bash
   cd lms-backend
   ```

3. Configure the database:
   - Update `application.properties` with your database credentials
   - Run the SQL scripts from `lms_updated_queries.sql`

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## 🔐 API Endpoints

### Book Management
- `GET /api/books` - Get all books
- `POST /api/books` - Add a new book
- `PUT /api/books/{id}` - Update book details
- `DELETE /api/books/{id}` - Remove a book

### Member Management
- `GET /api/members` - Get all members
- `POST /api/members` - Register new member
- `PUT /api/members/{id}` - Update member details
- `DELETE /api/members/{id}` - Remove a member

### Issue & Return
- `POST /api/issue` - Issue a book
- `POST /api/return` - Return a book
- `GET /api/issues` - Get issue records

### Reports
- Various reporting endpoints available through ReportsController

## 🔍 Key Features Implementation

### Book Status Management
- Uses `BookAvailability` enum for tracking book status
- Implements status transitions through `BookService`

### Member Management
- Tracks member status using `MemberStatus` enum
- Handles gender classification via `MemberGender` enum

### Issue Record Tracking
- Manages issue states through `IssueRecordStatus`
- Implements validation and business rules in service layer

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👥 Authors

- Rohit Varma Datla - [visaal1582892](https://github.com/visaal1582892)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Contributors and reviewers
