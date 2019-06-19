package petstore.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import petstore.models.petModelPack.PetModel;



public class PetEndpoint {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    //Настройки логгера через папку Serenity properties (возможно)

    private RequestSpecification given(){
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json");
        //.log().uri();
    }

    @Step
    public ValidatableResponse getPetById(int petId){
        log.info("Executing: getPetById");
        return  given()
                .get(Config.GET_PET_BY_ID, petId)
                .then();
        //.log().all();
    }

    public enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    @Step
    public ValidatableResponse getPetByStatus(Status status){
        return  given()
                .param("status", status)
                .get(Config.GET_PET_BY_STATUS)
                .then();
        //.log().all();
    }

    @Step
    public ValidatableResponse createPet(PetModel petModel){
        return  given()
                //.header("accept", "application/json")
                //.header("Content-Type", "application/json")
                .body(petModel)
                .post(Config.CREATE_PET)
                .then();
        //.log().all();
    }

    @Step
    public ValidatableResponse updatePet(PetModel petModel){
        return  given()
                .log().uri()
                .body(petModel)
                .put(Config.UPDATE_PET_BY_ID)
                .then();
        //.log().all();
    }

    @Step
    public ValidatableResponse deletePet(int petId){
        return  given()
                .delete(Config.DELETE_PET_BY_ID, petId)
                .then();
        //.log().all();
    }
}
