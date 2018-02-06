package de.jjedele.tcimporter.csv;

/**
 * Entry of a CSV file.
 */
public class Entry {

    private final String firstLanguage;
    private final String secondLanguage;

    public Entry(String firstLanguage, String secondLanguage) {
        this.firstLanguage = firstLanguage;
        this.secondLanguage = secondLanguage;
    }

    public String getFirstLanguage() {
        return firstLanguage;
    }

    public String getSecondLanguage() {
        return secondLanguage;
    }

}
