import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.ChannelType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.user.User;
import org.javacord.api.entity.webhook.IncomingWebhook;
import org.javacord.api.entity.webhook.Webhook;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.listener.ObjectAttachableListener;
import org.javacord.api.listener.channel.ChannelAttachableListener;
import org.javacord.api.listener.channel.TextChannelAttachableListener;
import org.javacord.api.listener.interaction.*;
import org.javacord.api.listener.message.*;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveAllListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;
import org.javacord.api.listener.user.UserStartTypingListener;
import org.javacord.api.util.cache.MessageCache;
import org.javacord.api.util.event.ListenerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* TODO: JAVACORD - TALK WITH DISCORD THROUGH JAVA
*   https://medium.com/java-playground/discord-bots-with-javacord-how-to-make-your-first-bot-f199bd1f11db
*   https://github.com/Javacord/Javacord
 */

public class stockeR {

    public static void main(String[] args) {

        ArrayList<String> urls = new ArrayList<>();
        // 3070 Ti
        urls.add("https://www.komplett.no/product/1187633/datautstyr/pc-komponenter/skjermkort/gigabyte-geforce-rtx3070ti-gaming-oc?productList=notSet&position=1");
        urls.add("https://www.komplett.no/product/1188790/datautstyr/pc-komponenter/skjermkort/asus-geforce-rtx3070ti-rog-strix?productList=notSet&position=2");
        urls.add("https://www.komplett.no/product/1187972/datautstyr/pc-komponenter/skjermkort/palit-geforce-rtx3070ti-gamerock?productList=notSet&position=3");
        urls.add("https://www.komplett.no/product/1186913/datautstyr/pc-komponenter/skjermkort/msi-geforce-rtx3070ti-suprim-x?productList=notSet&position=4");
        urls.add("https://www.komplett.no/product/1187741/datautstyr/pc-komponenter/skjermkort/zotac-geforce-rtx3070ti-amp-holo?productList=notSet&position=5");
        urls.add("https://www.komplett.no/product/1187938/datautstyr/pc-komponenter/skjermkort/asus-geforce-rtx3070ti-rog-strix-oc?productList=notSet&position=6");
        urls.add("https://www.komplett.no/product/1188520/datautstyr/pc-komponenter/skjermkort/gigabyte-geforce-rtx3070ti-eagle-oc?productList=notSet&position=7");
        urls.add("https://www.komplett.no/product/1187635/datautstyr/pc-komponenter/skjermkort/gigabyte-geforce-rtx3070ti-eagle?productList=notSet&position=8");
        urls.add("https://www.komplett.no/product/1188519/datautstyr/pc-komponenter/skjermkort/gigabyte-geforce-rtx3070ti-aorus-master?productList=notSet&position=9");
        urls.add("https://www.komplett.no/product/1188521/datautstyr/pc-komponenter/skjermkort/gigabyte-geforce-rtx3070ti-vision-oc?productList=notSet&position=10");
        urls.add("https://www.komplett.no/product/1192679/datautstyr/pc-komponenter/skjermkort/zotac-geforce-rtx-3070-ti-amp-extreme-holo?productList=notSet&position=11");
        urls.add("https://www.komplett.no/product/1189113/datautstyr/pc-komponenter/skjermkort/zotac-geforce-rtx3070ti-trinity-oc?productList=notSet&position=12");
        // RX 6800
        urls.add("https://www.komplett.no/product/1174518/datautstyr/pc-komponenter/skjermkort/asus-radeon-rx-6800-tuf-oc?productList=notSet&position=1");
        urls.add("https://www.komplett.no/product/1175822/datautstyr/pc-komponenter/skjermkort/msi-radeon-rx-6800-gaming-x-trio?productList=notSet&position=2");
        urls.add("https://www.komplett.no/product/1182668/datautstyr/pc-komponenter/skjermkort/asrock-radeon-rx6800-challenger-pro-oc?productList=notSet&position=5");
        urls.add("https://www.komplett.no/product/1182667/datautstyr/pc-komponenter/skjermkort/asrock-radeon-rx6800-phantom-gaming-d-oc?productList=notSet&position=6");
        urls.add("https://www.komplett.no/product/1176268/datautstyr/pc-komponenter/skjermkort/sapphire-radeon-rx-6800-pulse?productList=notSet&position=7");
        urls.add("https://www.komplett.no/product/1175707/datautstyr/pc-komponenter/skjermkort/gigabyte-radeon-rx-6800-gaming-oc?productList=notSet&position=8");
        urls.add("https://www.komplett.no/product/1174794/datautstyr/pc-komponenter/skjermkort/sapphire-radeon-rx-6800-xt-nitro-se?productList=notSet&position=9");
        urls.add("https://www.komplett.no/product/1174795/datautstyr/pc-komponenter/skjermkort/sapphire-radeon-rx-6800-nitro?productList=notSet&position=10");
        urls.add("https://www.komplett.no/product/1174516/datautstyr/pc-komponenter/skjermkort/asus-radeon-rx-6800-rog-strix-oc?productList=notSet&position=11");
        urls.add("https://www.komplett.no/product/1174793/datautstyr/pc-komponenter/skjermkort/sapphire-radeon-rx-6800-xt-nitro?productList=notSet&position=12");
        // 3080
        urls.add("https://www.komplett.no/product/1189592/datautstyr/pc-komponenter/skjermkort/asus-geforce-rtx-3080-rog-strix-oc-white?productList=notSet&position=1");
        urls.add("https://www.komplett.no/product/1192640/datautstyr/pc-komponenter/skjermkort/gainward-geforce-rtx3080-phantom?productList=notSet&position=2");
        urls.add("https://www.komplett.no/product/1171801/datautstyr/pc-komponenter/skjermkort/gainward-geforce-rtx-3080-phantom-gs?productList=notSet&position=3");
        urls.add("https://www.komplett.no/product/1171800/datautstyr/pc-komponenter/skjermkort/palit-geforce-rtx-3080-gamerock?productList=notSet&position=4");
        urls.add("https://www.komplett.no/product/1189233/datautstyr/pc-komponenter/skjermkort/asus-geforce-rtx3080-rog-strix-oc-v2?productList=notSet&position=5");
        urls.add("https://www.komplett.no/product/1168128/datautstyr/pc-komponenter/skjermkort/palit-geforce-rtx-3080-gamingpro?productList=notSet&position=6");
        urls.add("https://www.komplett.no/product/1188584/datautstyr/pc-komponenter/skjermkort/zotac-geforce-rtx3080-amp-holo-lhr?productList=notSet&position=7");
        urls.add("https://www.komplett.no/product/1192638/datautstyr/pc-komponenter/skjermkort/gainward-geforce-rtx3080-phantom-gs?productList=notSet&position=8");
        urls.add("https://www.komplett.no/product/1190964/datautstyr/pc-komponenter/skjermkort/asus-geforce-rtx3080-tuf-gaming-v2?productList=notSet&position=9");
        urls.add("https://www.komplett.no/product/1168425/datautstyr/pc-komponenter/skjermkort/palit-geforce-rtx-3080-gamingpro-oc?productList=notSet&position=10");
        urls.add("https://www.komplett.no/product/1171797/datautstyr/pc-komponenter/skjermkort/palit-geforce-rtx-3080-gamerock-oc?productList=notSet&position=11");
        urls.add("https://www.komplett.no/product/1192283/datautstyr/pc-komponenter/skjermkort/msi-geforce-rtx3080-suprim-x-10g?productList=notSet&position=12");

            // 6900XT Controls that program runs through
        urls.add("https://www.komplett.no/product/1188481/datautstyr/pc-komponenter/skjermkort/xfx-radeon-rx6900xt-merc-319-black-ltd?productList=notSet&position=4");

        DiscordApi api = new DiscordApiBuilder().setToken("YOURTOKENHERE").login().join();
        System.out.println(api.createBotInvite());

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")){
                event.getChannel().sendMessage("Scanning websites . . .");
                    for (int i = 0; i < urls.size(); i++) {
                        String stocker = stockeR(urls.get(i));
                        if (stocker!=null)
                            event.getChannel().sendMessage(stocker);
                    }
                event.getChannel().sendMessage("Scanning completed.");
            }
        });
    }

    public static String stockeR(String url) {

        // Exception catcher
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("NULL");
        }

        // Shortens url to get website domain (komplett, proshop, etc)
        String company = url.substring(url.indexOf("w") + 4);
        company = company.split(".no")[0];

        // KOMPLETT
        if (company.equals("komplett")) {
            Elements mainInfo = doc.select(".product-main-info-webtext1");
            Element span = doc.select(".stockstatus-stock-details").first();
            if (span != null) {
                String amountStr = span.text();
                amountStr = amountStr.substring(0, amountStr.indexOf(' '));
                amountStr = amountStr.replaceAll("[-+.^:, ]", "");
                if (!amountStr.equals("Ikke")) {
                    int amount = Integer.parseInt(amountStr);
                    String cardName = mainInfo.text();
                    Element priceEl = doc.select(".product-price-now").first();

                    String priceStr = priceEl.text();
                    priceStr = priceStr.replaceAll("[-+.^:, ]", "");
                    int price = Integer.parseInt(priceStr);

                    //System.out.println("CARD: " + cardName + " - STOCK: " + amount + ", PRICE: " + price + ",-  STORE: Komplett");
                    String output = "Komplett / " + cardName + " / PRICE: " + price + ",- / STOCK: " + amount;
                    return output;
                }
            }

            if (span == null) {
                //System.out.println("CARD: " + mainInfo.text() + " - STOCK: OUT OF STOCK"  + ", PRICE: X" + ",-  STORE: Komplett");
                return null;

            }

        }
    return null;
    }
}

