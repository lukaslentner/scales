package de.lukaslentner.harmony;

import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import de.lukaslentner.harmony.domain.formatter.CentFormat;
import de.lukaslentner.harmony.domain.formatter.FormatSettings;
import de.lukaslentner.harmony.domain.generator.ScalePatternGenerator;
import de.lukaslentner.harmony.domain.model.pitchInterval.EqualTemperedPitchIntervals;
import de.lukaslentner.harmony.domain.model.scale.ScalePattern;
import de.lukaslentner.harmony.domain.model.scale.ScalePatternPredicates;
import de.lukaslentner.harmony.domain.model.scale.Scales;

public class TryoutSplitMerge {
  
  @Test
  public void tryout2() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.HECTO_CENT;
    
    final ImmutableSet<ScalePattern> scalePatterns =
      ImmutableSet.copyOf(
        ScalePatternGenerator.INSTANCE
          .generate(Scales.EQUAL_TEMPERED_CHROMATIC)
          .stream()
          .filter(
            ScalePatternPredicates.RESTRICT_RELATIVE_INTERVALS
              .apply(EqualTemperedPitchIntervals.upTo(EqualTemperedPitchIntervals.MAJOR_SECOND)))
          .collect(Collectors.toList()));
          
    System.out.println("All:");
    scalePatterns.forEach(o -> System.out.println(o));
    System.out.println("Count: " + scalePatterns.size());
    System.out.println("");
    
    final ImmutableSet<ScalePattern> nonSplittableScalePatterns =
      ImmutableSet.copyOf(
        scalePatterns
          .stream()
          .filter(
            o -> o
              .getGeneratingScale()
              .getRelativeIntervalsWithOctave()
              .getPairsCountSmallerThan(EqualTemperedPitchIntervals.MAJOR_SECOND)
              .equals(0))
          .collect(Collectors.toList()));
    System.out.println("Non splittable:");
    nonSplittableScalePatterns.forEach(o -> System.out.println(o));
    System.out.println("Count: " + nonSplittableScalePatterns.size());
    
    final ImmutableSet<ScalePattern> onePairScalePatterns =
      ImmutableSet.copyOf(
        scalePatterns
          .stream()
          .filter(
            o -> o
              .getGeneratingScale()
              .getRelativeIntervalsWithOctave()
              .getPairsCountSmallerThan(EqualTemperedPitchIntervals.MAJOR_SECOND)
              .equals(1))
          .collect(Collectors.toList()));
    System.out.println("One pair:");
    onePairScalePatterns.forEach(o -> System.out.println(o));
    System.out.println("Count: " + onePairScalePatterns.size());
    
  }
  
  @Test
  public void tryout3() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.HECTO_CENT;
    
    final ImmutableSet<ScalePattern> scalePatterns =
      ImmutableSet.copyOf(
        ScalePatternGenerator.INSTANCE
          .generate(Scales.EQUAL_TEMPERED_CHROMATIC)
          .stream()
          .filter(
            ScalePatternPredicates.RESTRICT_RELATIVE_INTERVALS
              .apply(EqualTemperedPitchIntervals.upTo(EqualTemperedPitchIntervals.MAJOR_SECOND)))
          .collect(Collectors.toList()));
          
    System.out.println("All:");
    scalePatterns.forEach(o -> System.out.println(o));
    System.out.println("Count: " + scalePatterns.size());
    System.out.println("");
    
    final ImmutableSet<ScalePattern> nonSplittableScalePatterns =
      ImmutableSet.copyOf(
        scalePatterns
          .stream()
          .filter(
            o -> o
              .getGeneratingScale()
              .getRelativeIntervalsWithOctave()
              .getPairsCountSmallerThan(EqualTemperedPitchIntervals.MAJOR_SECOND)
              .equals(0))
          .collect(Collectors.toList()));
    System.out.println("Non splittable:");
    nonSplittableScalePatterns.forEach(o -> System.out.println(o));
    System.out.println("Count: " + nonSplittableScalePatterns.size());
    
    final ImmutableSet<ScalePattern> onePairScalePatterns =
      ImmutableSet.copyOf(
        scalePatterns
          .stream()
          .filter(
            o -> o
              .getGeneratingScale()
              .getRelativeIntervalsWithOctave()
              .getPairsCountSmallerThan(EqualTemperedPitchIntervals.MAJOR_SECOND)
              .equals(1))
          .collect(Collectors.toList()));
    System.out.println("One pair:");
    onePairScalePatterns.forEach(o -> System.out.println(o));
    System.out.println("Count: " + onePairScalePatterns.size());
    
  }
  
}
