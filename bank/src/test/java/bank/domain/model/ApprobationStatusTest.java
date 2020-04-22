package bank.domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ApprobationStatusTest {

    @Test
    public void getValue() {
        Integer valueSubscribed = 0;
        ApprobationStatus approbationStatus;

        assertEquals(valueSubscribed, ApprobationStatus.SUBSCRIBED.getValue());

    }

    @Test
    public void fromInteger() {
        Integer valueToTest = 0;
        assertEquals(ApprobationStatus.SUBSCRIBED, ApprobationStatus.fromInteger(valueToTest));
        valueToTest++;
        assertEquals(ApprobationStatus.WAITING_FOR_SUBSCRIPTION, ApprobationStatus.fromInteger(valueToTest));
        valueToTest++;
        assertEquals(ApprobationStatus.WAITING_FOR_DELETION, ApprobationStatus.fromInteger(valueToTest));
        valueToTest++;
        assertEquals(ApprobationStatus.NOT_SET, ApprobationStatus.fromInteger(valueToTest));

    }
}