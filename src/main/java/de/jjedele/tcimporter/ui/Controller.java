package de.jjedele.tcimporter.ui;

import de.jjedele.tcimporter.api.APIHelper;
import de.jjedele.tcimporter.api.TinyCardsAPI;
import de.jjedele.tcimporter.api.entities.cards.Card;
import de.jjedele.tcimporter.api.entities.cards.Deck;
import de.jjedele.tcimporter.csv.CSVHelper;
import de.jjedele.tcimporter.csv.Entry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField deckName;
    @FXML private TableView<Entry> previewTable;

    @FXML
    protected void handleLoadCSVButtonPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(username.getScene().getWindow());
        try {
            List<Entry> vocabs = CSVHelper.readCSV(file);

            previewTable.getItems().clear();
            previewTable.getColumns().clear();

            TableColumn<Entry, String> col1 = new TableColumn();
            col1.setText("Language 1");
            col1.setCellValueFactory(new PropertyValueFactory("firstLanguage"));

            TableColumn<Entry, String> col2 = new TableColumn();
            col2.setText("Language 2");
            col2.setCellValueFactory(new PropertyValueFactory("secondLanguage"));

            previewTable.getColumns().addAll(col1, col2);


            previewTable.getItems().addAll(vocabs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleImportButtonPressed(ActionEvent actionEvent) {
        String user = username.getText();
        String pass = password.getText();
        String deck = deckName.getText();

        if (user.isEmpty() ||pass.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username and password must be configured.");
            alert.showAndWait();
            return;
        }

        if (deck.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Deck name must be configured.");
            alert.showAndWait();
            return;
        }

        if (previewTable.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vocabulary CSV must be loaded first.");
            alert.showAndWait();
            return;
        }

        if (previewTable.getItems().size() > 150) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Card Limit exceeded");
            alert.setHeaderText("Card Limit exceeded");
            alert.setContentText("DuoLingo natively supports only 150 cards per deck. You can continue to create a bigger deck, but I advise to split your file over multiple decks.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
            } else {
                return;
            }
        }

        try {
            List<Card> cards = new ArrayList<>();
            previewTable.getItems().forEach(entry ->
                    cards.add(APIHelper.simpleVocabularyCard(
                            entry.getFirstLanguage(),
                            entry.getSecondLanguage())));

            Deck deckObj = new Deck(cards);
            TinyCardsAPI api = new TinyCardsAPI();
            api.login(user, pass);
            api.createDeck(deck, "Imported deck", true, false, deckObj);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deck has been created.");
            alert.showAndWait();

            api.logout();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception Dialog");

            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        }
    }
}
