package com.tgm.parser;
import com.tgm.gameObjects.Text;
import javafx.util.Pair;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class XMLConverterTest {

    private DocumentBuilder dBuilder = null;

    private DocumentBuilder getdBuilder() throws ParserConfigurationException {
        if(dBuilder == null) {
            dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        return dBuilder;
    }

    @Test
    public void textBuildTest() throws ParserConfigurationException, SAXException, IOException {
        String text = "Hey there Tim, this is Jon!";
        String textXML = "<Text>" + text + "</Text>";
        Element textNode = getdBuilder().parse(new InputSource(new StringReader(textXML))).getDocumentElement();
        Text textObj = XMLConverter.buildText(textNode);
        assertEquals(text, textObj.getText());
        assertEquals(null, textObj.getNextElement());
    }

    @Test
    public void textNextBuildTest() throws ParserConfigurationException, SAXException, IOException {
        String next_story = "Dialog";
        String next_id = "Happy Reply";
        String text = "I'm feeling great!";
        String textXML = String.format("<Text next=\"%s:%s\">" + text + "</Text>",next_story,next_id);
        Element textNode = getdBuilder().parse(new InputSource(new StringReader(textXML))).getDocumentElement();
        Text textObj = XMLConverter.buildText(textNode);
        assertEquals(text, textObj.getText());
        Pair<String,String> tempNext = textObj.getNextElement();
        assertEquals(next_story, tempNext.getKey());
        assertEquals(next_id, tempNext.getValue());
    }

    @Test
    public void replyBuildTest() throws ParserConfigurationException, SAXException, IOException {

    }
}
