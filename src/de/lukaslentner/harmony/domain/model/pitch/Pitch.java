package de.lukaslentner.harmony.domain.model.pitch;

import de.lukaslentner.harmony.domain.formatter.Formatter;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClass;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;
import de.lukaslentner.harmony.domain.util.Math2;

public class Pitch implements Comparable<Pitch> {
  
  private final Long value;
  
  public Pitch(final Long value) {
    this.value = value;
    if (this.value < 0) {
      throw new IllegalArgumentException("Value must be greater than or equal to 0");
    }
  }
  
  public static Pitch constructByHz(final Number valueInHz) {
    return PitchInterval.constructByFrequencyRation(valueInHz).getPitchFrom(StandardPitches.ZERO);
  }
  
  public Long getValue() {
    return this.value;
  }
  
  public Double getFrequency() {
    return this.getIntervalFrom(StandardPitches.ZERO).getFrequencyRatio();
  }
  
  public Long getMidiNoteNumber() {
    return StandardPitches.ISO16.getIntervalTo(this).getValue() / Math2.HUNDRED_MILLE + Math2.A440_MIDI_NOTE_NUMBER;
  }
  
  public PitchInterval getIntervalFrom(final Pitch base) {
    return new PitchInterval(this.value - base.getValue());
  }
  
  public PitchInterval getIntervalTo(final Pitch aim) {
    return this.getIntervalFrom(aim).invert();
  }
  
  public PitchClass getClazz(final Pitch zero) {
    return new PitchClass(Math2.moduloOctave(this.getIntervalFrom(zero).getValue()));
  }
  
  public Pitch mirror(final Pitch mirror) {
    return this.transpose(this.getIntervalTo(mirror).multiply(2L));
  }
  
  public Pitch transpose(final PitchInterval interval) {
    return new Pitch(this.value + interval.getValue());
  }
  
  public Pitch untranspose(final PitchInterval interval) {
    return new Pitch(this.value - interval.getValue());
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof Pitch)) {
      return false;
    }
    
    final Pitch castedOther = (Pitch) other;
    return this.value.equals(castedOther.value);
    
  }
  
  @Override
  public int hashCode() {
    return this.value.hashCode();
  }
  
  @Override
  public String toString() {
    return Formatter.INSTANCE.centWithUnit(this.value) + "[" + this.getFrequency() + " Hz]";
  }
  
  @Override
  public int compareTo(final Pitch other) {
    return this.value.compareTo(other.value);
  }
  
}
