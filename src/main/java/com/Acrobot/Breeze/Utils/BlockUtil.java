package com.Acrobot.Breeze.Utils;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Sign;
import org.bukkit.block.data.Rotatable;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * @author Acrobot
 */
public class BlockUtil {
    /**
     * Checks if the block is a sign
     *
     * @param block Block to check
     * @return Is this block a sign?
     */
    public static boolean isSign(Block block) {
    	
    	if(block == null || block.getState() == null) return false;
    	
    	return (block.getState() instanceof Sign);
    }

    /**
     * Checks if the block is a chest
     *
     * @param block Block to check
     * @return Is this block a chest?
     */
    public static boolean isChest(Block block) {
        return block.getState() instanceof Chest;
    }

    /**
     * Checks if the InventoryHolder is a chest
     *
     * @param holder Inventory holder to check
     * @return Is this holder a chest?
     */
    public static boolean isChest(InventoryHolder holder) {
        return holder instanceof Chest || holder instanceof DoubleChest;
    }

    /**
     * Gets the block to which the sign is attached
     *
     * @param sign Sign which is attached
     * @return Block to which the sign is attached
     */
    public static Block getAttachedBlock(Sign sign) {
    	if(sign.getBlock().getBlockData() instanceof WallSign) return sign.getBlock().getRelative(BlockFace.DOWN);
    	else if(sign.getBlock().getBlockData() instanceof org.bukkit.block.data.type.Sign) {
    		BlockFace blockFace = ((Rotatable) sign.getBlock().getBlockData()).getRotation();
        	
        	//org.bukkit.block.data.type.Sign a = (org.bukkit.block.data.type.Sign) sign.getBlock().getBlockData();
        	
        	
        	
        	
        	/*at net.minecraft.server.v1_14_R1.MinecraftServer.sleepForTick(MinecraftServer.java:869) [server.jar:git-Spigot-4d24e36-ba2e5e7]
        			at net.minecraft.server.v1_14_R1.MinecraftServer.run(MinecraftServer.java:802) [server.jar:git-Spigot-4d24e36-ba2e5e7]
        			at java.lang.Thread.run(Thread.java:748) [?:1.8.0_212]
        			Caused by: java.lang.ClassCastException: org.bukkit.craftbukkit.v1_14_R1.block.impl.CraftWallSign cannot be cast to org.bukkit.block.data.type.Sign
        			at com.Acrobot.Breeze.Utils.BlockUtil.getAttachedBlock(BlockUtil.java:56) ~[?:?]
        			at com.Acrobot.ChestShop.Listeners.Block.Break.SignBreak.onSign(SignBreak.java:54) ~[*/
        	
        	//org.bukkit.material.Sign s = ((org.bukkit.material.Sign) sign.getBlock().getState().getData());
        	//Attachable att = ((Attachable) sign.getBlock());
        	
            //return sign.getBlock().getRelative(att.getAttachedFace());
        	//return sign.getBlock().getRelative(a.getRotation());
        	return sign.getBlock().getRelative(blockFace);
    	}
    	
    	return null;
    }

    /**
     * Opens the holder's inventory GUI
     *
     * @param holder Inventory holder
     * @param player Player on whose screen the GUI is going to be shown
     * @return Was the opening successful?
     */
    public static boolean openBlockGUI(InventoryHolder holder, Player player) {
        Inventory inventory = holder.getInventory();
        player.openInventory(inventory);

        return true;
    }
}
