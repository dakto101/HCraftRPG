package me.dakto101;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dakto101.gui.MenuGUI;

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
				
				return true;
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



	  
	


