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

    public ArrayList<String>tempMin = new ArrayList<String>();
    public ArrayList<String>tempMax = new ArrayList<String>();
    public ArrayList<String>presion=new ArrayList<String>();
    public ArrayList<Double>velVent=new ArrayList<Double>();
    public ArrayList<String>dirVent=new ArrayList<String>();
    public ArrayList<String>humidad=new ArrayList<String>();
    public ArrayList<String>lluvia=new ArrayList<String>();
    public ArrayList<String>dia=new ArrayList<String>();

    /**
     *
     * @param list
     * @param ciutat
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void refresh(ObservableList list, String ciutat) throws ParserConfigurationException, IOException, SAXException {
        String text;
        URL xmlURL = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+"ciutat"+",es&lang=sp&mode=xml&APPID=720f431ee254e6c38e84787031900368");
        InputStream InputFile = xmlURL.openStream();
        /*
        Protocol d'entrada
         */
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(InputFile);
        doc.getDocumentElement().normalize();
        NodeList nl = doc.getElementsByTagName("time");

        for (int temp =0; temp<nl.getLength();temp++){
            Element temps = (Element) nl.item(temp);
            /**
             * Omplim les arrays amb a diferent informació que té cadascun dels dies.
             */
            tempMax.add(temps.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue());
            tempMin.add(temps.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue());
            velVent.add(Double.parseDouble(temps.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getNodeValue()));
            humidad.add(temps.getElementsByTagName("humidity").item(0).getAttributes().getNamedItem("value").getNodeValue()+" "+temps.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("unit").getNodeValue());
            presion.add(temps.getElementsByTagName("pressure").item(0).getAttributes().getNamedItem("value").getNodeValue()+" "+temps.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("unit").getNodeValue());
            dirVent.add(temps.getElementsByTagName("windDirection").item(0).getAttributes().getNamedItem("name").getNodeValue());
            dia.add(temps.getAttribute("day"));

            text="Dia :  "+temps.getAttribute("day")+"\n\n";
            if(temps.getElementsByTagName("precipitation").item(0).hasAttributes()){

                text=text+" - Previsiones de lluvia  :"+temps.getElementsByTagName("precipitation").item(0).getAttributes().getNamedItem("value").getNodeValue()+"\n";
                lluvia.add(temps.getElementsByTagName("precipitation").item(0).getAttributes().getNamedItem("value").getNodeValue());

            }else{
                lluvia.add("");
            }
            text=text+" - Temperatura Máxixma : "+temps.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue()+" Celcius"+"\n"+" - Temperatura Minima : "+temps.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue()+" Celcius\n";
            text=text+" - Estado  : " + temps.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("name").getNodeValue() + " " + temps.getElementsByTagName("clouds").item(0).getAttributes().getNamedItem("all").getNodeValue() + temps.getElementsByTagName("clouds").item(0).getAttributes().getNamedItem("unit").getNodeValue()+"\n\n";
            list.add(text);

        }

    }

}