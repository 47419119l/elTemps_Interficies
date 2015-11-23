package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    @FXML
    ListView list = new ListView();
    ArrayList lista;
    @FXML
    ChoiceBox ciutats;
    @FXML
    Label detalls;
    @FXML
    Label info;
    @FXML
    ImageView icon;

    private String ciutat;
    DOM_Parser temps;

    public void refresh() throws IOException, SAXException, ParserConfigurationException {

        detalls.setVisible(false);
        info.setVisible(false);
        list.setVisible(true);

        temps = new DOM_Parser();
        ciutat ="Barcelona";
        ObservableList<String> items = FXCollections.observableArrayList();
        temps.mostrar(items,ciutat);
        list.setItems(items);
    }

    public void onChangeCountClick(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ChangeCount.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Change item's count");
            newStage.setScene(new Scene(root, 320, 240));
            newStage.show();
            ciutats = new ChoiceBox();
            ciutats.setItems(FXCollections.observableArrayList("One","Two","Three"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    public void detall(Event event) {

        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            int i = list.getSelectionModel().getSelectedIndex();
            list.setVisible(false);
            info.setVisible(true);
            detalls.setVisible(true);
            info.setText(temps.text.get(i));
            detalls.setText(temps.detallinfo.get(i));
            Image image = new Image("pluja.gif");
            icon.setImage(image);

        });
    }
*/
    public void menu(ActionEvent actionEvent) {


    }
}
