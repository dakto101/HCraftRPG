package me.dakto101;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dakto101.gui.MenuGUI;
import me.dakto101.playerclass.PlayerClass;
import me.dakto101.playerclass.PlayerClassAPI;
import me.dakto101.skill.Skill;

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
					sender.sendMessage("§6/hcraftrpg admin class show <người chơi>: Xem thông tin class của người chơi.");
					sender.sendMessage("§6/hcraftrpg admin class setclass <người chơi> <tên class>: Đặt class cho người chơi. Nếu chưa có class, load dữ liệu mặc định (level, xp...) (UUID: not safe).");
					sender.sendMessage("§6/hcraftrpg admin class addskillpoint <người chơi> <skillPoint>: Thêm số skillpoint (phải có class).");
					sender.sendMessage("§6/hcraftrpg admin class addlevel <người chơi> <level> [require-xp]: Thêm cấp class (phải có class).");
					sender.sendMessage("§6/hcraftrpg admin class addskill <người chơi> <số thứ tự skill> <số level>: Thêm cấp kỹ năng cho người chơi (phải có class).");
					sender.sendMessage("§6/hcraftrpg admin class addxp <người chơi> <xpAdd>: Thêm điểm xp class người chơi (phải có class).");					
					sender.sendMessage("§8/hcraftrpg admin class setparameter <người chơi> <skillPoint> <skill1> <skill2> <skill3> <skill4> <level>: Đặt thông số cho người chơi (phải có class).");
					sender.sendMessage("§8/hcraftrpg admin class reset <người chơi> : Reset mặc định các thông số (trừ tên class).");
					break;
				}
				// /hcraftrpg admin class list
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
				// /hcraftrpg admin class show <người chơi>
				case 4: {
					if (args[1].toString().equals("class")) {
						if (args[2].toString().equals("show")) {
							String targetName = args[3].toString();
							@SuppressWarnings("deprecation")
							OfflinePlayer p = Bukkit.getOfflinePlayer(targetName);
							PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
							if (pc == null) {
								sender.sendMessage("§cDữ liệu class của người chơi §e" + targetName + "§c không tồn tại.");
							} else {
								sender.sendMessage("§a" + pc.toString());
								return true;
							}
						}
					}
					break;
				}
				
				case 5: {
					if (args[1].toString().equals("class")) {
						// /hcraftrpg admin class addskillpoint <người chơi> <skillPoint>
						if (args[2].toString().equals("addskillpoint")) {
							String playerName = args[3].toString();
							
							Integer add = 0;
							try {
								add = Integer.parseInt(args[4].toString());
							} catch (Exception e) {
								sender.sendMessage("§cSố điểm kỹ năng nhập vào phải là một con số.");
								add = 0;
								return true;
							}
							@SuppressWarnings("deprecation")
							OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
							PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
							if (pc == null) {
								sender.sendMessage("§cNgười chơi §e" + playerName + "§c không có class. Phải tạo class trước.");
							} else {
								pc.setSkillPoint(pc.getSkillPoint() + add, true);
								sender.sendMessage("§aThêm §2" + add + "§a điểm kỹ năng cho người chơi §e" + playerName + "§a.");
								pc.savePlayerClassToSQL();
							}
							return true;
						}
						// //hcraftrpg admin class addlevel <người chơi> <level> [require-xp]:
						if (args[2].toString().equals("addlevel")) {
							String playerName = args[3].toString();
							
							Integer add = 0;
							try {
								add = Integer.parseInt(args[4].toString());
							} catch (Exception e) {
								sender.sendMessage("§cSố level nhập vào phải là một con số.");
								add = 0;
								return true;
							}
							@SuppressWarnings("deprecation")
							OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
							PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
							if (pc == null) {
								sender.sendMessage("§cNgười chơi §e" + playerName + "§c không có class. Phải tạo class trước.");
							} else {
								pc.setLevel(pc.getLevel() + add, true);
								sender.sendMessage("§aThêm §2" + add + "§a cấp độ (level) cho người chơi §e" + playerName + "§a.");
								pc.savePlayerClassToSQL();
							}
							return true;
						}
						// /hcraftrpg admin class addxp <người chơi> <xpAdd>
						if (args[2].toString().equals("addxp")) {
							String playerName = args[3].toString();
							
							Long xpAdd = 0L;
							try {
								xpAdd = Long.parseLong(args[4].toString());
							} catch (Exception e) {
								sender.sendMessage("§cSố xp nhập vào phải là một con số.");
								xpAdd = 0L;
								return true;
							}
							@SuppressWarnings("deprecation")
							OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
							PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
							if (pc == null) {
								sender.sendMessage("§cNgười chơi §e" + playerName + "§c không có class. Phải tạo class trước.");
							} else {
								pc.setXP(pc.getXP() + xpAdd, true);
								sender.sendMessage("§aThêm §2" + xpAdd + "§a điểm kinh nghiệm (xp) cho người chơi §e" + playerName + "§a.");
								pc.savePlayerClassToSQL();
							}
							return true;
						}
					}
				}
				case 6: {
					// //hcraftrpg admin class addlevel <người chơi> <level> [require-xp]:
					if (args[2].toString().equals("addlevel")) {
						String playerName = args[3].toString();
						
						Integer addLevel = 0;
						Long requireXP = 0L;
						try {
							addLevel = Integer.parseInt(args[4].toString());
							requireXP = Long.parseLong(args[5].toString());
						} catch (Exception e) {
							sender.sendMessage("§cSố cấp độ (level) hoặc kinh nghiệm cần để lên cấp nhập vào phải là một con số.");
							addLevel = 0;
							return true;
						}
						@SuppressWarnings("deprecation")
						OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
						PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
						if (pc == null) {
							sender.sendMessage("§cNgười chơi §e" + playerName + "§c không có class. Phải tạo class trước.");
						} else {
							pc.setLevel(pc.getLevel() + addLevel, true);
							long oldRequireXP = pc.getRequireXP();
							pc.setRequireXP(requireXP);
							sender.sendMessage("§aThêm §2" + addLevel + "§a cấp độ (level) cho người chơi §e" + playerName + "§a.");
							sender.sendMessage("§aChỉnh sửa kinh nghiệm cần lên cấp của người chơi §e" + playerName + "§a từ §2" + oldRequireXP + "§a thành §2" + pc.getRequireXP() + "§a.");
							pc.savePlayerClassToSQL();
						}
						return true;
					}
					// /hcraftrpg admin class addskill <người chơi> <số thứ tự skill> <số level>
					if (args[2].toString().equals("addskill")) {
						String playerName = args[3].toString();
						int skillType = -1;
						int addLevel = 0;
						try {
							skillType = Integer.parseInt(args[4].toString());
						} catch (Exception e) {
							sender.sendMessage("§cGiá trị của số thứ tự skill phải thuộc từ §e1→4§c.");
							return true;
						}
						try {
							addLevel = Integer.parseInt(args[5].toString());
						} catch (Exception e) {
							sender.sendMessage("§cSố cấp (level) được thêm vào phải là con số.");
							return true;
						}
						if ((skillType < 1) || (skillType > 4)) {
							sender.sendMessage("§cGiá trị của số thứ tự skill phải thuộc từ §e1→4§c.");
							return true;
						}
						
						@SuppressWarnings("deprecation")
						OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
						PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
						if (pc == null) {
							sender.sendMessage("§cNgười chơi §e" + playerName + "§c không có class. "
									+ "Phải tạo class trước.");
						} else {
							List<Skill> skills = pc.getClassSkills();
							Skill skill = skills.get(skillType - 1);
							int oldValue = skill.getLevel();
							if (skill != null) {
								skill.setLevel(skill.getLevel() + addLevel);
								skills.set(skillType - 1, skill);
							}
							pc.setClassSkills(skills);
							pc.savePlayerClassToSQL();
							sender.sendMessage("§aThay đổi thành công kỹ năng §2" + skill.getSkillName()
							+ "§a từ cấp §2" + oldValue + "§a thành cấp §2"
									+ skill.getLevel() + "§a.");
						}
						return true;
					}
				}
				default: {
					if (args[1].toString().equals("class")) {
						// /hcraftrpg admin class setclass <người chơi> <tên class>
						if (args[2].toString().equals("setclass")) {
							String playerName = args[3].toString();
							String className = "";
							for (int i = 4; i < args.length; i++) {
								className = className + args[i].toString() + ((i == args.length - 1) ? "" : " ");
							}
							//Check className if not exists.
							boolean checkClassName = false;
							for (PlayerClass pc : PlayerClassAPI.getAllPlayerClass()) {
								if (pc.getClassName().equals(className)) checkClassName = true;
							}
							if (checkClassName == false) {
								sender.sendMessage("§cTên class §e" + className +  " §ckhông tồn tại.");
								return true;
							}
							
							@SuppressWarnings("deprecation")
							OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
							PlayerClass pc = PlayerClassAPI.getPlayerClass(p);
							if (pc == null) PlayerClassAPI.createNewPlayerClassToSQL(p);
							PlayerClassAPI.changePlayerClassNameSQL(p, className);
							
							sender.sendMessage("§aThay đổi tên class cho người chơi §6" + playerName + "§a thành công.");
						}
					}
					break;
				}
				}
				return true;
				//
			}
			
			return true;
		}
		
		return false;
		
	}

}



	  
	


