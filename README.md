
Individual-Project-Ayush-Gupta
Name: Ayush Gupta SJSU-ID: 014952184 Github-Repo-Link: https://github.com/gopinathsjsu/individual-project-aygupta9800.git

Steps to run project
Install maven in system:

brew install maven
Usage
Design Pattern used in Project:
Singleton Pattern: Singleton is a creational design pattern that lets you ensure that a class has only one instance while providing a global access point to this instance. I have used it in a project to have an only a single instance of Cards in which we keep track of all the card numbers in order;

Factory Design Pattern: Factory Method is a creational design pattern that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created. In the project, it is used to have different subclasses for each category: LuxuryItem, EssentialItem, MscItem

Chain of Responsibility Pattern: Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain. Before we prints the output of order, we check the availability of stock in Inventory class and then check the Category limit set.
