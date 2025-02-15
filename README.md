Traini8 Spring Boot 3.0

This project demonstrates the implementation of Rest API using Spring Boot 3.0. It includes the following features:

CRUD Endpoints
•POST endpoint to add center data
•http://localhost:8089/api/v1/car/upload-data
•sample input: {
    "studentCapacity": 19,
    "centerCode": "83J78tF7GT90",
    "contactEmail": "ussdname@domain.com",
    "contactPhone": "7654321900",
    "centerName": "op Rod amf ",
    "addressDTO": {

        "detailedAddress": "kdsg asknf",
        "city": "Rolfsonburgh",
        "state": "",
        "pincode": "42092"
    },
    "courses": [
        "Jamila",
        "Floretta",
        "Brittny"
    ]
}
•POST endpoint to seed random data 
•http://localhost:8089/api/v1/traini8/seed-centers
•Required fields: none
•GET endpoint to retrieve all center or search based on centerCode or centerName
•http://localhost:8089/api/v1/traini8/search?centerName=&centerCode=
•Filtering capabilities by:
•centerName, centerCode 

Technologies
•Spring Boot 3.0
•PostgresSQL
•Hibernate
•Postman
•Maven

Getting Started

To get started with this project, you will need to have the following installed on your local machine:
•JDK 21+
•Maven 3+
•PostgreSQL
•Eclipse

To build and run the project, follow these steps:
•Navigate to the project directory: src/main/resources
•Add database usersername and password in application-dev.yml 
•Build the project: mvn clean install
•Run the project: mvn spring-boot:run 

-> The application will be available at https://github.com/yashjoshi906/traini8/.
