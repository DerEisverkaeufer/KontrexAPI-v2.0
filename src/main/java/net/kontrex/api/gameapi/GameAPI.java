package net.kontrex.api.gameapi;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 14.09.2017
 * - TIME: 16:49
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class GameAPI {

    private static GameStatus gameStatus;

    public static void setGameStatus(GameStatus gameStatus) {
        GameAPI.gameStatus = gameStatus;
    }

    public static GameStatus getGameStatus() {
        return gameStatus;
    }
}
