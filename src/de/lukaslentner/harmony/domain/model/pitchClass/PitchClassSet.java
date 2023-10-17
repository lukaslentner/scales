package de.lukaslentner.harmony.domain.model.pitchClass;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import de.lukaslentner.harmony.domain.model.pitch.Pitch;
import de.lukaslentner.harmony.domain.model.pitch.PitchSet;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassInterval;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassIntervalList;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassIntervalSet;
import de.lukaslentner.harmony.domain.model.pitchInterval.PerfectPitchIntervals;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchIntervalList;
import de.lukaslentner.harmony.domain.model.scale.Scale;
import de.lukaslentner.harmony.domain.util.MySet;

public class PitchClassSet extends MySet<PitchClass, PitchClassSet> {
  
  public PitchClassSet(final ImmutableSortedSet<PitchClass> elements) {
    super(elements);
  }
  
  public PitchClassSet(final Collection<? extends PitchClass> elements) {
    this(ImmutableSortedSet.<PitchClass> copyOf(elements));
  }
  
  public PitchClassSet(final PitchClass... elements) {
    this(ImmutableSortedSet.<PitchClass> copyOf(elements));
  }
  
  @Override
  protected PitchClassSet construct(final Set<PitchClass> elements) {
    return new PitchClassSet(elements);
  }
  
  @Override
  protected PitchClassSet getThis() {
    return this;
  }
  
  public Scale toScale() {
    return new Scale(this);
  }
  
  public PitchSet getPitchEach(final Pitch zero) {
    return new PitchSet(this.stream().map(o -> o.getPitch(zero)).collect(Collectors.toList()));
  }
  
  public PitchSet getPitchEachWithOctave(final Pitch zero) {
    return this.getPitchEach(zero).with(zero.transpose(PerfectPitchIntervals.OCTAVE));
  }
  
  public PitchClassIntervalSet getIntervalFromEach(final PitchClass base) {
    return new PitchClassIntervalSet(this.stream().map(o -> o.getIntervalFrom(base)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalSet getIntervalToEach(final PitchClass aim) {
    return new PitchClassIntervalSet(this.stream().map(o -> o.getIntervalTo(aim)).collect(Collectors.toList()));
  }
  
  public PitchClassSet invertEach() {
    return new PitchClassSet(this.stream().map(o -> o.invert()).collect(Collectors.toList()));
  }
  
  public PitchClassSet transposeEach(final PitchClassInterval interval) {
    return new PitchClassSet(this.stream().map(o -> o.transpose(interval)).collect(Collectors.toList()));
  }
  
  public PitchClassSet untransposeEach(final PitchClassInterval interval) {
    return new PitchClassSet(this.stream().map(o -> o.untranspose(interval)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalList getRelativeIntervals() {
    
    final List<PitchClassInterval> relativeIntervals = new LinkedList<>();
    
    Optional<PitchClass> optionalFormerPitchClass = Optional.empty();
    for (final PitchClass pitchClass : this.innerSet) {
      if (optionalFormerPitchClass.isPresent()) {
        relativeIntervals.add(optionalFormerPitchClass.get().getIntervalTo(pitchClass));
      }
      optionalFormerPitchClass = Optional.of(pitchClass);
    }
    
    return new PitchClassIntervalList(relativeIntervals);
    
  }
  
  public PitchIntervalList getRelativeIntervalsWithOctave() {
    final PitchInterval lastInterval =
      this.last().invert().getIntervalFrom(PerfectPitchClasses.UNISON).getPitchInterval();
    return this.getRelativeIntervals().getPitchIntervalList().append(
      lastInterval.equals(PerfectPitchIntervals.UNISON) ? PerfectPitchIntervals.OCTAVE : lastInterval);
  }
  
  public Boolean isNTonic(final Integer size) {
    return this.size() == size;
  }
  
  public Boolean isLessThanNTonic(final Integer size) {
    return this.size() < size;
  }
  
  public Boolean isMoreThanNTonic(final Integer size) {
    return this.size() > size;
  }
  
  public ImmutableSet<PitchClassSet> permutationSet() {
    
    final Set<PitchClassSet> permutationSet = new LinkedHashSet<>();
    permutationSet.add(this);
    
    PitchClassSet actualPermutation = this;
    while (true) {
      actualPermutation = actualPermutation.rotate();
      if (!permutationSet.add(actualPermutation)) {
        break;
      }
    }
    
    return ImmutableSet.copyOf(permutationSet);
    
  }
  
  public PitchClassSet rotate() {
    
    final Iterator<PitchClass> iterator = this.iterator();
    
    if (!iterator.hasNext()) {
      return this;
    }
    final PitchClass first = iterator.next();
    
    if (!iterator.hasNext()) {
      return this;
    }
    final PitchClass second = iterator.next();
    
    final PitchClassInterval intervalBetweenFirstAndSecond = first.getIntervalTo(second);
    
    return this.untransposeEach(intervalBetweenFirstAndSecond);
    
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchClassSet)) {
      return false;
    }
    
    final PitchClassSet castedOther = (PitchClassSet) other;
    return this.innerSet.equals(castedOther.innerSet);
    
  }
  
  @Override
  public int hashCode() {
    return this.innerSet.hashCode();
  }
  
  @Override
  public String toString() {
    return "PitchClassSet(" + this.innerSet + ")";
  }
  
}
