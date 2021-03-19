package fr.mashilo;

import fr.mashilo.adapts.multithreading.BazaarPricesUpdater;
import fr.mashilo.adapts.multithreading.SkyblockInfosUpdater;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class SkyPlus{

    public static void main(String[] args) throws Exception {

        ShardManager client = DefaultShardManagerBuilder.createDefault(Config.TOKEN)
                .setShardsTotal(1)

                .setAutoReconnect(true)
                .setBulkDeleteSplittingEnabled(false)

                .disableCache(CacheFlag.ACTIVITY)
                .disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING)

                .addEventListeners(new Listener())

                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("\uD83D\uDC36 Doge to the moon \uD83C\uDF11                       Need help? Do /sky help                           I need money. Do /sky donate"))

                .build();

        HypixelAutoUpdater();

    }

    public static synchronized void HypixelAutoUpdater() throws InterruptedException {
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
