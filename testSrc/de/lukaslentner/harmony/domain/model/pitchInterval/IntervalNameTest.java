package de.lukaslentner.harmony.domain.model.pitchInterval;

import org.junit.Assert;
import org.junit.Test;

import de.lukaslentner.harmony.domain.formatter.Formatter;

public class IntervalNameTest {
  
  @Test
  public void testOctave() {
    Assert.assertEquals("Perfect Octave", Formatter.INSTANCE.intervalCommonName(PerfectPitchIntervals.OCTAVE));
  }
  
}
