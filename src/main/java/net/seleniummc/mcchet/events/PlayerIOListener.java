package net.seleniummc.mcchet.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.seleniummc.mcchet.MCChet;

import java.text.MessageFormat;

public class PlayerIOListener {
    private static MCChet mcchet;
    public static String console_avatar = "https://files.slack.com/files-tmb/T0266FRGM-F029M783RFF-77e6a14fee/image-removebg-preview__1__480.png";

    public PlayerIOListener(MCChet mcchet) {
        this.mcchet = mcchet;
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent e) {
        try {
            mcchet.slackUtil.sendMessage(MessageFormat.format("Joined the game", e.player.getDisplayNameString()), console_avatar , "Server");
        } catch (Exception ee){
            ee.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        try {
            mcchet.slackUtil.sendMessage(MessageFormat.format("Left the game", e.player.getDisplayNameString()), console_avatar, "Server");
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
