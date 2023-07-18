package com.miguelrz;

import com.miguelrz.commands.Help;
import com.miguelrz.commands.Shorten;
import com.miguelrz.commands.Unshorten;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DiscordBot {

    public static List<CommandData> commandDataList = new ArrayList<>();
    final static Logger LOGGER = LoggerFactory.getLogger(DiscordBot.class);

    public static void main(String[] args) {

        try {
            JDA shortener = JDABuilder.createDefault(System.getenv("token"))
                    .addEventListeners(new Shorten())
                    .addEventListeners(new Unshorten())
                    .addEventListeners(new Help())
                    .setActivity(Activity.playing("to /shorten"))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build();
        } catch (Exception e) {

            LOGGER.error("No se ha podido loggear \n" + e);

        }

    }

}
