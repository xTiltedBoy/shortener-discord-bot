FROM openjdk:jdk-slim

WORKDIR /shortener-discord-bot

CMD echo "Shortener Bot is Starting\n" && java -Xmx512M -jar shortener-discord-bot-1.0.0.jar
