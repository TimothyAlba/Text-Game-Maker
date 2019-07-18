package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private String _fileName;
    private String _fileData;

    public void ParseFile(String fileName) throws Exception {
        _fileName = fileName;

        ReadFile();
        ParseFileData();
    }

    private void ReadFile() throws Exception {
        StringBuilder result = new StringBuilder();
        File file = new File(getClass().getClassLoader().getResource(_fileName).getFile());

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
                result.append(System.lineSeparator());
            }
        }

        _fileData = result.toString();
    }

    private void ParseFileData() throws Exception{
        HeaderInfo header;
        List<SceneInfo> scenes;

        header = ParseTitleInfo();
        //ParseLocationInfo();
        //ParseCharacterInfo();
        scenes = ParseSceneInfo();


        System.out.println(header.toString());
        System.out.println(scenes.toString());
    }
    private HeaderInfo ParseTitleInfo(){
        HeaderInfo header = new HeaderInfo();

        for(String line : FileDataToList()){
            ParseLine p = new ParseLine(line);

            //Add check to look for multiple occurrences of values
            switch(p.Action) {
                case "title":
                    header.Title = p.Data;
                    break;
                case "author":
                    header.Author = p.Data;
                    break;
            }
        }

        //if any info found, create header object?
        return header;
    }
    private List<SceneInfo> ParseSceneInfo() throws Exception{
        List<SceneInfo> result = new ArrayList<>();
        SceneInfo scene = new SceneInfo();

        for(String line : FileDataToList()){
            ParseLine p = new ParseLine(line);
            switch (p.Action){
                case "scene":
                    if (scene.IsValid()) {
                        result.add(scene);
                        scene = new SceneInfo();
                    }
                case "script":
                case "start":
                case "end":
                case "option":
                    try {
                        scene.AddLine(p.Action, p.Data);
                    }
                    catch(Exception ex){
                        throw new Exception("Failed to parse scene data", ex);
                    }
                    break;
            }
        }

        return result;
    }

    private List<String> FileDataToList() {
        List<String> result = new ArrayList<>();
        StringBuilder current_line = new StringBuilder();
        int char_index = 0;
        Character curr_char;
        Character next_char;
        Character quote_char = 0;
        String mode = "normal";
        String data;
        boolean isEscaped = false;

        data = _fileData.replace(System.lineSeparator(), "\\n");
        current_line.setLength(0);

        while(char_index < data.length()){
            curr_char = data.charAt(char_index);
            next_char = 0;
            if (char_index + 1 < data.length()){
                next_char = data.charAt(char_index + 1);
            }

            switch(mode) {
                case "normal":
                    if (curr_char.equals('\\')){
                        if (!current_line.toString().isEmpty())
                            result.add(current_line.toString());
                        current_line.setLength(0);
                        char_index += 1;
                    } else if (curr_char.equals('/')){
                        if (next_char.equals('/')){
                            mode = "line_comment";
                        }
                        if (next_char.equals('*')){
                            mode = "comment_block";
                        }
                    } else if (curr_char.equals('\'') || curr_char.equals('"')){
                        mode = "quote";
                        quote_char = curr_char;
                        current_line.append(curr_char);
                    }
                    else {
                        current_line.append(curr_char);
                    }
                    break;

                case "line_comment":
                    if (curr_char.equals('\\') && next_char.equals('n')){
                        mode = "normal";
                        char_index += 1;
                    }
                    break;

                case "comment_block":
                    if (curr_char.equals('*') && next_char.equals('/')){
                        mode = "normal";
                        char_index += 1;
                    }
                    break;

                case "quote":
                    if (!isEscaped && curr_char.equals(quote_char)){
                        quote_char = 0;
                        mode = "normal";
                    }
                    if (curr_char.equals('\\')) {
                        isEscaped = true;
                    }
                    else {
                        isEscaped = false;
                    }

                    current_line.append(curr_char);
                    break;
            }

            char_index += 1;
        }

        if (!current_line.toString().isEmpty())
            result.add(current_line.toString());

        return result;
    }
}
