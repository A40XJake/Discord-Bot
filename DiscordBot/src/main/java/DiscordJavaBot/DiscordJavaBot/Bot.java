package DiscordJavaBot.DiscordJavaBot;

import javax.security.auth.login.LoginException;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Bot extends ListenerAdapter {

	// Allows to use env file for bot tokens to use here without showing the token
	private final Dotenv config;
	// Allows for the shard manager to be built,
	// final doesn't allow the variable to be changed, variable for use in other
	// classes
	private final ShardManager shardManager;

	// loads variables and builds the bot shard manager & constructor for the bot
	// adds LoginException & Interrupted Exception to handle unexpected error like
	// invalids token etc.
	public Bot() throws LoginException, InterruptedException {
		// loads the env file from the root directory
		config = Dotenv.configure().ignoreIfMissing().load();

		// gets the discord token from the .env file
		String token = config.get("TOKEN");

		// Builds the shards that the bot runs on, allows the bot to run on several
		// instances, the bot itself.
		// jda takes in the bot token and points to the bot itself
		DefaultShardManagerBuilder jda = DefaultShardManagerBuilder.createDefault(token);

		// sets status in discord of the bot on the server section
		jda.setStatus(OnlineStatus.ONLINE);

		// sets the activity on discord that the bot is doing while online;
		jda.setActivity(Activity.listening("/whatever"));
		
		//Allows for bot to send messages and introduce people
		jda.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES);
		
		//Caches users to work with with some commands that are overwritten
		jda.setMemberCachePolicy(MemberCachePolicy.ALL);
		//Forces bot to cache all user on start instead of lazy updating
		jda.setChunkingFilter(ChunkingFilter.ALL);
		//Stores online status of all users of servers the bot is on
		jda.enableCache(CacheFlag.ONLINE_STATUS);

		// builds the actually shard manager
		shardManager = jda.build();

		// adds event listeners for slash commands from Commands class to use in the bot
		shardManager.addEventListener(new commandManager());
	}

	// gets the bot environment variables, for other classes
	public Dotenv getConfig() {
		return config;
	}

	// returns the bot shard manager, retrieves the shard manager bot, for other
	// classes
	public ShardManager getShardManager() {
		return shardManager;
	}

	// Constructs the bot to run
	public static void main(String[] args) throws LoginException, InterruptedException {
		Bot bot = new Bot();
	}
}
