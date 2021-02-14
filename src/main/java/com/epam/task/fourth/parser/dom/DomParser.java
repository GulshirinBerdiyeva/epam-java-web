package com.epam.task.fourth.parser.dom;

import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.entity.PlantVisualParameters;
import com.epam.task.fourth.entity.Rosacea;
import com.epam.task.fourth.exception.XmlException;
import com.epam.task.fourth.parser.XmlParser;
import com.epam.task.fourth.parser.XmlTagsConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements XmlParser, XmlTagsConstants {
    private final static Logger LOGGER = LogManager.getLogger(DomParser.class);
    private List<Plant> plants = new ArrayList<>();

    @Override
    public List<Plant> parse(String xmlFile) throws XmlException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            Element root = document.getDocumentElement();
            NodeList plantsList = root.getElementsByTagName(PLANT);
            addPlants(plantsList);
            NodeList rosaceaList = root.getElementsByTagName(ROSACEA);
            addPlants(rosaceaList);

            LOGGER.info(xmlFile + " parsed by DomParser");
            return plants;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new XmlException(e.getMessage(), e);
        }
    }

    private void addPlants(NodeList plantsList){
        for (int i = 0; i < plantsList.getLength(); i++){
            Element plantElement = (Element) plantsList.item(i);
            String tagName = plantElement.getTagName();
            if (PLANT.equals(tagName)){
                Plant plant = fillPlant(plantElement);
                plants.add(plant);
            } else if (ROSACEA.equals(tagName)){
                Rosacea rosacea = (Rosacea) fillPlant(plantElement);
                plants.add(rosacea);
            }
        }
    }

    private Plant fillPlant(Element plantElement) {
        Plant plant = new Plant();
        PlantVisualParameters visualParameters = new PlantVisualParameters();

        String tagName = plantElement.getTagName();
        if (ROSACEA.equals(tagName)){
            Rosacea rosacea = new Rosacea();
            boolean thorns = Boolean.parseBoolean(plantElement.getAttribute(THORNS));
            rosacea.setThorns(thorns);
            plant = rosacea;
        }

        String id = plantElement.getAttribute(ID);
        plant.setId(id);

        String name = getTagText(plantElement, NAME);
        plant.setName(name);

        String color = getTagText(plantElement, COLOR);
        visualParameters.setColor(color);

        int floralParts = Integer.parseInt(getTagText(plantElement, FLORAL_PARTS));
        visualParameters.setFloralParts(floralParts);

        int length = Integer.parseInt(getTagText(plantElement, LENGTH));
        visualParameters.setLength(length);

        plant.setVisualParameters(visualParameters);

        return plant;
    }

    private String getTagText(Element plantElement, String tagName) {
        NodeList nodeList = plantElement.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return node.getTextContent();
    }

}