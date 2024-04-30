FROM openjdk:21-jdk

WORKDIR /app

COPY /target/MoviesWebShop-0.0.1-SNAPSHOT.jar /app/MoviesWebShop.jar

CMD ["java", "-jar", "MoviesWebShop.jar"]
