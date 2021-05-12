FROM openjdk
RUN mkdir /app
COPY target/alu-service-sso-0.0.1-SNAPSHOT.jar /app
CMD java -jar /app/alu-service-sso-0.0.1-SNAPSHOT.jar 
EXPOSE 8503 
