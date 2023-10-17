package de.lukaslentner.harmony.domain.model.pitchInterval;

public abstract class PerfectPitchIntervals {

  public static final PitchInterval UNISON = new PitchInterval(0L);

  public static final PitchInterval FORTH = new PitchInterval(500_000_000L);

  public static final PitchInterval FIFTH = new PitchInterval(700_000_000L);

  public static final PitchInterval OCTAVE = new PitchInterval(1200_000_000L);
  
}
