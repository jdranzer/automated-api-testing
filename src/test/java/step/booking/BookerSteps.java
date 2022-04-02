package step.booking;

import entity.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import step.GenericSteps;
import utils.Specifications;
import utils.constants.Endpoint;
import utils.constants.PathParams;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Edward
 * @since 03/24/2022
 */
public class BookerSteps extends GenericSteps {

    /**
     * @param path
     * @param resSpec
     * @return ValidatableResponse
     */
    @Step("Get all Objects of the path {0}.")
    public ValidatableResponse getObjects(String path, RequestSpecification resSpec) {
        return get(path, resSpec);
    }

    /**
     * @param id
     * @return ValidatableResponse
     */
    @Step("Get the object with id {0} and path {1}.")
    public ValidatableResponse getById(int id, String path) {
        Map<String, ?> pathParams = new HashMap<String, Integer>() {{
            put(PathParams.BOOKING_ID.getValue(), id);
        }};

        RequestSpecification reqSpec = new Specifications() {{
            setContentType(ContentType.JSON);
            setPathParams(pathParams);
        }}.buildSpecification();
        return get(path, reqSpec);
    }

    @Step("Create a booking with values {0} and path {1}")
    public ValidatableResponse createAnObject(Booking booking, String path) {
        RequestSpecification reqSpec = new Specifications() {{
            setContentType(ContentType.JSON);
            setBody(booking);
        }}.buildSpecification();

        return post(path, reqSpec);
    }

    @Step("Get a booking by key {0} using the path {1}")
    public ValidatableResponse getByKey(String key, String path){
        Map<String, ?> queryParams = new HashMap<String, String>(){{
            put(PathParams.FIRST_NAME.getValue(), key);
        }};
        RequestSpecification requestSpecification = new Specifications(){{
            setContentType(ContentType.JSON);
            setQueryParams(queryParams);
        }}.buildSpecification();

        return get(path, requestSpecification);
    }
}
