# 2024010016 - Shodh-a-Code Contest Platform

## Overview
The Shodh-a-Code platform is a full-stack, Dockerized web application designed to simulate a coding contest environment. It allows users to join contests, view problems, submit code, and see live leaderboard updates. The project demonstrates the integration of backend development, frontend interaction, and container-based execution orchestration.

## Objective
The goal was to build a complete coding contest prototype combining a React-Next.js frontend, a Spring Boot backend, and PostgreSQL for persistent storage, all containerized using Docker. It also includes a mock code judging service that mimics real-time code execution and result evaluation.

## Technologies Used
Frontend
- Next.js 14 (React 18 Framework)
- Tailwind CSS for design
- Axios for API communication

Backend
- Java 17 with Spring Boot 3
- Spring Data JPA for database operations
- PostgreSQL as the relational database

Infrastructure
- Docker and Docker Compose for container orchestration

## System Architecture
1. Frontend (Next.js): Displays contest data, problems, submission status, and leaderboard. It interacts with backend APIs every few seconds to update user submission results.
2. Backend (Spring Boot): Exposes a REST API to manage contests, problems, submissions, and users. It includes a mock CodeJudgeService that simulates execution.
3. Database (PostgreSQL): Stores persistent contest data such as contest details, participant submissions, and user information.
4. Docker Compose: Runs all services (frontend, backend, and database) in isolated containers with one-command deployment.

## Key Features
1. Contest Management – Supports multiple contests and preloaded problems in the database.
2. Mock Code Judging – Simulates code execution asynchronously with real-time polling from the client side.
3. Live Leaderboard – Updates periodically, showing the latest submission results.
4. Simple Join Flow – Users can join by entering a contest ID and username, then start coding immediately.
5. Docker Deployment – A single command using `docker-compose up --build` launches the entire stack.

## Implementation Details

### Backend Implementation
The backend is organized into clear layers following industry standards:
- Model Layer: Entities created for User, Contest, Problem, and Submission.
- Repository Layer: Interfaces extending JPA repositories for easy database access.
- Service Layer: Business logic handled through dedicated services (ContestService, SubmissionService, and JudgeService).
- Controller Layer: REST API endpoints for contest data retrieval and submission creation.

### Code Judging Logic
- When a submission is made, the system saves it to the database with a Pending status.
- The CodeJudgingService mimics Docker execution using a background thread and changes the status based on validation results.
- Output comparison decides whether a submission is “Accepted” or “Wrong Answer.”
- All temporary files and data are cleared after evaluation to avoid leaks.

This mock setup is efficient for demonstration while being safe from untrusted code execution.

### Error Handling
The backend includes validation for missing contests and submissions, returning clear HTTP responses. Logging is used for debugging and stability validation.

### Frontend Implementation
The frontend is built with Next.js and designed for simplicity and usability.
- Join Page: Collects the contest ID and username.
- Contest Page: Displays contest details, problems, submission area, and leaderboard.
- Code Editor: Simplified text area for code input.
- Status Polling: Automatically checks submission results every 2 seconds.
- Leaderboard Polling: Refreshes live user rankings every 15 seconds.

To enhance stability, null checks were added to prevent crashes when contest data is missing or undefined, and safe rendering ensures the app handles incomplete responses gracefully.

## Docker Setup
### Services Defined
- db: PostgreSQL database for persistent storage.
- backend: Builds the Spring Boot JAR and connects to the PostgreSQL service.
- frontend: Builds the Next.js app and runs on port 3000.

### Running the Application
From the project root directory: