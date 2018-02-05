package de.jjedele.tcimporter.api.entities.cards;

/**
 * Textual fact for a card.
 */
public class TextFact extends Fact {

    private final String text;

    public TextFact(String text) {
        super("TEXT");
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
