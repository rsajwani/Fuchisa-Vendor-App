import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHelper {

    public static String createJsonString(ShoeStepsMetaData stepsMetaData) {

        JSONObject obj = new JSONObject();
        obj.put("StepId", stepsMetaData.stepId);
        obj.put("StepName", stepsMetaData.stepName);
        obj.put("BatchId", stepsMetaData.batchId);
        SimpleDateFormat ft =
                new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        obj.put("Date", ft.format(stepsMetaData.date));
        obj.put("ArtisanName", stepsMetaData.artisanName);
        obj.put("Notes", stepsMetaData.notes);

        //JSONArray list = new JSONArray();
        //list.add("msg 1");
        //list.add("msg 2");
        //list.add("msg 3");
        //obj.put("messages", list);

        /*try (FileWriter file = new FileWriter("f:\\test.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("JSON String: " + obj.toString());
        System.out.println("JSON String: " + obj.toJSONString());
        
        return obj.toJSONString();

    }


    public static ShoeStepsMetaData parseJsonString(String jsonString) {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(jsonString);

            JSONObject jsonObject = (JSONObject) obj;

            //System.out.println(jsonObject);

            ShoeStepsMetaData stepsMetaData = new ShoeStepsMetaData();

            String stepId = (String) jsonObject.get("StepId");
            stepsMetaData.stepId = stepId;

            String stepName = (String) jsonObject.get("StepName");
            stepsMetaData.stepName = stepName;

            String batchId = (String) jsonObject.get("BatchId");
            stepsMetaData.batchId= batchId;

            String date = (String) jsonObject.get("Date");
            SimpleDateFormat ft =
                    new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
            try {
                stepsMetaData.date = ft.parse(date);
            } catch (java.text.ParseException ex){
                stepsMetaData.date = null;
            }

            String artisanName = (String) jsonObject.get("ArtisanName");
            stepsMetaData.artisanName = artisanName;

            String notes = (String) jsonObject.get("Notes");
            stepsMetaData.notes = notes;

            return stepsMetaData;

            // loop array
            //JSONArray msg = (JSONArray) jsonObject.get("messages");
            //Iterator<String> iterator = msg.iterator();
            //while (iterator.hasNext()) {
            //    System.out.println(iterator.next());
            //}

        }// catch (FileNotFoundException e) {
         //   e.printStackTrace();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
