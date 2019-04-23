FROM tomcat:8.5-jre8
EXPOSE 8080
RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY target/javademo.war /usr/local/tomcat/webapps/ROOT.war
