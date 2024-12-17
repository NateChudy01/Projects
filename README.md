# Nate Chudy Project List
### In this GitHub repository you can see different projects that I have created. They demonstrate many different skills such as java, python, SQL, and others. Below is a list of the projects and a breif descrption of what each of them do

## Projects:

### iStorage (iOS app to simulate iCloud with Xcode swift front end app and AWS connection)
The purpose of this project was to create an iOS app that can simulate the functions of iCloud. To begin, we start with a iOS app, this can be achieved through creating an Xcode project that we can then run on our iPhone allowing for an app to be downloaded. This entire app is coded in Swift. From there, we give the project upload capabilities from the iPhone. Then we create the upload method which connects to an AWS Lambda function through an API Gateway. This lambda function has a .jar file in is with java code that takes an encoded base64 image as a parameter and then uploades it to AWSS3. The java file on the lambda function and the Xcode project are attached in the project folder

### Grid-Based Flu Transmission Simulation (C++) ** Used OpenMP for parallelism and multithreading **
The purpose of this project was to create a visual simulation of a disease, in this case the Flu. There are a number of different variables you can change to see how the flu rate changed including the initial population that is infected with the flu (alpha), the chance that a person with transmit the flu to others (beta), the amount of time someone is infected with the flu (omega) and much more. In order to understand the prject more and how to use it, check out the README.md fild of the project to begin running

### DBScan Algorithm (C++) ** Used the standard thread library for parallelism and multithreading **
The purpose of this project is to create the DBScan algorithm. The DBScan algorithm is a point grouping algorithm that groups points into different clusters depending upon how far apart they are from other points. This is controlled by a number of inputs including Epsilon, and MinPts. Epsilon is the farthest distance two points can be away from each other while being in the same cluster, and MinPts is the minimum number of points a group needs to have to be considered a cluster. Look into the DBScan algorithm file and the README.md will show how to run it and the specifics of the algorithm

### Real Estate Contact Management System (Python, MySQL)
The purpose of this project was to create a contact management system for different real estate companies. This includes keeping track of employees, banks, bank accounts, customers, owners, properties and more. Using pthon to create a custome GUI with Tkinter and a database made in SQL we were able to create a database to store this information

### Gym Management (Java OOP)
This projects purpose was to create an interface for a gym management system. This system keeps track of newly created members, existing members, concession items. It also generates schedules with both group classes and one on one classes. It keeps track of members and how many times they check into the gym and gives points to members that they can use for concession items

### Investment (Java OOP)
This projects purpose when creating it was to create an interest calculator. It is able to incorporate the inputs from the user in the terminal but also if a .csv file is added in the correct format then it is able to process each line of the .csv file. You are able to calculate either the simple or the compound interest and it takes in parameters like the inital amount, two dates, interest type and rate. It uses these inputs to calculate the interest over that time period

### MaristCampus (Java OOP)
This projects purpose was to create the Marist campus using object oriented programming. Creating the classes for the campus, buildings, rooms, classrooms, offices, courses, and employees was all necessary and when implemented the campus would be a web of all of these objects. The MaristCampus.java file was used as a testing java file, creating the objects to put in the campus we had to test every function and attribute in every class we created making sure the the setters and getters as well as the additional functions worked properly

### ATM (Java OOP)
This projects purpose was to create an automatic banking system. This program simmulates the interface of an ATM allowing you to either load an account from the bank, or create an account to then be stored in the bank with its own unique ID number. This project tests the ability to make code user proof as well as link together different accounts and clients

### Warehouse Management (Java OOP)
This projects purpose was to create a warehouse ordering system using the input from .csv files. There were two input files (orders.csv and products.csv) and this projects purpose was to take each of them in and create a pick list which is sorted by the bin, shelf, and aisle of the product as well as the warehouse it was ordered from and then orderID of that specific order. This project tests your ability to get information from a .csv file as well as how you are able to sort information and untilately display it to a user for them to benefit from
