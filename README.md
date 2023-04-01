# Hotels web application

### Instructions:

1. Download this project from `github`
2. Create `.env` file with properties:
    * POSTGRES_HOST
    * POSTGRES_USERNAME
    * POSTGRES_PASSWORD
3. Go to the terminal and use docker command:
```
docker-compose up
```

Then the application will be started.

# Development of the automated hotel reservation system

At the moment, the client and administrator parts have been implemented in the project.

### Functionality on the client side:

The client registers via email with a password input.  Then he can book the hotel room with preferred criteria and desired dates (with dates validation) on the calendar. After selecting the necessary criteria, hotels are sorted according to the request and displayed based on the corresponding criteria. The client selects a hotel and makes a reservation, waiting for confirmation by the administrator.

### Functionality on the administrator side:

The administrator can log in using a specific URL. He can add or remove hotels from the client's page. The administrator also confirms client bookings upon request.

[GitHub page](https://github.com/Mahoolya/hotels)