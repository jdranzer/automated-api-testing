package app;

import entity.Booking;
import entity.BookingDate;
import entity.BookingParent;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Narrative;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTagValuesOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import step.booking.BookerSteps;
import utils.Specifications;
import utils.constants.Endpoint;
import utils.constants.StatusCode;

import java.util.List;

/**
 * @author Edward
 * @since 03/24/2022
 */
@Narrative(text={"In order to take control of reservations at the hotel,",
        "As an owner",
        "I want to search for availability to make reservations."})
@RunWith(SerenityRunner.class)
public class BookingTest extends TestBase {

    @Steps
    BookerSteps steps;
    BookingDate bookingDate;
    Booking booking;

    public BookingTest() {
        bookingDate = new BookingDate() {{
            setCheckIn("2022-04-22");
            setCheckOut("2022-04-28");
        }};

        booking = new Booking() {{
            setFirstname("session");
            setLastname("four");
            setTotalPrice(123);
            setDepositPaid(true);
            setBookingDates(bookingDate);
            setAdditionalNeeds("breakfast");
        }};
    }

    @Test
    @Title("Get all bookings to date.")
    @WithTag("sprint-1")
    public void getAllBookings() {
        RequestSpecification reqSpec = new Specifications().buildSpecification();

        List<Integer> bookings = steps.getObjects(Endpoint.BOOKING.getValue(), reqSpec)
                .statusCode(200)
                .extract()
                .path("bookingid");

        Assert.assertTrue(bookings.size() > 0);
    }

    @Test
    @Title("Create a booking with dummy data.")
    @WithTagValuesOf({"sprint-2", "sprint-3"})
    public void createABooking() {
        BookingParent bookingParent = steps.createAnObject(booking, Endpoint.BOOKING.getValue())
                .assertThat()
                .statusCode(StatusCode.OK.getValue())
                .and()
                .extract()
                .as(BookingParent.class);

        Assert.assertEquals(bookingParent.getBooking().getFirstname(), booking.getFirstname());
    }

    @Test
    @Title("Search a booking by booking id.")
    @WithTag("sprint-4")
    public void searchBookingById() {
        BookingParent bookingParent = steps.createAnObject(booking, Endpoint.BOOKING.getValue())
                .assertThat()
                .statusCode(StatusCode.OK.getValue())
                .and()
                .extract()
                .as(BookingParent.class);

        Booking booking1 = steps.getById(bookingParent.getBookingId(), Endpoint.BOOKING_ID.getValue())
                .assertThat()
                .statusCode(StatusCode.OK.getValue())
                .and()
                .extract()
                .as(Booking.class);

        Assert.assertEquals(bookingParent.getBooking(), booking1);
    }

    @Test
    @Title("Search a booking by First Name")
    public void searchBookingByFirstname(){
        List<String> bookings = steps.getByKey("Rachel", Endpoint.BOOKING.getValue())
                .statusCode(200)
                .extract()
                .path("bookingid");

        Assert.assertTrue(bookings.size()>0);

    }

}
