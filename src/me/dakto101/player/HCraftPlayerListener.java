package me.dakto101.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.scheduler.BukkitScheduler;

import me.dakto101.HCraftRPG;

public class HCraftPlayerListener implements Listener {
	//Check player online
	//Auto save player data.

	private static List<HCraftPlayer> playerList = new ArrayList<HCraftPlayer>();
	
	@EventHandler
	public void onEnable(PluginEnableEvent e) {
		//Check plugin
		if (!e.getPlugin().getName().equals(HCraftRPG.plugin.getName())) return;
		playerList = new ArrayList<HCraftPlayer>();
		
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        //Timer task (1.5s): Init list and add all player to list
        scheduler.runTaskLater(HCraftRPG.plugin, new Runnable() {
        	@Override
        	public void run() {
        		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
        			playerList = new ArrayList<HCraftPlayer>();
        			playerList.add(new HCraftPlayer(player));
        		}
        	}
        }, 20L);
        //Repeat task: Remove player if offline (600s each check)
        scheduler.scheduleSyncRepeatingTask(HCraftRPG.plugin, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < playerList.size(); i++) {
                	if (!playerList.get(i).getPlayer().isOnline()) playerList.remove(i);
                }
            }
        }, 100L, 600L*20);

	}
	@EventHandler
	public void onDisable(PluginDisableEvent e) {
		if (!e.getPlugin().getName().equals(HCraftRPG.plugin.getName())) return;
		
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
