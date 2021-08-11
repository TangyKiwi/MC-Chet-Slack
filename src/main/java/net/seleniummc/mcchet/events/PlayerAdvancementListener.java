package net.seleniummc.mcchet.events;

import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.seleniummc.mcchet.MCChet;
import net.seleniummc.mcchet.utils.SlackUtil;

import java.text.MessageFormat;

public class PlayerAdvancementListener {
    private static MCChet mcchet;

    public PlayerAdvancementListener(MCChet mcchet) {
        this.mcchet = mcchet;
    }

    @SubscribeEvent
    public void onAdvancement(AdvancementEvent e) {
        try {
            SlackUtil.sendMessage(
                    MessageFormat.format(
                    "*{0}* has just made the advancement **{1}** : **{2}**",
                    e.getEntityPlayer().
                    getDisplayNameString(),
                            e.getAdvancement().toString()
                    ),
                    SlackUtil.getPlayerAvatarLink(e.getEntityPlayer().getUniqueID().toString()),
                    e.getEntityPlayer().getDisplayNameString()
            );
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
