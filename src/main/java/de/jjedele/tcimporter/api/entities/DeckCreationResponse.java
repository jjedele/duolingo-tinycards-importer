package de.jjedele.tcimporter.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response of a successful Deck creation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeckCreationResponse implements Entity {

    private final String id;

    @JsonCreator
    public DeckCreationResponse(@JsonProperty("id") String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
