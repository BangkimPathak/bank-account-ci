# Bank Account Operations - Continuous Integration Pipeline

[![Java Maven CI](https://github.com/BangkimPathak/bank-account-ci/actions/workflows/ci.yml/badge.svg)](https://github.com/BangkimPathak/bank-account-ci/actions/workflows/ci.yml)

## Academic Information
- **Student Name**: Bangkim Pathak
- **USN**: 24BTRO006
- **Programme**: CSE (AI-Driven DevOps)
- **Course**: 24CSE538 - Cloud DevOps (Unit III)
- **Institution**: Jain (Deemed-to-be University), Faculty of Engineering and Technology

---

## Project Overview
This repository contains a robust Java application demonstrating core **Bank Account Operations** along with automated build and test validation via **GitHub Actions CI**.

### Features & Operations
1. `deposit(double amount)`: Safely credits funds to the account with positive amount validation.
2. `withdraw(double amount)`: Validates available funds and processes debits without permitting overdrafts.
3. `calculateCompoundInterest(double annualRate, int years)`: Calculates projected compounded balance using standard mathematical formulas.

---

## Tech Stack
- **Language**: Java 17 (OpenJDK / Eclipse Temurin)
- **Build & Test Automation**: Apache Maven 3.9+
- **Unit Testing Framework**: JUnit Jupiter 5.11.4 & Maven Surefire Plugin
- **Continuous Integration**: GitHub Actions (`ubuntu-latest` runner)

---

## Local Build & Test Execution
To build the application and run all 10 automated unit test cases locally:

```bash
mvn clean verify
```

To run the main application:
```bash
mvn compile exec:java -Dexec.mainClass="com.example.bank.BankAccount"
```
