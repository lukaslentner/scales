package de.lukaslentner.harmony.domain.model.pitchInterval;

import org.junit.Assert;
import org.junit.Test;

import de.lukaslentner.harmony.domain.util.Math2;

public class IntervalTest {
  
  @Test
  public void testEquality() {
    
    Assert.assertEquals(new PitchInterval(Math2.OCTAVE_VALUE), new PitchInterval(Math2.OCTAVE_VALUE));
    
  }
  
  @Test
  public void testHashCode() {
    
    Assert
      .assertEquals(new PitchInterval(Math2.OCTAVE_VALUE).hashCode(), new PitchInterval(Math2.OCTAVE_VALUE).hashCode());
      
  }
  
}
