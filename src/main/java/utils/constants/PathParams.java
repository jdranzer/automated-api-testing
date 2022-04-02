package utils.constants;

/**
 * @author Edward
 * @since 03/24/2022
 */
public enum PathParams implements Constant<String> {
    BOOKING_ID("booking_id"),
    FIRST_NAME("firstname");

    private String value;

    PathParams(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
