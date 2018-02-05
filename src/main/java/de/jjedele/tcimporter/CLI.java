package de.jjedele.tcimporter;

import de.jjedele.tcimporter.api.APIHelper;
import de.jjedele.tcimporter.api.TinyCardsAPI;
import de.jjedele.tcimporter.api.entities.cards.Card;
import de.jjedele.tcimporter.api.entities.cards.Deck;
import org.apache.commons.cli.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.mozilla.universalchardet.ReaderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CLI {

    public static void main(String[] args) throws IOException, ParseException {
        Options options = new Options();

        options.addOption("csv", true, "Path to vocabulary in CSV format.");
        options.addOption("user", true, "DuoLingo user name.");
        options.addOption("password", true, "DuoLingo password.");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        File inFile = new File(cmd.getOptionValue("csv"));
        BufferedReader br = ReaderFactory.createBufferedReader(inFile);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(br);
        List<Card> vocabs = new ArrayList<>();
        records.forEach(record ->
                vocabs.add(APIHelper.simpleVocabularyCard(record.get(0), record.get(1))));
        Deck deck = new Deck(vocabs);

        TinyCardsAPI api = new TinyCardsAPI();
        api.login(cmd.getOptionValue("user"), cmd.getOptionValue("password"));
        api.createDeck("My Deck", "Really cool deck",true, false, deck);
        api.logout();
    }

}
