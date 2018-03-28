FROM openjdk:8-jre-alpine

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /cross-blogs-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ADD *.jar /cross-blogs-0.0.1-SNAPSHOT.jar
