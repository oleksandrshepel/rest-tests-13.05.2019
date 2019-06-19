package petstore.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;

import org.junit.runner.RunWith;
import petstore.endpoints.PetEndpoint;
import petstore.models.petModelPack.CategoryModel;
import petstore.models.petModelPack.PetModel;
import petstore.models.petModelPack.TagModel;

import static petstore.endpoints.PetEndpoint.*;

@RunWith(SerenityRunner.class)
public class PetStoreTest {

    @Steps //Эта аннотация дает знать serenity в каком классе находятся steps
    private PetEndpoint petEndpoint;  // = new PetEndpoint(); аннотация @Steps дает возможность не создавть новый объект
    // но при этом мы должны использовать аннотацию @RunWith(SerenityRunner.class). Также
    //это дает возможность свормировать правильный отчет с красиво оформленными степами.

    @Test
    public void getPetByIdTest(){
        int petId = 2;
        petEndpoint
                .getPetById(petId)
                .statusCode(400);
    }

    @Test
    public void getPetByStatus(){

        for(Status status : Status.values()) {
            petEndpoint
                    .getPetByStatus(status)
                    .statusCode(200);
        }
    }


    @Test
    public void createPetTest(){
        PetModel petModel = new PetModel(
                7,
                new CategoryModel(),
                "Tiger",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(200);
    }

    @Test
    public void deleteCreatedPet(){

        int petId =  7;
        petEndpoint
                .deletePet(petId)
                .statusCode(200);

    }

}