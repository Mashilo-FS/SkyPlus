package fr.mashilo;

import fr.mashilo.adapts.JSONAdapter;
import fr.mashilo.adapts.multithreading.BazaarPricesUpdater;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Listener extends ListenerAdapter implements Config{

    public void onMessageReceived(MessageReceivedEvent e){
        if (!e.getAuthor().isBot()) {
            String msg = e.getMessage().getContentRaw();
            String[] args = msg.split(" ");

            if (msg.startsWith(PREFIX)){
                if (args.length <= 1){
                    e.getChannel().sendMessage("Reste tranquille. Je suis suppérieur à toi, tu ne sais pas m'utiliser").queue();
                } else{
                    switch (args[1]) {
                        case "test":
                            if (args.length <= 2){
                                e.getChannel().sendMessage("Que veux-tu tester ?").queue();
                            } else {
                                switch (args[2]) {
                                    case "japanese":
                                        e.getChannel().sendMessage("私は何かをテストします").queue();
                                        break;

                                    default:
                                        e.getChannel().sendMessage("Je n'ai pas compris").queue();
                                        break;
                                }
                            }
                            break;

                        case "bpu":
                            BazaarPricesUpdater BPU = new BazaarPricesUpdater();

                            break;

                        case "apiTest":
                            try{
                                e.getChannel().sendMessage("Début de la procédure").queue();
                                JSONAdapter.JSONWriter(Config.ITEMS_FOLDER, "", "", 0, "");
                                e.getChannel().sendMessage("Va regarder tes fichiers frero").queue();
                            } catch (IOException ex) {
                                e.getChannel().sendMessage("Une erreur est survenue").queue();
                                ex.printStackTrace();
                            }
                            break;

                        case "minion":
                            if (args.length <= 2){
                                e.getChannel().sendMessage("Merci de préciser le minion dont tu as besoin de plus d'informations").queue();
                            } else { try {
                                    Minion minion = new Minion(args[2].toUpperCase());
                                    CustomEmbeds customEmbeds = new CustomEmbeds();
                                    if (args.length <= 3){
                                        try{
                                            e.getChannel().sendMessage(customEmbeds.embedMinion(minion, "1").build()).queue();
                                        }catch (Exception execption){
                                            System.out.println("Un probleme est survenu (Commande minionInfo)");
                                        }
                                    } else {
                                        try{
                                            e.getChannel().sendMessage(customEmbeds.embedMinion(minion, args[3]).build()).queue();
                                        }catch (Exception execption){
                                            System.out.println("Un probleme est survenu (Commande minionTier)");
                                        }

                                    }

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }

                            }
                            break;
                        case "item":
                            if (args.length <= 2){
                                e.getChannel().sendMessage("Merci de préciser l'item").queue();
                            } else{ try{
                                    Item item = new Item(args[2]);
                                    e.getChannel().sendMessage(item.getID()).queue();
                                } catch (IOException exception){
                                    exception.printStackTrace();
                                }
                            }
                            break;
                        case "minionParse":

                            File minions = new File(Config.MINIONS_FOLDER + "\\minions.json");
                            File directory = new File(Config.MINIONS_FOLDER);

                            try {
                                JSONObject jsonMinions = JSONAdapter.JSONReader(minions.getPath());

                                for (String minion : jsonMinions.keySet()){
                                    FileWriter fileWriter = new FileWriter(directory + minion + ".json");
                                    fileWriter.write(
                                            JSONAdapter.JSONReader(minions.getPath()).getJSONObject(minion).toString(3)
                                    );
                                    fileWriter.flush();
                                    fileWriter.close();
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            break;

                        default:
                            e.getChannel().sendMessage("Je n'ai pas compris votre demande").queue();
                    }

                }

            }

        }

    }

}
