package net.seleniummc.mcchet;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.seleniummc.mcchet.events.PlayerAdvancementListener;
import net.seleniummc.mcchet.events.PlayerChatListener;
import net.seleniummc.mcchet.events.PlayerIOListener;
import net.seleniummc.mcchet.utils.SlackUtil;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = MCChet.MODID, name = MCChet.NAME, version = MCChet.VERSION)
public class MCChet
{

    // credits to xwerswoodx on forge forums for config code
    public static Configuration config;
    private static String file = "config/MCChet.cfg";
    public static final String MODID = "mc_chet_slack";
    public static final String NAME = "MC Chet Slack";
    public static final String VERSION = "Alpha 1.0";

    private static Logger logger;
    public static SlackUtil slackUtil;

//    public static void main(String[] args) throws IOException, SlackApiException, Exception {
//        slackUtil = new SlackUtil();
//        slackUtil.initSlack();
//
//    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) throws Exception {
        config = new Configuration(new File(file));
        slackUtil = new SlackUtil();
        try {
            config.load();
        } catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        } finally {
            config.save();
        }

        MinecraftForge.EVENT_BUS.register(new PlayerChatListener(this));
        MinecraftForge.EVENT_BUS.register(new PlayerIOListener(this));
        MinecraftForge.EVENT_BUS.register(new PlayerAdvancementListener(this));
        slackUtil.sendMessage("Mod initialized","https://emoji.slack-edge.com/T0266FRGM/snakecat/41d90e5cd1fbf2ce.png", "Minecraft Chat(Modded)");

        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    slackUtil.initSlack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static String getString(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, "").getString();
            }
        } catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        } finally {
            config.save();
        }
        return "";
    }


}
