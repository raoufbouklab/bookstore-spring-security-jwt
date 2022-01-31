FROM openjdk:17

VOLUME /tmp

EXPOSE 8082

ADD target/bookstore-spring-security-jwt.jar bookstore-spring-security-jwt.jar

ENTRYPOINT ["java", "-jar", "bookstore-spring-security-jwt.jar"]