FROM openjdk:jdk-slim

CMD echo "Shortener Bot is Starting\n" && java -Xmx512M -jar ShortenerBot-1.0.0.jar
