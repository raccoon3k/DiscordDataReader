import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {
    
    JSONParser parser = new JSONParser(); 
    JSONObject singeleMessage;
    JSONObject latestMessage;
    JSONObject firstMessage;
    JSONArray jsonArray;
    
    public JSONObject getJsonObject(File fileToRead, int index) {

        try (FileReader reader = new FileReader(fileToRead.getAbsolutePath())) {

            jsonArray = (JSONArray) parser.parse(reader);
            latestMessage = (JSONObject) jsonArray.get(index);

            try {
                    FileWriter fw = new FileWriter("testfile.txt", true);
                    fw.write("ID: " + latestMessage.get("ID") + "\nTimestamp: "+ latestMessage.get("Timestamp") + "\nMessage: " + latestMessage.get("Contents"));
                    fw.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (JSONObject) jsonArray.get(index);    
        }

    public GeneralData getGeneralData(File fileToRead) {// get general data from json file, return as GeneralData object

        GeneralData generalJSONdata = new GeneralData();

            try (FileReader reader = new FileReader(fileToRead.getAbsolutePath())) {

                long StartTime = System.nanoTime();
                jsonArray = (JSONArray) parser.parse(reader);//split messages to one per index

                firstMessage = (JSONObject) jsonArray.get(0);
                latestMessage = (JSONObject) jsonArray.get(jsonArray.size()-1);

                //first/latest message timestamp
                String firstArrayTimestamp = String.valueOf(firstMessage.get("Timestamp"));
                generalJSONdata.setFirstTimestamp(firstArrayTimestamp);
                String latestArrayTimestamp = String.valueOf(latestMessage.get("Timestamp"));
                generalJSONdata.setLatestTimestamp(latestArrayTimestamp);

                generalJSONdata.setTotalMessages(jsonArray.size());

                //wordcounter
                String singleMessageString;
                String[] singleMessageArray;
                int wordcount = 0;

                for (int i=0;i<jsonArray.size();i++) {

                    singeleMessage = (JSONObject) jsonArray.get(i);
                    
                    singleMessageString = String.valueOf(singeleMessage.get("Contents"));
                    singleMessageArray = singleMessageString.split(" ");
                    wordcount += (int)singleMessageArray.length;
                }
                generalJSONdata.settotalWords(wordcount);

                
                long FinishTime = System.nanoTime();
                double TimeElapsed = TimeUnit.NANOSECONDS.toMillis(FinishTime - StartTime);
                generalJSONdata.settimeElapsed(TimeElapsed);

                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(generalJSONdata.getTotalMessages());
                return generalJSONdata;    
            }

    public int getJsonArraylengh(File fileToMeasure) { //
        
            try (FileReader reader = new FileReader(fileToMeasure)) {

                jsonArray = (JSONArray) parser.parse(reader);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonArray.size();
        
    }

    public int getAmountInJson(File fileToSearch, String searchWord) { //search for a word in the json file, return amount of times it was found

        int searchcount = 0;
        try (FileReader reader = new FileReader(fileToSearch)) {

                jsonArray = (JSONArray) parser.parse(reader);

                String singleMessageString;
                ArrayList<String> singleMessageList = new ArrayList<>();

                for (int i=0;i<jsonArray.size();i++) {

                    singeleMessage = (JSONObject) jsonArray.get(i);
                    
                    singleMessageString = String.valueOf(singeleMessage.get("Contents"));
                    for (int j=0; j<singleMessageString.split(" ").length; j++) {
                        singleMessageList.add(singleMessageString.split(" ")[j]);
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchcount;

    }
}