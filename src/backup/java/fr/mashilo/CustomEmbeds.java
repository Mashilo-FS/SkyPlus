package fr.mashilo;

import net.dv8tion.jda.api.EmbedBuilder;
import org.json.JSONArray;

import java.awt.*;

import static fr.mashilo.Config.PREFIX;

public class CustomEmbeds {

    private StringBuilder generatedRessourcesBuilder(Minion minion) {
        JSONArray generated_items = minion.getGenerated_items();
        JSONArray generated_quantity = minion.getQuantity();

        StringBuilder generated = new StringBuilder();
        for (int item = 0 ; item < generated_items.length() ; item++){
            generated
                    .append("__")
                    .append(generated_items.getString(item))
                    .append("__ : ")
                    .append(Double.valueOf(generated_quantity.getDouble(item) * 100))
                    .append("%\n");
        }
        return generated;
    }

    public EmbedBuilder embedMinion(Minion minion, String minionTier){
        String minionView = "https://firebasestorage.googleapis.com/v0/b/skyplus-9905.appspot.com/o/minions%2Fassets%2F" + minion.getName().toLowerCase() + "%2FMinion.png?alt=media";
        String minionCraft = "https://firebasestorage.googleapis.com/v0/b/skyplus-9905.appspot.com/o/minions%2Fassets%2F" + minion.getName().toLowerCase() + "%2FTier" + minionTier + ".png?alt=media";

        long cooldown = minion.getCooldown(Integer.parseInt(minionTier));
        int slots = minion.getSlots(Integer.parseInt(minionTier));

        StringBuilder generated = generatedRessourcesBuilder(minion);

        return new EmbedBuilder().setTitle("Minion Info - __**" + minion.getName() + "** Tier " + minionTier + "__")
                .setDescription("Informations about __" + minion.getName() +"__ Minion \n__Commande :__ " + PREFIX + " minion <minion> [tier]")
                .setColor(new Color(14381312))
                .setFooter("SkyPlus Bot | Third-part software | not affiliated", "https://cdn.discordapp.com/avatars/811645919937298483/282a00834c910de512b2abe2053ccb09.png")
                .setThumbnail(minionView)
                .setImage(minionCraft)
                .setAuthor("SkyPlus | Hypixel Skyblock Utility", "https://skyplus.fr", "https://cdn.discordapp.com/avatars/811645919937298483/282a00834c910de512b2abe2053ccb09.png")
                .addField("🖨️ Items Generated", generated.toString(), false)
                .addField("🕐 Cooldown", "__" + Long.toString(cooldown) +" seconds__ (between place and break/kill)", false)
                .addField("📦 Storage", "__" + Integer.toString(slots) + " slots__\nor " + Integer.toString(slots*64) + " items", false)
                .addField("✂️ Recipe", "[]()", false);
    }
}
