package dev.mrauro.keypads;

import com.github.stefvanschie.inventoryframework.font.util.Font;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.DropperGui;
import com.github.stefvanschie.inventoryframework.pane.component.Label;
import dev.mrauro.keypads.commands.Create;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Keypads extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("create").setExecutor(new Create());
        getServer().getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onPlayerInteractEvent(org.bukkit.event.player.PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType() == Material.IRON_DOOR) {
                event.getPlayer().sendMessage("A");
            }
        }
    }

    @EventHandler
    public void onPlayerInteractAtEntityEvent(org.bukkit.event.player.PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof org.bukkit.entity.ArmorStand armorStand) {
            if (armorStand.getCustomName() != null) {

                String code = armorStand.getCustomName();

                StringBuilder codeBuilder = new StringBuilder();

                DropperGui gui = new DropperGui("PIN");

                gui.setOnGlobalClick(_event -> _event.setCancelled(true));

                Label input = new Label(0, 0, 3, 3, Font.LIGHT_GRAY);

                Player player = event.getPlayer();

                input.setText("123456789", (character, item) -> new GuiItem(item, _event -> {
                    codeBuilder.append(character);

                    if (codeBuilder.length() == 4) {
                        if (codeBuilder.toString().equals(code)) {
                            player.getWorld().playSound(player.getLocation(), "block.note_block.pling", 1, 2);
                            for (int x = -1; x <= 1; x++) {
                                for (int y = -1; y <= 1; y++) {
                                    for (int z = -1; z <= 1; z++) {
                                        Block block = event.getRightClicked().getLocation().getBlock().getRelative(x, y, z);
                                        if (block.getType() == Material.IRON_DOOR) {
                                            Door door = (Door) block.getBlockData();
                                            door.setOpen(true);
                                            block.setBlockData(door);
                                            block.getWorld().playSound(block.getLocation(), "block.iron_door.open", 1, 1);
                                            getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                                                door.setOpen(false);
                                                block.setBlockData(door);
                                                block.getWorld().playSound(block.getLocation(), "block.iron_door.close", 1, 1);
                                            }, 40L);
                                        }
                                    }
                                }
                            }
                            player.closeInventory();
                        } else {
                            player.getWorld().playSound(player.getLocation(), "entity.villager.no", 1, 1);
                            player.damage(2);
                            player.sendMessage(ChatColor.RED + "Incorrect code");
                            player.closeInventory();
                        }
                    }
                }));

                gui.getContentsComponent().addPane(input);

                gui.show(player);
            }
        }
    }
}
