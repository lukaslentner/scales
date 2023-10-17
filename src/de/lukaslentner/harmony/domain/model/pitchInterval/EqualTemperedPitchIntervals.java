package de.lukaslentner.harmony.domain.model.pitchInterval;

import java.util.Set;

import com.google.common.collect.ImmutableSortedSet;

public abstract class EqualTemperedPitchIntervals {
  
  public static final PitchInterval MINOR_SECOND = new PitchInterval(100_000_000L);
  
  public static final PitchInterval MAJOR_SECOND = new PitchInterval(200_000_000L);
  
  public static final PitchInterval MINOR_THIRD = new PitchInterval(300_000_000L);
  
  public static final PitchInterval MAJOR_THIRD = new PitchInterval(400_000_000L);
  
  public static final PitchInterval TRITONE = new PitchInterval(600_000_000L);
  
  public static final PitchInterval MINOR_SIXTH = new PitchInterval(800_000_000L);
  
  public static final PitchInterval MAJOR_SIXTH = new PitchInterval(900_000_000L);
  
  public static final PitchInterval MINOR_SEVENTH = new PitchInterval(1000_000_000L);
  
  public static final PitchInterval MAJOR_SEVENTH = new PitchInterval(1100_000_000L);
  
  public static final ImmutableSortedSet<PitchInterval> VALUES =
    ImmutableSortedSet.of(
      MINOR_SECOND,
      MAJOR_SECOND,
      MINOR_THIRD,
      MAJOR_THIRD,
      PerfectPitchIntervals.FORTH,
      TRITONE,
      PerfectPitchIntervals.FIFTH,
      MINOR_SIXTH,
      MAJOR_SIXTH,
      MINOR_SEVENTH,
      MAJOR_SEVENTH,
      PerfectPitchIntervals.OCTAVE);
      
  public static final ImmutableSortedSet<PitchInterval> VALUES_WITH_UNISON =
    ImmutableSortedSet.of(
      PerfectPitchIntervals.UNISON,
      MINOR_SECOND,
      MAJOR_SECOND,
      MINOR_THIRD,
      MAJOR_THIRD,
      PerfectPitchIntervals.FORTH,
      TRITONE,
      PerfectPitchIntervals.FIFTH,
      MINOR_SIXTH,
      MAJOR_SIXTH,
      MINOR_SEVENTH,
      MAJOR_SEVENTH,
      PerfectPitchIntervals.OCTAVE);
      
  public static Set<PitchInterval> upTo(final PitchInterval interval, final Boolean withUnison) {
    return (withUnison ? VALUES_WITH_UNISON : VALUES).headSet(interval, true);
  }
  
  public static Set<PitchInterval> upTo(final PitchInterval interval) {
    return upTo(interval, false);
  }
  
}
