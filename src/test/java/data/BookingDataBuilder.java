package data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class BookingDataBuilder {
    private static final Faker facker= new Faker();
    public static BookingData getBookingData(){
        SimpleDateFormat formatter= new SimpleDateFormat("YYYY-MM-dd");
        return BookingData.builder().firstname(facker.name().firstName())
                .lastname(facker.name().lastName())
                .totalprice(facker.number().numberBetween(1,1000))
                .depositpaid(true)
                .bookingdates(BookingDates.builder().checkin(formatter.format(facker.date().past(20, TimeUnit.DAYS)))
                        .checkout(formatter.format(facker.date().future(5,TimeUnit.DAYS))).build())
                .additionalneeds("BreakFast"). build();

    }


}
