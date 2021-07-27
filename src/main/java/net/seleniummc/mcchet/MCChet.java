package net.seleniummc.mcchet;


import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
    public static final String VERSION = "1.0";

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
        try {
            config.load();
        } catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        } finally {
            config.save();
        }

        slackUtil = new SlackUtil();
        slackUtil.initSlack();
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
