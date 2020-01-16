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
		String playerClassName = PlayerClassAPI.getPlayerClassName(player.getName());
		infoMeta.setDisplayName("§7§lLớp của bạn: §6§l" + (playerClassName.equals("") ? "Không có" : playerClassName));
		info.setItemMeta(infoMeta);
		
		player.sendMessage("" + PlayerClassAPI.getPlayerClass(player, playerClassName));
		
		PlayerClass pc = null;
		pc = PlayerClassAPI.getPlayerClass(player, playerClassName);
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
				skillInfoLore.add("§7Mô tả: " + pcSkill.getDescription());
				skillInfoMeta.setLore(skillInfoLore);
				skillInfo.setItemMeta(skillInfoMeta);
				
				inv.setItem(i, skillInfo);
				
			}
		}

		
		inv.setItem(10, info);
		
		player.openInventory(inv);
		player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
	}
}
