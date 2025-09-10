💰 Expense Tracker with Reports

A Spring Boot microservices-based Expense Tracker that allows users to manage daily expenses, set monthly budgets, and generate PDF/Excel reports. The system also integrates Kafka for notifications and uses MySQL for persistence. Frontend is built with React + HTML/CSS for basic interaction.

🛠 Features

User Authentication & Authorization (JWT)

Expense Management

Add, view, update, delete expenses

Categorize expenses

Budget Management

Set monthly budgets

Track spending vs budget

Reports

Generate monthly PDF/Excel reports

Scheduled job for automated monthly report generation

Event-Driven Notifications (Optional)

Kafka-based notifications when budgets are exceeded

Frontend

React + HTML/CSS dashboard for managing expenses, budgets, and reports

📦 Microservices Architecture
Service	Responsibilities
User Service	Registration, login, JWT authentication
Expense Service	Expense CRUD, category management
Budget Service	Monthly budgets, budget tracking
Report Service	PDF/Excel report generation, scheduled jobs
Notification Service (Optional)	Kafka consumer for notifications (budget exceeded, new expense)
🗂 Tech Stack

Backend: Spring Boot, Spring Data JPA, Spring Security (JWT), MySQL

Frontend: React, HTML, CSS, Axios, Chart.js (optional)

Messaging: Apache Kafka

Scheduling: Spring @Scheduled

Reporting: Apache POI (Excel), iText/OpenPDF (PDF)

Build & Deployment: Maven, Docker (optional)

🔧 Setup Instructions
1. Clone the repository
git clone https://github.com/yourusername/expense-tracker.git
cd expense-tracker

2. Backend Setup

Install MySQL and create databases for each service (userdb, expensedb, budgetdb)

Update application.properties / application.yml in each microservice with your MySQL credentials

Start Kafka broker (if using notifications)

Run each microservice:

cd user-service
mvn spring-boot:run

cd expense-service
mvn spring-boot:run

cd budget-service
mvn spring-boot:run

cd report-service
mvn spring-boot:run

cd notification-service (optional)
mvn spring-boot:run

3. Frontend Setup
cd frontend
npm install
npm start


Frontend runs on http://localhost:3000

Connects to backend microservices via REST APIs

🚀 Usage

Register a new user

Log in and save the JWT token

Add expense entries and categories

Set monthly budgets

View dashboards and generate reports

(Optional) Receive Kafka notifications for budget alerts

📈 Screenshots (Optional)

(Add screenshots of your dashboard, expense table, charts, or reports here)

⚡ Future Enhancements

Dockerize all microservices for easy deployment

Add OAuth2 login (Google, Facebook)

Mobile-responsive frontend using Tailwind

Real-time notifications with WebSocket
