package net.kontrex.api.network;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 07.09.2017
 * - TIME: 22:23
 * - PROJECT: KontrexAPI
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class NetworkException extends Exception {

    private NetworkExceptionResult result;

    public NetworkException(NetworkExceptionResult result) {
        this.result = result;
    }

    @Override
    public String getMessage() {
        return result.getMessage();
    }

}
