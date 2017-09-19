package net.kontrex.api.gameapi;

public enum GameStatus {

    LOADING("LOADING", false, false),
    LOBBY("LOBBY", false, true),
    LOBBY_FULL("LOBBYFULL", false, true),
    STARTING("STARTING", false, false),
    INGAME_SPECTATE("INGAME", true, true),
    INGAME("INGAME", true, false),
    ENDING("ENDING", false, false),
    RESET("RESET", false, false);

    private String motd;
    private boolean enableDamage;
    private boolean enableJoin;

    GameStatus(String motd, boolean enableDamage, boolean enableJoin) {
        this.motd = motd;
        this.enableDamage = enableDamage;
        this.enableJoin = enableJoin;
    }

    public String getMotd() {
        return motd;
    }

    public boolean isEnableDamage() {
        return enableDamage;
    }

    public boolean isEnableJoin() {
        return enableJoin;
    }
}
