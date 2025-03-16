
## City Suggestion

This is my submission for the city suggestion test.


## Built with

 - Java 21 (Latest LTS)
 - Spring Boot
 - Gradle

## Getting Started

You can start by cloning this repository

    git clone https://github.com/Nozzlium/city-suggestion-cesa.git
 After you've done that, go to the root folder. Make sure you have gradle installed. You can run the program using this command:
 

    gradle bootRun
And access the app by going to 
    http://localhost:8080/
## Example
Here is an example:
### request:
    /suggestions?q=La%20Prai
### response
    {
        "suggestions": [
            {
                "name": "La Prairie",
                "latitude": 45.41678,
                "longitude": -73.49917,
                "score": 0.7857142857142857
            },
            {
                "name": "La Jolla",
                "latitude": 32.84727,
                "longitude": -117.2742,
                "score": 0.7
            },
            {
                "name": "La Plata",
                "latitude": 38.52929,
                "longitude": -76.97525,
                "score": 0.7
            },
            {
                "name": "Portage la Prairie",
                "latitude": 49.97282,
                "longitude": -98.29263,
                "score": 0.5238095238095238
            },
            {
                "name": "La Grange",
                "latitude": 41.80503,
                "longitude": -87.86923,
                "score": 0.5
            }
        ]
    }
You can also add latitude and longitude as parameters for better result:

    /suggestions?q=La%20Prai&latitude=45.41678&longitude=-73.49917

The above request will result in this output:
   

     {
        "suggestions": [
            {
                "name": "La Prairie",
                "latitude": 45.41678,
                "longitude": -73.49917,
                "score": 0.8571428571428571
            },
            {
                "name": "Saint-Laurent",
                "latitude": 45.50008,
                "longitude": -73.66585,
                "score": 0.029822775942475066
            }
        ]
    }

You can also add paginations
   

     /suggestions?q=Ab&pageNo=4&pageSize=5

which will result in:
   

     {
        "suggestions": [
            {
                "name": "Aberdeen",
                "latitude": 33.82511,
                "longitude": -88.54366,
                "score": 0.25
            },
            {
                "name": "Escanaba",
                "latitude": 45.74525,
                "longitude": -87.06458,
                "score": 0.25
            },
            {
                "name": "Mableton",
                "latitude": 33.81574,
                "longitude": -84.56194,
                "score": 0.25
            },
            {
                "name": "Aberdeen",
                "latitude": 46.97537,
                "longitude": -123.81572,
                "score": 0.25
            },
            {
                "name": "Seabrook",
                "latitude": 42.89481,
                "longitude": -70.87116,
                "score": 0.25
            }
        ]
    }

By default pageNo is set to 1 and pageSize is set to 5
