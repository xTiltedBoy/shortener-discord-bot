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
import static com.miguelrz.utils.HttpPetition.getPetition;

public class Unshorten extends ListenerAdapter {

    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (event.getName().equals("unshorten")) {

            if (event.getOption("url").getAsString().length() < 7) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Unshorten URL - " + event.getUser().getName())
                        .setDescription("Url no válida")
                        .setColor(new Color(252, 3, 3, 255));
                event.replyEmbeds(embed.build()).queue();
                return;
            }

            String protocolSecure = event.getOption("url").getAsString().substring(0, 8);

            if (protocolSecure.equals("https://")) {

                String url = event.getOption("url").getAsString();
                String code = url.substring(25, url.length());

                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Unshorten URL - " + event.getUser().getName())
                        .setDescription(getPetition(code))
                        .setColor(new Color(3, 107, 252));
                event.replyEmbeds(embed.build()).queue();

            } else {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Unshorten URL - " + event.getUser().getName())
                        .setDescription("Url no válida")
                        .setColor(new Color(252, 3, 3, 255));
                event.replyEmbeds(embed.build()).queue();
            }

        }
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        commandDataList.add(Commands.slash("unshorten", "Get source URL")
                .addOption(OptionType.STRING, "url", "URL you want to unshorten", true));
        event.getJDA().updateCommands().addCommands(commandDataList).queue();
    }

}
