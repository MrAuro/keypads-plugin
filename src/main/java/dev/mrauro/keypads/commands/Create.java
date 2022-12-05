package dev.mrauro.keypads.commands;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class Create implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("create")) {
            if (sender instanceof Player player) {
                Block block = player.getTargetBlock(null, 2);
                if (block.getType().toString().equalsIgnoreCase("IRON_DOOR")) {
                    World world = player.getWorld();

                    if (args.length == 0) {
                        player.sendMessage("Please specify a code");
                        return true;
                    }

                    String code = args[0];

                    if (code.length() != 4) {
                        player.sendMessage("Code must be 4 digits long");
                        return true;
                    }

                    if (!code.matches("[1-9]+")) {
                        player.sendMessage("Code must be a number");
                        return true;
                    }

                    switch (block.getState().getBlockData().getAsString().split("\\[")[1].split("=")[1].split(",")[0]) {
                        case "north" -> {
                            Location location = new Location(world, block.getLocation().getX() - 0.5, block.getLocation().getY() - 0.5, block.getLocation().getZ() + 0.90);
                            ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
                            armorStand.setCustomName(code);
                            armorStand.setGravity(false);
                            armorStand.setRotation(0, 0);
                            armorStand.setSmall(true);
                            armorStand.setInvulnerable(true);
                            armorStand.setBasePlate(false);
                            armorStand.addDisabledSlots(org.bukkit.inventory.EquipmentSlot.values());

                            armorStand.setVisible(false);

                            ItemStack skullItem = NBTEditor.getHead("http://textures.minecraft.net/texture/2bc1284d273688a9613bffb13171e37b82f0d33be0f9c2fde17b7bec9e25f4");
                            SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
                            armorStand.setHelmet(skullItem);
                        }
                        case "south" -> {
                            Location location = new Location(world, block.getLocation().getX() + 1.5, block.getLocation().getY() - 0.5, block.getLocation().getZ() + 0.10);
                            ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
                            armorStand.setCustomName(code);
                            armorStand.setGravity(false);
                            armorStand.setRotation(180, 0);
                            armorStand.setSmall(true);
                            armorStand.setInvulnerable(true);
                            armorStand.setBasePlate(false);
                            armorStand.addDisabledSlots(org.bukkit.inventory.EquipmentSlot.values());

                            armorStand.setVisible(false);
                            ItemStack skullItem = NBTEditor.getHead("http://textures.minecraft.net/texture/2bc1284d273688a9613bffb13171e37b82f0d33be0f9c2fde17b7bec9e25f4");
                            SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
                            armorStand.setHelmet(skullItem);
                        }
                        case "east" -> {
                            Location location = new Location(world, block.getLocation().getX() + 0.10, block.getLocation().getY() - 0.5, block.getLocation().getZ() - 0.5);
                            ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
                            armorStand.setCustomName(code);
                            armorStand.setGravity(false);
                            armorStand.setRotation(90, 0);
                            armorStand.setSmall(true);
                            armorStand.setInvulnerable(true);
                            armorStand.setBasePlate(false);
                            armorStand.addDisabledSlots(org.bukkit.inventory.EquipmentSlot.values());

                            armorStand.setVisible(false);
                            ItemStack skullItem = NBTEditor.getHead("http://textures.minecraft.net/texture/2bc1284d273688a9613bffb13171e37b82f0d33be0f9c2fde17b7bec9e25f4");
                            SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
                            armorStand.setHelmet(skullItem);
                        }
                        case "west" -> {
                            Location location = new Location(world, block.getLocation().getX() + 0.90, block.getLocation().getY() - 0.5, block.getLocation().getZ() + 1.5);
                            ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
                            armorStand.setCustomName(code);
                            armorStand.setGravity(false);
                            armorStand.setRotation(270, 0);
                            armorStand.setSmall(true);
                            armorStand.setInvulnerable(true);
                            armorStand.setBasePlate(false);
                            armorStand.setVisible(false);
                            armorStand.addDisabledSlots(org.bukkit.inventory.EquipmentSlot.values());


                            ItemStack skullItem = NBTEditor.getHead("http://textures.minecraft.net/texture/2bc1284d273688a9613bffb13171e37b82f0d33be0f9c2fde17b7bec9e25f4");
                            SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
                            armorStand.setHelmet(skullItem);

                        }
                    }
                } else {
                    player.sendMessage("You must be looking at an Iron Door");
                }
            }
        }

        return true;
    }
}
