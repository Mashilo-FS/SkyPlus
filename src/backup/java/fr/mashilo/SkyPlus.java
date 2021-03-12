package fr.mashilo;

import fr.mashilo.adapts.multithreading.BazaarPricesUpdater;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class SkyPlus{

    public static void main(String[] args) throws Exception {

        DefaultShardManagerBuilder client = DefaultShardManagerBuilder.createDefault(Config.TOKEN);

        client.disableCache(CacheFlag.ACTIVITY);
        client.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING);

        client.addEventListeners(new Listener());

        client.setStatus(OnlineStatus.ONLINE);
        client.setActivity(Activity.watching("Mashilo qui me programme :)"));

        client.build();

        HypixelRateLimiter();
    }

    public static synchronized void HypixelRateLimiter() throws InterruptedException {
        Thread thread = new Thread();
        thread.setPriority(2);
        thread.start();
        thread.join(4000);
        while(true){
            SkyblockInfosUpdater SIU = new SkyblockInfosUpdater();
            BazaarPricesUpdater BPU = new BazaarPricesUpdater();
            Thread.sleep(120000);
        }
    }

}
