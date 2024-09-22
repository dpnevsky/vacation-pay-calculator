
FROM openjdk:11-jre-slim

WORKDIR /build

COPY target/vacationpaycalculator-1.0-SNAPSHOT.jar vpcapp.jar

ENTRYPOINT ["java", "-jar", "vpcapp.jar"]

EXPOSE 8080

CMD java -jar vpcapp.jar
