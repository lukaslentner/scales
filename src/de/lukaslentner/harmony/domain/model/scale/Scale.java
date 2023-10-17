package de.lukaslentner.harmony.domain.model.scale;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import de.lukaslentner.harmony.domain.model.pitchClass.PerfectPitchClasses;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClass;
import de.lukaslentner.harmony.domain.model.pitchClass.PitchClassSet;
import de.lukaslentner.harmony.domain.model.pitchClassInterval.PitchClassInterval;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;

public class Scale extends PitchClassSet {
  
  private final ImmutableSet<PitchClassSet> permutationSet;
  
  public Scale(final ImmutableSortedSet<PitchClass> elements) {
    super(elements);
    this.permutationSet = super.permutationSet();
    if (!this.contains(PerfectPitchClasses.UNISON)) {
      throw new IllegalArgumentException("Scale must contain the unison");
    }
  }
  
  public Scale(final Collection<? extends PitchClass> elements) {
    this(ImmutableSortedSet.<PitchClass> copyOf(elements));
  }
  
  public Scale(final PitchClass... elements) {
    this(ImmutableSortedSet.<PitchClass> copyOf(elements));
  }
  
  @Override
  public ImmutableSet<PitchClassSet> permutationSet() {
    return this.permutationSet;
  }
  
  public ImmutableSet<Scale> getModifications(final PitchClassInterval modificationInterval) {
    
    final Set<Scale> modifications = new LinkedHashSet<>();
    
    for (final PitchClass pitchClassToModify : this) {
      final List<PitchClass> modification =
        this
          .stream()
          .map(o -> pitchClassToModify.equals(o) ? o.transpose(modificationInterval) : o)
          .map(
            o -> pitchClassToModify.equals(PerfectPitchClasses.UNISON) ? o.transpose(modificationInterval.invert()) : o)
          .collect(Collectors.toList());
      modifications.add(new PitchClassSet(modification).toScale());
    }
    
    return ImmutableSet.copyOf(modifications);
    
  }
  
  public ImmutableSet<ImmutableList<Scale>> getSelfContainedModificationBranches(
    final PitchClassInterval modificationInterval) {
    
    ImmutableSet<ImmutableList<Scale>> currentBranches = ImmutableSet.of(ImmutableList.of(this));
    
    while (true) {
      
      final Set<ImmutableList<Scale>> newBranches = new LinkedHashSet<>();
      for (final ImmutableList<Scale> currentBranch : currentBranches) {
        
        final Scale currentLeaf = currentBranch.get(currentBranch.size() - 1);
        final ImmutableSet<Scale> currentLeafModifications = currentLeaf.getModifications(modificationInterval);
        
        final Set<Scale> newLeafs = new LinkedHashSet<>(currentLeafModifications);
        newLeafs.retainAll(this.permutationSet());
        
        if (newLeafs.isEmpty()) {
          return ImmutableSet.of();
        }
        
        newLeafs.removeAll(currentBranch);
        
        for (final Scale newLeaf : newLeafs) {
          newBranches.add(new ImmutableList.Builder<Scale>().addAll(currentBranch).add(newLeaf).build());
        }
        
      }
      
      if (newBranches.isEmpty()) {
        break;
      } else {
        currentBranches = ImmutableSet.copyOf(newBranches);
      }
      
    }
    
    return currentBranches;
    
  }
  
  public ImmutableSet<PitchClassSet> chromaticPermutationSet(final Scale chroma) {
    
    final Set<PitchClassSet> permutationSet = new LinkedHashSet<>();
    permutationSet.add(this);
    
    PitchClassSet actualPermutation = this;
    for (final PitchClassInterval interval : chroma.getRelativeIntervals()) {
      actualPermutation = actualPermutation.transposeEach(interval);
      if (!permutationSet.add(actualPermutation)) {
        break;
      }
    }
    
    return ImmutableSet.copyOf(permutationSet);
    
  }
  
  public ImmutableSet<Scale> mergePairsSetSmallerThan(final PitchInterval intervalLimit) {
    return ImmutableSet.copyOf(
      this
        .getRelativeIntervalsWithOctave()
        .mergePairsSetSmallerThan(intervalLimit)
        .stream()
        .map(o -> o.getScaleByRelativeIntervals())
        .collect(Collectors.toList()));
  }
  
  public ImmutableSet<ImmutableList<Scale>> getBranches(final Function<Scale, ImmutableSet<Scale>> modification) {

    ImmutableSet<ImmutableList<Scale>> currentBranches = ImmutableSet.of(ImmutableList.of(this));
    
    while (true) {
      sss
      final Set<ImmutableList<Scale>> newBranches = new LinkedHashSet<>();
      for (final ImmutableList<Scale> currentBranch : currentBranches) {
        
        final Scale currentLeaf = currentBranch.get(currentBranch.size() - 1);
        final ImmutableSet<Scale> currentLeafModifications = modification.apply(currentLeaf);
        
        final Set<Scale> newLeafs = new LinkedHashSet<>(currentLeafModifications);
        newLeafs.retainAll(this.permutationSet());
        
        if (newLeafs.isEmpty()) {
          return ImmutableSet.of();
        }
        
        newLeafs.removeAll(currentBranch);
        
        for (final Scale newLeaf : newLeafs) {
          newBranches.add(new ImmutableList.Builder<Scale>().addAll(currentBranch).add(newLeaf).build());
        }
        
      }
      
      if (newBranches.isEmpty()) {
        break;
      } else {
        currentBranches = ImmutableSet.copyOf(newBranches);
      }
      
    }
    
    return currentBranches;
    
  }
  
}
