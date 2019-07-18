import javafx.application.Application;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class andrew_main {

    public static void main (String[] args) throws Exception {
        andrew_main app = new andrew_main();
        app.Start();
    }

    private void Start() throws Exception {
        String fileName = "drew-story.txt";
        fileName = "job-interview.txt";
        //System.out.println(ParseFile(ReadFile(fileName)) );
        parser.FileParser pf = new parser.FileParser();
        pf.ParseFile(fileName);
    }

   /* private String ReadFile(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();
        File file = new File(
                getClass().getClassLoader().getResource(fileName).getFile()
        );

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }*/

/*    private List<String> ParseFile(String data) {
        List<String> result = new ArrayList<>();
        StringBuilder current_line = new StringBuilder();
        int char_index = 0;
        Character curr_char;
        Character next_char;
        Character quote_char = 0;
        String mode = "normal";

        data = data.replace(System.lineSeparator(), "\\n");
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
                    if (curr_char.equals(quote_char)){
                        quote_char = 0;
                        mode = "normal";
                    }
                    current_line.append(curr_char);
                    break;
            }

            char_index += 1;
        }

        if (!current_line.toString().isEmpty())
            result.add(current_line.toString());

        return result;
    }*/
}
