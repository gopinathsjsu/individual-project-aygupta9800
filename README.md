
# Individual-Project-Ayush-Gupta

Name: Ayush Gupta
SJSU-ID: 014952184
Github-Repo-Link: https://github.com/gopinathsjsu/individual-project-aygupta9800.git

## Steps to run project

1. Install maven in system:

```bash
brew install maven
```

2. add a new configuration of Application type, set main Class to Main.java and set program arguments to input csv file path. File name should not have space in name so that it can be executed.
<img width="1440" alt="Screen Shot 2021-12-05 at 6 49 05 PM" src="https://user-images.githubusercontent.com/55319952/144781082-56cea62b-418b-46d2-b0f1-393167bbf13f.png">
3. Run the Main file
console will print output result and an output.csv file will be generated for success case while errorOutput.txt file in error case in project root folder.

## Design Pattern used in Project:
1. Singleton Pattern: 
Singleton is a creational design pattern that lets you ensure that a class has only one instance while providing a global access point to this instance. I have used it in a project to have only a single instance of Cards in which we keep track of all the card numbers in order;

2. Factory Design Pattern:
Factory Method is a creational design pattern that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created.
In the project, it is used to have different subclasses for each category: LuxuryItem, EssentialItem, MscItem 

3. Chain of Responsibility Pattern:
Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.
Before we print the output of the order, we check the availability of stock in the Inventory class and then check the Category limit set.

# Screenshots
<img width="1087" alt="Screen Shot 2021-12-05 at 6 49 57 PM" src="https://user-images.githubusercontent.com/55319952/144780166-e5a12569-9a7e-4b0b-a9de-309737cf7165.png">
<img width="696" alt="Screen Shot 2021-12-05 at 6 51 54 PM" src="https://user-images.githubusercontent.com/55319952/144780178-9dae5406-1582-4a89-8290-1d7c34d64a4b.png">
<img width="1041" alt="Screen Shot 2021-12-05 at 6 53 50 PM" src="https://user-images.githubusercontent.com/55319952/144780314-3a58885b-5be1-4c9d-9d2f-f228db1130cc.png">
<img width="532" alt="Screen Shot 2021-12-05 at 6 56 03 PM" src="https://user-images.githubusercontent.com/55319952/144780328-82565135-2fae-44a6-a1c4-f97448a570e2.png">
<img width="1008" alt="Screen Shot 2021-12-05 at 6 57 13 PM" src="https://user-images.githubusercontent.com/55319952/144780343-4c147506-e5fd-4c6c-a2da-fb1d591b5e28.png">

<img width="423" alt="Screen Shot 2021-12-05 at 6 57 38 PM" src="https://user-images.githubusercontent.com/55319952/144780355-7073ea37-0b3c-431d-bddb-c57951bfe9da.png">

card number ss:


<img width="423" alt="Screen Shot 2021-12-05 at 10 05 37 PM" src="https://user-images.githubusercontent.com/55319952/144795942-04ac464f-45e4-4e6e-bcc2-5eab46a53bbd.png">

# Class Diagram
![ClassDiagram drawio](https://user-images.githubusercontent.com/55319952/144783173-cf09dc31-96c4-4387-b7f8-d3295b2617db.png)


## Problem Statement
Design and implement a Java application for the following requirements. You may choose up to 3 design patterns. Include a class diagram for your design. All submissions should be committed to the assigned Github repo. Please include a README file with instructions on how to build and run your application.

Requirements:

The application should maintain an internal, static database (inventory of stock) (this may be developed using HashMaps and/or other built-in Java Data structures). This means once we re-run the program, the changes to the data would not persist. We will provide the data that has to be maintained. The data will contain the following tables and fields: (Sample input file and sample data set for the inventory would be provided separately) Table 1: Items Category (Essentials, Luxury, Miscellaneous) Item for each category (Essentials - Clothes, soap, milk; Luxury - perfume, chocolates; Misc - Bedsheets, footwear) The available Quantity of each item Price of each item Table 2: Cards Card Numbers
Input CSV file will contain an order including Items, Quantity needed, and the payment card number.
Input file should be processed as follows: Validate if the requested quantity for each item is permissible. For example, if the request is to order 3 soaps, check the database if we have at least 3 soaps in our inventory. There will be a cap on the quantity of each category that can be ordered in one single order. For example, restrict Essentials to a maximum of 3, Luxury to 4, and Misc to 6. (This will be configured beforehand) In case it is an incorrect request, generate and output TXT file with message "Please correct quantities." and include the items with incorrect quantities After this validation, if the cart is valid, calculate prices for the cart. Take the card number of the user and if it is not present in DB add it. Output the CSV list with the total amount paid.
