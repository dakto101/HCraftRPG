package me.dakto101.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.dakto101.playerstat.PlayerStat;
import me.dakto101.playerstat.PlayerStatType;

public class GUIListener implements Listener {
	//Menu
	@EventHandler
	public void menuGUI(InventoryClickEvent e) {
		if (!e.getView().getTitle().equals(MenuGUI.MENU_GUI)) {
			return;
		} else e.setCancelled(true);
		if (!e.getClickedInventory().getType().equals(InventoryType.CHEST)) return;
		Player p = null;
		if (e.getWhoClicked() instanceof Player) p = (Player) e.getWhoClicked();
		else return;
		
		int clickedSlot = e.getSlot();
		switch (clickedSlot) {
		case 0: {
			PlayerStatGUI.open(p); break;
		}
		case 1: {
			ClassAndSkillGUI.open(p); break;
		}
		default: 
			p.playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
		}
		
	}
	//Menu→Chỉ số
	@EventHandler
	public void playerStatGUI(InventoryClickEvent e) {
		if (!e.getView().getTitle().equals(PlayerStatGUI.PLAYER_STAT_GUI)) {
			return;
		} else e.setCancelled(true);
		if (!e.getClickedInventory().getType().equals(InventoryType.CHEST)) return;
		Player p = null;
		if (e.getWhoClicked() instanceof Player) p = (Player) e.getWhoClicked();
		else return;
		
		
		int clickedSlot = e.getSlot();
		PlayerStat playerStat = new PlayerStat(0, 0, 0, 0, 0, 0, 0);
		playerStat.loadPlayerStatsFromSQL(p);
		
		switch (clickedSlot) {
		case 11: {
			playerStatGUIaddStat(e, 1, playerStat, PlayerStatType.STRENGTH); break;
		}
		case 12: {
			playerStatGUIaddStat(e, 10, playerStat, PlayerStatType.STRENGTH); break;
		}
		case 15: {
			playerStatGUIaddStat(e, 1, playerStat, PlayerStatType.DEFENCE); break;
		}
		case 16: {
			playerStatGUIaddStat(e, 10, playerStat, PlayerStatType.DEFENCE); break;
		}
		case 20: {
			playerStatGUIaddStat(e, 1, playerStat, PlayerStatType.DEXTERITY); break;
		}
		case 21: {
			playerStatGUIaddStat(e, 10, playerStat, PlayerStatType.DEXTERITY); break;
		}
		case 24: {
			playerStatGUIaddStat(e, 1, playerStat, PlayerStatType.LUCK); break;
		}
		case 25: {
			playerStatGUIaddStat(e, 10, playerStat, PlayerStatType.LUCK); break;
		}
		case 29: {
			playerStatGUIaddStat(e, 1, playerStat, PlayerStatType.INTELLIGENCE); break;
		}
		case 30: {
			playerStatGUIaddStat(e, 10, playerStat, PlayerStatType.INTELLIGENCE); break;
		}
		case 33: {
			playerStatGUIaddStat(e, 1, playerStat, PlayerStatType.ENDURANCE); break;
		}
		case 34: {
			playerStatGUIaddStat(e, 10, playerStat, PlayerStatType.ENDURANCE); break;
		}
		case 53: {
			MenuGUI.open(p); break;
		}
		default:
			p.playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
		}
		//Coding...
	}
	
	@EventHandler
	public void classAndSkillGUI(InventoryClickEvent e) {
		if (!e.getView().getTitle().equals(ClassAndSkillGUI.CLASS_AND_SKILL_GUI)) {
			return;
		} else e.setCancelled(true);
		if (!e.getClickedInventory().getType().equals(InventoryType.CHEST)) return;
		Player p = null;
		if (e.getWhoClicked() instanceof Player) p = (Player) e.getWhoClicked();
		else return;
		int clickedSlot = e.getSlot();
		switch (clickedSlot) {
		case 26: {
			MenuGUI.open(p); break;
		}
		default: 
			p.playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
		}
		
	}
	
	private void playerStatGUIaddStat(InventoryClickEvent e, int addStat, PlayerStat playerStat, PlayerStatType statType) {
		if (playerStat.addStats(addStat, statType, true)) {
			if (e.getWhoClicked() instanceof Player) {
				playerStat.savePlayerStatsToSQL((Player) e.getWhoClicked());
				PlayerStatGUI.open((Player) e.getWhoClicked());
				((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
				
			}
		} else {
			ItemStack clickedItem = e.getClickedInventory().getItem(e.getSlot());
			List<String> clickedItemLore = new ArrayList<String>();
			clickedItemLore.add("");
			clickedItemLore.add("§cKhông đủ điểm cộng chỉ số!");
			ItemMeta clickedItemMeta = clickedItem.getItemMeta();
			clickedItemMeta.setLore(clickedItemLore);
			clickedItem.setItemMeta(clickedItemMeta);
			
			e.getClickedInventory().setItem(e.getSlot(), clickedItem);
			if (e.getWhoClicked() instanceof Player) ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
		}
	}
}
