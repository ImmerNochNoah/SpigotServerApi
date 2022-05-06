package de.immernochnoah.api.file_system;

public class Config_File {

    public void createConfig() {

        File_Manager fm = new File_Manager();

        if(!fm.configIsExisting("MYSQL")) {
            fm.addConfigText("Host", "localhost");
            fm.addConfigText("Port", "3306");
            fm.addConfigText("Database", "api");
            fm.addConfigText("User", "root");
            fm.addConfigText("Password", "");
            fm.saveConfig("MYSQL");
        }
        if(!fm.configIsExisting("PERMISSIONS")) {
            fm.addConfigText("Reload", "api-reload");
            fm.saveConfig("PERMISSIONS");
        }
        if(!fm.configIsExisting("PREFIX")) {
            fm.addConfigText("Server Prefix", "&e&lSystem &8●");
            fm.saveConfig("PREFIX");
        }
        if(!fm.configIsExisting("LEVEL SYSTEM")) {
            fm.addConfigText("Next Level XP", "75");
            fm.addConfigText("Next XP Multiplier", "1.2");
            fm.addConfigText("Next Level Text", "&7Du bist nun &aLevel %s!");
            fm.saveConfig("LEVEL SYSTEM");
        }
        if(!fm.configIsExisting("MESSAGES")) {
            fm.addConfigText("Reload", "&aDie API wurde erfolgreich neu geladen.");
            fm.addConfigText("No Permission", "&cDazu hast du keine Rechte!");
            fm.saveConfig("MESSAGES");
        }
    }
}
