package net.kontrex.api;

import java.util.UUID;

public class UUIDFetcher {

	private KontrexAPI api;
	
	public UUIDFetcher(KontrexAPI api) {
		this.api = api;
	}
	
	public String fetch(String name) {
		return (KontrexAPI.getJsonAPI().readString("https://api.mojang.com/users/profiles/minecraft/" + name, "id"));
	}

}
