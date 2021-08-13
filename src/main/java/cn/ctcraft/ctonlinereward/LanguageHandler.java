package cn.ctcraft.ctonlinereward;

import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageHandler {
    private final CtOnlineReward ctOnlineReward = CtOnlineReward.getPlugin(CtOnlineReward.class);
    private final YamlConfiguration langYaml = CtOnlineReward.lang;
    private final String prefix;

    public LanguageHandler() {
        prefix = langYaml.getString("prefix");
    }

    public String getLang(String key) {
        String string = langYaml.getString(key);
        return string.replace("&", "§").replace("{prefix}", prefix);
    }
}
