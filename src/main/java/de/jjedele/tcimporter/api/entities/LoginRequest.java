package de.jjedele.tcimporter.api.entities;

/**
 * Request to log in.
 */
public class LoginRequest implements Entity {

    private final String identifier;
    private final String password;
    private final boolean isResettingPassword;
    private final boolean hasResetPassword;

    public LoginRequest(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
        this.isResettingPassword = false;
        this.hasResetPassword = false;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public boolean isResettingPassword() {
        return isResettingPassword;
    }

    public boolean isHasResetPassword() {
        return hasResetPassword;
    }

}
