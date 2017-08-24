package net.kontrex.api;

import java.util.UUID;

import com.mojang.authlib.properties.Property;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import com.google.gson.JsonArray;

@SuppressWarnings("unused")
public class SkinAPI {

	private KontrexAPI api;
	
	public SkinAPI(KontrexAPI api) {
		this.api = api;
	}
	
	public void setSkin(Player p, String uuid) {
		JsonArray array = KontrexAPI.getJsonAPI().readObj("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString() + "?unsigned=false").get("properties").getAsJsonArray();
		String value = array.get(0).getAsJsonObject().get("value").getAsString();
		String signature = array.get(0).getAsJsonObject().get("signature").getAsString();
		
		((CraftPlayer)p).getProfile().getProperties().put("textures", new Property("textures", value, signature));
	}
	
	public Property getSkin(String uuid) {
		JsonArray array = KontrexAPI.getJsonAPI().readObj("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString() + "?unsigned=false").get("properties").getAsJsonArray();
		String value = array.get(0).getAsJsonObject().get("value").getAsString();
		String signature = array.get(0).getAsJsonObject().get("signature").getAsString();
		return new Property("textures", value, signature);
	}

}
