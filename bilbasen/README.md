# Bilbasen Price data downloader

The purpose of this applicaiton is to download price information for a given search on www.bilbasen.dk and store the result in a CSV file. The data can then be processed using other tools.

## Build

Move to the folder containing the source code and run

````bash
mvn clean install
````

## Run

1. Go to bilbasen.dk
2. Customize your search and press search
3. Copy the link of the first result page and run the following command to save the result to demo.csv. Please note the single quests around the URL to avoid special charecters in the URL being interpreted by the shell.

````bash
java -jar target/bilbasen-0.0.1-SNAPSHOT-jar-with-dependencies.jar download --url '<url>' -O demo.csv
````

### Run Example

````bash
java -jar target/bilbasen-0.0.1-SNAPSHOT-jar-with-dependencies.jar download --url 'https://www.bilbasen.dk/brugt/bil/Skoda/Fabia?IncludeEngrosCVR=false&YearFrom=2017&PriceFrom=0&PriceTo=175000&includeLeasing=false&Fuel=1&TowBar=1&RemovableTowBar=1&Cartypes=Stationcar&IncludeWithoutVehicleRegistrationTax=false' -O demo.csv
````
