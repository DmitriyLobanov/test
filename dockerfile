FROM amazoncorretto:17-alpine as builder
WORKDIR /applications/bot
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM amazoncorretto:17-alpine
WORKDIR /applications/bot
COPY --from=builder /applications/bot/target/*.jar /applications/bot/bot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bot.jar"]