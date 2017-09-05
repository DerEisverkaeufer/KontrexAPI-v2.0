package net.kontrex.api.chatapi;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by DerEisverkaeufer on 05.09.2017.
 */
public final class TitleAPI {

    /**
        Sends a title and subtitle to a specified Player.
        @param p The player who will see the title.
        @param title The title the player will see.
        @param subtitle The subtitle the player will see. (If you don't want any subtitle, set the paramater subtitle to 'null'.
        @param fadeIn The time the title fades in until it will be 100% visible
        @param fadeOut The time the title fades out until it will be invisible
     */
    public static void send(Player p, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title + "\"}");
        PacketPlayOutTitle titlepPckt = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(titlepPckt);

        if(subtitle != null) {
            IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subtitle + "\"}");
            PacketPlayOutTitle subtitlepPckt = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitlepPckt);
        }

        PacketPlayOutTitle length = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);

    }

    /**
     * Resets the title, subtitle and timings (fadeIn, stay, fadeOut) of a specified player.
     * @param p The player which title will be resetted.
     */
    public static void reset(Player p) {
        PacketPlayOutTitle reset = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, IChatBaseComponent.ChatSerializer.a("{\"text\":\"resetting...\"}"));
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(reset);
    }

    /**
     * Clears the title and subtitle of a specified player, the timings won't be changed.
     * @param p The player which title will be cleared.
     */
    public static void clear(Player p) {
        PacketPlayOutTitle reset = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, IChatBaseComponent.ChatSerializer.a("{\"text\":\"clearing...\"}"));
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(reset);
    }

}
