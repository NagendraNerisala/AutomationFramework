package api.test;

import api.endpoints.ObjectsEndPoints;
import api.payload.Data;
import api.payload.Device;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

public class DeviceTests {
    private Device devicePayload;
    private Data dataPayload;
    private String deviceId;

    @Given("setup device payload")
    public void setupDevicePayload(DataTable dataTable) {
        devicePayload = new Device();
        dataPayload = new Data();
        List<Map<String, String>> deviceDetails = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> device : deviceDetails) {
            String name = device.get("Name");
            int year = Integer.parseInt(device.get("Year"));
            double price = Double.parseDouble(device.get("Price"));
            String cpuModel = device.get("CPU Model");
            String hardDiskSize = device.get("Hard Disk Size");

            /*Construct Device Payload*/
            devicePayload.setName(name);
            dataPayload.setYear(year);
            dataPayload.setPrice(price);
            dataPayload.setCPU_model(cpuModel);
            dataPayload.setHard_disk_size(hardDiskSize);
            devicePayload.setData(dataPayload);

        }
    }

    @Given("Hit POST Url to create device")
    public void testCreateObject()
    {
        Response response= ObjectsEndPoints.createObject(devicePayload);
        response.then().log().all();
        /*Assert success response code*/
        Assert.assertEquals(response.getStatusCode(),200);

        /*validate response body with input devicePayload*/
        JsonPath jsonPathEvaluator = response.jsonPath();
        jsonPathEvaluator.get().equals(devicePayload);
        // Read the object id for API chaining process or to update object using PATCH.
        deviceId = jsonPathEvaluator.get("id");

    }

    @And("Hit GET Url to read device")
   public void testGetObject()
    {
        Response response= ObjectsEndPoints.readObject(String.valueOf(deviceId));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @And("Hit PATCH URL to update device details using data table")
    public void updateCompleteDeviceDetails(DataTable dataTable) {
        List<Map<String, String>> deviceDetails = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> device : deviceDetails) {
            String name = device.get("Name");
            int year = Integer.parseInt(device.get("Year"));
            double price = Double.parseDouble(device.get("Price"));
            String cpuModel = device.get("CPU Model");
            String hardDiskSize = device.get("Hard Disk Size");

            devicePayload.setName(name);
            dataPayload.setYear(year);
            dataPayload.setPrice(price);
            dataPayload.setCPU_model(cpuModel);
            dataPayload.setHard_disk_size(hardDiskSize);
            devicePayload.setData(dataPayload);

            Response response = ObjectsEndPoints.updateObject(deviceId, devicePayload);
            response.then().log().all();
            /*Assert success response code*/
            Assert.assertEquals(response.getStatusCode(), 200);
            /*validate output response body with input devicePayload*/
            JsonPath jsonPathEvaluator = response.jsonPath();
            jsonPathEvaluator.get().equals(devicePayload);

        }
    }

    @Then("Hit PATCH URL to update device modified name")
    @Then("Hit PATCH URL to update device data modified year")
    @Then("Hit PATCH URL to update device data modified price")
    @Then("Hit PATCH URL to update device data modified cpu model")
    @Then("Hit PATCH URL to update device data modified hard disk size")
    @Then("Hit PATCH URL to update device modified details")
    public void updateDeviceDetails(DataTable dataTable) {
        try {
            Device modifiedDevicePayload = new Device();
            Data modifiedDataPayload = new Data();

            List<Map<String, String>> deviceDetails = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> device : deviceDetails) {

                /*using devicePayload, dataPayload from POST request to update with modified payload
                 and to validate with output PATCH response body*/

                String name = null;
                if (device.containsKey("Name")) {
                    name = device.get("Name");
                    modifiedDevicePayload.setName(name);
                    devicePayload.setName(name);
                }
                int year = 0;
                if (device.containsKey("Year")) {
                    year = Integer.parseInt(device.get("Year"));
                    modifiedDataPayload.setYear(year);
                    dataPayload.setYear(year);
                }
                double price = 0;
                if (device.containsKey("Price")) {
                    price = Double.parseDouble(device.get("Price"));
                    modifiedDataPayload.setPrice(price);
                    dataPayload.setPrice(price);
                }
                String cpuModel = null;
                if (device.containsKey("CPU Model")) {
                    cpuModel = device.get("CPU Model");
                    modifiedDataPayload.setCPU_model(cpuModel);
                    dataPayload.setCPU_model(cpuModel);
                }
                String hardDiskSize = null;
                if (device.containsKey("Hard Disk Size")) {
                    hardDiskSize = device.get("Hard Disk Size");
                    modifiedDataPayload.setHard_disk_size(hardDiskSize);
                    dataPayload.setHard_disk_size(hardDiskSize);
                }
                modifiedDevicePayload.setData(modifiedDataPayload);
                devicePayload.setData(dataPayload);
                Response response = ObjectsEndPoints.updateObject(deviceId, modifiedDevicePayload);
                response.then().log().all();

                /*validate success response code*/
                Assert.assertEquals(response.getStatusCode(), 200);

                /*validate response body with modified payload*/
                JsonPath jsonPathEvaluator = response.jsonPath();
                jsonPathEvaluator.get().equals(devicePayload);

                /*validate response body with modified attributes*/
                if (device.containsKey("Name")) {
                    Assert.assertTrue(response.getBody().asString().contains(name));
                }
                if (device.containsKey("Year")) {
                    Assert.assertTrue(response.getBody().asString().contains(Integer.toString(year)));
                }
                if (device.containsKey("Price")) {
                    Assert.assertTrue(response.getBody().asString().contains(Double.toString(price)));
                }
                if (device.containsKey("CPU Model")) {
                    Assert.assertTrue(response.getBody().asString().contains(cpuModel));
                }
                if (device.containsKey("Hard Disk Size")) {
                    Assert.assertTrue(response.getBody().asString().contains(hardDiskSize));
                }
            }
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Given("Hit DELETE Url to delete device")
    public void testDeleteObject()
    {
        Response response= ObjectsEndPoints.deleteObject(deviceId);
        response.then().log().all();
        /*validate success response code*/
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
