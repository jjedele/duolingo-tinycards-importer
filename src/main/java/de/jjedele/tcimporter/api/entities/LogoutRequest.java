package de.jjedele.tcimporter.api.entities;

/**
 * Request to log out.
 */
public class LogoutRequest implements Entity {

    private final boolean logout = true;

    public boolean isLogout() {
        return logout;
    }

}
