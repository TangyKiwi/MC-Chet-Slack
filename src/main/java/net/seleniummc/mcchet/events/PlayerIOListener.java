package net.seleniummc.mcchet.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.seleniummc.mcchet.MCChet;

import java.text.MessageFormat;

public class PlayerIOListener {
    private static MCChet mcchet;

    public PlayerIOListener(MCChet mcchet) {
        this.mcchet = mcchet;
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent e) {
        try {
            mcchet.slackUtil.sendMessage(MessageFormat.format("Joined the game", e.player.getDisplayNameString()),  mcchet.slackUtil.getPlayerAvatarLink(e.player.getUniqueID().toString()), e.player.getDisplayNameString());

        } catch (Exception ee){
            ee.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        try {
            mcchet.slackUtil.sendMessage(MessageFormat.format("Left the game", e.player.getDisplayNameString()),  mcchet.slackUtil.getPlayerAvatarLink(e.player.getUniqueID().toString()), e.player.getDisplayNameString());
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
