package me.dakto101;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dakto101.gui.MenuGUI;
import me.dakto101.playerclass.PlayerClass;
import me.dakto101.playerclass.PlayerClassAPI;
import me.dakto101.playerclass.dausi.DauSi;

public class HCraftRPGCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equals("hcraftrpg")) {
			if (args.length == 1) {
				if ((args[0].toString().equals("reload") && (sender.hasPermission(HCraftRPGPermission.COMMAND_RELOAD)))) {
					sender.sendMessage("§aReload thanh cong.");
				}
				if ((args[0].toString().equals("test") && (sender.hasPermission(HCraftRPGPermission.ADMIN)))) {
					//Insert test code here...
					if (sender instanceof Player) {
						Player p = (Player) sender;
						PlayerClass pc = new DauSi();
						pc.loadPlayerClassFromSQL(p);
						pc.savePlayerClassToSQL(p);
					}
					//
				}
				if ((args[0].toString().equals("admin") && (sender.hasPermission(HCraftRPGPermission.ADMIN)))) {
					sender.sendMessage("§6======HCraftRPGAdmin======");
					sender.sendMessage("§6/hcraftrpg ?: ?");
				}
				return true;
			}
			if ((args.length >= 2)) {
				
			}
			
			if ((args.length == 0) && (sender.hasPermission(HCraftRPGPermission.MENU_MAINMENU))) {
				if (sender instanceof Player) MenuGUI.open((Player) sender);
				sender.sendMessage("§a======HCraftRPG======");
				if (sender.hasPermission(HCraftRPGPermission.ADMIN)) {
					sender.sendMessage("§a/hcraftrpg reload: Reload plugin.");
					sender.sendMessage("§a/hcraftrpg test: Test plugin.");
					sender.sendMessage("§a/hcraftrpg admin: Admin menu.");
				}
			}
			
			return true;
		}
		
		return false;
		
	}

}



	  
	


