package de.lukaslentner.harmony.domain.model.pitchClass;

import java.util.Set;

import com.google.common.collect.ImmutableSortedSet;

public abstract class EqualTemperedPitchClasses {
  
  public static final PitchClass MINOR_SECOND = new PitchClass(100_000_000L);
  
  public static final PitchClass MAJOR_SECOND = new PitchClass(200_000_000L);
  
  public static final PitchClass MINOR_THIRD = new PitchClass(300_000_000L);
  
  public static final PitchClass MAJOR_THIRD = new PitchClass(400_000_000L);
  
  public static final PitchClass TRITONE = new PitchClass(600_000_000L);
  
  public static final PitchClass MINOR_SIXTH = new PitchClass(800_000_000L);
  
  public static final PitchClass MAJOR_SIXTH = new PitchClass(900_000_000L);
  
  public static final PitchClass MINOR_SEVENTH = new PitchClass(1000_000_000L);
  
  public static final PitchClass MAJOR_SEVENTH = new PitchClass(1100_000_000L);
  
  public static final ImmutableSortedSet<PitchClass> VALUES =
    ImmutableSortedSet.of(
      MINOR_SECOND,
      MAJOR_SECOND,
      MINOR_THIRD,
      MAJOR_THIRD,
      PerfectPitchClasses.FORTH,
      TRITONE,
      PerfectPitchClasses.FIFTH,
      MINOR_SIXTH,
      MAJOR_SIXTH,
      MINOR_SEVENTH,
      MAJOR_SEVENTH);
      
  public static final ImmutableSortedSet<PitchClass> VALUES_WITH_UNISON =
    ImmutableSortedSet.of(
      PerfectPitchClasses.UNISON,
      MINOR_SECOND,
      MAJOR_SECOND,
      MINOR_THIRD,
      MAJOR_THIRD,
      PerfectPitchClasses.FORTH,
      TRITONE,
      PerfectPitchClasses.FIFTH,
      MINOR_SIXTH,
      MAJOR_SIXTH,
      MINOR_SEVENTH,
      MAJOR_SEVENTH);
      
  public static Set<PitchClass> upTo(final PitchClass pitchClass, final Boolean withUnison) {
    return (withUnison ? VALUES_WITH_UNISON : VALUES).headSet(pitchClass, true);
  }
  
  public static Set<PitchClass> upTo(final PitchClass pitchClass) {
    return upTo(pitchClass, false);
  }
  
}
