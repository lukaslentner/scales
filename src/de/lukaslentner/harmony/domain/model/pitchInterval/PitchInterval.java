package de.lukaslentner.harmony.domain.model.pitchInterval;

import de.lukaslentner.harmony.domain.formatter.Formatter;
import de.lukaslentner.harmony.domain.model.pitch.Pitch;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassInterval;
import de.lukaslentner.harmony.domain.util.Math2;

public class PitchInterval implements Comparable<PitchInterval> {
  
  private final Long value;
  
  public PitchInterval(final Long value) {
    this.value = value;
  }
  
  public static PitchInterval constructByFrequencyRation(final Number frequencyRatio) {
    return new PitchInterval((long) (Math2.lb(frequencyRatio) * Math2.OCTAVE_VALUE));
  }
  
  /**
   * Returns the value of the interval measured in millicents (mc)
   * 
   * @return Value in millicents (µcs)
   */
  public Long getValue() {
    return this.value;
  }
  
  public Long getAbsoluteValue() {
    return Math.abs(this.value);
  }
  
  /**
   * Returns the ratio of the frequencies associated with this interval
   * 
   * @return Ratio of the frequencies
   */
  public Double getFrequencyRatio() {
    return Math2.raise2(this.value.doubleValue() / Math2.OCTAVE_VALUE);
  }
  
  public PitchClassInterval getClazz() {
    return new PitchClassInterval(this.value % Math2.OCTAVE_VALUE);
  }
  
  public Pitch getPitchFrom(final Pitch base) {
    return base.transpose(this);
  }
  
  public PitchInterval invert() {
    return new PitchInterval(-this.value);
  }
  
  public PitchInterval add(final PitchInterval summandInterval) {
    return new PitchInterval(this.value + summandInterval.value);
  }
  
  public PitchInterval substract(final PitchInterval minuendInterval) {
    return new PitchInterval(this.value - minuendInterval.value);
  }
  
  public PitchInterval multiply(final Long factor) {
    return new PitchInterval(this.value * factor);
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchInterval)) {
      return false;
    }
    
    final PitchInterval castedOther = (PitchInterval) other;
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
  public int compareTo(final PitchInterval other) {
    return this.value.compareTo(other.value);
  }
  
}
