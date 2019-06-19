package petstore.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import petstore.endpoints.PetEndpoint;
import petstore.endpoints.StoreEndpoint;
import petstore.models.petModelPack.CategoryModel;
import petstore.models.petModelPack.PetModel;
import petstore.models.petModelPack.TagModel;
import petstore.models.storeModelPack.StoreModel;

import static org.hamcrest.Matchers.is;

public class PetFindPurchaseOrderTest {
    private PetEndpoint petEndpoint = new PetEndpoint();
    private StoreEndpoint storeEndpoint = new StoreEndpoint();
    private PetModel petModel;
    private StoreModel storeModel;

    @Before
    public void preCondition(){
        petModel = new PetModel(
                7,
                new CategoryModel(),
                "tiger",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(200);

        storeModel = new StoreModel(
                7,
                7,
                1,
                "placed",
                "false");

        storeEndpoint
                .placeOrder(storeModel)
                .statusCode(200);
    }

    @After
    public void postCondition(){
        storeEndpoint
                .deleteOrder(storeModel.getId())
                .statusCode(200);

        petEndpoint
                .deletePet(petModel.getId())
                .statusCode(200);
    }

    @Test
    public void findOrderByIdTest(){
        storeEndpoint
                .findOrder(storeModel.getId())
                .statusCode(200)
                .body("petId", is(storeModel.getPetId()));

        ;
    }




}
