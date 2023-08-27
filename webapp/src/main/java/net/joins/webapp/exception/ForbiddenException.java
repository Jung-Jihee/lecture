package net.joins.webapp.exception;

import net.joins.webapp.ApiStatus;

/**
 * @author ock
 */
public class ForbiddenException extends ApiException {
    private static final long serialVersionUID = 1078208706190054436L;
    /**
     *
     */
    public ForbiddenException() {
        super(ApiStatus.FORBIDDEN, "");
    }

    /**
     * @param message
     */
    public ForbiddenException(String message) {
        super(ApiStatus.FORBIDDEN, message);
    }
}
