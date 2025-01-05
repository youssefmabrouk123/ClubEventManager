# Club Event Management Application

This is a full-stack application for managing events within a club. The application is built with Angular for the frontend and Spring Boot for the backend. It includes features for managing events, user authentication, and email notifications for participants.

---

## Features

### Frontend (Angular)
- User authentication with role-based access (e.g., `CLUB`, `USER`).
- Event listing and management.
- Email notifications for participants when an event is updated.
- Participants modal to view participants for a specific event.

### Backend (Spring Boot)
- RESTful APIs for authentication, event management, and participation.
- JWT-based authentication.
- Email notifications to participants upon event updates.
- Database integration with MySQL/PostgreSQL.

---

## Prerequisites

### General Requirements
- Node.js (v16+)
- Angular CLI (v16+)
- Java 17+
- Maven
- MySQL/PostgreSQL

### Libraries and Frameworks
#### Angular:
- `@angular/material`
- `@angular/forms`
- `rxjs`
- `bootstrap` (optional, for modals and styling)

#### Spring Boot:
- Spring Security (JWT)
- Spring Data JPA
- Java Mail API

---

## Installation

### Backend
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd backend
   ```
2. Configure the `application.properties` file with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/club_event
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password

   spring.mail.host=smtp.your-email-provider.com
   spring.mail.port=587
   spring.mail.username=your_email_address
   spring.mail.password=your_email_password
   ```
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Frontend
1. Navigate to the `frontend` directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Angular development server:
   ```bash
   ng serve
   ```
4. Open your browser and navigate to `http://localhost:4200`.

---

## Usage

### Authentication
- **CLUB** users can:
  - Create, update, and delete events.
  - View participants for events.
  - Send email notifications upon event updates.
- **USER** users can:
  - View events.
  - Participate in events.

### Event Management
- Create, update, and delete events from the club dashboard.
- Notify participants via email when an event is updated.

### Email Notifications
- The backend sends emails to all participants of an event when the event is updated.

---



---

## API Endpoints

### Authentication
- `POST /auth/signin`: Login endpoint.
- `POST /auth/register`: User registration.

### Events
- `GET /events/all`: Fetch all events.
- `POST /events`: Create a new event.
- `PUT /events/{id}`: Update an existing event.
- `DELETE /events/{id}`: Delete an event.

### Participation
- `GET /users/participation/all/{eventId}`: Fetch participants for an event.
- `POST /users/participation/add/{eventId}`: Add a participant to an event.

---

## Troubleshooting

### Common Issues
1. **401 Unauthorized**: Ensure the token is stored in localStorage and sent in the Authorization header.
2. **Bootstrap Modal Issues**: Ensure `bootstrap` is imported correctly in your Angular project.

### Debugging Tips
- Use browser developer tools to inspect API calls and responses.
- Check the backend logs for errors.

---

## Contribution
Feel free to fork the repository and submit pull requests for improvements or new features.

---

## License
This project is owned by Youssef Mabrouk.
---

## Acknowledgments
Special thanks to all contributors and open-source libraries used in this project.

