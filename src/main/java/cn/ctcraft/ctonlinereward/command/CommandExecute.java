package cn.ctcraft.ctonlinereward.command;

import cn.ctcraft.ctonlinereward.CtOnlineReward;
import cn.ctcraft.ctonlinereward.database.YamlData;
import cn.ctcraft.ctonlinereward.inventory.InventoryFactory;
import cn.ctcraft.ctonlinereward.inventory.RewardSetInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class CommandExecute {
    private static final CommandExecute instance = new CommandExecute();
    private final CtOnlineReward ctOnlineReward;

    private CommandExecute() {
        ctOnlineReward = CtOnlineReward.getPlugin(CtOnlineReward.class);
    }

    public static CommandExecute getInstance() {
        return instance;
    }

    public void openRewardSetInventory(CommandSender sender, String[] args) {
        if (!sender.hasPermission("CtOnlineReward.rewardSet")) {
            sender.sendMessage("§c■ 权限不足!");
            return;
        }
        if (args.length != 3) {
            sender.sendMessage("§c■ 缺少参数,正确格式为/cor reward set [奖励名称]");
            return;
        }
        if (!args[1].equalsIgnoreCase("set")) {
            sender.sendMessage("§c■ 参数错误,正确格式为/cor reward set [奖励名称]");
            return;
        }
        String reward = args[2];
        RewardSetInventory rewardSetInventory = new RewardSetInventory();
        rewardSetInventory.openInventory((Player) sender, reward);

    }

    public void openInventory(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c■ 该命令仅能在游戏中使用");
            return;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            Inventory menu = InventoryFactory.build("menu.yml", player);
            player.openInventory(menu);
            return;
        }
        if (args.length == 2) {
            Map<String, YamlConfiguration> guiYaml = YamlData.guiYaml;
            if (!guiYaml.containsKey(args[1])) {
                player.sendMessage("§c■ 未找到指定菜单!");
                return;
            }
            try {
                if (!player.hasPermission("CtOnlineReward.open." + args[1])) {
                    player.sendMessage("§c■ 权限不足!");
                    return;
                }

                Inventory menu = InventoryFactory.build(args[1], (Player) sender);
                if (menu == null) {
                    player.sendMessage("§c§l菜单不存在!");
                    player.closeInventory();
                    return;
                }

                player.openInventory(menu);
            } catch (Exception e) {
                ctOnlineReward.getLogger().warning("§c■ " + args[1] + "菜单配置异常!");
                e.printStackTrace();
            }
            return;
        }
        sender.sendMessage("§c■ 参数错误,正确格式为/cor open [菜单ID(可选)]");
    }


}
