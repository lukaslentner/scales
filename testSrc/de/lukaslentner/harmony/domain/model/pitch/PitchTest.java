package de.lukaslentner.harmony.domain.model.pitch;

import org.junit.Assert;
import org.junit.Test;

import de.lukaslentner.harmony.domain.util.Math2;

public class PitchTest {
  
  private static final Double DOUBLE_DELTA = 0.002d;
  
  @Test
  public void testEquality() {
    Assert.assertEquals(new Pitch(Math2.OCTAVE_VALUE), new Pitch(Math2.OCTAVE_VALUE));
  }
  
  @Test
  public void testHashCode() {
    Assert.assertEquals(new Pitch(Math2.OCTAVE_VALUE).hashCode(), new Pitch(Math2.OCTAVE_VALUE).hashCode());
  }
  
  @Test
  public void testMidiNoteNumber() {
    Assert.assertEquals(Math2.A440_MIDI_NOTE_NUMBER, StandardPitches.ISO16.getMidiNoteNumber(), DOUBLE_DELTA);
  }
  
}
