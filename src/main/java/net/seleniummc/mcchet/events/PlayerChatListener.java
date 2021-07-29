package net.seleniummc.mcchet.events;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.seleniummc.mcchet.MCChet;

public class PlayerChatListener {
    private static MCChet mcchet;

    public PlayerChatListener(MCChet mcchet) {
        this.mcchet = mcchet;
    }

    @SubscribeEvent
    public void onChat(ServerChatEvent e) {
        System.out.println("chet");
        try {
            System.out.println("try to run funny code");
            mcchet.slackUtil.sendMessage(e.getMessage(), "http://cravatar.eu/avatar/" + e.getPlayer().getUniqueID(), e.getPlayer().getDisplayNameString());
            System.out.println("funnycode ran");
        } catch (Exception exception) {
            System.out.println("ono my code broke and a exception has ben cat");
            exception.printStackTrace();
        }

    }
}
