package de.jjedele.tcimporter.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for successful login requests.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse implements Entity {

    private final String email;
    private final long id;
    private final String username;

    @JsonCreator
    public LoginResponse(@JsonProperty("email") String email,
                         @JsonProperty("id") long id,
                         @JsonProperty("username") String username) {
        this.email = email;
        this.id = id;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
