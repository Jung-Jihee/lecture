package net.joins.webapp.exception;

import net.joins.webapp.ApiStatus;

/**
 * @author ock
 */
public class BadRequestException extends ApiException {
    private static final long serialVersionUID = 5727135242632049020L;

    /**
     *
     */
    public BadRequestException() {
        super(ApiStatus.BAD_REQUEST, "");
    }

    /**
     * @param message
     */
    public BadRequestException(String message) {
        super(ApiStatus.BAD_REQUEST, message);
    }
}
