FROM java:8-jre-alpine

ADD build/libs/app.jar .

CMD exec java -jar /app.jar


