package de.lukaslentner.harmony.domain.generator;

import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;

import de.lukaslentner.harmony.domain.model.pitchClass.PerfectPitchClasses;
import de.lukaslentner.harmony.domain.model.scale.Scale;

public enum ScaleGenerator {
  
  INSTANCE;
  
  public ImmutableSet<Scale> generate(final Scale chroma) {
    return ImmutableSet.copyOf(
      chroma
        .without(PerfectPitchClasses.UNISON)
        .powerSet()
        .stream()
        .map(o -> o.with(PerfectPitchClasses.UNISON).toScale())
        .collect(Collectors.toList()));
  }
  
}
