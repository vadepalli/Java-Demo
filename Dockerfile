FROM tomcat:8.5-jre8
EXPOSE 8080
ADD target/java-demo.war java-demo.war
ENTRYPOINT ['java','-war','/java-demo.war']
