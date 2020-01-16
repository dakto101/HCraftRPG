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

public class MenuGUI {
	
	public static final String MENU_GUI = "§2§a§b§e§7§f§1§lRPG - Danh mục";
	
	public static void open(Player player) {
		Inventory inv = HCraftRPG.plugin.getServer().createInventory(null, 9, MENU_GUI);
		
		ItemStack playerStats = new ItemStack(Material.BOOK, 1);
		ItemMeta playerStatsMeta = playerStats.getItemMeta();
		playerStatsMeta.setDisplayName("§a§lChỉ số");
		List<String> playerStatsLore = new ArrayList<String>();
		playerStatsLore.add("§7Click vào để xem chi tiết thông tin về chỉ số của bạn.");
		playerStatsMeta.setLore(playerStatsLore);
		playerStats.setItemMeta(playerStatsMeta);
		
		ItemStack skillAndClass = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta skillAndClassMeta = skillAndClass.getItemMeta();
		skillAndClassMeta.setDisplayName("§c§lLớp và kỹ năng");
		List<String> skillAndClassLore = new ArrayList<String>();
		skillAndClassLore.add("§7Click vào để xem chi tiết thông tin về lớp và kỹ năng của bạn.");
		skillAndClassMeta.setLore(skillAndClassLore);
		skillAndClass.setItemMeta(skillAndClassMeta);
		
		inv.setItem(0, playerStats);
		inv.setItem(1, skillAndClass);

		
		player.openInventory(inv);
		player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
	}

}
