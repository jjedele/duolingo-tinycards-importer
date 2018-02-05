package de.jjedele.tcimporter.api;

import de.jjedele.tcimporter.api.entities.cards.*;

import java.util.Arrays;

/**
 * Helper methods to work with the API.
 */
public final class APIHelper {

    private APIHelper() {}

    public static Card simpleVocabularyCard(String firstlanguge, String secondLanguage) {
        Fact fact1 = new TextFact(firstlanguge);
        Concept concept1 = new Concept(fact1);
        Side side1 = new Side(Arrays.asList(concept1));

        Fact fact2 = new TextFact(secondLanguage);
        Concept concept2 = new Concept(fact2);
        Side side2 = new Side(Arrays.asList(concept2));

        Card card = new Card(Arrays.asList(side1, side2));

        return card;
    }

}
