FROM openjdk:11-oracle

RUN mkdir -p /home/app

WORKDIR /home/app

COPY ./target/crm-build-comfort-0.0.1-SNAPSHOT.jar /home/app

CMD ["java", "-jar", "/home/app/crm-build-comfort-0.0.1-SNAPSHOT.jar"]
