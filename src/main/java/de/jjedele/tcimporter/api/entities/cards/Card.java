package de.jjedele.tcimporter.api.entities.cards;

import de.jjedele.tcimporter.api.entities.Entity;

import java.util.List;

/**
 * A card.
 */
public class Card implements Entity {

    private final long creationTimestamp;
    private final List<Side> sides;

    public Card(List<Side> sides) {
        this.creationTimestamp = System.currentTimeMillis();
        this.sides = sides;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public List<Side> getSides() {
        return sides;
    }

}
