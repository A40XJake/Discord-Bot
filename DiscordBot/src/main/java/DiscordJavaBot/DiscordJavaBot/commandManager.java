package DiscordJavaBot.DiscordJavaBot;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class commandManager extends ListenerAdapter {

	/*
	 * Slash Commands, adds the slash commands to discord
	 */
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
		String command = event.getName();

		// listens or waits for the '/welcome' command
		if (command.equals("welcome")) {
			// gets the user tag that joined the server
			String userTag = event.getUser().getAsTag();
			// Bot replies to the command on the server with a rest interaction through
			// REST/queue
			event.reply("Welcome to the server, **" + userTag + "**!").queue();

			// listens or waits for the '/say' command
		} else if (command.equals("say")) {
			OptionMapping messageOption = event.getOption("message");
			OptionMapping channelOption = event.getOption("channel");

			String message = messageOption.getAsString();
			MessageChannel channel;

			if (channelOption != null) {
				channel = channelOption.getAsMessageChannel();
			} else {
				channel = event.getChannel();
			}

			channel.sendMessage(message).queue();
			event.reply("Your message was sent!").setEphemeral(true).queue();

			// listens or waits for the '/tftcomp' command
		} else if (command.equals("tftcomp")) {
			OptionMapping option = event.getOption("type");
			String type = option.getAsString();

			String replyMessage = "";
			switch (type.toLowerCase()) {
			case "mage": {
				replyMessage = "**Early Game:** Nami, Tristana, Heimerdinger, and Vladimir." + "\n"
						+ "**Mid Game:** Ryze, Sylas, Lulu, Nami, Heimerdinger, and Vladimir." + "\n"
						+ "**Late Game:** Ao Shin, Sylas, Zoe, Ornn, Bard, Lulu, and Heimerdinger." + "\n"
						+ "**Carousel Priority:** Bow > Blue > Belt > Sword > Rod." + "\n"
						+ "--------------------------------------------------------" + "\n"
						+ "**Champion Items:** Zoe> Statikk Shiv, Sylas> 2 x Zz'Rot Portal, Ornn> Zz'Rot Portal, Ao Shin>(Spear of Shojin, Archangel's Staff, Giant Slayer)."
						+ "\n" + "--------------------------------------------------------" + "\n"
						+ "**Augments:** Teir1> Celestial Blessing, Teir2> Thrill of Hunt,and Teir3> Mage Crown." + "\n"
						+ "--------------------------------------------------------" + "\n"
						+ "**Game plan:** Level to 8 as quickly as possible." + "\n";
			}
			case "jade": {
				replyMessage = "**Early Game:** Ashe, Karma, Taric, and Braum." + "\n"
						+ "**Mid Game:** Taric, Anivia, Lulu, Braum, Gnar, and Nami." + "\n"
						+ "**Late Game:** Shi Oh Yu, Neeko, Anivia, Bard, Lulu, Gnar, and Nami." + "\n"
						+ "**Carousel Priority:** Sword > Chest Mail > Rod > Belt > Bow." + "\n"
						+ "--------------------------------------------------------" + "\n"
						+ "**Champion Items:** Anivia> (Archangel's Staff & Morellonomicon), Neeko> (Garg Plate & Warmog's), Shi Oh Yu> (Edge of Night, Titan's, Giant Slayer)."
						+ "\n" + "--------------------------------------------------------" + "\n"
						+ "**Augments:** Teir1> Jade Crest, Teir2> Penitence,and Teir3> Jade Soul." + "\n"
						+ "--------------------------------------------------------" + "\n"
						+ "**Game plan:** Level to 7 and roll for Shi Oh Yu." + "\n";
			}
			case "ragewing": {
				replyMessage = "**Early Game:** Ashe, Karma, Taric, and Sett." + "\n"
						+ "**Mid Game:** Shen, Karma, Swain, Ashe, Gnar, and Sett." + "\n"
						+ "**Late Game:** Shyvana, Neeko, Xayah, Hacarim, Swain, Ashe, and Gnar." + "\n"
						+ "**Carousel Priority:** Rod > Belt > Blue > Cloak > Chest Mail." + "\n"
						+ "--------------------------------------------------------" + "\n"
						+ "**Champion Items:** Xayah> (Giant Slayer, Hand of Justice, Quicksilver), Neeko> (Warmog's, Redemption, Garg Plate), Shyvana> (Ionic, Morellonomicon, Archangel's Staff)  ."
						+ "\n" + "--------------------------------------------------------" + "\n"
						+ "**Augments:** Teir1> Celestial Blessing, Teir2> Portable Forge,and Teir3> Dragonmancer Crown."
						+ "\n" + "--------------------------------------------------------" + "\n"
						+ "**Game plan:** Level to 8 as quickly as possible." + "\n";
			}
			case "dragon": {
				replyMessage = "**Early Game:** Jinx, Tahm Kench, Tristana, and Heimerdinger." + "\n"
						+ "**Mid Game:** Jinx, Tahm Kench, Lulu, Shen, Tristana, and Senna." + "\n"
						+ "**Late Game:** Daeja, Idas, Shi Oh Yu, Bard, Soraka, and Leona." + "\n"
						+ "**Carousel Priority:** Bow > Sword > Rod > Chest Mail > Cloak." + "\n"
						+ "--------------------------------------------------------" + "\n"
						+ "**Champion Items:** Shi Oh Yu> (Blood, Edge of Night, Giant Slayer), Idas> (Garg Plate, Dragon's Claw, Sunfire), Daeja> (Guinsoo's, Statikk Shiv, Archangel's Staff)."
						+ "\n" + "--------------------------------------------------------" + "\n"
						+ "**Augments:** Teir1> Thrill of Hunt, Teir2> Dragon Alliance, and Teir3> Highend Shopping."
						+ "\n" + "--------------------------------------------------------" + "\n"
						+ "**Game plan:** Level to 8 as quickly as possible to roll for Dragons." + "\n";
				;
			}
			}
			event.reply(replyMessage).setEphemeral(true).queue();

			// listens or waits for the '/roles' command
		} else if (command.equals("roles")) {
			// has discord wait longer than 3 seconds for reply as it can take time
			event.deferReply().queue();
			String response = "";
			// Gets all roles of users on the server
			for (Role role : event.getGuild().getRoles()) {
				response += role.getAsMention() + "\n";
			}
			// uses getHook to send deferred message
			event.getHook().sendMessage(response).queue();

			// listens or waits for the '/giverole' command
		} else if (command.equals("giverole")) {
			Member member = event.getOption("user").getAsMember();
			Role role = event.getOption("role").getAsRole();

			event.getGuild().addRoleToMember(member, role).queue();
			event.reply(member.getAsMention() + " has been assigned to " + role.getAsMention() + "role!").queue();

			// listens or waits for the '/roleusers' command
		} else if (command.equals("roleusers")) {
			OptionMapping option = event.getOption("userrole");
			String userRole = option.getAsString();

			List<Role> roles = event.getGuild().getRolesByName(userRole, true);
			List<Member> members = event.getGuild().getMembersWithRoles(roles);
			// has discord wait longer than 3 seconds for reply as it can take time
			event.deferReply().queue();
			String response = "";
			// Gets all roles of users on the server
			for (Member member : members) {
				response += member.getAsMention() + "\n";
			}
			// uses getHook to send deferred message
			event.getHook().sendMessage(response).queue();
		}

	}

	/*
	 * Guild Commands update instantly
	 */

	// Adds guild commands for guilds that have the bot already on bot startup
	public void onGuildReady(@NotNull GuildReadyEvent event) {

		// stores data for commands for each server it's in
		List<CommandData> commandData = new ArrayList<>();

		// adds commands to bot by taking in the name of the command and a description
		// for it.
		/* Command: /welcome, bot welcomes */
		commandData.add(Commands.slash("welcome", "Get welcomed by the bot."));

		// Command that sends a <message>, true requires that the user has to add a
		// message
		/* Command: /say, bot says a custom message */
		OptionData option1 = new OptionData(OptionType.STRING, "message", "Message that the bot will say.", true);
		OptionData option2 = new OptionData(OptionType.CHANNEL, "channel", "Channel you want to send the message in.",
				false).setChannelTypes(ChannelType.TEXT, ChannelType.NEWS, ChannelType.GUILD_PUBLIC_THREAD);
		commandData.add(Commands.slash("say", "Makes the bot say a message.").addOptions(option1, option2));

		/* Command: /tftcomp, bot gives different TFT comps and displays in chat */
		OptionData option3 = new OptionData(OptionType.STRING, "type", "Starting comp TFT", true)
				.addChoice("Mage", "mage").addChoice("Jade", "jade").addChoice("Ragewing", "ragewing")
				.addChoice("Dragon", "dragon");
		commandData.add(Commands.slash("tftcomp", "Quick TFT comps to text").addOptions(option3));
		event.getGuild().updateCommands().addCommands(commandData).queue();

		/* Command: /roles, bot displays all role on server */
		commandData.add(Commands.slash("roles", "Displays all roles on the server."));

		/* Command: /giverole, bot assigns a user a role */
		OptionData option4 = new OptionData(OptionType.USER, "user", "User to give role to.", true);
		OptionData option5 = new OptionData(OptionType.ROLE, "role", "Role being assigned.", true);
		commandData.add(Commands.slash("giverole", "Assign a role for user.").addOptions(option4, option5));

		/*
		 * Command: /roleusers, bot gives names of user in assigned role, WORK IN
		 * PROGRESS
		 */
		OptionData option6 = new OptionData(OptionType.ROLE, "userrole", "Role looking for.", true);
		commandData.add(Commands.slash("roleusers", "Get users of given role.").addOptions(option6));

		// updates commands on the given guild/server the bot is on
		event.getGuild().updateCommands().addCommands(commandData).queue();
	}

	// Adds guild commands for the bot that guilds add later, after bot start up
	public void onGuildJoin(@NotNull GuildJoinEvent event) {
		// stores data for commands
		List<CommandData> commandData = new ArrayList<>();
		commandData.add(Commands.slash("welcome", "Get welcomed by the bot."));
		commandData.add(Commands.slash("say", "Bot says custom message."));
		commandData.add(Commands.slash("tftcomp", "Displays tft comps in private text."));
		commandData.add(Commands.slash("roles", "Displays all roles on the server."));
		commandData.add(Commands.slash("giverole", "Assigns user a role."));
		commandData.add(Commands.slash("roleusers", "Displays all users in given role."));
		event.getGuild().updateCommands().addCommands(commandData).queue();
	}

	/*
	 * Global Commands update can take an hour through Discord
	 */

	public void onReady(@NotNull ReadyEvent event) {
		List<CommandData> commandData = new ArrayList<>();
		commandData.add(Commands.slash("welcome", "Get welcomed by the bot."));
		event.getJDA().updateCommands().addCommands(commandData).queue();
	}

	/*
	 * eventListners, that reply when certain flags are triggered through reactions,
	 * messages, join, etc.
	 */

	// if a user reacts to message state the emote they reacted with and a jump link
	// to the message
	public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
		// User represents a discord user
		User user = event.getUser();
		// gets reaction/emote on a message from user
		String emote = event.getReaction().getReactionEmote().getAsReactionCode();
		// gets what channel the reaction was mentioned in
		String channelMention = event.getChannel().getAsMention();
		// gets a hyperlink to where the message happened
		String jumpLink = event.getJumpUrl();
		// sends message of what user did the reaction
		String message = user.getAsTag() + " reacted to a message with " + emote + " in the " + channelMention
				+ " channel!";
		event.getGuild().getDefaultChannel().sendMessage(message).queue();

	}

	// replies back when certain words are replies to in the server
	public void onMessageReceived(@NotNull MessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		// if the author is a bot, dont reply
		if (event.getAuthor().isBot()) {
			return;
		} else if (message.contains("Bing") || message.contains("bing")) {
			event.getChannel().sendMessage("Bing bong, fuck ya life!").queue();
		}
	}

	// prints out url of the user that joins the server/guild
	public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
		String avatar = event.getUser().getEffectiveAvatarUrl();
		System.out.println(avatar);
	}

	// Event that trigger when a user updates their status, requires cache & intent
	// to work
	public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {
		List<Member> members = event.getGuild().getMembers();
		int onlineMember = 0;
		for (Member member : members) {
			if (member.getOnlineStatus() == OnlineStatus.ONLINE) {
				onlineMember++;
			}
		}
		// needs user cache to run
		User user = event.getUser();
		String message = "**" + user.getAsTag() + "** updated their online status to " + event.getNewOnlineStatus()
				+ "!";
		event.getGuild().getDefaultChannel().sendMessage(message).queue();

		// retrieve method that works instead of caching, requires a REST action
		// event.getJDA().retrieveUserById(278679411044253696).queue(user -> {
		// });
	}

}
