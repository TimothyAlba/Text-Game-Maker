package com.worldCreator.parser;

import com.worldCreator.gameObjects.*;
import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class XMLConverter {

    static public final String STORY = "story";
    static public final String CHAPTER = "chapter";
    static public final String SCENE = "scene";
    static public final String DIALOG = "dialog";
    static public final String REPLY = "reply";
    static public final String TEXT = "text";
    static public final String BACKGROUND_IMG = "backgroundimage";
    static public final String BACKGROUND_MUSIC = "backgroundmusic";
    static public final String ID = "id";
    static public final String NEXT = "next";
    static public final String NEXT_REGEX = ":";
    static private final String [] HIERARCHY = {STORY, CHAPTER, SCENE, DIALOG, REPLY};

    static public Game parse(String filepath) throws Exception {
        try {
            File xmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            HashMap<String,Chapter> gameStory = null;
            NodeList childNodes = root.getChildNodes();
            Node temp;

            for (int i = 0; i < childNodes.getLength(); i++) {
                temp = childNodes.item(i);
                if(temp.getNodeType() == Node.ELEMENT_NODE){
                    switch(temp.getNodeName().toLowerCase()) {
                        case STORY: gameStory = buildStory((Element) temp);
                    }
                }
            }

            return new Game(gameStory);
        }
        catch(ParserConfigurationException | SAXException | IOException e){
            throw new Exception(e);
        }
    }

    static public HashMap<String,Chapter> buildStory(Element storyNode) {
        HashMap<String,Chapter> chapters = new HashMap<>();
        NodeList childNodes = storyNode.getChildNodes();
        Node temp;

        for (int i = 0; i < childNodes.getLength(); i++) {
            temp = childNodes.item(i);
            if(temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equalsIgnoreCase(CHAPTER)) {
                Pair<String,Chapter> tempScene = buildChapter((Element) temp);
                chapters.put(tempScene.getKey(), tempScene.getValue());
            }
        }

        return chapters;
    }

    static public Pair<String,Chapter> buildChapter(Element chapterNode) {
        String identification = chapterNode.getAttribute(ID);
        HashMap<String,Scene> scenes = new HashMap<>();
        NodeList childNodes = chapterNode.getChildNodes();
        Node temp;

        for (int i = 0; i < childNodes.getLength(); i++) {
            temp = childNodes.item(i);
            if(temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equalsIgnoreCase(CHAPTER)) {
                Pair<String,Scene> tempScene = buildScene((Element) temp);
                scenes.put(tempScene.getKey(), tempScene.getValue());
            }
        }

        return new Pair<>(identification, new Chapter(scenes));
    }

    static public Pair<String,Scene> buildScene(Element sceneNode) {
        String identification = sceneNode.getAttribute(ID);
        String backgroundImg = "EMPTY";
        String backgroundMusic = "EMPTY";
        HashMap<String,Dialog> dialog = new HashMap<>();
        NodeList childNodes = sceneNode.getChildNodes();
        Node temp;

        for (int i = 0; i < childNodes.getLength(); i++) {
            temp = childNodes.item(i);
            if(temp.getNodeType() == Node.ELEMENT_NODE) {
                switch(temp.getNodeName().toLowerCase()) {
                    case BACKGROUND_IMG: backgroundImg = temp.getTextContent();
                    case BACKGROUND_MUSIC: backgroundMusic = temp.getTextContent();
                    case DIALOG: {
                        Pair<String,Dialog> tempDialog = buildDialog((Element) temp);
                        dialog.put(tempDialog.getKey(), tempDialog.getValue());
                    }
                }
            }
        }

        return new Pair<>(identification, new Scene(backgroundImg, backgroundMusic, dialog));
    }

    static public Pair<String,Dialog> buildDialog(Element dialogNode) {
        String identification = dialogNode.getAttribute(ID);
        ArrayList<Text> speaker = new ArrayList<>();
        Reply response = null;
        NodeList childNodes = dialogNode.getChildNodes();
        Node temp;

        for (int i = 0; i < childNodes.getLength(); i++) {
            temp = childNodes.item(i);
            if(temp.getNodeType() == Node.ELEMENT_NODE) {
                switch(temp.getNodeName().toLowerCase()) {
                    case TEXT: speaker.add(buildText((Element) temp));
                    case REPLY: {
                        if(response == null){
                            response = buildReply((Element) temp);
                        }
                    }
                }
            }
        }

        return new Pair<>(identification, new Dialog(speaker, response));
    }

    static public Reply buildReply(Element replyNode) {
        ArrayList<Text> replies = new ArrayList<>();
        NodeList childNodes = replyNode.getChildNodes();
        Node temp;

        for (int i = 0; i < childNodes.getLength(); i++) {
            temp = childNodes.item(i);
            if(temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equalsIgnoreCase(TEXT)) {
                replies.add(buildText((Element)temp));
            }
        }

        return new Reply(replies);
    }

    static public Text buildText(Element textNode) {
        String [] next = textNode.hasAttribute(NEXT) ? textNode.getAttribute(NEXT).split(NEXT_REGEX) : null;
        if(next == null){
            return new Text(textNode.getTextContent());
        }
        else {
            return new Text(textNode.getTextContent(), new Pair<>(next[0], next[1]));
        }
    }
}
