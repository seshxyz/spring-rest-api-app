FROM maven:3.8.1-openjdk-11 as builder
WORKDIR /ru
COPY . /ru/.
RUN mvn -f /ru/pom.xml clean package spring-boot:repackage -Dmaven.test.skip=true

FROM eclipse-temurin:11.0.22_7-jre
WORKDIR /ru
COPY --from=builder /ru/target/FileStorage-0.0.1.jar /ru/FileStorage-0.0.1.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/ru/FileStorage-0.0.1.jar"]