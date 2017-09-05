package net.kontrex.api.chatapi;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by DerEisverkaeufer on 05.09.2017.
 */
public class ActionBarAPI {

    public static void send(Player p, String msg) {
        PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msg + "\"}");
        PacketPlayOutChat chat = new PacketPlayOutChat(component, (byte)2);
        con.sendPacket(chat);
    }

}
