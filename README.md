# bookings #

## Features completed
Below is the list of features completed for the assignment
<br>
#### authentication
* POST /api/v1/auth/signup: Allows users to be signed up with email/password entries. **Endpoint is not authenticated.**
  <br>
* POST /api/v1/auth/login: Allows users to login and get access token. **Endpoint is not authenticated.**

#### properties

* **POST /api/v1/properties**: Enables creation of a property. Requires OWNER role.
  <br>
* **PUT /api/v1/properties**: Enables updating a property. Requires OWNER role.
  <br>
* **DELETE /api/v1/properties**: Enables deleting of a properties. Requires OWNER role.
  <br>
* **GET /api/v1/properties**: Enables fetching of properties. Requires OWNER or USER role.
  <br>
* **GET /api/v1/properties/:id**: Enables fetching a property by id. Requires OWNER or USER role.
  <br>
* **GET /api/v1/properties/:id/availability**: Enables fetching list of bookings and blocks on property by id. Requires OWNER or USER role.
  <br>
#### blocks
* **POST /api/v1/blocks**: Enables creation of a block. Requires OWNER role.
  <br>
* **PUT /api/v1/blocks**: Enables updating a block. Requires OWNER role.
  <br>
* **DELETE /api/v1/blocks**: Enables deleting of a block. Requires OWNER role.
  <br>
* **GET /api/v1/blocks/:propertyId**: Enables fetching of blocks by propertyId. Requires OWNER role.
  <br>
#### bookings
* **POST /api/v1/bookings**: Enables creation of a booking. Requires USER role.
  <br>
* **PUT /api/v1/bookings**: Enables updating a booking. Requires USER role.
  <br>
* **DELETE /api/v1/bookings**: Enables deleting of a booking. Requires USER role.
  <br>
* **GET /api/v1/bookings/:propertyId**: Enables fetching of bookings by propertyId. Requires USER role.

## Functionalities provided
Below is the list of functionalities provided for the assignment
<br>
* **JWT token authentication**: After successful registration and login of the user, corresponding JWT token is generated and returned as *accesToken* in the response body. It can be used from that point for endpoints that require authentication. After access token is expired, new one can be generated with new login. Alternative to this would be to use refresh token, which would be generated through authentication/login and returned together with access token. For the sake of simplicity of this assignment, I did not implement this part. If needed, this can be provided as well.
  <br>
* **Authorization**: Roles of OWNER and USER have been provided. Endpoints for managing properties and blocks can be only done with role OWNER. Bookings managing can be done with the role USER. Fetching of properties and their availabilities can be done with both roles
* **JSON server responses** All responses are implemented to use JSON
  <br>
* **Docker** Corresponding docker files are included, and they enable building and running the project in docker containers. Additional to that, project can be run locally, if java is installed on the machine.
  <br>
* **Tests** Unit and integration tests have been provided to showcase best practices in TDD. Not all code has been covered, due to time constraint.
  <br>
* **H2 Database** I decided to use H2 in memory database for this project. Decision was made mostly to keep things simple. However, project is decoupled in a nice way so that it is easy to introduce different database if needed.
  <br>
* **Flyway**: Project is using migrations towards database with Flyway provider.
  <br>
* **Separation of concern**: Each component has a particular role, where the roles are mutually exclusive. This makes the project easy to be unit tested.
  <br>
* **Centralised Error handling**: All the errors are handled centrally. This reduces the ambiguity in the development when the project grows larger.
  <br>
* **Centralised Response handling**: Similar to Error handling, response handling is centralised. This makes it very convenient to apply a common API response pattern.
* **OpenApi**: Open API has been provided. It can be accessed on http://localhost:8080/swagger-ui/index.html#/
* **Validation** All endpoints have corresponding validation rules applied. Endpoints for managing bookings and blocks have checks in place, as described in the assignment.
* **Data seeding** For the sake of easier testing, data seeding has been provided. It reads the list of users from application.yaml and creates them with their corresponding role. Also, all owners get one default property created. Data seeding can be easily disabled through application.yaml file
## How to build and run this project
#### 1. Gradle
Navigate to the root of the project via command line and execute the commands
```
./gradlew build
./gradlew bootRun
```
#### 2. IntellJ IDEA
Navigate to the OffersApplication.java class and run main method. Application will start on the port 8080

#### 3. Docker
Build the container by going to the folder containing Dockerfile and run following command
```
docker build -t spring-boot:1.0 .
```
Start the container with following command
```
docker run -d -p 8080:8080 -t spring-boot:1.0
```
Application will start on the port 8080

## How to run tests
#### 1. Gradle
Navigate to the root of the project via command line and execute the command
```
./gradlew test
```
##### 2. IntellJ IDEA
Navigate to the tests package and run the tests inside it
<br>

## How to manually test the API endpoints

After running the project, go to http://localhost:8080/swagger-ui/index.html#. As part of data seeding, user and owner are created by default.
<br>
1. Call **/api/v1/auth/login** endpoint with credentials provided in the application.yaml file to get the JWT token.
2. Click **Authorize** on top of the page and paste the token from the response.

<br>
*If you get 403 as a response, make sure that you used correct user for the endpoint, as described in the #Features completed section.
