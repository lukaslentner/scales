package de.lukaslentner.harmony.domain.model.pitchClass;

import org.junit.Assert;
import org.junit.Test;

import de.lukaslentner.harmony.domain.model.pitch.Pitch;
import de.lukaslentner.harmony.domain.model.pitch.StandardPitches;
import de.lukaslentner.harmony.domain.model.pitchInterval.PerfectPitchIntervals;

public class PitchClassTest {
  
  @Test
  public void test() {
    
    final Pitch zeroPitch = StandardPitches.BAROQUE;
    
    final Pitch unison = zeroPitch.transpose(PerfectPitchIntervals.UNISON);
    final Pitch forth = zeroPitch.transpose(PerfectPitchIntervals.FORTH);
    final Pitch fifth = zeroPitch.transpose(PerfectPitchIntervals.FIFTH);
    final Pitch octave = zeroPitch.transpose(PerfectPitchIntervals.OCTAVE);
    
    final PitchClass unisonPitchClass = unison.getClazz(zeroPitch);
    final PitchClass forthPitchClass = forth.getClazz(zeroPitch);
    final PitchClass fifthPitchClass = fifth.getClazz(zeroPitch);
    final PitchClass octavePitchClass = octave.getClazz(zeroPitch);
    
    Assert.assertEquals(new Long(0), unisonPitchClass.getValue());
    Assert.assertEquals(new Long(500_000_000), forthPitchClass.getValue());
    Assert.assertEquals(new Long(700_000_000), fifthPitchClass.getValue());
    Assert.assertEquals(new Long(0), octavePitchClass.getValue());
    
  }
  
}
