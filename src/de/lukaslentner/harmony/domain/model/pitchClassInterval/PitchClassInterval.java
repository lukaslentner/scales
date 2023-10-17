package de.lukaslentner.harmony.domain.model.pitchClassInterval;

import de.lukaslentner.harmony.domain.formatter.Formatter;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClass;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;
import de.lukaslentner.harmony.domain.util.Math2;

public class PitchClassInterval implements Comparable<PitchClassInterval> {
  
  private final Long value;
  
  public PitchClassInterval(final Long value) {
    this.value = value;
    if (this.value <= -1200_000_000 || this.value >= 1200_000_000) {
      throw new IllegalArgumentException("Value must be within ]-1200_000_000;1200_000_000[");
    }
  }
  
  public Long getValue() {
    return this.value;
  }
  
  public Long getAbsoluteValue() {
    return Math.abs(this.value);
  }
  
  public PitchInterval getPitchInterval() {
    return new PitchInterval(this.value);
  }
  
  public PitchClass getPitchClassFrom(final PitchClass base) {
    return base.transpose(this);
  }
  
  public PitchClassInterval invert() {
    return new PitchClassInterval(-this.value);
  }
  
  public PitchClassInterval add(final PitchClassInterval summandInterval) {
    return new PitchClassInterval((this.value + summandInterval.value) % Math2.OCTAVE_VALUE);
  }
  
  public PitchClassInterval substract(final PitchClassInterval minuendInterval) {
    return new PitchClassInterval((this.value - minuendInterval.value) % Math2.OCTAVE_VALUE);
  }
  
  public PitchClassInterval multiply(final Long factor) {
    return new PitchClassInterval((this.value * factor) % Math2.OCTAVE_VALUE);
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchClassInterval)) {
      return false;
    }
    
    final PitchClassInterval castedOther = (PitchClassInterval) other;
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
  public int compareTo(final PitchClassInterval other) {
    return this.value.compareTo(other.value);
  }
  
}
