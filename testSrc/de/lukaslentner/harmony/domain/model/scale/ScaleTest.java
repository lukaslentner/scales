package de.lukaslentner.harmony.domain.model.scale;

import org.junit.Assert;
import org.junit.Test;

public class ScaleTest {
  
  @Test
  public void test() {
    
    final ScalePattern pattern = new ScalePattern(Scales.EQUAL_TEMPERED_MAJOR);
    
    Assert.assertEquals(7, pattern.getModusCount());
    Assert.assertEquals(7, pattern.getScaleSize());
    
  }
  
  @Test
  public void testMerging() {
    
    final Scale chromaticScale = ;
    
    
    
    Assert.assertEquals(7, pattern.getModusCount());
    Assert.assertEquals(7, pattern.getScaleSize());
    
  }
  
}
