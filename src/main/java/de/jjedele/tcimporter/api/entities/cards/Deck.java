package de.jjedele.tcimporter.api.entities.cards;

import com.fasterxml.jackson.annotation.JsonValue;
import de.jjedele.tcimporter.api.entities.Entity;

import java.util.List;

/**
 * A card deck.
 */
public class Deck implements Entity {

    @JsonValue
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

}
