package net.kontrex.api.network;

/**
 * Created by DerEisverkaeufer on 07.09.2017.
 */
public enum NetworkExceptionResult {

    CLIENT_NOT_CONNECTED("The Client is't connected and cannot be used!"),
    MESSAGE_RECEIVE_ERROR("Error while receiving message!"),
    MESSAGE_SEND_ERROR("Error while sending message!"),
    NETWORK_ERROR("An unknown error occured!");

    private String message;

    private NetworkExceptionResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
