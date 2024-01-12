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

import static StepDefs.allVariables.*;

public class DeviceTests {
    Device devicePayload;
    Data dataPayload;
    String deviceId;

    @Given("setup the payload")
    public void setupDevicePayLoad() {
        devicePayload = new Device();
        dataPayload = new Data();
        // reading the object details from variables and create payload
        devicePayload.setName(deviceName);
        dataPayload.setYear(year);
        dataPayload.setPrice(price);
        dataPayload.setCPU_model(cpuModel);
        dataPayload.setHard_disk_size(HardDiscSize);

        devicePayload.setData(dataPayload);
    }

    @Given("setup the payload using data table")
    public void setupDevicePayLoadUsingDataTable(DataTable dataTable) {
        devicePayload = new Device();
        dataPayload = new Data();
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

        }
    }

    @Given("Hit POST Url to create device")
    public void testCreateObject()
    {
        Response response= ObjectsEndPoints.createObject(devicePayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath jsonPathEvaluator = response.jsonPath();
        jsonPathEvaluator.get().equals(devicePayload);
        // Reading the object id for API chaining process or to update object using PATCH.
        deviceId = jsonPathEvaluator.get("id");

    }

    @And("read Created Object using GET")
   public void testGetObject()
    {
        Response response= ObjectsEndPoints.readObject(String.valueOf(deviceId));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @And("Hit PATCH URL to update device details")
    public void testPatchObject()
    {

        dataPayload.setCPU_model(updatedCpuModel);
        devicePayload.setData(dataPayload);
        System.out.println(devicePayload.toString());
        Response response= ObjectsEndPoints.updateObject(deviceId,devicePayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.body().asString().contains(updatedCpuModel),true);
        JsonPath jsonPathEvaluator = response.jsonPath();
        jsonPathEvaluator.get().equals(devicePayload);

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
            Assert.assertEquals(response.getStatusCode(), 200);
            JsonPath jsonPathEvaluator = response.jsonPath();
            jsonPathEvaluator.get().equals(devicePayload);

        }
    }

    @And("Hit PATCH URL to update device Name")
    public void updateDeviceName(DataTable dataTable) {
        List<Map<String, String>> deviceDetails = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> device : deviceDetails) {
            String name = device.get("Name");
            devicePayload.setName(name);
            Response response = ObjectsEndPoints.updateObject(deviceId, devicePayload);
            response.then().log().all();
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.getBody().asString().contains(name),true);

        }
    }

    @And("Hit PATCH URL to update device year")
    public void updateDeviceYear(DataTable dataTable) {
        List<Map<String, String>> deviceDetails = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> device : deviceDetails) {
            int year = Integer.parseInt(device.get("Year"));
            dataPayload.setYear(year);
            devicePayload.setData(dataPayload);
            Response response = ObjectsEndPoints.updateObject(deviceId, devicePayload);
            response.then().log().all();
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.getBody().asString().contains(Integer.toString(year)),true);

        }
    }

    @And("Hit PATCH URL to update device price")
    public void updateDevicePrice(DataTable dataTable) {
        List<Map<String, String>> deviceDetails = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> device : deviceDetails) {
            double price = Double.parseDouble(device.get("Price"));
            dataPayload.setPrice(price);
            devicePayload.setData(dataPayload);
            Response response = ObjectsEndPoints.updateObject(deviceId, devicePayload);
            response.then().log().all();
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.getBody().asString().contains(Double.toString(price)),true);

        }
    }

    @Then("Hit PATCH URL to update device cpu model")
    @Then("Hit PATCH URL to update device hard disk size")
    @Then("Hit PATCH URL to update device details from table")
    public void updateDeviceCPUModel(DataTable dataTable) {
        try {
            List<Map<String, String>> deviceDetails = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> device : deviceDetails) {

                String name = null;
                if (device.containsKey("Name")) {
                    name = device.get("Name");
                    devicePayload.setName(name);
                }
                int year = 0;
                if (device.containsKey("Year")) {
                    year = Integer.parseInt(device.get("Year"));
                    dataPayload.setYear(year);
                }
                double price = 0;
                if (device.containsKey("Price")) {
                    price = Double.parseDouble(device.get("Price"));
                    dataPayload.setPrice(price);
                }
                String cpuModel = null;
                if (device.containsKey("CPU Model")) {
                    cpuModel = device.get("CPU Model");
                    dataPayload.setCPU_model(cpuModel);
                }
                String hardDiskSize = null;
                if (device.containsKey("Hard Disk Size")) {
                    hardDiskSize = device.get("Hard Disk Size");
                    dataPayload.setHard_disk_size(hardDiskSize);
                }
                devicePayload.setData(dataPayload);
                Response response = ObjectsEndPoints.updateObject(deviceId, devicePayload);
                response.then().log().all();
                Assert.assertEquals(response.getStatusCode(), 200);

                if (device.containsKey("Name")) {
                    Assert.assertEquals(response.getBody().asString().contains(name), true);
                }
                if (device.containsKey("Year")) {
                    Assert.assertEquals(response.getBody().asString().contains(Integer.toString(year)), true);
                }
                if (device.containsKey("Price")) {
                    Assert.assertEquals(response.getBody().asString().contains(Double.toString(price)), true);
                }
                if (device.containsKey("CPU Model")) {
                    Assert.assertEquals(response.getBody().asString().contains(cpuModel), true);
                }
                if (device.containsKey("Hard Disk Size")) {
                    Assert.assertEquals(response.getBody().asString().contains(hardDiskSize), true);
                }

            }
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
