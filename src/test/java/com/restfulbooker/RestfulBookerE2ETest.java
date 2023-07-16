package com.restfulbooker;

import data.BookingData;
import data.TokenCreds;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static data.BookingDataBuilder.getBookingData;
import static data.TokenBuilder.getToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RestfulBookerE2ETest  extends BaseSetup{
    private BookingData newBooking;
    private BookingData updatedBooking;
    private TokenCreds  tokenCreds;


    private int  bookingId;

    @BeforeTest
    public void testSetup(){
        newBooking = getBookingData ();
        updatedBooking=getBookingData();
        tokenCreds=getToken();
    }

    @Test
    public void createBookingTest(){
       bookingId= given().body(newBooking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("bookingid",notNullValue())
                .body ("booking.firstname", equalTo (newBooking.getFirstname ()), "booking.lastname",
                        equalTo (newBooking.getLastname ()), "booking.totalprice", equalTo (newBooking.getTotalprice ()),
                        "booking.depositpaid", equalTo (newBooking.isDepositpaid ()), "booking.bookingdates.checkin", equalTo (
                                newBooking.getBookingdates ()
                                        .getCheckin ()), "booking.bookingdates.checkout", equalTo (newBooking.getBookingdates ()
                                .getCheckout ()), "booking.additionalneeds", equalTo (newBooking.getAdditionalneeds ()))
                .extract ()
                .path ("bookingid");
    }
    @Test
    public void getBookingTest () {
        given ().get ("/booking/" + bookingId)
                .then ()
                .statusCode (200)
                .and ()
                .assertThat ()
                .body ("firstname", equalTo (newBooking.getFirstname ()), "lastname", equalTo (newBooking.getLastname ()),
                        "totalprice", equalTo (newBooking.getTotalprice ()), "depositpaid",
                        equalTo (newBooking.isDepositpaid ()), "bookingdates.checkin", equalTo (newBooking.getBookingdates ()
                                .getCheckin ()), "bookingdates.checkout", equalTo (newBooking.getBookingdates ()
                                .getCheckout ()), "additionalneeds", equalTo (newBooking.getAdditionalneeds ()));

    }
    @Test
    public void updateBookingTest () {
        given ().body (updatedBooking)
                .when ()
                .header ("Cookie", "token=" + generateToken())
                .put ("/booking/" + bookingId)
                .then ()
                .statusCode (200)
                .and ()
                .assertThat ()
                .body ("firstname", equalTo (updatedBooking.getFirstname ()), "lastname",
                        equalTo (updatedBooking.getLastname ()), "totalprice", equalTo (updatedBooking.getTotalprice ()),
                        "depositpaid", equalTo (updatedBooking.isDepositpaid ()), "bookingdates.checkin", equalTo (
                                updatedBooking.getBookingdates ()
                                        .getCheckin ()), "bookingdates.checkout", equalTo (updatedBooking.getBookingdates ()
                                .getCheckout ()), "additionalneeds", equalTo (updatedBooking.getAdditionalneeds ()));
    }
    @Test
    public void deleteBookingTest(){
        given()
                .header("Cookie", "token=" + generateToken())
                .delete("/booking/"+bookingId)
                .then()
                .statusCode(201);
    }
    @Test
    public void checkBookingIsDeleted () {
        given ().get ("/booking/" + bookingId)
                .then ()
                .statusCode (404);
    }
    private String generateToken () {
        return given ().body (tokenCreds)
                .when ()
                .post ("/auth")
                .then ()
                .assertThat ()
                .body ("token", notNullValue())
                .extract ()
                .path ("token");
    }

}
