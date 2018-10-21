# Financial tech challenge

Tech challenge done for a company in the financial industry.

The UI is pretty ugly, but works.

## Running the challenge

After cloning this project, create the jar by doing:

```
mvn package
```

Then you can run the dockers with:

```
docker-compose up -d
````

After everything boots, you should have the app working on localhost:8080/index.html

## Requested tools
* Docker
* MySQL
* Java 8 + Spring Framework

## Questions

1. Should we use indexes for this data? Which ones?

    There should be an index on the timestamp.

1. What issues can you see in this feature request that could be a problem in a near or long term future?

    It's very easy for a table with this kind of information to grow out of hand. This is first reflected on performance
    but then it becomes a set of problems.

    This service will also suffer from consistency issues, assuming the original (not a duplicate) users table doesn't
    become part of this database.

1. Is there any tool you consider should be used in replacement of one of the requested tools? Which one?

    * Docker wouldn't change
    * MySQL would probably change to Postgres
    * Java 8 + Spring would change to Scala or Kotlin + Play