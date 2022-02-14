# Maven build container 

FROM maven:3.6.3-openjdk-8 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk

#maintainer 
MAINTAINER swathi@yahoo.com
#expose port 8080
EXPOSE 3000

#default command
CMD java -jar /data/polls-0.0.1-SNAPSHOT.jar

#copy hello world to polls-0.0.1-SNAPSHOT image from builder image

COPY --from=maven_build /tmp/target/polls-0.0.1-SNAPSHOT.jar /data/polls-0.0.1-SNAPSHOT.jar