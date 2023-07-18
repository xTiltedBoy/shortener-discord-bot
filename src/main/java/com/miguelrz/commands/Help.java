package com.miguelrz.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.miguelrz.DiscordBot.commandDataList;

public class Help extends ListenerAdapter {

    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (event.getName().equals("help")) {

            if (event.getOption("command") == null) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Help - " + event.getUser().getName())
                        .setDescription("The function of the bot is to shorten links, for more information execute " +
                                "/help shorten or /help unshorten.")
                        .setColor(new Color(3, 252, 132));
                event.replyEmbeds(embed.build()).queue();
            } else if (event.getOption("command").getAsString().equals("unshorten")) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Help - " + event.getUser().getName())
                        .setDescription("If any URL has already been shortened using /shorten, it will return the " +
                                "original URL. \n\n /unshorten [URL]")
                        .setColor(new Color(3, 252, 132));
                event.replyEmbeds(embed.build()).queue();
            } else if (event.getOption("command").getAsString().equals("shorten")) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Help - " + event.getUser().getName())
                        .setDescription("A URL must be entered and it returns a shortened URL. \n \n /shorten [URL]")
                        .setColor(new Color(3, 252, 132));
                event.replyEmbeds(embed.build()).queue();
            }
            else {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Help - " + event.getUser().getName())
                        .setDescription("Invalid command to describe.")
                        .setColor(new Color(252, 3, 3, 255));
                event.replyEmbeds(embed.build()).queue();
            }

        }
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        commandDataList.add(Commands.slash("help", "Show bot help")
                .addOption(OptionType.STRING, "command", "COMMAND you want to describe", false));
        event.getJDA().updateCommands().addCommands(commandDataList).queue();
    }

}
