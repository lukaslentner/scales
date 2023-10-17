package de.lukaslentner.harmony.domain.model.scale;

public class ScalePattern {
  
  private final Scale generatingScale;
  
  public ScalePattern(final Scale generatingScale) {
    this.generatingScale = generatingScale;
  }
  
  public Scale getGeneratingScale() {
    return this.generatingScale;
  }
  
  public int getScaleSize() {
    return this.generatingScale.size();
  }
  
  public int getModusCount() {
    return this.generatingScale.permutationSet().size();
  }
  
  public int getChromaticPermutationSize() {
    return this.generatingScale.chromaticPermutationSet(Scales.EQUAL_TEMPERED_CHROMATIC).size();
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof ScalePattern)) {
      return false;
    }
    
    final ScalePattern castedOther = (ScalePattern) other;
    return this.generatingScale.permutationSet().equals(castedOther.generatingScale.permutationSet());
    
  }
  
  @Override
  public int hashCode() {
    return this.generatingScale.permutationSet().hashCode();
  }
  
  @Override
  public String toString() {
    return String.format(
      "ScalePattern(scaleSize=%02d; modusCount=%02d; chromaticPermutationSize=%02d; relativeIntervals=%s)",
      this.getScaleSize(),
      this.getModusCount(),
      this.getChromaticPermutationSize(),
      this.getGeneratingScale().getRelativeIntervalsWithOctave());
  }
  
}
