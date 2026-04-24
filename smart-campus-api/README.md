#  Smart Campus REST API

##  Overview

The Smart Campus REST API is a backend system developed using **JAX-RS (Jersey)** with an embedded **Grizzly HTTP server**. It manages rooms, sensors, and sensor readings using in-memory data storage (HashMaps). The API follows RESTful principles with proper HTTP methods, status codes, and structured exception handling.

---

##  Technologies Used

* Java
* JAX-RS (Jersey)
* Grizzly HTTP Server (Embedded)
* Maven
* Postman

---

##  How to Run the Project

### Option 1: Using IDE

* Open project in IntelliJ / Eclipse / VS Code
* Run `Main.java`

### Option 2: Using Maven

```bash
mvn clean compile exec:java
```

---

##  Base URL

```
http://localhost:8080/api/v1/
```

---

##  API Endpoints

###  1. Discovery Endpoint

**GET /api/v1/**

Response:

```json
{
  "resources": {
    "rooms": "/api/v1/rooms",
    "sensors": "/api/v1/sensors"
  },
  "version": "v1"
}
```

---

###  2. Rooms

**Create Room**

```
POST /api/v1/rooms
```

```json
{
  "id": "R1",
  "name": "Lab",
  "capacity": 30
}
```

**Get All Rooms**

```
GET /api/v1/rooms
```

**Get Room by ID**

```
GET /api/v1/rooms/{id}
```

**Delete Room**

```
DELETE /api/v1/rooms/{id}
```

---

###  3. Sensors

**Create Sensor**

```
POST /api/v1/sensors
```

```json
{
  "id": "S1",
  "type": "CO2",
  "status": "ACTIVE",
  "currentValue": 0,
  "roomId": "R1"
}
```

**Get All Sensors**

```
GET /api/v1/sensors
```

**Filter Sensors**

```
GET /api/v1/sensors?type=CO2
```

---

###  4. Sensor Readings

**Add Reading**

```
POST /api/v1/sensors/{id}/readings
```

```json
{
  "id": "READ1",
  "timestamp": 1710000000,
  "value": 25.5
}
```

**Get Readings**

```
GET /api/v1/sensors/{id}/readings
```

---

##  Error Handling

| Scenario                 | Status Code |
| ------------------------ | ----------- |
| Room not found           | 404         |
| Invalid room reference   | 422         |
| Sensor under maintenance | 403         |
| Room has active sensors  | 409         |
| Server error             | 500         |

---

##  Key Design Decisions

 In-Memory Storage
HashMaps are used instead of a database to meet coursework requirements.

 REST Architecture
Resources are structured as:

```
/rooms  
/sensors  
/sensors/{id}/readings
```

Exception Handling
Custom exceptions with ExceptionMapper provide meaningful responses.

---

##  Testing

Tested using Postman:

* Create and retrieve rooms
* Create and filter sensors
* Add sensor readings
* Validate error scenarios (422, 403, 409)

---

##  Demo Includes

* API execution
* Postman testing
* Error handling demonstration
* REST design explanation

---

#  REPORT (Included as Required)

##  Part 1

* The API follows REST principles using proper HTTP methods (GET, POST, DELETE).
* Resources are clearly defined and mapped to endpoints.
* JSON is used for request and response formatting.

##  Part 2

* Rooms and sensors are modeled as separate resources.
* Each sensor is linked to a room using `roomId`.
* CRUD operations are implemented for rooms and sensors.

## Part 3

* Sensor readings are implemented as a sub-resource.
* Each reading includes timestamp and value.
* Readings are linked to sensors using sensor ID.

##  Part 4

* Exception handling is implemented using custom ExceptionMapper classes.
* Proper HTTP status codes are returned for different scenarios.
* This improves API reliability and usability.

## Part 5

* HashMap is used for in-memory storage instead of a database.
* This simplifies development and meets coursework constraints.
* The system is lightweight and fast but not persistent.

---

##  Author

Name: Nisal
Project: Smart Campus API Coursework

---

##  Notes

* Ensure server is running before testing
* Always use `/api/v1/` base path
* Postman collection recommended for testing

---

