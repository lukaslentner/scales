package de.lukaslentner.harmony.domain.model.pitch;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSortedSet;

import de.lukaslentner.harmony.domain.model.pitchClass.PitchClassSet;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchIntervalList;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchIntervalSet;
import de.lukaslentner.harmony.domain.util.MySet;

public class PitchSet extends MySet<Pitch, PitchSet> {
  
  public PitchSet(final ImmutableSortedSet<Pitch> elements) {
    super(elements);
  }
  
  public PitchSet(final Collection<? extends Pitch> elements) {
    this(ImmutableSortedSet.<Pitch> copyOf(elements));
  }
  
  public PitchSet(final Pitch... elements) {
    this(ImmutableSortedSet.<Pitch> copyOf(elements));
  }
  
  @Override
  protected PitchSet construct(final Set<Pitch> elements) {
    return new PitchSet(elements);
  }
  
  @Override
  protected PitchSet getThis() {
    return this;
  }
  
  public PitchClassSet getClazzSet(final Pitch zero) {
    return new PitchClassSet(this.stream().map(o -> o.getClazz(zero)).collect(Collectors.toList()));
  }
  
  public PitchIntervalSet getIntervalFromEach(final Pitch base) {
    return new PitchIntervalSet(this.stream().map(o -> o.getIntervalFrom(base)).collect(Collectors.toList()));
  }
  
  public PitchIntervalSet getIntervalToEach(final Pitch aim) {
    return new PitchIntervalSet(this.stream().map(o -> o.getIntervalTo(aim)).collect(Collectors.toList()));
  }
  
  public PitchSet mirrorEach(final Pitch mirror) {
    return new PitchSet(this.stream().map(o -> o.mirror(mirror)).collect(Collectors.toList()));
  }
  
  public PitchSet transposeEach(final PitchInterval interval) {
    return new PitchSet(this.stream().map(o -> o.transpose(interval)).collect(Collectors.toList()));
  }
  
  public PitchSet untransposeEach(final PitchInterval interval) {
    return new PitchSet(this.stream().map(o -> o.untranspose(interval)).collect(Collectors.toList()));
  }
  
  public PitchIntervalList getRelativeIntervals() {
    
    final List<PitchInterval> relativeIntervals = new LinkedList<>();
    
    Optional<Pitch> optionalFormerPitch = Optional.empty();
    for (final Pitch pitch : this.innerSet) {
      if (optionalFormerPitch.isPresent()) {
        relativeIntervals.add(optionalFormerPitch.get().getIntervalTo(pitch));
      }
      optionalFormerPitch = Optional.of(pitch);
    }
    
    return new PitchIntervalList(relativeIntervals);
    
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchSet)) {
      return false;
    }
    
    final PitchSet castedOther = (PitchSet) other;
    return this.innerSet.equals(castedOther.innerSet);
    
  }
  
  @Override
  public int hashCode() {
    return this.innerSet.hashCode();
  }
  
  @Override
  public String toString() {
    return "PitchSet(" + this.innerSet + ")";
  }
  
}
