### REST-API-for-dbs

Provides a simple RESTful service for adding, updating, deleting and fetching records of DBs connections saved in MySQL table defined in `application.properties`. Also it has a functionality to provide basic info about db tables and its' columns. Furthemore, there is an option to get basic statistics (max, min, avg) for each column.
Server runs on Spring FW port 8080.

#### How to run it locally

One prerequisite is to have a ready MySQL db (locally or remotely) and set it in `application.properties` file. 

```bash
./gradlew bootRun

curl 'localhost:8080/v1/list'
```

#### Methods

* `/v1/list` - list all records of connections
* `/v1/add` - POST method to add a new connection to db. Mandatory args are `name`,`hostName`,`port`,`databaseName`,`user`,`password`
* `/v1/update` - POST method for updating a connection with unique `id`. Mandatory args are `id`,`name`,`hostName`,`port`,`databaseName`,`user`,`password`
* `/v1/delete` - POST method delete a connection with unique `id`. Mandatory args are `id`
* `/v1/schema` - return the list of the db schemas
* `/v1/tables` - return list of tables
* `/v1/columns` - return list of columns for each table
* `/v1/statistics` - return simple statistics for each column (max, min, avg, median)
* `/v1/tableStatistics` - return simple statistics for each table about num of columns and num of records.

#### TODO

The code could use more TLC mainly the code around the use od MySQL could be packaged into an object.