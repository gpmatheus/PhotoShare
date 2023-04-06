FROM openjdk:11
run mkdir photos
COPY target/PhotoShare-0.0.1-SNAPSHOT.jar photoshare.jar
ENTRYPOINT ["java", "-jar", "/photoshare.jar"]