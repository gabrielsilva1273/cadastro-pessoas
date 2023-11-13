FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/cadastro-pessoas-0.0.1-snapshot.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
