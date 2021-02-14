package com.epam.task.fourth.parser.sax;

import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.entity.PlantVisualParameters;
import com.epam.task.fourth.entity.Rosacea;
import com.epam.task.fourth.parser.XmlTagsConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlantHandler extends DefaultHandler implements XmlTagsConstants {
    private final static Logger LOGGER = LogManager.getLogger(PlantHandler.class);
    private List<Plant> plants = new ArrayList<>();
    private Plant currentPlant = new Plant();
    private String currentTag = null;

    private final List<String> INFO_TAGS = Arrays.asList(NAME, LENGTH, FLORAL_PARTS, COLOR);

    public List<Plant> getPlants() {
        return plants;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (PLANT.equals(localName)){
            currentPlant = new Plant();
            String temp = attributes.getValue(0);
            currentPlant.setId(temp);

        } else if (ROSACEA.equals(localName)){
            Rosacea rosacea = new Rosacea();

            String temp = attributes.getValue(0);
            rosacea.setId(temp);

            int length = attributes.getLength();
            if (length == 2){
                temp = attributes.getValue(1);
                boolean thorns = Boolean.parseBoolean(temp);
                rosacea.setThorns(thorns);
            } else {
                rosacea.setThorns(false);
            }
            currentPlant = rosacea;

        } else if (INFO_TAGS.contains(localName)){
                currentTag = localName;
            }

    }

    public void endElement(String uri, String localName, String qName) {
        if (PLANT.equals(localName) || ROSACEA.equals(localName)){
            plants.add(currentPlant);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String textInfo = new String(ch, start, length);
        PlantVisualParameters parameters = currentPlant.getVisualParameters();

        if(currentTag != null){
            switch (currentTag) {
                case NAME:
                    currentPlant.setName(textInfo);
                    break;
                case COLOR:
                    parameters.setColor(textInfo);
                    break;
                case FLORAL_PARTS:
                    parameters.setFloralParts(Integer.parseInt(textInfo));
                    break;
                case LENGTH:
                    parameters.setLength(Integer.parseInt(textInfo));
                    break;
                default:
                    LOGGER.info("<" + textInfo + ">" + " no such tag");
            }
        }
        currentTag = null;
    }

}