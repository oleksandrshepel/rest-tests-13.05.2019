package weather;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class WeatherTest {
   private static final String BASE_PATH = "https://pinformer.sinoptik.ua/";

    @Test
    public void getWeatherPerCityTest() {
        String cityName = "Lviv";
        RestAssured.baseURI = BASE_PATH;
        //RestAssured.basePath = "search.php"; -- лучше заменить на аналогичный метод в "ValidatableResponse"
        ValidatableResponse responseCityId = given()
                .basePath("search.php")// --можно также заменить данный метод и указать basePath в методе get, кторый ниже
                .param("lang", "ua")
                .param("return_id", 1)
                .param("q", cityName)
                .when()
                //.log().uri()
                .get()// -- сдесь можно передать в пареметре basePath
                .then()
                //.log().all()
                .statusCode(200);
        //Можно решить через split()
        /* String[] responseArr = response.extract().asString().split("\\|");
        String cityId = responseArr[2];*/

        //Можно решить через substring(String.lastIndexOf())
        String responseString = responseCityId.extract().asString();
        String cityId = responseString.substring(responseString.lastIndexOf("|")+1);

        System.out.println(cityId);

        RestAssured.basePath = "pinformer4.php";
        ValidatableResponse responseWeatherId = given()
                .param("type", "js")
                .param("lang", "ua")
                .param("id", cityId)
                .when()
                //.log().uri()
                .get()
                .then()
                .log().all()
                .statusCode(200)
        // Сделаем проверку responseBody
                .body("'{pcity}'", is(cityId)); //-- в данном случае, чтобы сработал json path нужно обернуть в одинарные кавычки {pcity}
                // .body("any {it.key == '{pcity}'}", is(cityId));//-- сдесь мы применили скриптовый язык groovy path
        System.out.println(responseWeatherId);
    }
}
