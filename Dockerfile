FROM openjdk:8
ADD target/spring-boot-rest-api-v1.jar spring-boot-rest-api-v1.jar
EXPOSE 9090
ENV ACTIVE_PROFILE=dev
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","spring-boot-rest-api-v1.jar"]