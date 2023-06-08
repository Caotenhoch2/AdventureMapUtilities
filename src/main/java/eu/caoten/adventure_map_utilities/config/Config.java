package eu.caoten.adventure_map_utilities.config;

import eu.caoten.adventure_map_utilities.Main;

import java.io.*;

public class Config {
    public static boolean KEY1 = true;
    public static boolean KEY2 = true;
    public static boolean KEY3 = true;
    public static boolean AUTOMATIC_DISABLING = true;
    public static boolean SHOW_AMUBUTTON = true;

    public static File CONFIG = new File("config/AMU-Config.properties");

    public static void Write() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG));
            writer.write(Main.MOD_ID + ":" + Main.MOD_VERSION + "\n");
            writer.write("options.adventure_map_utilities.key1=" + KEY1 + "\n");
            writer.write("options.adventure_map_utilities.key2=" + KEY2 + "\n");
            writer.write("options.adventure_map_utilities.key3=" + KEY3 + "\n");
            writer.write("options.adventure_map_utilities.automaticdisabling=" + AUTOMATIC_DISABLING + "\n");
            writer.write("options.adventure_map_utilities.showamubutton=" + SHOW_AMUBUTTON + " #This can be changed manually!");
            writer.close();
            Main.LOGGER.info("[AMU] Wrote Config!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Read() {
        if (CONFIG.exists() && CONFIG.isFile()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(CONFIG));
                String line1 = reader.readLine();
                String line2 = reader.readLine();
                String line3 = reader.readLine();
                String line4 = reader.readLine();
                String line5 = reader.readLine();
                String line6 = reader.readLine();
                reader.close();
                KEY1 = line2.equals("options.adventure_map_utilities.key1=true");
                KEY2 = line3.equals("options.adventure_map_utilities.key2=true");
                KEY3 = line4.equals("options.adventure_map_utilities.key3=true");
                AUTOMATIC_DISABLING = line5.equals("options.adventure_map_utilities.automaticdisabling=true");
                SHOW_AMUBUTTON = line6.equals("options.adventure_map_utilities.showamubutton=true #This can be changed manually!");
                Main.LOGGER.info("[AMU] Read Config!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Write();
        }
    }

}
