package de.jjedele.tcimporter.api.entities.cards;

/**
 * Abstract fact for cards.
 */
public abstract class Fact {

    private final String type;

    public Fact(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
