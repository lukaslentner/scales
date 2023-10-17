package de.lukaslentner.harmony.domain.model.pitchInterval;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSortedSet;

import de.lukaslentner.harmony.domain.model.pitch.Pitch;
import de.lukaslentner.harmony.domain.model.pitch.PitchSet;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassIntervalSet;
import de.lukaslentner.harmony.domain.util.MySet;

public class PitchIntervalSet extends MySet<PitchInterval, PitchIntervalSet> {
  
  public PitchIntervalSet(final ImmutableSortedSet<PitchInterval> elements) {
    super(elements);
  }
  
  public PitchIntervalSet(final Collection<? extends PitchInterval> elements) {
    this(ImmutableSortedSet.<PitchInterval> copyOf(elements));
  }
  
  public PitchIntervalSet(final PitchInterval... elements) {
    this(ImmutableSortedSet.<PitchInterval> copyOf(elements));
  }
  
  @Override
  protected PitchIntervalSet construct(final Set<PitchInterval> elements) {
    return new PitchIntervalSet(elements);
  }
  
  @Override
  protected PitchIntervalSet getThis() {
    return this;
  }
  
  public PitchClassIntervalSet getClazzSet() {
    return new PitchClassIntervalSet(this.stream().map(o -> o.getClazz()).collect(Collectors.toList()));
  }
  
  public PitchSet getPitchSetFromEach(final Pitch base) {
    return new PitchSet(this.stream().map(o -> o.getPitchFrom(base)).collect(Collectors.toList()));
  }
  
  public PitchIntervalSet invertEach() {
    return new PitchIntervalSet(this.stream().map(o -> o.invert()).collect(Collectors.toList()));
  }
  
  public PitchIntervalSet addEach(final PitchInterval summandInterval) {
    return new PitchIntervalSet(this.stream().map(o -> o.add(summandInterval)).collect(Collectors.toList()));
  }
  
  public PitchIntervalSet substractEach(final PitchInterval minuendInterval) {
    return new PitchIntervalSet(this.stream().map(o -> o.substract(minuendInterval)).collect(Collectors.toList()));
  }
  
  public PitchIntervalSet multiplyEach(final Long factor) {
    return new PitchIntervalSet(this.stream().map(o -> o.multiply(factor)).collect(Collectors.toList()));
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchIntervalSet)) {
      return false;
    }
    
    final PitchIntervalSet castedOther = (PitchIntervalSet) other;
    return this.innerSet.equals(castedOther.innerSet);
    
  }
  
  @Override
  public int hashCode() {
    return this.innerSet.hashCode();
  }
  
  @Override
  public String toString() {
    return "PitchIntervalSet(" + this.innerSet + ")";
  }
  
}
