FROM azul/zulu-openjdk:17 as builder

WORKDIR /app

COPY gradlew gradle/ ./
COPY gradle/wrapper/ gradle/wrapper/
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew

COPY src ./src

RUN ./gradlew bootJar --no-daemon

FROM azul/zulu-openjdk:17-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]