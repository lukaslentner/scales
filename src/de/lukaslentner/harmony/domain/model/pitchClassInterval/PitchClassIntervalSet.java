package de.lukaslentner.harmony.domain.model.pitchClassInterval;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSortedSet;

import de.lukaslentner.harmony.domain.model.pitchClass.PitchClass;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClassSet;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchIntervalSet;
import de.lukaslentner.harmony.domain.util.MySet;

public class PitchClassIntervalSet extends MySet<PitchClassInterval, PitchClassIntervalSet> {
  
  public PitchClassIntervalSet(final ImmutableSortedSet<PitchClassInterval> elements) {
    super(elements);
  }
  
  public PitchClassIntervalSet(final Collection<? extends PitchClassInterval> elements) {
    this(ImmutableSortedSet.<PitchClassInterval> copyOf(elements));
  }
  
  public PitchClassIntervalSet(final PitchClassInterval... elements) {
    this(ImmutableSortedSet.<PitchClassInterval> copyOf(elements));
  }
  
  @Override
  protected PitchClassIntervalSet construct(final Set<PitchClassInterval> elements) {
    return new PitchClassIntervalSet(elements);
  }
  
  @Override
  protected PitchClassIntervalSet getThis() {
    return this;
  }
  
  public PitchIntervalSet getPitchIntervalSet() {
    return new PitchIntervalSet(this.stream().map(o -> o.getPitchInterval()).collect(Collectors.toList()));
  }
  
  public PitchClassSet getPitchClassFromEach(final PitchClass base) {
    return new PitchClassSet(this.stream().map(o -> o.getPitchClassFrom(base)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalSet invertEach() {
    return new PitchClassIntervalSet(this.stream().map(o -> o.invert()).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalSet addEach(final PitchClassInterval summandInterval) {
    return new PitchClassIntervalSet(this.stream().map(o -> o.add(summandInterval)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalSet substractEach(final PitchClassInterval minuendInterval) {
    return new PitchClassIntervalSet(this.stream().map(o -> o.substract(minuendInterval)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalSet multiplyEach(final Long factor) {
    return new PitchClassIntervalSet(this.stream().map(o -> o.multiply(factor)).collect(Collectors.toList()));
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchClassIntervalSet)) {
      return false;
    }
    
    final PitchClassIntervalSet castedOther = (PitchClassIntervalSet) other;
    return this.innerSet.equals(castedOther.innerSet);
    
  }
  
  @Override
  public int hashCode() {
    return this.innerSet.hashCode();
  }
  
  @Override
  public String toString() {
    return "PitchClassIntervalSet(" + this.innerSet + ")";
  }
  
}
