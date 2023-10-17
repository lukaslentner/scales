package de.lukaslentner.harmony.domain.model.pitchInterval;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import de.lukaslentner.harmony.domain.model.pitch.Pitch;
import de.lukaslentner.harmony.domain.model.pitch.PitchList;
import de.lukaslentner.harmony.domain.model.pitchClass.PerfectPitchClasses;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClass;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassIntervalList;
import de.lukaslentner.harmony.domain.model.scale.Scale;
import de.lukaslentner.harmony.domain.util.MyList;

public class PitchIntervalList extends MyList<PitchInterval, PitchIntervalList> {
  
  public PitchIntervalList(final ImmutableList<PitchInterval> elements) {
    super(elements);
  }
  
  public PitchIntervalList(final List<? extends PitchInterval> elements) {
    this(ImmutableList.<PitchInterval> copyOf(elements));
  }
  
  public PitchIntervalList(final PitchInterval... elements) {
    this(ImmutableList.<PitchInterval> copyOf(elements));
  }
  
  @Override
  protected PitchIntervalList construct(final List<PitchInterval> elements) {
    return new PitchIntervalList(elements);
  }
  
  @Override
  protected PitchIntervalList getThis() {
    return this;
  }
  
  public PitchClassIntervalList getClazzList() {
    return new PitchClassIntervalList(this.stream().map(o -> o.getClazz()).collect(Collectors.toList()));
  }
  
  public PitchList getPitchListFromEach(final Pitch base) {
    return new PitchList(this.stream().map(o -> o.getPitchFrom(base)).collect(Collectors.toList()));
  }
  
  public Scale getScaleByRelativeIntervals() {
    
    final Set<PitchClass> pitchClasses = new LinkedHashSet<>();
    
    PitchClass currentPitchClass = PerfectPitchClasses.UNISON;
    pitchClasses.add(currentPitchClass);
    
    for (final PitchInterval pitchInterval : this) {
      currentPitchClass = currentPitchClass.transpose(pitchInterval.getClazz());
      pitchClasses.add(currentPitchClass);
    }
    
    return new Scale(pitchClasses);
    
  }
  
  public PitchIntervalList invertEach() {
    return new PitchIntervalList(this.stream().map(o -> o.invert()).collect(Collectors.toList()));
  }
  
  public PitchIntervalList addEach(final PitchInterval summandInterval) {
    return new PitchIntervalList(this.stream().map(o -> o.add(summandInterval)).collect(Collectors.toList()));
  }
  
  public PitchIntervalList substractEach(final PitchInterval minuendInterval) {
    return new PitchIntervalList(this.stream().map(o -> o.substract(minuendInterval)).collect(Collectors.toList()));
  }
  
  public PitchIntervalList multiplyEach(final Long factor) {
    return new PitchIntervalList(this.stream().map(o -> o.multiply(factor)).collect(Collectors.toList()));
  }
  
  public Integer getPairsCountSmallerThan(final PitchInterval intervalLimit) {
    Integer pairsCount = 0;
    for (Integer intervalIndex = 0; intervalIndex < this.size(); intervalIndex++) {
      final PitchInterval interval1 = this.get(intervalIndex);
      final PitchInterval interval2 = this.get((intervalIndex + 1) % this.size());
      if (interval1.compareTo(intervalLimit) < 0 && interval2.compareTo(intervalLimit) < 0) {
        pairsCount++;
      }
    }
    return pairsCount;
  }
  
  public ImmutableSet<PitchIntervalList> mergePairsSetSmallerThan(final PitchInterval intervalLimit) {
    
    final Set<PitchIntervalList> mergePairsSet = new LinkedHashSet<>();
    
    for (Integer intervalIndex1 = 0; intervalIndex1 < this.size(); intervalIndex1++) {
      final Integer intervalIndex2 = (intervalIndex1 + 1) % this.size();
      final PitchInterval interval1 = this.get(intervalIndex1);
      final PitchInterval interval2 = this.get(intervalIndex2);
      if (interval1.compareTo(intervalLimit) < 0 && interval2.compareTo(intervalLimit) < 0) {
        final List<PitchInterval> newIntervals = new LinkedList<>(this);
        newIntervals.set(intervalIndex1, interval1.add(interval2));
        newIntervals.remove(intervalIndex2);
        mergePairsSet.add(new PitchIntervalList(newIntervals));
      }
    }
    
    return ImmutableSet.copyOf(mergePairsSet);
    
  }
  
  public Boolean getMatch(final Pattern regexPattern) {
    final String heystack = Strings.repeat(Joiner.on(",").join(this), 2);
    return regexPattern.matcher(heystack).matches();
  }
  
  public Integer getCountSmallerThan(final PitchInterval intervalLimit) {
    return (int) this.stream().filter(o -> o.compareTo(intervalLimit) < 0).count();
  }
  
  public Integer getCountGreaterThan(final PitchInterval intervalLimit) {
    return (int) this.stream().filter(o -> o.compareTo(intervalLimit) > 0).count();
  }
  
  public Integer getCountEqualTo(final PitchInterval intervalLimit) {
    return (int) this.stream().filter(o -> o.compareTo(intervalLimit) == 0).count();
  }
  
  @Override
  public boolean equals(final Object other) {
    
    if (this == other) {
      return true;
    }
    
    if (other == null) {
      return false;
    }
    
    if (!(other instanceof PitchIntervalList)) {
      return false;
    }
    
    final PitchIntervalList castedOther = (PitchIntervalList) other;
    return this.innerList.equals(castedOther.innerList);
    
  }
  
  @Override
  public int hashCode() {
    return this.innerList.hashCode();
  }
  
  @Override
  public String toString() {
    return "PitchIntervalList(" + this.innerList + ")";
  }
  
}
