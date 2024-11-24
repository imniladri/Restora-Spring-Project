# Step 1: Use the official Tomcat base image with JDK 17
FROM tomcat:10-jdk17

# Step 2: Set environment variables (optional)
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Step 3: Remove the default webapps in Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Step 4: Copy the WAR file into the webapps directory
COPY target/RestoraApp-1.0.war /usr/local/tomcat/webapps/ROOT.war

# Step 5: Expose the default Tomcat port
EXPOSE 8080

# Step 6: Define the startup command
CMD ["catalina.sh", "run"]
