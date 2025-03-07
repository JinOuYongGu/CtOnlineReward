package cn.ctcraft.ctonlinereward.utils;

import cn.ctcraft.ctonlinereward.CtOnlineReward;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.configuration.file.YamlConfiguration;

import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class version {
    public static List<String> getVersionMsg() {
        List<String> versionMsg = new ArrayList<>();
        String version = "获取失败！";
        try {
            Path tempFile = Files.createTempFile("version", ".yml");
            Files.copy(new URL("https://note.youdao.com/yws/public/note/eead3b553997e3392d008f9787c45757").openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            tempFile.toFile().deleteOnExit();
            String str = new String(Files.readAllBytes(tempFile), StandardCharsets.UTF_8);
            JsonParser jsonParser = new JsonParser();
            JsonElement parse = jsonParser.parse(str);
            JsonObject asJsonObject = parse.getAsJsonObject();
            JsonElement content = asJsonObject.get("content");
            String yamlText = content.getAsString().replace("<div yne-bulb-block=\"paragraph\" style=\"white-space: pre-wrap;\">", "").replace("</div>", "").replace("&nbsp;", " ").replace("<br>", "\n");
            YamlConfiguration yamlConfiguration = new YamlConfiguration();
            yamlConfiguration.loadFromString(yamlText);
            version = yamlConfiguration.getString("CtOnlineReward.version");
        } catch (UnknownHostException e) {
            version = "版本信息获取失败！";
            versionMsg.add(version);
            return versionMsg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        CtOnlineReward plugin = CtOnlineReward.getPlugin(CtOnlineReward.class);
        versionMsg.add("§6===========[CtOnlineReward]============");
        if (plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
            versionMsg.add("欢迎您使用CtOnlineReward最新版本! 版本号:" + version);
            versionMsg.add("§6=======================================");
            return versionMsg;
        }
        versionMsg.add("CtOnlineReward不是最新版本!最新版本: §b" + version + "§6!你的版本: §b" + plugin.getDescription().getVersion());
        versionMsg.add("§6=======================================");
        return versionMsg;
    }
}
