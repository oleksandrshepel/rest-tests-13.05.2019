package petstore.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import petstore.models.petModelPack.PetModel;
import petstore.models.storeModelPack.StoreModel;

import static io.restassured.RestAssured.given;

public class StoreEndpoint {
    private RequestSpecification given(){
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json")
                .log().uri();
    }

    @Step
    public ValidatableResponse placeOrder(StoreModel storeModel){
        return  given()
                .body(storeModel)
                .post(Config.STORE_PLACE_ORDER)
                .then()
                .log().all();
    }

    @Step
    public ValidatableResponse deleteOrder(int orderId){
        return  given()
                .delete(Config.DELETE_ORDER_BY_ID, orderId)
                .then()
                .log().all();
    }

    @Step
    public ValidatableResponse findOrder(int orderId){
        return  given()
                .get(Config.FIND_ORDER_BY_ID, orderId)
                .then()
                .log().all();
    }

}
