package me.dakto101.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.scheduler.BukkitScheduler;

import me.dakto101.HCraftRPG;

public class HCraftPlayerListener implements Listener {
	
	private static List<HCraftPlayer> playerList = new ArrayList<HCraftPlayer>();
	
	@EventHandler
	public void onEnable(PluginEnableEvent e) {
		playerList = new ArrayList<HCraftPlayer>();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(HCraftRPG.plugin, new Runnable() {
            @Override
            public void run() {
                // Do something
            }
        }, 0L, 20L);
	}
	
	@EventHandler
	public void onDisable(PluginDisableEvent e) {
		for (HCraftPlayer p : playerList) {
			p.saveData();
		}
	}
	
	//Them nguoi choi khi vao game.
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		HCraftPlayer p = new HCraftPlayer(e.getPlayer());
		playerList.add(p);
	}
	
	//Xoa nguoi choi khoi list khi thoat game.
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		for (int i = 0; i < playerList.size(); i++) {
			String target = e.getPlayer().getName();
			String check = playerList.get(i).getPlayer().getName();
			if (target.equals(check)) playerList.remove(i);
		}
	}


}
