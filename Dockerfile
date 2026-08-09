FROM eclipse-temurin:8-jre
WORKDIR /app
COPY target/cloud-native-order-platform-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
