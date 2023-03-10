# Invoice Management Assignment (React + Kotlin Spring Boot)
This is the Backend component for the assignment. For the Frontend component, please refer to the Frontend Repo as follows: https://github.com/LeongCheeWah1991/InvoiceManagementReactTypescript

This project (Invoice Management) is a Spring boot Application that consists of the following features:

1. Retrieving list of invoices
2. Uploading list of invoices

# Spring Boot Version and Dependencies 
Spring Boot version: 2.7.9-SNAPSHOT
Java version: 17
Kotlin version: 1.6.21

Dependencies used:
- spring-boot-starter
- spring-boot-starter-data-jpa
- spring-boot-starter-web-services
- spring-boot-starter-validation
- spring-boot-starter-test
- spring-boot-starter-log4j2
- junit-jupiter-engine
- jackson-module-kotlin
- kotlin-reflect
- kotlin-stdlib-jdk8
- commons-csv
- h2

# Assumptions:
- Invoice can contain multiple same items
- Uploading invoices with existing Invoice No will append to existing Invoice List

# Cloning down the repository and setting up project
- Git clone the repository: e.g. git clone https://github.com/LeongCheeWah1991/InvoiceManagementKotlinSpringBoot.git
- Open Project using desired IDE
- Using IntelliJ IDEA
    - After cloning project, navigate to \InvoiceManagementKotlinSpringBoot\invoicemanagement\
    - Right-Click -> Open Folder as IntelliJ IDEA Community Edition Project 
    * (Naming may differ depends on IntelliJ IDEA installed)
    - After IntelliJ loads the project, load the dependencies required
        - Right-Click "invoicemanagement" -> Maven -> Reload Project

# Running the application:
Using IntelliJ IDEA
- locate the following class: InvoicemanagementApplication.kt
- Right-Click InvoicemanagementApplication.kt -> Run 'InvoicemanagementApp...'
- Verify on console that application is running

# Verifying and querying H2 DB Instance
- In application.properties, "spring.h2.console.enabled" is set to true, this enables the H2 console.
- After application has started, verify H2 instance is running through the H2 console
- H2 console can be accessed at: http://localhost:8080/h2-console
- Use the following details:
    - JDBC URL: jdbc:h2:mem:invoiceDB
    - User Name: sa
    - Password: password
- Click "Connect"
- On left hand side, should see "INVOICE" table
- Click on "INVOICE" table OR Enter "SELECT * FROM INVOICE" and Click Run
- Table should return empty results

# Testing the application: (Using Postman)
- At Postman, import the collection (InvoiceManagement.postman_collection.json)
- Test the respective service endpoints (getInvoices & uploadInvoices). 
    - For testing upload invoice, create a csv file and select as file in postman (form-data)
