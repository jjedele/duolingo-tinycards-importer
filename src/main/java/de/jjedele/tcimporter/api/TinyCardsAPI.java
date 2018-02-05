package de.jjedele.tcimporter.api;

import de.jjedele.tcimporter.api.entities.*;
import de.jjedele.tcimporter.api.entities.cards.Deck;
import de.jjedele.tcimporter.api.httpclient.HttpClient;
import de.jjedele.tcimporter.api.httpclient.JsonMapper;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
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
        URI logoutUri = API_ROOT.resolve("logout");
        LogoutRequest request = new LogoutRequest();
        LogoutResponse response = httpClient.post(logoutUri, request, LogoutResponse.class);
        this.isSignedIn = false;
    }

    /**
     * Create a card deck.
     *
     * @param name Name of the deck
     * @param description Description of the deck
     * @param isPrivate If other people can see the deck
     * @param shareable If the deck can be shared
     * @param deck The deck itself
     * @throws IOException
     */
    public void createDeck(String name,
                           String description,
                           boolean isPrivate,
                           boolean shareable,
                           Deck deck) throws IOException {
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA);
        entityBuilder.addTextBody("name", name);
        entityBuilder.addTextBody("description", description);
        entityBuilder.addTextBody("private", Boolean.toString(isPrivate));
        entityBuilder.addTextBody("shareable", Boolean.toString(shareable));
        entityBuilder.addTextBody("cards", JsonMapper.INSTANCE.writeValueAsString(deck));
        final String emptyJson = "[]";
        entityBuilder.addTextBody("ttsLanguages", emptyJson);
        entityBuilder.addTextBody("blacklistedSideIndices", emptyJson);
        entityBuilder.addTextBody("blacklistedQuestionTypes", emptyJson);
        entityBuilder.addTextBody("gradingModes", emptyJson);
        entityBuilder.addTextBody("fromLanguage", "en");
        entityBuilder.addBinaryBody("imageFile",
                new File("square.png"),
                ContentType.create("image/png"),
                "cover.png");

        URI decksUri = API_ROOT.resolve("decks");

        DeckCreationResponse response = httpClient.post(decksUri, entityBuilder.build(), DeckCreationResponse.class);

        System.out.println(response.getId());
    }

}
