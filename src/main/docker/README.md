# Market Tracker Application

This Docker image is used to run the Market Tracker application. Below, you will find basic steps on how to use this Docker image.

## Usage

1. Import the Docker Image:
   Import the image using the Docker image file you exported.

   ```bash
   docker load -i market-tracker.tar

2. Run the Market Tracker Application:
   Start the Market Tracker application using the Docker image.

   ```bash
   docker run -d -p 8080:80 market_tracker:latest

   This will run the application inside Docker and expose iton port 8080

3. Access in the browser:
   Open your web browser and access the Market Tracker API documents by visiting the following URL.

   ```bash
   http://localhost:8080/swagger-ui/index.html#/