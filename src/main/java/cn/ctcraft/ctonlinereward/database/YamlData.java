package cn.ctcraft.ctonlinereward.database;

import com.google.gson.JsonArray;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

public class YamlData {
    public static Map<String, YamlConfiguration> guiYaml = new HashMap<>();
    public static YamlConfiguration rewardYaml = new YamlConfiguration();
    public static JsonArray remindJson = new JsonArray();
}
