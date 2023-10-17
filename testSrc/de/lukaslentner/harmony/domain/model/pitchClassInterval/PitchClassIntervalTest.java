package de.lukaslentner.harmony.domain.model.pitchClassInterval;

import org.junit.Assert;
import org.junit.Test;

import de.lukaslentner.harmony.domain.model.pitch.Pitch;
import de.lukaslentner.harmony.domain.model.pitch.StandardPitches;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClass;
import de.lukaslentner.harmony.domain.model.pitchInterval.PerfectPitchIntervals;

public class PitchClassIntervalTest {
  
  @Test
  public void test() {
    
    final Pitch zeroPitch = StandardPitches.BAROQUE;
    
    final Pitch unison = zeroPitch.transpose(PerfectPitchIntervals.UNISON);
    final Pitch forth = zeroPitch.transpose(PerfectPitchIntervals.FORTH);
    
    final PitchClass unisonPitchClass = unison.getClazz(zeroPitch);
    final PitchClass forthPitchClass = forth.getClazz(zeroPitch);
    
    Assert.assertEquals(new Long(500_000_000), unisonPitchClass.getIntervalTo(forthPitchClass).getValue());
    Assert.assertEquals(new Long(-500_000_000), forthPitchClass.getIntervalTo(unisonPitchClass).getValue());
    
  }
  
}
