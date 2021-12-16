# AgriLife - we got you covered :)


**AgriLife** is an android application for a company focused on `Farming Finance` 
Our product will have various insurance plans for farmers for farming equipment , crop insurance also animal insurance.Also various financing plans 
Important thing about our app is, many crop - insurance plans offered have an obligation to follow suggested crop by our app which is given by Machine learning model which will be determined by parameters such as land, weather forcast and surrounding. We choose this topic considering less penetration in farmer financing sector. 

## Installation Instructions  
- check [repl](https://replit.com/join/nyucmmwtkx-saymyname002) which is hosting the API for crop recommendation
- Find APK of android project = [link](https://drive.google.com/file/d/1oz4rDE7mR3GTqYSByQpml0xftGPcAUSu/view?usp=sharing)
- Feel free to see internals of app from repo / cloning repo.
- Demo Video [Link](https://drive.google.com/file/d/1uhJdgKmB-p74CaHCH7WHpf3ZJhvCtPgl/view?usp=sharing) of our Application 


## Features
- Farmer can apply for insurance
- Farmer can apply for loan
- Crop recommendation based considering nearby atmosphere also factors of land on which crop yield is dependent
- Dashboard for farmer to see which insurance & financing plans he/she picked for.

## Upcoming features
- Buying a insurance process will be on smart contracts .
- Loans also can be implemented using smart contract.
- UI & UX improvements. 
- A window displaying government schemes for empowering farmers. 

## How smart contracts can be used?
Smart contracts are based on blockchain and executes a conditional transaction. At simple words its traditional contract/agreement but it will execute as per code and remove chance of other party to default . When farmer buys an insurance; thier agreement will be on smart contract.  


## Approch 
We created a `ML` model which is backend of [API](https://replit.com/@saymyname002/API#main.py) 
it's an API taking 7 parameters mainly weather info, soil information which farmer has to insert, agrilife android app is communicating to this API for crop . Firesbase cloudstore is our database, firebase authetication for login-signup. 

## Tech & tools used 
- Android studio and VS code for development
- Firebase authentication  
- cloud firestore 
- Razorpay API for integration of payment gateway ( while paying premium & repay loan installment)
- flask for creating crop recommendation API
- repl.it for hosting crop recommendation API
- KNN algorithm for creating crop recommendation machine learning model
- dataset from kaggle

## Screeshots Of Working Application
![App Screenshot](/DemoImages/AgriLife5.jpg )
![App Screenshot](/DemoImages/AgriLife6.jpg)
![App Screenshot](/DemoImages/AgriLife4.jpg)
![App Screenshot](/DemoImages/AgriLife3.jpg)
![App Screenshot](/DemoImages/AgriLife2.jpg)
![App Screenshot](/DemoImages/AgriLife1.jpg)



## Creators and Contribution
- Akshay Pawar :- Implementation of skeleton of app and premium payment ,backend of crop recommendation API based on ml model. 
- Athrav Nhavkar :- Hosting crop recommendation API and implementation of front end of crop recommendation system
- Onkar Gavali :- Implementation of ML model
- Shripad Bhat :- Implementation of database, ( browse insurance plan) feed of app, dashboard functionality. 
