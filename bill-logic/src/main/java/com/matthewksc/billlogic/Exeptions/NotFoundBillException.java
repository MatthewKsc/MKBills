package com.matthewksc.billlogic.Exeptions;

public class NotFoundBillException extends RuntimeException {

    public NotFoundBillException(Long id) {
        super("Can't find bill with id : "+id);
    }
}
