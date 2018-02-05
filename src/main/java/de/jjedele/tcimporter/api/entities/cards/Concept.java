package de.jjedele.tcimporter.api.entities.cards;

/**
 * A concept for a card.
 */
public class Concept {

    private final Fact fact;

    public Concept(Fact fact) {
        this.fact = fact;
    }

    public Fact getFact() {
        return fact;
    }

}
