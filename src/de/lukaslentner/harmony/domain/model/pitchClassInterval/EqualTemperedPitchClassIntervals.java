package de.lukaslentner.harmony.domain.model.pitchClassInterval;

import java.util.Set;

import com.google.common.collect.ImmutableSortedSet;

public abstract class EqualTemperedPitchClassIntervals {
  
  public static final PitchClassInterval MINOR_SECOND = new PitchClassInterval(100_000_000L);
  
  public static final PitchClassInterval MAJOR_SECOND = new PitchClassInterval(200_000_000L);
  
  public static final PitchClassInterval MINOR_THIRD = new PitchClassInterval(300_000_000L);
  
  public static final PitchClassInterval MAJOR_THIRD = new PitchClassInterval(400_000_000L);
  
  public static final PitchClassInterval TRITONE = new PitchClassInterval(600_000_000L);
  
  public static final PitchClassInterval MINOR_SIXTH = new PitchClassInterval(800_000_000L);
  
  public static final PitchClassInterval MAJOR_SIXTH = new PitchClassInterval(900_000_000L);
  
  public static final PitchClassInterval MINOR_SEVENTH = new PitchClassInterval(1000_000_000L);
  
  public static final PitchClassInterval MAJOR_SEVENTH = new PitchClassInterval(1100_000_000L);
  
  public static final ImmutableSortedSet<PitchClassInterval> VALUES =
    ImmutableSortedSet.of(
      MINOR_SECOND,
      MAJOR_SECOND,
      MINOR_THIRD,
      MAJOR_THIRD,
      PerfectPitchClassIntervals.FORTH,
      TRITONE,
      PerfectPitchClassIntervals.FIFTH,
      MINOR_SIXTH,
      MAJOR_SIXTH,
      MINOR_SEVENTH,
      MAJOR_SEVENTH);
      
  public static final ImmutableSortedSet<PitchClassInterval> VALUES_WITH_UNISON =
    ImmutableSortedSet.of(
      PerfectPitchClassIntervals.UNISON,
      MINOR_SECOND,
      MAJOR_SECOND,
      MINOR_THIRD,
      MAJOR_THIRD,
      PerfectPitchClassIntervals.FORTH,
      TRITONE,
      PerfectPitchClassIntervals.FIFTH,
      MINOR_SIXTH,
      MAJOR_SIXTH,
      MINOR_SEVENTH,
      MAJOR_SEVENTH);
      
  public static Set<PitchClassInterval> upTo(final PitchClassInterval interval, final Boolean withUnison) {
    return (withUnison ? VALUES_WITH_UNISON : VALUES).headSet(interval, true);
  }
  
  public static Set<PitchClassInterval> upTo(final PitchClassInterval interval) {
    return upTo(interval, false);
  }
  
}
