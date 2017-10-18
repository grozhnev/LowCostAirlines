package mockito;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import  static org.mockito.Mockito.*;

import services.PriceCalculator;

public class PriceCalculatorTest {

    @Mock
    PriceCalculator calculator = mock(PriceCalculator.class);

    @Test
    public void calculatePrice(){
        when(calculator.calculatePrice()).thenReturn(0);
        Assert.assertEquals(0, calculator.calculatePrice());

    }
}
