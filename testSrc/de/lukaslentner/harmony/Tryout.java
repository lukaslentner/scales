package de.lukaslentner.harmony;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;

import de.lukaslentner.harmony.domain.formatter.CentFormat;
import de.lukaslentner.harmony.domain.formatter.FormatSettings;
import de.lukaslentner.harmony.domain.generator.ScalePatternGenerator;
import de.lukaslentner.harmony.domain.model.pitchClass.EqualTemperedPitchClasses;
import de.lukaslentner.harmony.domain.model.pitchClass.PerfectPitchClasses;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClass;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.EqualTemperedPitchClassIntervals;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassInterval;
import de.lukaslentner.harmony.domain.model.pitchInterval.EqualTemperedPitchIntervals;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;
import de.lukaslentner.harmony.domain.model.scale.Scale;
import de.lukaslentner.harmony.domain.model.scale.ScalePattern;
import de.lukaslentner.harmony.domain.model.scale.ScalePatternPredicates;
import de.lukaslentner.harmony.domain.model.scale.Scales;

public class Tryout {
  
  @Test
  public void tryout2() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.HECTO_CENT;
    
    final PitchClassInterval modificationInterval = EqualTemperedPitchClassIntervals.MINOR_SECOND;
    final ImmutableSet<ScalePattern> scalePatternGroups =
      ScalePatternGenerator.INSTANCE.generate(Scales.EQUAL_TEMPERED_CHROMATIC);
      
    scalePatternGroups
      .stream()
      .filter(o -> !o.getGeneratingScale().getSelfContainedModificationBranches(modificationInterval).isEmpty())
      .forEach(o -> {
        System.out.println(o);
      });
      
  }
  
  @Test
  public void tryout3() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.HECTO_CENT;
    
    final ImmutableSet<ScalePattern> scalePatternGroups =
      ScalePatternGenerator.INSTANCE.generate(Scales.EQUAL_TEMPERED_CHROMATIC);
      
    for (final PitchInterval interval : EqualTemperedPitchIntervals.VALUES) {
      
      System.out.println("Interval: " + interval);
      
      scalePatternGroups.stream().filter(ScalePatternPredicates.XXX.apply(interval)).forEach(o -> {
        System.out.println(o);
      });
      
      final Long count = scalePatternGroups.stream().filter(ScalePatternPredicates.XXX.apply(interval)).count();
      
      System.out.println("Count: " + count);
      
      System.out.println("");
      
    }
    
  }
  
  @Test
  public void tryout4() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.HECTO_CENT;
    
    final ImmutableSet<ScalePattern> scalePatternGroups =
      ScalePatternGenerator.INSTANCE.generate(Scales.EQUAL_TEMPERED_CHROMATIC);
      
    scalePatternGroups.stream().filter(ScalePatternPredicates.HIGHER_SYMETRY).forEach(o -> {
      System.out.println(o);
    });
    
  }
  
  @Test
  public void tryout5() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.HECTO_CENT;
    
    final ImmutableSet<ScalePattern> scalePatternGroups =
      ScalePatternGenerator.INSTANCE.generate(Scales.EQUAL_TEMPERED_CHROMATIC);
      
    scalePatternGroups.stream().filter(ScalePatternPredicates.DIATONIC).forEach(o -> {
      System.out.println(o);
    });
    
  }
  
  @Test
  public void tryout6() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.CENT;
    
    System.out.println(new ScalePattern(new Scale(PerfectPitchClasses.UNISON, new PitchClass(250_000_000))));
    
    System.out.println(
      new ScalePattern(
        new Scale(
          PerfectPitchClasses.UNISON,
          new PitchClass(150_000_000),
          new PitchClass(300_000_000),
          new PitchClass(450_000_000),
          new PitchClass(600_000_000),
          new PitchClass(750_000_000),
          new PitchClass(900_000_000),
          new PitchClass(1_050_000_000))));
          
    System.out.println(
      new ScalePattern(
        new Scale(
          PerfectPitchClasses.UNISON,
          new PitchClass(300_000_000),
          new PitchClass(450_000_000),
          new PitchClass(600_000_000),
          new PitchClass(750_000_000),
          new PitchClass(900_000_000),
          new PitchClass(1_050_000_000))));
          
  }
  
  @Test
  public void tryout7() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.HECTO_CENT;
    
    final ImmutableSet<ScalePattern> scalePatternGroups =
      ScalePatternGenerator.INSTANCE.generate(Scales.EQUAL_TEMPERED_CHROMATIC);
      
    scalePatternGroups.stream().forEach(o -> {
      System.out.println(o);
    });
    
  }
  
  @Test
  public void tryout8() {
    
    FormatSettings.INSTANCE.CENT_FORMAT = CentFormat.HECTO_CENT;
    
    final ImmutableSet<ScalePattern> allScalePatternGroups =
      ScalePatternGenerator.INSTANCE.generate(Scales.EQUAL_TEMPERED_CHROMATIC);
      
    final SetMultimap<ScalePattern, ScalePattern> splitted = LinkedHashMultimap.create();
    final SetMultimap<ScalePattern, ScalePattern> combined = LinkedHashMultimap.create();
    
    for (final ScalePattern d : allScalePatternGroups) {
      for (final PitchClass clazz : EqualTemperedPitchClasses.VALUES_WITH_UNISON) {
        final Set<PitchClass> pitchClazzes = new LinkedHashSet<>(d.getGeneratingScale());
        final Boolean splitHappened = pitchClazzes.add(clazz);
        if (!splitHappened) {
          continue;
        }
        final ScalePattern splitScalePattern = new ScalePattern(new Scale(pitchClazzes));
        splitted.put(d, splitScalePattern);
        combined.put(splitScalePattern, d);
      }
    }
    
    for (final ScalePattern d : allScalePatternGroups) {
      
      final Set<ScalePattern> currentSplitted = splitted.get(d);
      final Set<ScalePattern> currentCombined = combined.get(d);
      
      if (currentSplitted.isEmpty()) {
        System.out.println("No new patterns split: " + d);
      }
      
      if (currentSplitted.size() == 1) {
        System.out.println("1 new patterns split: " + d);
      }
      
      if (currentCombined.isEmpty()) {
        System.out.println("No new patterns combined: " + d);
      }
      
      if (currentCombined.size() == 1) {
        System.out.println("1 new patterns combined: " + d);
      }
      
    }
    
  }
  
}
