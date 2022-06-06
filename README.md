# Match Manager

## Environmentals

This project's configuration is best altered through environmental variables.
All the available enviromentals are listed below along with their defaults.
Those are available for both installation types.

- SERVER_PORT: 8080
- DB_HOST: localhost
- DB_PORT: 5432
- DB_USERNAME: postgres
- DB_PASSWORD: postgres
- DB_THREADS: 20
  * Maximum number of threads to be used when querying the database.
- DB_SCHEDULER_BUFFER: 100
  * Scheduler buffer. Utilized to hold elements waiting
    for a blocking operation if all threads are tied up.
- DB_SCHEDULER_SECONDS_TO_LIVE: 300
  * Number of seconds an operation may remain in the buffer,
    awaiting a thread to open up, before it gets dropped. 

## Running locally

Dependencies to run locally:
- Java 17
- Maven 3.8.1
- A postgres installation available

To build the jar using maven run, from the project's root:

```bash
mvn package -f pom.xml
```

and running the jar is done with:

```bash
java -jar ./match-manager-core/target/match-manager.jar
```

## Running in a container

To run the application as a container you must first build the image.
From project root run:

```bash
docker build . -t match-manager:1
```

Having build the image, the application can be run by manually creating 
the container:

```bash
docker run -d --network host --name mm match-manager:1
```

or, if a postgres is not readily available, through docker-compose:

```bash
docker-compose up -d
```
