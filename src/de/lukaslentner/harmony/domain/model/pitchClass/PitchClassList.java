package de.lukaslentner.harmony.domain.model.pitchClass;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import de.lukaslentner.harmony.domain.model.pitch.Pitch;
import de.lukaslentner.harmony.domain.model.pitch.PitchList;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassInterval;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassIntervalList;
import de.lukaslentner.harmony.domain.model.pitchInterval.PerfectPitchIntervals;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchIntervalList;
import de.lukaslentner.harmony.domain.model.scale.Scale;
import de.lukaslentner.harmony.domain.util.MyList;

public class PitchClassList extends MyList<PitchClass, PitchClassList> {
  
  public PitchClassList(final ImmutableList<PitchClass> elements) {
    super(elements);
  }
  
  public PitchClassList(final List<? extends PitchClass> elements) {
    this(ImmutableList.<PitchClass> copyOf(elements));
  }
  
  public PitchClassList(final PitchClass... elements) {
    this(ImmutableList.<PitchClass> copyOf(elements));
  }
  
  @Override
  protected PitchClassList construct(final List<PitchClass> elements) {
    return new PitchClassList(elements);
  }
  
  @Override
  protected PitchClassList getThis() {
    return this;
  }
  
  public Scale toScale() {
    return new Scale(this);
  }
  
  public PitchList getPitchEach(final Pitch zero) {
    return new PitchList(this.stream().map(o -> o.getPitch(zero)).collect(Collectors.toList()));
  }
  
  public PitchList getPitchEachWithOctave(final Pitch zero) {
    return this.getPitchEach(zero).append(zero.transpose(PerfectPitchIntervals.OCTAVE));
  }
  
  public PitchClassIntervalList getIntervalFromEach(final PitchClass base) {
    return new PitchClassIntervalList(this.stream().map(o -> o.getIntervalFrom(base)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalList getIntervalToEach(final PitchClass aim) {
    return new PitchClassIntervalList(this.stream().map(o -> o.getIntervalTo(aim)).collect(Collectors.toList()));
  }
  
  public PitchClassList invertEach() {
    return new PitchClassList(this.stream().map(o -> o.invert()).collect(Collectors.toList()));
  }
  
  public PitchClassList transposeEach(final PitchClassInterval interval) {
    return new PitchClassList(this.stream().map(o -> o.transpose(interval)).collect(Collectors.toList()));
  }
  
  public PitchClassList untransposeEach(final PitchClassInterval interval) {
    return new PitchClassList(this.stream().map(o -> o.untranspose(interval)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalList getRelativeIntervals() {
    
    final List<PitchClassInterval> relativeIntervals = new LinkedList<>();
    
    Optional<PitchClass> optionalFormerPitchClass = Optional.empty();
    for (final PitchClass pitchClass : this.innerList) {
      if (optionalFormerPitchClass.isPresent()) {
        relativeIntervals.add(optionalFormerPitchClass.get().getIntervalTo(pitchClass));
      }
      optionalFormerPitchClass = Optional.of(pitchClass);
    }
    
    return new PitchClassIntervalList(relativeIntervals);
    
  }
  
  public PitchIntervalList getRelativeIntervalsWithOctave() {
    if (this.isEmpty()) {
      return new PitchIntervalList();
    }
    final PitchInterval lastInterval =
      this.get(this.size() - 1).invert().getIntervalFrom(PerfectPitchClasses.UNISON).getPitchInterval();
    return this.getRelativeIntervals().getPitchIntervalList().append(
      lastInterval.equals(PerfectPitchIntervals.UNISON) ? PerfectPitchIntervals.OCTAVE : lastInterval);
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchClassList)) {
      return false;
    }
    
    final PitchClassList castedOther = (PitchClassList) other;
    return this.innerList.equals(castedOther.innerList);
    
  }
  
  @Override
  public int hashCode() {
    return this.innerList.hashCode();
  }
  
  @Override
  public String toString() {
    return "PitchClassList(" + this.innerList + ")";
  }
  
}
