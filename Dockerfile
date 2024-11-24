# Use a Maven image to build the project
FROM maven:3.8-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml ./
COPY src ./src

# Build the project with Maven
RUN mvn clean package -DskipTests

# Now use a Tomcat image to run the WAR file
FROM tomcat:9-jdk17

# Copy the built WAR file from the build stage into the Tomcat webapps folder
COPY --from=build /app/target/RestoraApp-1.0.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080 for the Tomcat app
EXPOSE 8080

# Run Tomcat in the container
CMD ["catalina.sh", "run"]
