package me.dakto101;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dakto101.gui.MenuGUI;
import me.dakto101.playerclass.PlayerClass;
import me.dakto101.playerclass.PlayerClassAPI;

public class HCraftRPGCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equals("hcraftrpg")) {
			//Main menu
			if (args.length == 0) {
				if (!sender.hasPermission(HCraftRPGPermission.MENU_MAINMENU)) {
					sender.sendMessage("§c[HCraftRPG] (!) Bạn không có quyền! §e" + HCraftRPGPermission.MENU_MAINMENU);
					return true;
				}
				if (sender instanceof Player) {
					MenuGUI.open((Player) sender);
				} else {
					sender.sendMessage("§c[HCraftRPG] (!) Chi co nguoi choi moi dung duoc lenh nay.");
				}
				return true;
			} 
			//Help
			if (args[0].toString().equals("help") || args[0].toString().equals("?")) {
				if (!sender.hasPermission(HCraftRPGPermission.ADMIN)) {
					sender.sendMessage("§c[HCraftRPG] (!) Bạn không có quyền! §e" + HCraftRPGPermission.ADMIN);
					return true;
				}
				sender.sendMessage("§a======HCraftRPG======");
				sender.sendMessage("§a/hcraftrpg reload: Reload plugin.");
				sender.sendMessage("§a/hcraftrpg test: Test plugin.");
				sender.sendMessage("§a/hcraftrpg admin: Admin menu.");
				return true;
			}
			//Reload
			if (args[0].toString().equals("reload")) {
				if (!sender.hasPermission(HCraftRPGPermission.ADMIN)) {
					sender.sendMessage("§c[HCraftRPG] (!) Bạn không có quyền! §e" + HCraftRPGPermission.ADMIN);
					return true;
				}
				sender.sendMessage("§a??");
				return true;
			}
			//Test
			if (args[0].toString().equals("test")) {
				if (!sender.hasPermission(HCraftRPGPermission.ADMIN)) {
					sender.sendMessage("§c[HCraftRPG] (!) Bạn không có quyền! §e" + HCraftRPGPermission.ADMIN);
					return true;
				}
				//Test code
				switch (args.length) {
				case 1: {
					if (sender instanceof Player) {
						//Get uuid
						
					}
					break;
				}
				case 2: {
					if (sender instanceof Player) {
						String playerName = args[1].toString();
						@SuppressWarnings("deprecation")
						OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
						Bukkit.getServer().getConsoleSender().sendMessage("p is null? " + (p == null));
						if (p != null) Bukkit.getServer().getConsoleSender().sendMessage("UUID(p) = " + p.getUniqueId());
					}
					break;
				}
				}
				//
				return true;
			}
			//Admin
			if (args[0].toString().equals("admin")) {
				if (!sender.hasPermission(HCraftRPGPermission.ADMIN)) {
					sender.sendMessage("§c[HCraftRPG] (!) Bạn không có quyền! §e" + HCraftRPGPermission.ADMIN);
					return true;
				}
				//Test code
				switch (args.length) {
				case 1: {
					sender.sendMessage("§2======HCraftRPG-Admin======");
					sender.sendMessage("§2/hcraftrpg admin class [số trang]: Xem thêm lệnh về class.");
					sender.sendMessage("§2/hcraftrpg admin ?: ?");
					break;
				}
				case 2: {
					sender.sendMessage("§6======HCraftRPGAdmin======");
					sender.sendMessage("§6======Trang 1/1======");
					sender.sendMessage("§6/hcraftrpg admin class list: Xem danh sách lớp nhân vật.");
					sender.sendMessage("§6/hcraftrpg admin class setclass <người chơi> <tên class>: Đặt class cho người chơi. Nếu chưa có class, load dữ liệu mặc định (level, xp...) (UUID: not safe).");
					sender.sendMessage("§6/hcraftrpg admin class setparameter <người chơi> <skillPoint> <skill1> <skill2> <skill3> <skill4> <level>: Đặt thông số cho người chơi (phải có class).");
					sender.sendMessage("§6/hcraftrpg admin class addxp <người chơi> <xpAdd>: Thêm điểm xp class người chơi (phải có class).");
					sender.sendMessage("§6/hcraftrpg admin class show: Xem thông tin class của người chơi.");
					break;
				}
				case 3: {
					if (args[1].toString().equals("class")) {
						if (args[2].toString().equals("list")) {
							for (PlayerClass c : PlayerClassAPI.getAllPlayerClass()) {
								sender.sendMessage("" + c.getClassName());
							}
						}
					}
					break;
				}
				case 5: {
					if (args[1].toString().equals("class")) {
						if (args[2].toString().equals("setclass")) {
							/* havent dont yet...
							String playerName = args[2].toString();
							String className = args[3].toString();
							@SuppressWarnings("deprecation")
							OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
							PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
							if (pc == null) PlayerClassAPI.createNewPlayerClassToSQL(p);
							pc = PlayerClassAPI.getPlayerClass(p);
							*/
							
						}
					}
					break;
				}
				}
				return true;
				//
			}
			
			
			
			
			/*
			//
			if ((args.length == 0)) {
				if (sender.hasPermission(HCraftRPGPermission.MENU_MAINMENU)) {
					
				}
				if (sender.hasPermission(HCraftRPGPermission.ADMIN)) {

				}
				return true;
			}
			
			if (args.length == 1) {
				if ((args[0].toString().equals("reload") && (sender.hasPermission(HCraftRPGPermission.COMMAND_RELOAD)))) {
					sender.sendMessage("§aReload thanh cong.");
				}
				if ((args[0].toString().equals("test") && (sender.hasPermission(HCraftRPGPermission.ADMIN)))) {
					//Insert test code here...
					if (sender instanceof Player) {
						Player p = (Player) sender;
						PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
						pc.loadPlayerClassFromSQL();
						pc.reset();
						pc.savePlayerClassToSQL();
					}
					//
				}
				if ((args[0].toString().equals("admin") && (sender.hasPermission(HCraftRPGPermission.ADMIN)))) {
					sender.sendMessage("§6======HCraftRPGAdmin======");
					sender.sendMessage("§6/hcraftrpg admin class list: Xem danh sách lớp nhân vật.");
					sender.sendMessage("§6/hcraftrpg admin class setclass <người chơi> <tên class>: Đặt class cho người chơi.");
					sender.sendMessage("§6/hcraftrpg admin class show <người chơi> <tên class>: Đặt class cho người chơi.");
				} 
				return true;
			}
			if ((args.length == 2)) {
				if ((args[0].toString().equals("test") && (sender.hasPermission(HCraftRPGPermission.ADMIN)))) {
					//Insert test code here...
					if (sender instanceof Player) {
						int level = Integer.parseInt(args[1].toString());
						sender.sendMessage("totalXP(" + level + ") = " + PlayerClassAPI.getTotalRequireXP(level));
					}
					//
				}
				return true;
			}
			if ((args.length == 3)) {
				//Admin...
				if (!sender.hasPermission(HCraftRPGPermission.ADMIN)) return true;
				
				if (args[0].toString().equals("admin")) {
					if (args[1].toString().equals("class")) {
						if (args[2].toString().equals("list")) {
							for (PlayerClass c : PlayerClassAPI.getAllPlayerClass()) {
								sender.sendMessage("" + c.getClassName());
							}
						}
					}
				}
				
				return true;
			}
			*/
			return true;
		}
		
		return false;
		
	}

}



	  
	


