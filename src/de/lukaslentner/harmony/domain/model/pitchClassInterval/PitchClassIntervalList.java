package de.lukaslentner.harmony.domain.model.pitchClassInterval;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import de.lukaslentner.harmony.domain.model.pitchClass.PitchClass;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClassList;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchIntervalList;
import de.lukaslentner.harmony.domain.util.MyList;

public class PitchClassIntervalList extends MyList<PitchClassInterval, PitchClassIntervalList> {
  
  public PitchClassIntervalList(final ImmutableList<PitchClassInterval> elements) {
    super(elements);
  }
  
  public PitchClassIntervalList(final List<? extends PitchClassInterval> elements) {
    this(ImmutableList.<PitchClassInterval> copyOf(elements));
  }
  
  public PitchClassIntervalList(final PitchClassInterval... elements) {
    this(ImmutableList.<PitchClassInterval> copyOf(elements));
  }
  
  @Override
  protected PitchClassIntervalList construct(final List<PitchClassInterval> elements) {
    return new PitchClassIntervalList(elements);
  }
  
  @Override
  protected PitchClassIntervalList getThis() {
    return this;
  }
  
  public PitchIntervalList getPitchIntervalList() {
    return new PitchIntervalList(this.stream().map(o -> o.getPitchInterval()).collect(Collectors.toList()));
  }
  
  public PitchClassList getPitchClassFromEach(final PitchClass base) {
    return new PitchClassList(this.stream().map(o -> o.getPitchClassFrom(base)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalList invertEach() {
    return new PitchClassIntervalList(this.stream().map(o -> o.invert()).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalList addEach(final PitchClassInterval summandInterval) {
    return new PitchClassIntervalList(this.stream().map(o -> o.add(summandInterval)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalList substractEach(final PitchClassInterval minuendInterval) {
    return new PitchClassIntervalList(
      this.stream().map(o -> o.substract(minuendInterval)).collect(Collectors.toList()));
  }
  
  public PitchClassIntervalList multiplyEach(final Long factor) {
    return new PitchClassIntervalList(this.stream().map(o -> o.multiply(factor)).collect(Collectors.toList()));
  }
  
  public Integer getPairsCountSmallerThan(final PitchClassInterval intervalLimit) {
    Integer pairsCount = 0;
    for (Integer intervalIndex = 0; intervalIndex < this.size(); intervalIndex++) {
      final PitchClassInterval interval1 = this.get(intervalIndex);
      final PitchClassInterval interval2 = this.get((intervalIndex + 1) % this.size());
      if (interval1.compareTo(intervalLimit) < 0 && interval2.compareTo(intervalLimit) < 0) {
        pairsCount++;
      }
    }
    return pairsCount;
  }
  
  public Boolean getMatch(final Pattern regexPattern) {
    final String heystack = Strings.repeat(Joiner.on(",").join(this), 2);
    return regexPattern.matcher(heystack).matches();
  }
  
  public Integer getCountSmallerThan(final PitchClassInterval intervalLimit) {
    return (int) this.stream().filter(o -> o.compareTo(intervalLimit) < 0).count();
  }
  
  public Integer getCountGreaterThan(final PitchClassInterval intervalLimit) {
    return (int) this.stream().filter(o -> o.compareTo(intervalLimit) > 0).count();
  }
  
  public Integer getCountEqualTo(final PitchClassInterval intervalLimit) {
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
    
    if (!(other instanceof PitchClassIntervalList)) {
      return false;
    }
    
    final PitchClassIntervalList castedOther = (PitchClassIntervalList) other;
    return this.innerList.equals(castedOther.innerList);
    
  }
  
  @Override
  public int hashCode() {
    return this.innerList.hashCode();
  }
  
  @Override
  public String toString() {
    return "PitchClassIntervalList(" + this.innerList + ")";
  }
  
}
