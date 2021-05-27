# Currency Converter
**Thalles A.S.**

**May 2021**

This repository was created as part of a selection process for the Jaya Tech company.

To run it you must have Maven and Java 11 installed in your machine. From then on it's only a matter of opening and letting Maven run its magic. I suggest using IntelliJ to run it as the IDE facilitates this process, the free version should be more than enough.

The project consists of a currency converter, capable of converting between any currencies listed on the [ExchangeRates API](https://manage.exchangeratesapi.io), under the URL https://api.exchangeratesapi.io/latest?base=USD&access_key=[personalKey]. Generating te key on the website is free and you have 1 thousand requests <sup>1</sup>.

This is a web application ran with in-memory database (H2). The main endpoints are:
- GET:
  - **localhost:8080/users** -- to get the listing of all the registered users.
  - **localhost:8080/currencies** -- to get the listing of all the registered currencies and their respective values in relation to Euro (EUR).
  - **localhost:8080/transactions** -- to get the listing of all preexisting transactions.
  - **localhost:8080/transactions/byIdUser?idUser=[idUser]** -- to get the listing of all preexisting transactions by a given user.
- POST:
  - **localhost:8080/transactions/convert** -- to convert between currencies and return a JSON containing all the information of the operation. It requires a body of a transaction, but not all fields are required. See below the minimum requirements for the success of this operation.
  ```JSON
    {
        "currencyOrigin": "USD",
        "currencyTarget": "BRL",
        "value": 5.0,
        "user": {
            "id": 4
        }
    }
  ```

I have chosen to develop this application with Java and Spring Boot as both a matter of comfort, given that I had some prior experience with it, as well as to improve my knowledge of these technologies. As per the database, H2 in memory is very convenient for prototyping and getting a project going.

The layers of this project are the usual layers you will find on most Spring Boot projects and are described as follows:

- Controller, for the endpoints (URLs);
- Service, for the more robust operations as well as business rules;
- Repository, for queriying the database. Note that most of the operations were done with the help of JPA Repositories;
- DTOs, mostly used for response to the user, not allowing for the database raw object to reach the controller layer;
- Entities/Model, which are essentially the representation of the database tables.

Furthermore, having realized that querying the API frequently while testing would eat through my querying limit, I took the liberty to create a task package with a task to query the API every 5 hours and save the exchange rates of the currencies (in relation to EUR) to our local database.

**Final considerations:**
<br>
This task has proven to be a highly valuable learning experience. I appreciate the opportunity and I'm looking forward to seeing notes and remakrs on the work I've done so far. That is to say I will still work on it as with time I realized I could've done some of these things differently. Either way, it was fun.

**Footnotes:**
1. The API key is personal, and for that reason I will be generating myself a new one and invalidating the one present in the file application-test.properties. If you wish to run it please generate yourself a key on the website (again, it's free!) and overwrite the one on the mentioned file.