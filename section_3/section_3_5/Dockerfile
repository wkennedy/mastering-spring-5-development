FROM java:8
WORKDIR /deployments
ADD  /target/section_3_5.jar /deployments/app.jar
CMD java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n -jar app.jar