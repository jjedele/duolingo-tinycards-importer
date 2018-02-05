package de.jjedele.tcimporter.api.entities.cards;

import java.util.List;

/**
 * Side of a card.
 */
public class Side {

    private final List<Concept> concepts;

    public Side(List<Concept> concepts) {
        this.concepts = concepts;
    }

    public List<Concept> getConcepts() {
        return concepts;
    }

}
