package com.miguelrz.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.miguelrz.utils.HttpPetition.postPetition;
import static com.miguelrz.DiscordBot.commandDataList;

public class Shorten extends ListenerAdapter {

    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (event.getName().equals("shorten")) {

            if (event.getOption("url").getAsString().length() < 7) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Shorter URL - " + event.getUser().getName())
                        .setDescription("Url no válida")
                        .setColor(new Color(252, 3, 3, 255));
                event.replyEmbeds(embed.build()).queue();
                return;
            }

            String protocolNotSecure = event.getOption("url").getAsString().substring(0, 7);
            String protocolSecure = event.getOption("url").getAsString().substring(0, 8);

            if (protocolNotSecure.equals("http://") || protocolSecure.equals("https://")) {

                if (event.getOption("out-put-code") == null) {
                    EmbedBuilder embed = new EmbedBuilder()
                            .setTitle("Shorter URL - " + event.getUser().getName())
                            .setDescription("https://api.miguelrz.com/" + postPetition(event.getOption("url").getAsString()))
                            .setColor(new Color(3, 107, 252));
                    event.replyEmbeds(embed.build()).queue();
                } else {
                    EmbedBuilder embed = new EmbedBuilder()
                            .setTitle("Shorter URL - " + event.getUser().getName())
                            .setDescription("https://api.miguelrz.com/" + postPetition(event.getOption("url").getAsString(), event.getOption("out-put-code").getAsString()))
                            .setColor(new Color(3, 107, 252));
                    event.replyEmbeds(embed.build()).queue();
                }

            } else {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Shorter URL - " + event.getUser().getName())
                        .setDescription("Url no válida")
                        .setColor(new Color(252, 3, 3, 255));
                event.replyEmbeds(embed.build()).queue();
            }

        }
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        commandDataList.add(Commands.slash("shorten", "Create a shortened link")
                .addOption(OptionType.STRING, "url", "URL you want to shorten", true)
                .addOption(OptionType.STRING, "out-put-code", "Exit code you want in the URL", false));
        event.getJDA().updateCommands().addCommands(commandDataList).queue();
    }

}
