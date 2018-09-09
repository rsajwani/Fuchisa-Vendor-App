import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args){

        boolean bb = isValid("())(a)") ;
        System.out.println(bb);

        AWSInteraction awsInteraction = new AWSInteraction();
        List<String> buckets = awsInteraction.listBuckets();
        String BUCKET_NAME = "fuchsia-pakistan-sangla-balletflats";
        String BATCH_FOLDER_NAME = "Batch/";
        System.out.println("Listing existing buckets");

        List<String> steps = awsInteraction.downloadJsonDataListV2(BUCKET_NAME, BATCH_FOLDER_NAME, "2148");
        List<String> results = new ArrayList<String>();
        int i =0;
        for(String tmp : steps){
            System.out.println("Step #" + i);
            System.out.println(tmp);
            ShoeStepsMetaData metaData = ShoeStepsMetaData.parseJsonString(tmp);
            String artisanString = awsInteraction.downloadJsonData(BUCKET_NAME, "MasterData/Artisans",  metaData.artisanName);
            System.out.println(artisanString);
            Artisan artisan = Artisan.parseJsonString(artisanString);
            String shoeStepString = awsInteraction.downloadJsonData(BUCKET_NAME, "MasterData/Production_Steps",  metaData.stepName);
            System.out.println(shoeStepString);
            ShoeStep step = ShoeStep.parseJsonString(shoeStepString);
            ProductionStep productionStep = getProductionStep(step, metaData, artisan);
            System.out.println(productionStep.toJsonString());
            results.add(productionStep.toJsonString());
            System.out.println("--------------------------------------------------------------------------------------------------------");
            i++;
        }


        /*for (String s : buckets) {
            System.out.println("bucketName: " + s);
        }

        //addArtisanProfile(bucketName);
        addShoeSteps(bucketName);
        */

        /*System.out.println("Uploading json");
        ShoeStepsMetaData metaData = new ShoeStepsMetaData();
        metaData.stepId = "1";
        metaData.stepName = "Place Order";
        metaData.batchId = "Batch/2710-2720";

        SimpleDateFormat ft =
                new SimpleDateFormat ("MM.dd.yyyy");
        try {
            metaData.date = ft.parse("04.04.2018");
        } catch (java.text.ParseException ex){
            metaData.date = null;
        }

        metaData.date = new Date();
        metaData.artisanName = "Rafiq Bajwa";
        metaData.notes = "this is test";

        // add one step
        awsInteraction.uploadJsonData(bucketName, "Batch/2710-2720", "Place Order", JsonHelper.createJsonString(metaData) );
        String jsonString = awsInteraction.downloadJsonData(bucketName, "Batch/2710-2720", "Place Order");
        System.out.println(jsonString);*/
        //ShoeStepsMetaData metaData1 = ShoeStepsMetaData.parseJsonString("{\"StepId\":\"1\",\"BatchId\":\"Batch\\/2710-2720\",\"StepName\":\"Place Order\",\"ArtisanName\":\"Rafiq Bajwa\",\"Date\":\"Wed 2018.05.23 at 08:50:21 PM PDT\",\"Notes\":\"this is test\"}");


        /*
        metaData = new ShoeStepsMetaData();
        metaData.stepName = "Raw Material Acquired";
        metaData.batchId = "1234";
        metaData.date = new Date();
        metaData.artisanName = "Shahid";
        metaData.notes = "this is test";
        awsInteraction.uploadJsonData(bucketName, "1234", "Raw Material Acquired", JsonHelper.createJsonString(metaData) );
        jsonString = awsInteraction.downloadJsonData(bucketName, "1234", "Raw Material Acquired");
        System.out.println(jsonString);
        metaData1 = JsonHelper.parseJsonString(jsonString);*/

        // find the batch which will hold our order
        //List<String> keyRange = awsInteraction.downloadJsonDataKeysV2(bucketName, "Batch/", "2714");
        /*
        int lookoutKey = Integer.parseInt("2704");
        System.out.println("list all the data in batch: 1234");
        String chosenKey = "";
        for(String key : keyRange){
            String[] splittedKey = key.split("/");
            //if (splittedKey.length != 3) return null;
            String tmpKey = splittedKey[1];
            String[] tmp = tmpKey.split("-");
            //if (tmp.length != 2) return null;
            int start = Integer.parseInt(tmp[0]);
            int end = Integer.parseInt(tmp[1]);
            if (lookoutKey >= start && lookoutKey <= end){
                chosenKey = key;
                break;
            }
        }*/

        // list data
        /*String key = "Batch/" + chosenKey;
        List<String> data = awsInteraction.downloadJsonDataList(bucketName, key);
        System.out.println("list all the data in batch: 1234");
        int i =0;
        for(String tmp : data){
            System.out.println("Step #" + i);
            System.out.println(tmp);
            ShoeStepsMetaData metaData = ShoeStepsMetaData.parseJsonString(tmp);
            String artisanString = awsInteraction.downloadJsonData(bucketName, "MasterData/Artisans",  metaData.artisanName);
            System.out.println(artisanString);
            Artisan artisan = Artisan.parseJsonString(artisanString);
            String shoeStepString = awsInteraction.downloadJsonData(bucketName, "MasterData/Production_Steps",  metaData.stepName);
            System.out.println(shoeStepString);
            ShoeStep step = ShoeStep.parseJsonString(shoeStepString);
            ProductionStep productionStep = getProductionStep(step, metaData, artisan);
            System.out.println(productionStep.toJsonString());
            System.out.println("--------------------------------------------------------------------------------------------------------");
            i++;
        }*/

        String ss = getShoeStepIds();
        System.out.println(ss);

        /*List<String> data = awsInteraction.downloadJsonDataListV2(bucketName, "Batch/", "2734");
        System.out.println("list all the data in batch: 1234");
        int i =0;
        for(String tmp : data){
            System.out.println("Step #" + i);
            System.out.println(tmp);
            ShoeStepsMetaData metaData2 = ShoeStepsMetaData.parseJsonString(tmp);
            String artisanString = awsInteraction.downloadJsonData(bucketName, "MasterData/Artisans",  metaData2.artisanName);
            System.out.println(artisanString);
            Artisan artisan = Artisan.parseJsonString(artisanString);
            String shoeStepString = awsInteraction.downloadJsonData(bucketName, "MasterData/Production_Steps",  metaData2.stepName);
            System.out.println(shoeStepString);
            ShoeStep step = ShoeStep.parseJsonString(shoeStepString);
            ProductionStep productionStep = getProductionStep(step, metaData2, artisan);
            System.out.println(productionStep.toJsonString());
            System.out.println("--------------------------------------------------------------------------------------------------------");
            i++;
        }*/


        /*List<String> data = awsInteraction.downloadJsonDataListV2(bucketName, "Batch/", "2704");
        System.out.println("list all the data in batch: 1234");
        int i =0;
        List<Integer> steps = new ArrayList<>();
        for(String tmp : data){
            System.out.println("Step #" + i);
            System.out.println(tmp);
            ShoeStepsMetaData metaData2 = ShoeStepsMetaData.parseJsonString(tmp);
            steps.add(Integer.parseInt(metaData2.stepId));
            System.out.println("--------------------------------------------------------------------------------------------------------");
            i++;
        }
        Collections.sort(steps);*/
    }

    public static String getShoeStepIds() {

        AWSInteraction awsInteraction = new AWSInteraction();
        List<String> data = awsInteraction.downloadJsonDataListV2("fuchsia-pakistan-sangla-balletflats", "Batch/", "2734");
        //System.out.println("list all the data in batch: " + orderId);
        int i =0;
        HashSet<String> stepsIds = new HashSet<>();
        for(String tmp : data){
            System.out.println("Step #" + i);
            System.out.println(tmp);
            ShoeStepsMetaData metaData2 = ShoeStepsMetaData.parseJsonString(tmp);
            stepsIds.add(metaData2.stepId);
            i++;
        }

        List<ProductionStep> steps = new ArrayList<ProductionStep>();
        String allShoeSteps = awsInteraction.downloadJsonData("fuchsia-pakistan-sangla-balletflats", "MasterData/Production_Steps", "Production_Steps");
        System.out.println(allShoeSteps);
        List<ShoeStepWithStatus> list = ShoeStepWithStatus.parseJsonList(allShoeSteps);
        Collections.sort(list, statusComparator);
        for(ShoeStepWithStatus s: list){
            if (stepsIds.contains(s.stepId))
                s.status = "1";
            else
                s.status = "0";
        }


        return ShoeStepWithStatus.toJsonList(list);
    }

    private static Comparator<ShoeStepWithStatus> statusComparator = new Comparator<ShoeStepWithStatus>() {
        @Override
        public int compare(ShoeStepWithStatus o1, ShoeStepWithStatus o2) {
            return Integer.parseInt(o1.stepId) - Integer.parseInt(o2.stepId);
        }
    };
    // helper function checks if string s contains valid parantheses
    public static boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) {
                System.out.println("inside --");
                return false;
            }
            System.out.println(count);

        }

        return count == 0;
    }

    public static ProductionStep getProductionStep(ShoeStep shoeStep, ShoeStepsMetaData shoeStepsMetaData, Artisan artisan) {

        ProductionStep productionStep = new ProductionStep();
        if (shoeStepsMetaData != null) {
            productionStep.stepId = shoeStepsMetaData.stepId;
            productionStep.batchId = shoeStepsMetaData.batchId;
            productionStep.date = shoeStepsMetaData.date;
            productionStep.notes = shoeStepsMetaData.notes;
            productionStep.estimatedDeliveryDate = shoeStepsMetaData.estimatedDeliveryDate;
        }
        if (shoeStep != null) {
            productionStep.stepName = shoeStep.name;
            productionStep.stepNotes = shoeStep.notes;
            productionStep.stepImgUrl = shoeStep.imgUrl;
            productionStep.rawMaterialImgUrl = shoeStep.rawMaterialImgUrl;
            productionStep.locationImageUrl = shoeStep.locationImageUrl;
        }
        if (artisan != null){
            productionStep.artisanName = artisan.name;
            productionStep.artisanImgUrl = artisan.imgUrl;
            productionStep.artisanNotes = artisan.notes;
        }

        return productionStep;

    }

    public static void addArtisanProfile (String bucketName){
        AWSInteraction awsInteraction = new AWSInteraction();
        System.out.println("Uploading artisan profile");
        Artisan artisan = new Artisan();
        artisan.name = "Waqas";
        artisan.imgUrl = "https://s3-us-west-2.amazonaws.com/fuchsia-pakistan-sangla-balletflats/MasterData/Artisans/Waqas.jpg";
        artisan.notes = "Waqas brings 25 years of experience to Fuchsia. He is primarily responsible for cutting the uppers and lower sole of the ballet flats.";

        // add one step
        //awsInteraction.uploadJsonData(bucketName, "MasterData/Artisans", "Waqas", artisan.toJsonString() );
        String jsonString = awsInteraction.downloadJsonData(bucketName, "MasterData/Artisans", "Waqas");
        System.out.println(jsonString);
    }

    public static void addShoeSteps (String bucketName){
        AWSInteraction awsInteraction = new AWSInteraction();
        System.out.println("Order Placed");
        ShoeStep step = new ShoeStep();
        step.name = "Order Placed";
        step.imgUrl = "https://s3-us-west-2.amazonaws.com/fuchsia-pakistan-sangla-balletflats/MasterData/Prodution_Steps/Order_Placed.jpg";
        step.notes = "Our artisans usually work on a batch of 40 to 60 pairs in one cycle and it takes them around a week (sometimes 2 weeks) to complete a cycle. The usually follow assembly line for their manufacturing. Some steps take from hours to days depending on weather and climate.";
        step.artisanName = "Fuchsia Shoes";
        // add one step
        awsInteraction.uploadJsonData(bucketName, "MasterData/Production_Steps", "Order_Placed", step.toJsonString() );
        String jsonString = awsInteraction.downloadJsonData(bucketName, "MasterData/Production_Steps", "Order_Placed");
        System.out.println(jsonString);
    }
}
