FROM openjdk:17
ADD target/medilabo_ui-0.0.1-SNAPSHOT.jar medilabo_ui.jar
ENTRYPOINT ["java","-jar","/medilabo_ui.jar"]
EXPOSE 8090