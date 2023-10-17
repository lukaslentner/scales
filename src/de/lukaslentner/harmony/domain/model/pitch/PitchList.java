package de.lukaslentner.harmony.domain.model.pitch;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import de.lukaslentner.harmony.domain.model.pitchClass.PitchClassList;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchIntervalList;
import de.lukaslentner.harmony.domain.util.MyList;

public class PitchList extends MyList<Pitch, PitchList> {
  
  public PitchList(final ImmutableList<Pitch> elements) {
    super(elements);
  }
  
  public PitchList(final List<? extends Pitch> elements) {
    this(ImmutableList.<Pitch> copyOf(elements));
  }
  
  public PitchList(final Pitch... elements) {
    this(ImmutableList.<Pitch> copyOf(elements));
  }
  
  @Override
  protected PitchList construct(final List<Pitch> elements) {
    return new PitchList(elements);
  }
  
  @Override
  protected PitchList getThis() {
    return this;
  }
  
  public PitchClassList getClazzList(final Pitch zero) {
    return new PitchClassList(this.stream().map(o -> o.getClazz(zero)).collect(Collectors.toList()));
  }
  
  public PitchIntervalList getIntervalFromEach(final Pitch base) {
    return new PitchIntervalList(this.stream().map(o -> o.getIntervalFrom(base)).collect(Collectors.toList()));
  }
  
  public PitchIntervalList getIntervalToEach(final Pitch aim) {
    return new PitchIntervalList(this.stream().map(o -> o.getIntervalTo(aim)).collect(Collectors.toList()));
  }
  
  public PitchList mirrorEach(final Pitch mirror) {
    return new PitchList(this.stream().map(o -> o.mirror(mirror)).collect(Collectors.toList()));
  }
  
  public PitchList transposeEach(final PitchInterval interval) {
    return new PitchList(this.stream().map(o -> o.transpose(interval)).collect(Collectors.toList()));
  }
  
  public PitchList untransposeEach(final PitchInterval interval) {
    return new PitchList(this.stream().map(o -> o.untranspose(interval)).collect(Collectors.toList()));
  }
  
  public PitchIntervalList getRelativeIntervals() {
    
    final List<PitchInterval> relativeIntervals = new LinkedList<>();
    
    Optional<Pitch> optionalFormerPitch = Optional.empty();
    for (final Pitch pitch : this.innerList) {
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
    
    if (!(other instanceof PitchList)) {
      return false;
    }
    
    final PitchList castedOther = (PitchList) other;
    return this.innerList.equals(castedOther.innerList);
    
  }
  
  @Override
  public int hashCode() {
    return this.innerList.hashCode();
  }
  
  @Override
  public String toString() {
    return "PitchList(" + this.innerList + ")";
  }
  
}
