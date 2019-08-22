FROM openjdk:8-jdk-alpine
MAINTAINER br.com.maxplorer
COPY build/libs/app.jar .
ENV DATASOURCE_URL=""
ENV DATASOURCE_USERNAME=""
ENV DATASOURCE_PASSWORD=""
ENV RABBITMQ_ADDRESS=""
ENV RABBITMQ_USERNAME=""
ENV RABBITMQ_PASSWORD=""
ENTRYPOINT exec java -jar app.jar \
          --spring.datasource.url=$DATASOURCE_URL \
          --spring.datasource.username=$DATASOURCE_USERNAME \
          --spring.datasource.password=$DATASOURCE_PASSWORD \
          --spring.rabbitmq.addresses=$RABBITMQ_ADDRESS \
          --spring.rabbitmq.password=$RABBITMQ_PASSWORD