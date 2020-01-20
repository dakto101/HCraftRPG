package me.dakto101.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.dakto101.HCraftRPG;
import me.dakto101.playerstat.PlayerStat;
import me.dakto101.playerstat.PlayerStatType;

public class PlayerStatGUI {
	
	public static final String PLAYER_STAT_GUI = "§2§a§b§e§7§f§2§lChỉ số của bạn";
	

	@SuppressWarnings("deprecation")
	public static void open(Player player) {
		Inventory inv = HCraftRPG.plugin.getServer().createInventory(null, 54, PLAYER_STAT_GUI);
		
		ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName(" ");
		empty.setItemMeta(emptyMeta);
		for (int i = 0; i < inv.getSize() - 9; i++) {
			inv.setItem(i, empty);
		}
		ItemStack empty2 = new ItemStack(Material.IRON_BARS, 1);
		empty2.setItemMeta(emptyMeta);
		for (int i = inv.getSize() - 9; i < inv.getSize(); i++) {
			inv.setItem(i, empty2);
		}
		
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skullMeta.setOwner("MHF_Question");
		skullMeta.setDisplayName("§f§lHướng dẫn");
		List<String> skullLore = new ArrayList<String>();
		skullLore.add("§7Click vào mũi tên để cộng thêm chỉ số cho bạn.");
		skullMeta.setLore(skullLore);
		skull.setItemMeta(skullMeta);
		//
		PlayerStat ps = new PlayerStat(0, 0, 0, 0, 0, 0, 0);
		ps.loadPlayerStatsFromSQL(player);
		ItemStack info = new ItemStack(Material.BOOK, 1);
		ItemMeta infoMeta = info.getItemMeta();
		infoMeta.setDisplayName("§6§lChỉ số của bạn");
		List<String> infoLore = new ArrayList<String>();
		infoLore.add("§bSức mạnh: §a" + ps.getStats(PlayerStatType.STRENGTH));
		infoLore.add("§bChuẩn xác: §a" + ps.getStats(PlayerStatType.DEXTERITY));
		infoLore.add("§bTrí tuệ: §a" + ps.getStats(PlayerStatType.INTELLIGENCE));
		infoLore.add("§bPhòng thủ: §a" + ps.getStats(PlayerStatType.DEFENCE));
		infoLore.add("§bMay mắn: §a" + ps.getStats(PlayerStatType.LUCK));
		infoLore.add("§bBền bỉ: §a" + ps.getStats(PlayerStatType.ENDURANCE));
		infoLore.add("");
		infoLore.add("§7Điểm cộng chỉ số: §f" + ps.getStatPoint());
		infoMeta.setLore(infoLore);
		info.setItemMeta(infoMeta);
		//
		ItemStack addOne = new ItemStack(Material.ARROW, 1);
		ItemMeta addOneMeta = addOne.getItemMeta();
		addOneMeta.setDisplayName("§a§lCộng §b§l1 §a§lđiểm chỉ số");
		addOne.setItemMeta(addOneMeta);
		
		ItemStack addTen = new ItemStack(Material.SPECTRAL_ARROW, 1);
		ItemMeta addTenMeta = addTen.getItemMeta();
		addTenMeta.setDisplayName("§a§lCộng §6§l10 §a§lđiểm chỉ số");
		addTen.setItemMeta(addTenMeta);
		//
		ItemStack attack = new ItemStack(Material.PAPER, 1);
		ItemMeta attackMeta = attack.getItemMeta();
		attackMeta.setDisplayName("§f§lSức mạnh");
		List<String> attackLore = new ArrayList<String>();
		attackLore.add("§7Tăng mạnh sát thương đòn đánh cận chiến.");
		attackLore.add("");
		attackLore.add("§bĐiểm sức mạnh: §a" + ps.getStats(PlayerStatType.STRENGTH));
		attackMeta.setLore(attackLore);
		attack.setItemMeta(attackMeta);
		
		ItemStack dexterity = new ItemStack(Material.PAPER, 1);
		ItemMeta dexterityMeta = dexterity.getItemMeta();
		dexterityMeta.setDisplayName("§f§lChuẩn xác");
		List<String> dexterityLore = new ArrayList<String>();
		dexterityLore.add("§7Tăng sát thương khi tấn công bằng vũ khí tầm xa.");
		dexterityLore.add("§7Tăng sát thương chí mạng.");
		dexterityLore.add("");
		dexterityLore.add("§bĐiểm chuẩn xác: §a" + ps.getStats(PlayerStatType.DEXTERITY));
		dexterityMeta.setLore(dexterityLore);
		dexterity.setItemMeta(dexterityMeta);
		
		ItemStack intelligence = new ItemStack(Material.PAPER, 1);
		ItemMeta intelligenceMeta = intelligence.getItemMeta();
		intelligenceMeta.setDisplayName("§f§lTrí tuệ");
		List<String> intelligenceLore = new ArrayList<String>();
		intelligenceLore.add("§7Tăng sát thương phép.");
		intelligenceLore.add("");
		intelligenceLore.add("§bĐiểm trí tuệ: §a" + ps.getStats(PlayerStatType.INTELLIGENCE));
		intelligenceMeta.setLore(intelligenceLore);
		intelligence.setItemMeta(intelligenceMeta);
		
		ItemStack defence = new ItemStack(Material.PAPER, 1);
		ItemMeta defenceMeta = defence.getItemMeta();
		defenceMeta.setDisplayName("§f§lPhòng thủ");
		List<String> defenceLore = new ArrayList<String>();
		defenceLore.add("§7Giảm sát thương vật lý nhận được.");
		defenceLore.add("");
		defenceLore.add("§bĐiểm phòng thủ: §a" + ps.getStats(PlayerStatType.DEFENCE));
		defenceMeta.setLore(defenceLore);
		defence.setItemMeta(defenceMeta);
		
		ItemStack luck = new ItemStack(Material.PAPER, 1);
		ItemMeta luckMeta = luck.getItemMeta();
		luckMeta.setDisplayName("§f§lMay mắn");
		List<String> luckLore = new ArrayList<String>();
		luckLore.add("§7Tăng tỉ lệ chí mạng và tỉ lệ né đòn.");
		luckLore.add("");
		luckLore.add("§bĐiểm may mắn: §a" + ps.getStats(PlayerStatType.LUCK));
		luckMeta.setLore(luckLore);
		luck.setItemMeta(luckMeta);
		
		ItemStack endurance = new ItemStack(Material.PAPER, 1);
		ItemMeta enduranceMeta = endurance.getItemMeta();
		enduranceMeta.setDisplayName("§f§lBền bỉ");
		List<String> enduranceLore = new ArrayList<String>();
		enduranceLore.add("§7Tăng máu tối đa.");
		enduranceLore.add("");
		enduranceLore.add("§bĐiểm bền bỉ: §a" + ps.getStats(PlayerStatType.ENDURANCE));
		enduranceMeta.setLore(enduranceLore);
		endurance.setItemMeta(enduranceMeta);
		
		ItemStack resetStat = new ItemStack(Material.NAME_TAG, 1);
		ItemMeta resetStatMeta = resetStat.getItemMeta();
		resetStatMeta.setDisplayName("§6§lKhôi phục điểm cộng chỉ số");
		List<String> resetStatLore = new ArrayList<String>();
		resetStatLore.add("§7Click để khôi phục.");
		resetStatLore.add("");
		resetStatLore.add("§c(!) Tính năng đang bảo trì.");
		resetStatMeta.setLore(resetStatLore);
		resetStat.setItemMeta(resetStatMeta);
		
		ItemStack mainmenu = new ItemStack(Material.ARROW, 1);
		ItemMeta mainmenuMeta = mainmenu.getItemMeta();
		mainmenuMeta.setDisplayName("§f§lQuay về danh mục");
		mainmenu.setItemMeta(mainmenuMeta);

		
		inv.setItem(4, info);
		inv.setItem(10, attack);
		inv.setItem(11, addOne);
		inv.setItem(12, addTen);
		inv.setItem(14, defence);
		inv.setItem(15, addOne);
		inv.setItem(16, addTen);
		inv.setItem(19, dexterity);
		inv.setItem(20, addOne);
		inv.setItem(21, addTen);
		inv.setItem(23, luck);
		inv.setItem(24, addOne);
		inv.setItem(25, addTen);
		inv.setItem(28, intelligence);
		inv.setItem(29, addOne);
		inv.setItem(30, addTen);
		inv.setItem(32, endurance);
		inv.setItem(33, addOne);
		inv.setItem(34, addTen);
		inv.setItem(40, resetStat);
		inv.setItem(49, skull);
		inv.setItem(53, mainmenu);
		
		player.openInventory(inv);
		player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
	}

}
