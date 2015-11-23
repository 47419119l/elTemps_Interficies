package sample;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


import java.net.URL;
import java.util.*;


public class DOM_Parser {
    /**
     * Mostra el Resum del XML i crida a la funcio crearFitxerXMl
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public ArrayList<String>text = new ArrayList<String>();
    public ArrayList<String>detallinfo = new ArrayList<String>();
    public ArrayList<String>icon=new ArrayList<String>();


    public void mostrar(ObservableList list, String ciutat) throws ParserConfigurationException, IOException, SAXException {
        String text;
        String detall;
        //File InputFile = new File("forecast.xml");
        URL xmlURL = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+"ciutat"+",es&lang=sp&mode=xml&APPID=720f431ee254e6c38e84787031900368");
        InputStream InputFile = xmlURL.openStream();

        /*
        Protocol d'entrada
         */
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        /*
        import org.w3c.dom.Document; És important fer aquest import.
         */
        Document doc = db.parse(InputFile);
        /*
        Important Normalitzar.
         */
        doc.getDocumentElement().normalize();

        /*
        NodeList per extreure el temps que fa
         */
        NodeList nl = doc.getElementsByTagName("time");

        for (int temp =0; temp<nl.getLength();temp++){

            /*
            IMMPORTANT : import org.w3c.dom.Element;
             */

            Element temps = (Element) nl.item(temp);
            /*
            Extreiem la velocitat del vent per despres calcularle en Kph
             */
            Double vel_Vent_Mps = Double.parseDouble(temps.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getNodeValue());
            Double vel_Vent_Kph = vel_Vent_Mps*3.6;
            /*
            Mostrem la informació
             */

            text="Dia : "+temps.getAttribute("day")+"\n\n";

            /*
            Si Presipitacions té atributs mostrarem la informmació si no no mostrarem res.
             */
            if(temps.getElementsByTagName("precipitation").item(0).hasAttributes()){

                text=text+"     - Previsions pluja  :"+temps.getElementsByTagName("precipitation").item(0).getAttributes().getNamedItem("value").getNodeValue()+"\n";
            }
            text=text+"     - Temperatura : "+temps.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue()+" Celcius"+"\n";

            text=text+"     - Núvols  : " + temps.getElementsByTagName("clouds").item(0).getAttributes().getNamedItem("value").getNodeValue() + " " + temps.getElementsByTagName("clouds").item(0).getAttributes().getNamedItem("all").getNodeValue() + temps.getElementsByTagName("clouds").item(0).getAttributes().getNamedItem("unit").getNodeValue()+"\n\n";
            this.text.add(text);
            this.detallinfo.add("mostra");
            this.icon.add("ok");
            list.add(text);

        }

    }

}