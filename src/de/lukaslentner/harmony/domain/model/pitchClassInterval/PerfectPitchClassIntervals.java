package de.lukaslentner.harmony.domain.model.pitchClassInterval;

public abstract class PerfectPitchClassIntervals {
  
  public static final PitchClassInterval UNISON = new PitchClassInterval(0L);
  
  public static final PitchClassInterval FORTH = new PitchClassInterval(500_000_000L);
  
  public static final PitchClassInterval FIFTH = new PitchClassInterval(700_000_000L);
  
}
