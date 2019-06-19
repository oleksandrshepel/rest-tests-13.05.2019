package petstore.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoints.PetEndpoint;
import petstore.models.petModelPack.CategoryModel;
import petstore.models.petModelPack.PetModel;
import petstore.models.petModelPack.TagModel;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value="testPetNamesData.csv")
public class PetNameValidationDataprovTest {

    private String  name;
    private int expectedStatusCode;

    public void setName(String  name){
        this.name = name;
    }

    public void setExpectedStatusCode(int expectedStatusCode){
        this.expectedStatusCode = expectedStatusCode;
    }

    @Qualifier
    public String qualifier() {
        return name + "=>" + expectedStatusCode;
    }

    @Steps
    PetEndpoint petEndpoint;

    @Test
    public void validatePetNamesWithDataprovider(){
        int petId = 87;

        PetModel petModel = new PetModel(
                petId,
                new CategoryModel(),
                name,
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .deletePet(petId);
        petEndpoint
                .createPet(petModel)
                .statusCode(expectedStatusCode);
        petEndpoint
                .deletePet(petId)
                .statusCode(200);
    }

}
