package net.kontrex.api;

import com.mojang.authlib.properties.Property;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import com.google.gson.JsonArray;

@SuppressWarnings("unused")
public class SkinAPI {

	public static void setSkin(Player p, String uuid) {
		JsonArray array = JsonAPI.readObj("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString() + "?unsigned=false").get("properties").getAsJsonArray();
		String value = array.get(0).getAsJsonObject().get("value").getAsString();
		String signature = array.get(0).getAsJsonObject().get("signature").getAsString();
		
		((CraftPlayer)p).getProfile().getProperties().put("textures", new Property("textures", value, signature));
	}
	
	public static Property getSkin(String uuid) {
		JsonArray array = JsonAPI.readObj("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString() + "?unsigned=false").get("properties").getAsJsonArray();
		String value = array.get(0).getAsJsonObject().get("value").getAsString();
		String signature = array.get(0).getAsJsonObject().get("signature").getAsString();
		return new Property("textures", value, signature);
	}

}
