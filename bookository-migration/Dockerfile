FROM openjdk:17-jdk-slim
WORKDIR /opt
RUN mkdir debug
ADD /target/bookository-migration-*.jar /opt/bookository-migration.jar
ADD /target/classes/application.yaml /opt/application.yml
ENV JAVA_OPTS="-Dspring.config.location=/opt/application.yml -agentlib:jdwp=transport=dt_socket,address=*:38006,server=y,suspend=n"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar bookository-migration.jar"]