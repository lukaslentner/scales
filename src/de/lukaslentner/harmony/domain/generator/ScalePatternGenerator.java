package de.lukaslentner.harmony.domain.generator;

import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;

import de.lukaslentner.harmony.domain.model.scale.Scale;
import de.lukaslentner.harmony.domain.model.scale.ScalePattern;

public enum ScalePatternGenerator {
  
  INSTANCE;
  
  public ImmutableSet<ScalePattern> generate(final Scale chroma) {
    return ImmutableSet.copyOf(
      ScaleGenerator.INSTANCE.generate(chroma).stream().map(o -> new ScalePattern(o)).collect(Collectors.toList()));
  }
  
}
