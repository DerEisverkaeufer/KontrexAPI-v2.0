package net.kontrex.api;

public class UUIDFetcher {

	public static String fetch(String name) {
		return (JsonAPI.readString("https://api.mojang.com/users/profiles/minecraft/" + name, "id"));
	}

}
