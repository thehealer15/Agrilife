# AgriLife - we got you covered :)

**AgriLife** is an android application for insurance company focused on `Farming insurance` 
Our product will have various insurance plans for farmers for farming equipment , crop insurance also animal insurance. 

Important thing about our app is, many crop - insurance plans offered have an obligation to follow suggested crop by our app which is given by Machine learning model and expert monitored.
We intend to make insurance purchase by using smart contracts to make system more quick ,  responding also to develop trust amongst farmers

## Installation Instructions  
- check [repl](https://replit.com/join/nyucmmwtkx-saymyname002) which is hosting the API for crop recommendation
- clone this project to your local machine and lauch the app using Android Studio 

## Features

- To avail farmers to buy and pay premium of insurance
- Crop recommendation based considering nearby atmosphere also factors of land
- As we are trying to implement smart contracts here, claiming an insurance will be easy task to perform which will increase trust factor in app.



## Upcoming features
- Buying a insurance process will be on smart contracts
- further automations in app which intend to cut of insurance agents from hierarchy


## Approch 
We created a m.l. model which is backend of [api](https://replit.com/@saymyname002/API#main.py) 
Aip route - @recommendation it's a post request taking 7 parameters mainly weather info, soil information which farmer has to insert 
Our app will have plans which might obligate farmers, usage of our recommendation as insurance condition ( which is totally based on land, weather conditions i.e. interest of farmer )


## Tech & tools used 
- Android studio and VS code for development
- Firebase authentication for authentication 
- cloud firestore as database
- Razorpay API for integration of payment gateway ( while paying premium )
- flask for creating crop recommendation API
- repl.it for hosting crop recommendation API
- KNN algorithm for creating crop recommendation machine learning model
- dataset from kaggle


## Creators and Contribution
- Akshay Pawar :- Implementation of skeleton of app, crop recommendation api based on ml model
- Athrav Nhavkar :- Hosting crop recommendation API and implementation of front end of crop recommendation system
- Onkar Gavali :- Implementation of ML model
- Shripad Bhat :- Implementation of database, ( browse insurance plan) feed of app.
