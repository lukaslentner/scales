package de.lukaslentner.harmony.domain.model.pitchClass;

import de.lukaslentner.harmony.domain.formatter.Formatter;
import de.lukaslentner.harmony.domain.model.pitch.Pitch;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassInterval;
import de.lukaslentner.harmony.domain.util.Math2;

public class PitchClass implements Comparable<PitchClass> {
  
  private final Long value;
  
  public PitchClass(final Number value) {
    this.value = value.longValue();
    if (this.value < 0 || this.value >= 1200_000_000) {
      throw new IllegalArgumentException("Value must be within [0;1200_000_000[");
    }
  }
  
  public Long getValue() {
    return this.value;
  }
  
  public Pitch getPitch(final Pitch zero) {
    return new Pitch(zero.getValue() + this.value);
  }
  
  public PitchClassInterval getIntervalFrom(final PitchClass base) {
    return new PitchClassInterval((this.value - base.getValue()) % Math2.OCTAVE_VALUE);
  }
  
  public PitchClassInterval getIntervalTo(final PitchClass aim) {
    return this.getIntervalFrom(aim).invert();
  }
  
  public PitchClass invert() {
    return new PitchClass(Math2.moduloOctave(-this.value));
  }
  
  public PitchClass transpose(final PitchClassInterval interval) {
    return new PitchClass(Math2.moduloOctave(this.value + interval.getValue()));
  }
  
  public PitchClass untranspose(final PitchClassInterval interval) {
    return new PitchClass(Math2.moduloOctave(this.value - interval.getValue()));
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchClass)) {
      return false;
    }
    
    final PitchClass castedOther = (PitchClass) other;
    return this.value.equals(castedOther.value);
    
  }
  
  @Override
  public int hashCode() {
    return this.value.hashCode();
  }
  
  @Override
  public String toString() {
    return Formatter.INSTANCE.centWithUnit(this.value);
  }
  
  @Override
  public int compareTo(final PitchClass other) {
    return this.value.compareTo(other.value);
  }
  
}
