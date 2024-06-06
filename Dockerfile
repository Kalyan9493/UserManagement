# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the jar file from the target directory to the container
COPY target/usermanagement-0.0.1-SNAPSHOT.jar usermanagement-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","usermanagement-0.0.1-SNAPSHOT.jar"]
