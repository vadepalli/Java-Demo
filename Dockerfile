FROM openjdk:8
EXPOSE 8080
ADD target/java-demo.war java-demo.war
ENTRYPOINT ['java','-war','/java-demo.war']
