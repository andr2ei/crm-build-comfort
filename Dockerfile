FROM openjdk:11-oracle

RUN mkdir -p /home/app/src/main/resources

WORKDIR /home/app

COPY ./target/crm-build-comfort-0.0.1-SNAPSHOT.jar /home/app
COPY ./src/main/resources/cat_on_the_window.jpg /home/app/src/main/resources/cat_on_the_window.jpg

CMD ["java", "-jar", "/home/app/crm-build-comfort-0.0.1-SNAPSHOT.jar"]
