package de.lukaslentner.harmony.domain.model.scale;

import de.lukaslentner.harmony.domain.model.pitchClass.EqualTemperedPitchClasses;
import de.lukaslentner.harmony.domain.model.pitchClass.PerfectPitchClasses;

public abstract class Scales {
  
  public static final Scale EQUAL_TEMPERED_CHROMATIC = new Scale(EqualTemperedPitchClasses.VALUES_WITH_UNISON);
  
  public static final Scale EQUAL_TEMPERED_MAJOR =
    new Scale(
      PerfectPitchClasses.UNISON,
      EqualTemperedPitchClasses.MAJOR_SECOND,
      EqualTemperedPitchClasses.MAJOR_THIRD,
      PerfectPitchClasses.FORTH,
      PerfectPitchClasses.FIFTH,
      EqualTemperedPitchClasses.MAJOR_SIXTH,
      EqualTemperedPitchClasses.MAJOR_SEVENTH);
      
  public static final Scale EQUAL_TEMPERED_MINOR =
    new Scale(
      PerfectPitchClasses.UNISON,
      EqualTemperedPitchClasses.MAJOR_SECOND,
      EqualTemperedPitchClasses.MINOR_THIRD,
      PerfectPitchClasses.FORTH,
      PerfectPitchClasses.FIFTH,
      EqualTemperedPitchClasses.MINOR_SIXTH,
      EqualTemperedPitchClasses.MINOR_SEVENTH);
      
}
