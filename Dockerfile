# Build stage
FROM maven:3.8-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package

# Runtime stage
FROM tomcat:10-jdk17
ENV JAVA_OPTS="-Xms512m -Xmx1024m"
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /app/target/restora-app.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
