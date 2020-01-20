package me.dakto101.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.dakto101.HCraftRPG;
import me.dakto101.playerclass.PlayerClass;
import me.dakto101.playerclass.PlayerClassAPI;
import me.dakto101.skill.Skill;

public class ClassAndSkillGUI {
	
	public static final String CLASS_AND_SKILL_GUI = "§2§a§b§e§7§f§2§lLớp và kỹ năng";
	
	public static void open(Player player) {
		Inventory inv = HCraftRPG.plugin.getServer().createInventory(null, 27, CLASS_AND_SKILL_GUI);
		
		ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName(" ");
		empty.setItemMeta(emptyMeta);
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, empty);
		}
		
		ItemStack info = new ItemStack(Material.PAPER, 1);
		ItemMeta infoMeta = info.getItemMeta();
		PlayerClass pc = PlayerClassAPI.getPlayerClass(player);
		pc.loadPlayerClassFromSQL();
		infoMeta.setDisplayName("§f§lLớp của bạn: §6§l" + (pc == null ? "Không có" : pc.getClassName()));
		List<String> infoLore = new ArrayList<String>();
		if (pc != null) {
			infoLore.add("§7Cấp độ: §6" + pc.getLevel());
			infoLore.add("§7Kinh nghiệm: §6" + pc.getXP() + " / " + (pc.getRequireXP() + pc.getXP()));
			infoLore.add("§7Điểm cộng kỹ năng: §6" + pc.getSkillPoint());
		}
		infoMeta.setLore(infoLore);
		info.setItemMeta(infoMeta);

		pc = PlayerClassAPI.getPlayerClass(player);
		if (pc != null) {
			List<Skill> pcSkills = new ArrayList<Skill>();
			pcSkills = pc.getClassSkills();
			
			for (int i = 12, ii = 0; i < 16; i++, ii++) {
				ItemStack skillInfo = new ItemStack(Material.PAPER, 1);
				ItemMeta skillInfoMeta = skillInfo.getItemMeta();
				Skill pcSkill = pcSkills.get(ii);
				
				skillInfoMeta.setDisplayName("§a§l" + pcSkill.getSkillName());
				List<String> skillInfoLore = new ArrayList<String>();
				skillInfoLore.add("§7Cấp độ: " + pcSkill.getLevel());
				skillInfoLore.add("§7Loại kỹ năng: " + pcSkill.getSkillType().toString());
				List<String> desc = pcSkill.getDescription();
				desc.set(0, "§7Mô tả: " + desc.get(0).replaceAll("§7Mô tả: ", ""));
				skillInfoLore.addAll(desc);
				skillInfoMeta.setLore(skillInfoLore);
				skillInfo.setItemMeta(skillInfoMeta);
				inv.setItem(i, skillInfo);
				
			}
		}
		ItemStack mainmenu = new ItemStack(Material.ARROW, 1);
		ItemMeta mainmenuMeta = mainmenu.getItemMeta();
		mainmenuMeta.setDisplayName("§f§lQuay về danh mục");
		mainmenu.setItemMeta(mainmenuMeta);

		
		inv.setItem(10, info);
		inv.setItem(inv.getSize() - 1, mainmenu);
		
		player.openInventory(inv);
		player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
	}
}
