package de.lukaslentner.harmony.domain.util;

import com.google.common.math.DoubleMath;

public class Math2 {
  
  public static final Long TWELTH = 12L;
  
  public static final Long HUNDRED = 100L;
  
  public static final Long MILLE = 1_000_000L;
  
  public static final Long HUNDRED_MILLE = HUNDRED * MILLE;
  
  public static final Double EQUAL_TEMPERED_HALF_TONE_FREQUENCY_RATIO = raise2(1d / TWELTH);
  
  public static final Long OCTAVE_VALUE = TWELTH * HUNDRED_MILLE;
  
  public static final Long A440_MIDI_NOTE_NUMBER = 69L;
  
  public static Double raise2(final Number power) {
    return Math.pow(2d, power.doubleValue());
  }
  
  public static Double equalTemperedFrequencyRatio(final Number halfToneCount) {
    return Math.pow(EQUAL_TEMPERED_HALF_TONE_FREQUENCY_RATIO, halfToneCount.doubleValue());
  }
  
  public static Double lb(final Number argument) {
    return DoubleMath.log2(argument.doubleValue());
  }
  
  public static Long modulo(final Long argument, final Long modulus) {
    return argument % modulus + (argument < 0 ? modulus : 0);
  }
  
  public static Long moduloOctave(final Long argument) {
    return modulo(argument, OCTAVE_VALUE);
  }
  
}
