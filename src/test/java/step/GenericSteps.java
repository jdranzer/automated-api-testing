package step;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

/**
 * @author Edward
 * @since 03/24/2022
 */
public class GenericSteps {
    /**
     * Generic method to make a Get request
     * @param path
     * @param reqSpec
     * @return ValidatableResponse
     */
    protected ValidatableResponse get(String path, RequestSpecification reqSpec){
        return SerenityRest
                .rest()
                .given()
                    .spec(reqSpec)
                .when()
                    .get(path)
                .then();
    }

    /**
     * Generic method to make a post request
     * @param path
     * @param reqSpec
     * @return
     */
    protected ValidatableResponse post(String path, RequestSpecification reqSpec){
        return SerenityRest
                .rest()
                .given().log().all()
                .spec(reqSpec)
                .when()
                .post(path)
                .then().log().all();
    }

    /**
     * Generic method to make a delete request
     * @param path
     * @param reqSpec
     * @return
     */
    protected ValidatableResponse delete(String path, RequestSpecification reqSpec){
        return SerenityRest
                .rest()
                .given().log().all()
                .spec(reqSpec)
                .when()
                .delete(path)
                .then().log().all();
    }

    /**
     * Generic method to make a patch request
     * @param path
     * @param reqSpec
     * @return
     */
    protected ValidatableResponse patch(String path, RequestSpecification reqSpec){
        return SerenityRest
                .given()
                .spec(reqSpec)
                .when()
                .patch(path)
                .then().log().all();
    }
}
