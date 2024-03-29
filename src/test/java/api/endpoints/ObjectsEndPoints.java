package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payload.Device;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*Created to perform Create , Read , Update and Delete requests.*/
public class ObjectsEndPoints {
    public static Response createObject(Device payload){
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routes.post_url);

        return response;
    }

    public static Response readObject(String Id){
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",Id)
        .when()
                .get(Routes.get_url);

        return response;
    }

    public static Response updateObject(String Id,Device payload){
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",Id)
                .body(payload)
                .log().body()
         .when()
                .patch(Routes.patch_url);

        return response;
    }

    public static Response deleteObject(String Id){
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",Id)
                .when()
                .delete(Routes.delete_url);

        return response;
    }

}
