FROM fabric8/java-alpine-openjdk8-jdk
VOLUME /tmp
ADD /target/section-1-1.jar /deployments/app.jar
ENTRYPOINT ["/deployments/run-java.sh", "-Djava.security.egd=file:/dev/./urandom", "/app.jar"]