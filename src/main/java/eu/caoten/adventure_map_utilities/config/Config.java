package eu.caoten.adventure_map_utilities.config;

import eu.caoten.adventure_map_utilities.Main;

import java.io.*;

public class Config {
    public static boolean KEY1 = true;
    public static boolean KEY2 = true;
    public static boolean KEY3 = true;
    public static boolean AUTOMATICDISABLING = true;
    public static boolean SHOWAMUBUTTON = true;

    public static File CONFIG = new File("config/AMU-Config.properties");

    public static void Write() throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG));
            writer.write(Main.MOD_ID + ":" + Main.MOD_VERSION + "\n");
            writer.write("options.adventure_map_utilities.key1=" + KEY1 + "\n");
            writer.write("options.adventure_map_utilities.key2=" + KEY2 + "\n");
            writer.write("options.adventure_map_utilities.key3=" + KEY3 + "\n");
            writer.write("options.adventure_map_utilities.automaticdisabling=" + AUTOMATICDISABLING + "\n");
            writer.write("options.adventure_map_utilities.showamubutton=" + SHOWAMUBUTTON + " #This can be changed manually!");
            writer.close();
            Main.LOGGER.info("[AMU] Wrote Config!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Read() throws IOException {
        if (CONFIG.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(CONFIG));
                String line1 = reader.readLine();
                String line2 = reader.readLine();
                String line3 = reader.readLine();
                String line4 = reader.readLine();
                String line5 = reader.readLine();
                String line6 = reader.readLine();
                reader.close();
                if (!line1.equals(Main.MOD_ID + ":" + Main.MOD_VERSION)) {
                    Main.LOGGER.info("[AMU] Altered or old config detected! Rewriting config...");
                    Write();
                    return;
                }
                KEY1 = line2.equals("options.adventure_map_utilities.key1=true");
                KEY2 = line3.equals("options.adventure_map_utilities.key2=true");
                KEY3 = line4.equals("options.adventure_map_utilities.key3=true");
                AUTOMATICDISABLING = line5.equals("options.adventure_map_utilities.automaticdisabling=true");
                SHOWAMUBUTTON = line6.equals("options.adventure_map_utilities.showamubutton=true #This can be changed manually!");
                Main.LOGGER.info("[AMU] Read Config!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Write();
        }
    }

}
