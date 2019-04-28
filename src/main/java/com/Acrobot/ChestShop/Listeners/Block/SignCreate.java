package com.Acrobot.ChestShop.Listeners.Block;

import com.Acrobot.Breeze.Utils.BlockUtil;
import com.Acrobot.Breeze.Utils.StringUtil;
import com.Acrobot.ChestShop.ChestShop;
import com.Acrobot.ChestShop.Events.PreShopCreationEvent;
import com.Acrobot.ChestShop.Events.ShopCreatedEvent;
import com.Acrobot.ChestShop.Signs.ChestShopSign;
import com.Acrobot.ChestShop.Utils.uBlock;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * @author Acrobot
 */
public class SignCreate implements Listener {

    @EventHandler
    public static void onSignChange(SignChangeEvent event) {
        Block signBlock = event.getBlock();
        String[] line = StringUtil.stripColourCodes(event.getLines());

        	//Bukkit.broadcastMessage("test");
        
        if (!BlockUtil.isSign(signBlock)) {
        	//XXX: debug
        	//Bukkit.broadcastMessage("Not sign? : " + signBlock.getType().toString());
            return;
        }

        //Bukkit.broadcastMessage("test2");
        
        if (!ChestShopSign.isValidPreparedSign(line)) {
        	//Bukkit.broadcastMessage("end");
            return;
        }

       // Bukkit.broadcastMessage("test3");
        
        PreShopCreationEvent preEvent = new PreShopCreationEvent(event.getPlayer(), (Sign) signBlock.getState(), line);
        ChestShop.callEvent(preEvent);

        for (byte i = 0; i < event.getLines().length; ++i) {
            event.setLine(i, preEvent.getSignLine(i));
        }

       // Bukkit.broadcastMessage("test4");
        
        if (preEvent.isCancelled()) {
        	//Bukkit.broadcastMessage("end22");
            return;
        }

        ShopCreatedEvent postEvent = new ShopCreatedEvent(preEvent.getPlayer(), preEvent.getSign(), uBlock.findConnectedChest(preEvent.getSign()), preEvent.getSignLines());
        ChestShop.callEvent(postEvent);
       // Bukkit.broadcastMessage("test5");
    }
}
