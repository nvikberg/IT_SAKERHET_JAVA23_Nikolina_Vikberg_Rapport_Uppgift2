# IT Security End Project
## Timecapsule with hashed login, encrypted and decrypted messages
### Nikolina Vikberg, Grit Academy Malmlö

### Background
This system allows for the creation and storage of secured time capsules with encrypted messages that are only accessible after logging in.

### Features
- Register Account: Create an account with email and hashed password.
- Log In: Use JWT for authentication.
- Create Time Capsule: Encrypt and save messages (AES).
- Read Time Capsule: Retrieve and decrypt messages after logging in.<br>
### Technology
- Server: Spring Boot
- Database: MySQL
- Client: Web application (JavaScript)<br>
### Security
- Passwords are hashed with bcrypt and messages are encrypted with AES.
- JWT is used to protect user data.

Log in
<img width="1265" alt="Skärmbild 2024-10-11 152957" src="https://github.com/user-attachments/assets/21a3cfe7-e556-4a61-9184-3e59efcc3c52">


Register
<img width="1278" alt="Skärmbild 2024-10-11 152943" src="https://github.com/user-attachments/assets/329ff3a7-2389-4c1c-ac44-e427c9df2b71">


Showing encrypted message getting sent from user log in page
<img width="728" alt="encrypted message" src="https://github.com/user-attachments/assets/fcade991-a7c0-4af0-9faf-32de838cab24">


Hashed password with bcrypt
<img width="488" alt="hashedpw" src="https://github.com/user-attachments/assets/919795f0-6748-4a2c-a47a-7b197d0e0f59">


Encryption/decryption code 
<img width="572" alt="encryptcode" src="https://github.com/user-attachments/assets/ff6be573-9919-4e66-9c11-7b5423b90e90">


