package de.lukaslentner.harmony.domain.model.pitch;

import org.junit.Assert;
import org.junit.Test;

public class StandardPitchesTest {
  
  private static final Double DOUBLE_DELTA = 0.002d;
  
  @Test
  public void testAliase() {
    Assert.assertEquals(StandardPitches.MODERN, StandardPitches.ISO16);
  }
  
  @Test
  public void testIntervalFrequencyRation() {
    Assert
      .assertEquals(new Double(440), StandardPitches.MODERN.getIntervalFrom(StandardPitches.ZERO).getFrequencyRatio(), DOUBLE_DELTA);
    Assert
      .assertEquals(new Double(430), StandardPitches.CLASSICAL.getIntervalFrom(StandardPitches.ZERO).getFrequencyRatio(), DOUBLE_DELTA);
    Assert
      .assertEquals(new Double(415), StandardPitches.BAROQUE.getIntervalFrom(StandardPitches.ZERO).getFrequencyRatio(), DOUBLE_DELTA);
    Assert
      .assertEquals(new Double(466), StandardPitches.CHORTON.getIntervalFrom(StandardPitches.ZERO).getFrequencyRatio(), DOUBLE_DELTA);
  }
  
  @Test
  public void testIntervalValue() {
    
    Assert.assertEquals(new Long(10_537_631_656L), StandardPitches.MODERN.getValue());
    Assert.assertEquals(new Long(10_497_831_419L), StandardPitches.CLASSICAL.getValue());
    Assert.assertEquals(new Long(10_436_361_031L), StandardPitches.BAROQUE.getValue());
    Assert.assertEquals(new Long(10_637_023_373L), StandardPitches.CHORTON.getValue());
    
  }
  
}
