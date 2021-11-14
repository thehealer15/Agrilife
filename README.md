# AgriLife - we got you covered :)


**AgriLife** is an android application for insurance company focused on `Farming insurance` 
Our product will have various insurance plans for farmers for farming equipment , crop insurance also animal insurance. 

Important thing about our app is, many crop - insurance plans offered have an obligation to follow suggested crop by our app which is given by Machine learning model and expert monitored.
We intend to make insurance purchase by using smart contracts to make system more quick ,  responding also to develop trust amongst farmers

## Installation Instructions  
- check [repl](https://replit.com/join/nyucmmwtkx-saymyname002) which is hosting the API for crop recommendation
- Find APK of android project = [link]()
- Feel free to see internals of app from repo / cloning repo.


## Features
- To enable farmers to buy insurance and pay premium of insurance
- Crop recommendation based considering nearby atmosphere also factors of land on which crop yield is dependent
- As we are trying to implement smart contracts here, claiming an insurance will be very easy task also it will increase trust factor and convenience in app.


## Upcoming features
- Buying a insurance process will be on smart contracts 
- further automations in app which intend to cut of insurance agents from hierarchy
- A window displaying government schemes for farmers 

## How smart contracts can be used?
Smart contracts are based on blockchain and executes a conditional transaction. At simple words its traditional contract/agreement but it will execute as per code and remove chance of other party to default . When farmer buys an insurance; thier agreement will be on smart contract.  


## Approch 
We created a m.l. model which is backend of [API](https://replit.com/@saymyname002/API#main.py) 
it's an API taking 7 parameters mainly weather info, soil information which farmer has to insert 


## Tech & tools used 
- Android studio and VS code for development
- Firebase authentication  
- cloud firestore 
- Razorpay API for integration of payment gateway ( while paying premium )
- flask for creating crop recommendation API
- repl.it for hosting crop recommendation API
- KNN algorithm for creating crop recommendation machine learning model
- dataset from kaggle

## Screeshots Of Working Application
![App Screenshot](/Agrilife/DemoImages/AgriLife5.jpg width="150" height="150")
![App Screenshot](/Agrilife/DemoImages/AgriLife6.jpg)
![App Screenshot](/Agrilife/DemoImages/AgriLife4.jpg)
![App Screenshot](/Agrilife/DemoImages/AgriLife3.jpg)
![App Screenshot](/Agrilife/DemoImages/AgriLife2.jpg)
![App Screenshot](/Agrilife/DemoImages/AgriLife1.jpg)



## Creators and Contribution
- Akshay Pawar :- Implementation of skeleton of app and premium payment ,backend of crop recommendation API based on ml model. 
- Athrav Nhavkar :- Hosting crop recommendation API and implementation of front end of crop recommendation system
- Onkar Gavali :- Implementation of ML model
- Shripad Bhat :- Implementation of database, ( browse insurance plan) feed of app, dashboard functionality. 
