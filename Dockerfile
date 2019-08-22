FROM openjdk:8-jdk-alpine
MAINTAINER br.com.maxplorer
COPY build/libs/app.jar app/
ENV SPRING_DATASOURCE_URL=""
ENV SPRING_DATASOURCE_USERNAME=""
ENV SPRING_DATASOURCE_PASSWORD=""
ENV SPRING_RABBITMQ_ADDRESSES=""
ENV SPRING_RABBITMQ_USERNAME=""
ENV SPRING_RABBITMQ_PASSWORD=""
ENTRYPOINT exec java -jar /app/app.jar --spring.datasource.url=$SPRING_DATASOURCE_URL --spring.datasource.username=$SPRING_DATASOURCE_USERNAME --spring.datasource.password=$SPRING_DATASOURCE_PASSWORD --spring.rabbitmq.addresses=$SPRING_RABBITMQ_ADDRESSES --spring.rabbitmq.password=$SPRING_RABBITMQ_PASSWORD