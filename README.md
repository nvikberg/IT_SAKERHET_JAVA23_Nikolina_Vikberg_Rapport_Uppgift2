### IT Security End Project
## Timecapsule with hashed login and encrypted messages
# Nikolina Vikberg, Grit Academy Malmlö

Background
This system allows for the creation and storage of secured time capsules with encrypted messages that are only accessible after logging in.

Features
- Register Account: Create an account with email and hashed password.
- Log In: Use JWT for authentication.
- Create Time Capsule: Encrypt and save messages (AES).
- Read Time Capsule: Retrieve and decrypt messages after logging in.<br>
Technology
- Server: Spring Boot
- Database: MySQL
- Client: Web application (JavaScript)<br>
Security
- Passwords are hashed with bcrypt and messages are encrypted with AES.
- JWT is used to protect user data.
