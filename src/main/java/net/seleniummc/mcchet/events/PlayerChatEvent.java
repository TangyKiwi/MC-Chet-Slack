package net.seleniummc.mcchet.events;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.seleniummc.mcchet.MCChet;

import java.text.MessageFormat;

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
            mcchet.slackUtil.sendMessage(MessageFormat.format("MC => {0}: {1}", e.getPlayer().getDisplayNameString(), e.getMessage()), "CD1JSG9UK");
            System.out.println("funnycode ran");
        } catch (Exception exception) {
            System.out.println("ono my code broke and a exception has ben cat");
            exception.printStackTrace();
        }

    }
}
