package net.seleniummc.mcchet.events;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.seleniummc.mcchet.MCChet;

public class PlayerChatEvent {
    private static MCChet mcchet;

    public PlayerChatEvent(MCChet mcchet) {
        this.mcchet = mcchet;
    }

    @SubscribeEvent
    public void onChat(ServerChatEvent e) {
        System.out.println("chet");
        try {
            System.out.println("try to run funny code");
            mcchet.slackUtil.sendMessage(e.getMessage(), "CD1JSG9UK", "https://emoji.slack-edge.com/T0266FRGM/snakecat/41d90e5cd1fbf2ce.png", "snake cat bot");
            System.out.println("funnycode ran");
        } catch (Exception exception) {
            System.out.println("ono my code broke and a exception has ben cat");
            exception.printStackTrace();
        }

    }
}
