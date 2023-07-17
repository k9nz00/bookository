FROM openjdk:17-jdk-slim
WORKDIR /opt
RUN mkdir debug
ADD /target/bookository-server-*.jar /opt/bookository-serverr.jar
ADD /target/classes/application.yaml /opt/bookository-application.yml
ENV JAVA_OPTS="-Dspring.config.location=/opt/bookository-application.yml -agentlib:jdwp=transport=dt_socket,address=*:38005,server=y,suspend=n"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar bookository-serverr.jar"]