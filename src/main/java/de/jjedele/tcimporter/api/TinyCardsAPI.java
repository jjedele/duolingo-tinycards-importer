package de.jjedele.tcimporter.api;

import de.jjedele.tcimporter.api.entities.LoginRequest;
import de.jjedele.tcimporter.api.entities.LoginResponse;
import de.jjedele.tcimporter.api.entities.LogoutRequest;
import de.jjedele.tcimporter.api.entities.LogoutResponse;
import de.jjedele.tcimporter.api.httpclient.HttpClient;

import java.io.IOException;
import java.net.URI;

/**
 * API for the DuoLingo TinyCards application.
 */
public class TinyCardsAPI {

    public static final URI API_ROOT = URI.create("https://tinycards.duolingo.com/api/1/");

    private final HttpClient httpClient;

    private boolean isSignedIn = false;

    public TinyCardsAPI() {
        this.httpClient = new HttpClient();
    }

    /**
     * Login to API using DuoLingo account.
     *
     * Must be called before all other actions.
     *
     * @param user DuoLingo user name
     * @param password DuoLingo password
     * @throws IOException
     */
    public void login(String user, String password) throws IOException {
        URI loginUri = API_ROOT.resolve("login");
        LoginRequest request = new LoginRequest(user, password);
        LoginResponse response = httpClient.post(loginUri, request, LoginResponse.class);
        this.isSignedIn = true;
    }

    /**
     * Logout from API.
     *
     * @throws IOException
     */
    public void logout() throws IOException {
        URI logout_uri = API_ROOT.resolve("logout");
        LogoutRequest request = new LogoutRequest();
        LogoutResponse response = httpClient.post(logout_uri, request, LogoutResponse.class);
        this.isSignedIn = false;
    }

}
