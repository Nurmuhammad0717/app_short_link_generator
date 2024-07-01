package uz.pdp.appshortlink.exception;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException {

    private String message;

    public RestException(String message) {
        this.message = message;
    }

    public static RestException restThrow(String message){
        return new RestException(message);
    }
    public static RestException alreadyExist(String key){
        return new RestException(key + " already exists");
    }

    public static RestException notFound(String key) {
        return new RestException(key + " not found");
    }
}
