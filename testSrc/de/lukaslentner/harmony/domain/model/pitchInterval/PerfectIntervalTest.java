package de.lukaslentner.harmony.domain.model.pitchInterval;

import org.junit.Assert;
import org.junit.Test;

public class PerfectIntervalTest {
  
  private static final Double DOUBLE_DELTA = 0.002d;
  
  @Test
  public void testFrequencyRation() {
    
    Assert.assertEquals(1d / 1, PerfectPitchIntervals.UNISON.getFrequencyRatio().doubleValue(), DOUBLE_DELTA);
    Assert.assertEquals(4d / 3, PerfectPitchIntervals.FORTH.getFrequencyRatio().doubleValue(), DOUBLE_DELTA);
    Assert.assertEquals(3d / 2, PerfectPitchIntervals.FIFTH.getFrequencyRatio().doubleValue(), DOUBLE_DELTA);
    Assert.assertEquals(2d / 1, PerfectPitchIntervals.OCTAVE.getFrequencyRatio().doubleValue(), DOUBLE_DELTA);
    
  }
  
}
