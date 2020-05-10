package com.matthewksc.billlogic.Exeptions;

public class NotFoundUserException extends RuntimeException {

    public NotFoundUserException(Long id) {
        super("Can't find User with provided id: "+id);
    }
}
