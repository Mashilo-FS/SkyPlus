package fr.mashilo;

import fr.mashilo.adapts.TimeAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.sql.Time;

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
                        case "timeConvert":
                            if (args.length <= 2){
                                e.getChannel().sendMessage("Que veux-tu tester ?").queue();
                            } else {
                                e.getChannel().sendMessage("Cela vaux " + new TimeAdapter().convert(args[2]) + " secondes. Selon moi xD").queue();
                            }
                            break;

                        case "test":
                            try {
                                Minion minion = new Minion("CHICKEN");
                                e.getChannel().sendMessage(minion.item_summary(2, new TimeAdapter().convert("5d")).toString()).queue();
                            } catch (IOException ex) {
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
                                            e.getChannel().sendMessage(customEmbeds.embedMinion(minion, 1).build()).queue();
                                        }catch (Exception execption){
                                            System.out.println("Un probleme est survenu (Commande minionInfo 0)");
                                        }
                                    } else {
                                        try{
                                            e.getChannel().sendMessage(customEmbeds.embedMinion(minion, Integer.parseInt(args[3])).build()).queue();
                                        }catch (Exception execption){
                                            System.out.println("Un probleme est survenu (Commande minionTier 1)");
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
                        case "emojiTest":
                            e.getChannel().sendMessage("\uD83D\uDC36").queue();
                            break;

                        default:
                            e.getChannel().sendMessage("Je n'ai pas compris votre demande").queue();
                    }

                }

            }

        }

    }

}
