package de.jjedele.tcimporter;

import de.jjedele.tcimporter.api.APIHelper;
import de.jjedele.tcimporter.api.TinyCardsAPI;
import de.jjedele.tcimporter.api.entities.cards.Card;
import de.jjedele.tcimporter.api.entities.cards.Deck;

import java.io.IOException;
import java.util.Arrays;

public class Importer {

    public static void main(String[] args) throws IOException {
        Card card = APIHelper.simpleVocabularyCard("foo", "bar");
        Deck deck = new Deck(Arrays.asList(card));
        TinyCardsAPI tca = new TinyCardsAPI();

        tca.login("bla", "bla");
        tca.createDeck("mydeck2", "some deck", true, false, deck);
        tca.logout();
    }

}
