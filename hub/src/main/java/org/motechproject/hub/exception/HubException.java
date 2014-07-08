package org.motechproject.hub.exception;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

@SuppressWarnings("serial")
public class HubException extends Exception {

    private HubErrors hubErrors;
    private String reason;

    public HubException(HubErrors hubErrors) {
        super(hubErrors.getMessage());
        this.hubErrors = hubErrors;
    }

    public HubException(HubErrors hubErrors, String reason) {
        super(hubErrors.getMessage());
        this.hubErrors = hubErrors;
        this.reason = reason;
    }

    public HubException(HubErrors hubErrors, Throwable throwable) {
        super(hubErrors.getMessage(), throwable);
        this.hubErrors = hubErrors;
    }

    public HubException(HubErrors hubErrors, Throwable throwable, String reason) {
        super(hubErrors.getMessage(), throwable);
        this.hubErrors = hubErrors;
        this.reason = reason;
    }

    public int getErrorCode() {
        return hubErrors.getCode();
    }

    public String getErrorMessage() {
        if (reason == null || StringUtils.length(reason) < 1) {
            return this.getMessage();
        } else {
            return this.getMessage() + ". Reason: " + reason;
        }
    }

    public HubErrors getError() {
        return hubErrors;
    }

    public String getErrorMessageDetails() {

        return getStackTraceString();
    }

    private String getStackTraceString() {
        return ExceptionUtils.getStackTrace(this);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
