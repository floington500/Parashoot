FROM openjdk:17
LABEL authors="zacha"

WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

COPY scripts/init.sh ./
RUN ./init.sh

CMD ["./mvnw", "spring-boot:run"]