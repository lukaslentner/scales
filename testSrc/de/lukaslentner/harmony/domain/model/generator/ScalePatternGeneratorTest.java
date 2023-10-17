package de.lukaslentner.harmony.domain.model.generator;

import java.util.Set;

import org.junit.Assert;

import com.google.common.collect.ImmutableSet;

import de.lukaslentner.harmony.domain.generator.ScaleGenerator;
import de.lukaslentner.harmony.domain.generator.ScalePatternGenerator;
import de.lukaslentner.harmony.domain.model.scale.Scale;
import de.lukaslentner.harmony.domain.model.scale.ScalePattern;
import de.lukaslentner.harmony.domain.model.scale.Scales;

public class ScalePatternGeneratorTest {
  
  public void testCounts() {
    
    final Set<Scale> scalePatterns = ScaleGenerator.INSTANCE.generate(Scales.EQUAL_TEMPERED_CHROMATIC);
    
    Assert.assertEquals(new Double(Math.pow(2, 11)).intValue(), scalePatterns.size());
    
    final ImmutableSet<ScalePattern> scalePatternGroups =
      ScalePatternGenerator.INSTANCE.generate(Scales.EQUAL_TEMPERED_CHROMATIC);
      
    Assert.assertEquals(351, scalePatternGroups.size());
    
  }
  
}
