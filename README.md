# Currency Rate API Wrapper

This project is a Java application developed as part of an advanced Java course at Polish-Japanese Academy of Information Technology. It serves as an API wrapper that retrieves currency rates from the api.nbp.pl endpoint and calculates the average of those rates based on a given timestamp. The project utilizes the Spring Web framework and connects to a MongoDB database to store details about the requests made.

## Features

Retrieves currency rates from the api.nbp.pl endpoint.
Calculates the average of the currency rates based on a provided timestamp.
Exposes a single endpoint that is documented in Swagger UI.
Utilizes the WebClient to perform HTTP calls to the external API.
Implements custom exception handling and maps exceptions to appropriate responses using the NpbAdvice class.

## Technologies Used

Java
Spring
MongoDB
WebFlux
Swagger UI
