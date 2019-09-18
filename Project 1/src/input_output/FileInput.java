package input_output;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileInput {
    private BufferedReader mBufferedReader;

    public FileInput() {

    }

    public String getFileContents() {
        return "prog     main       {    print       ( \"ASCII:\"   , \" A= \" , 65    , \" Z= \"    ,    9.5   )   ;     }";
        //main { print( \"ASCII:\", \" A= \", 65, \" Z= \", 90 ); }";
    }

    public String readFileToString() {
        StringBuilder sb = new StringBuilder();
        try{
            mBufferedReader = new BufferedReader(new FileReader("testfile.txt"));
            String line;
            while ((line = mBufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            mBufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public Map<String, Integer> readFileToMap(String filepath) {
        Map<String, Integer> mMap = new HashMap<String, Integer>();
        try{
            mBufferedReader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = mBufferedReader.readLine()) != null) {
                String[] values = line.split(" ");
                mMap.put(values[2], Integer.valueOf(values[0]));
            }
            mBufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mMap;
    }

    public Map<Integer, String> getIdToStringMap(){
        Map<Integer, String> idToStringMap = new HashMap();
        try{
            mBufferedReader = new BufferedReader(new FileReader("lexermapping.txt"));
            String line;
            while ((line = mBufferedReader.readLine()) != null) {
                String[] values = line.split(" ");
                idToStringMap.put(Integer.valueOf(values[0]), values[2]);
            }
            mBufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idToStringMap;

    }
}
