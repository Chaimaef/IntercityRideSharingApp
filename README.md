# t04
# Inter City Ride Sharing System 

This whole system include a spring-boot based backend and two Android apps as frontend. The project intercityRideSharingSystem_Android is for the driver and intercityRideSharingSystem_Android _passenger is for the passenger. And all the data is being handled using backend.

## Backend Services

The backend service will be needed to run before the Android app so they could interact. The commands to run backend are: 

```
cd t04/intercityRideSharingSystem
mvn install -DskipTests
mvn spring-boot:run
```

### Android frontend 

In order to run the Android frontend, please make sure that the port 8080 is available on your machine. That is, there is no other processes running on that port. If the port is already allocated, for example, if jenkins is running on 8080, just use the following command to kill it: 
```
sudo systemctl stop jenkins 
```

### Driver app
In the Driver app, user can create, modify, and close journey. To create journey, user will need to input necessary parameters when instructed to do so. Note that the input need to follow some format. 

```
The format of the start time should be: "dd-mmm-yyyy-hh:mm:ss". For example, "12-Dec-2018-12:21:21". 
When entering the stops for creating journey, the stops should be seperated by "_". For example, "montreal_ottawa_toronto". Please note that this is case-sensitive.
When entering the prices for creating journey, please note that you will only need to provide prices for stops after the start city because our app autofills 0$ for the start point 
For example, if the stops are "montreal_ottawa_toronto", then the price should be something like "12_45". 
```
Please note that when using the app, you will need to fill in every text fields. Do not leave it blank otherwise it will have exceptions.

## Passenger app 
In the passenger app, the user can search for all available journey provided start position and destination; sort journeys by relevance by inputing sorting criterias; and choose a journey to join. When searching for all available journeys, the passenger need to enter in two textfields as instructed, and then click the button with "All journey" to retreive a list of journeys. 
To sort journeys, the user will need to fill in all the criterias textfields as instructed and click on the button "Sort by Relevance" to sort journeys. 
To join journeys, the user can just click on the journey he/she would like to join from the scrollview and enter his/her name to join the journey as instructed to do so. 
The button with "new search" will clear everything and let the user to start a new journey search. 
Please note that: 
1. When sorting with relevance, you will have to fill in all the textfields otherwise it will cause errors. 
2. When searching for all journeys, you will only need to fill the two textfields for start city and destination. 
3. All the format for input data will be the same as that of in the driver app, like the "_" and time format, etc. 

## Deployment

We use Heorku to deploy our services and integrated with Travis CI.

## Built With

* [Gradle](https://gradle.org) -- For android app 
* [Maven](https://maven.apache.org/) -- For backend app


## Authors

Shaodi Sun
