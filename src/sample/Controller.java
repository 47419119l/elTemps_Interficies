package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    String ciutatSel;
    @FXML
    ListView list = new ListView();
    ArrayList lista;
    @FXML
    MenuButton ciutats;
    @FXML
    Label detalls;
    @FXML
    Label info;
    @FXML
    ImageView icon;
    @FXML
    CheckMenuItem bcn;
    @FXML
    CheckMenuItem bdln;
    @FXML
    CheckMenuItem grn;
    @FXML
    CheckMenuItem sbd;

    DOM_Parser temps;

    public void refresh() throws IOException, SAXException, ParserConfigurationException {
        list.setVisible(true);
        temps = new DOM_Parser();
        ObservableList<String> items = FXCollections.observableArrayList();
        ciutatSel=ciutats.getText();
        temps.refresh(items,ciutatSel);
        list.setItems(items);
    }

    public void menu(ActionEvent actionEvent) {
        bcn.setSelected(false);
        bdln.setSelected(false);
        grn.setSelected(false);
        sbd.setSelected(false);

        if (actionEvent.getSource().equals(bcn)){
            bcn.setSelected(true);
            ciutats.setText(bcn.getText());
        }
        else if (actionEvent.getSource().equals(sbd)){
            sbd.setSelected(true);
            ciutats.setText(sbd.getText());
        }
        else if (actionEvent.getSource().equals(grn)){
            grn.setSelected(true);
            ciutats.setText(grn.getText());
        }else {
            bdln.setSelected(true);
            ciutats.setText(bdln.getText());
        }
    }
}
