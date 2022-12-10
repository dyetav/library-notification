FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /opt/app
RUN apk update && apk upgrade && apk add bash
COPY target/library-notifications-1.0-SNAPSHOT.jar /opt/app
COPY ./wait-for-it.sh /opt/app
RUN chmod 764 /opt/app/wait-for-it.sh
ENTRYPOINT ["/opt/app/wait-for-it.sh", "mysql-test:3306", "--strict" , "--timeout=60", "--", "java", "-jar", "/opt/app/library-notifications-1.0-SNAPSHOT.jar"]