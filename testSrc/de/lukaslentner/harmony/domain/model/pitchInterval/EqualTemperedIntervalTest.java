package de.lukaslentner.harmony.domain.model.pitchInterval;

import org.junit.Assert;
import org.junit.Test;

import de.lukaslentner.harmony.domain.util.Math2;

public class EqualTemperedIntervalTest {
  
  private static final Double DOUBLE_DELTA = 0.002d;
  
  @Test
  public void testFrequencyRation() {
    
    Assert.assertEquals(
      Math2.equalTemperedFrequencyRatio(1),
      EqualTemperedPitchIntervals.MINOR_SECOND.getFrequencyRatio().doubleValue(),
      DOUBLE_DELTA);
    Assert.assertEquals(
      Math2.equalTemperedFrequencyRatio(2),
      EqualTemperedPitchIntervals.MAJOR_SECOND.getFrequencyRatio().doubleValue(),
      DOUBLE_DELTA);
    Assert.assertEquals(
      Math2.equalTemperedFrequencyRatio(3),
      EqualTemperedPitchIntervals.MINOR_THIRD.getFrequencyRatio().doubleValue(),
      DOUBLE_DELTA);
    Assert.assertEquals(
      Math2.equalTemperedFrequencyRatio(4),
      EqualTemperedPitchIntervals.MAJOR_THIRD.getFrequencyRatio().doubleValue(),
      DOUBLE_DELTA);
    Assert.assertEquals(
      Math2.equalTemperedFrequencyRatio(8),
      EqualTemperedPitchIntervals.MINOR_SIXTH.getFrequencyRatio().doubleValue(),
      DOUBLE_DELTA);
    Assert.assertEquals(
      Math2.equalTemperedFrequencyRatio(9),
      EqualTemperedPitchIntervals.MAJOR_SIXTH.getFrequencyRatio().doubleValue(),
      DOUBLE_DELTA);
    Assert.assertEquals(
      Math2.equalTemperedFrequencyRatio(10),
      EqualTemperedPitchIntervals.MINOR_SEVENTH.getFrequencyRatio().doubleValue(),
      DOUBLE_DELTA);
    Assert.assertEquals(
      Math2.equalTemperedFrequencyRatio(11),
      EqualTemperedPitchIntervals.MAJOR_SEVENTH.getFrequencyRatio().doubleValue(),
      DOUBLE_DELTA);
      
  }
  
}
