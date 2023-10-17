package de.lukaslentner.harmony.domain.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.UnmodifiableIterator;

public abstract class MySet<E, S extends MySet<E, S>> implements NavigableSet<E> {
  
  protected final ImmutableSortedSet<E> innerSet;
  
  public MySet(final ImmutableSortedSet<E> elements) {
    this.innerSet = elements;
  }
  
  protected abstract S construct(final Set<E> elements);
  
  protected abstract S getThis();
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean add(final E e) {
    return this.innerSet.add(e);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean addAll(final Collection<? extends E> newElements) {
    return this.innerSet.addAll(newElements);
  }
  
  @Override
  public E ceiling(final E e) {
    return this.innerSet.ceiling(e);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final void clear() {
    this.innerSet.clear();
  }
  
  @Override
  public Comparator<? super E> comparator() {
    return this.innerSet.comparator();
  }
  
  @Override
  public boolean contains(final Object object) {
    return this.innerSet.contains(object);
  }
  
  @Override
  public boolean containsAll(final Collection<?> c) {
    return this.innerSet.containsAll(c);
  }
  
  @Override
  public UnmodifiableIterator<E> descendingIterator() {
    return this.innerSet.descendingIterator();
  }
  
  @Override
  public ImmutableSortedSet<E> descendingSet() {
    return this.innerSet.descendingSet();
  }
  
  @Override
  public E first() {
    return this.innerSet.first();
  }
  
  @Override
  public E floor(final E e) {
    return this.innerSet.floor(e);
  }
  
  @Override
  public ImmutableSortedSet<E> headSet(final E toElement) {
    return this.innerSet.headSet(toElement);
  }
  
  @Override
  public ImmutableSortedSet<E> headSet(final E toElement, final boolean inclusive) {
    return this.innerSet.headSet(toElement, inclusive);
  }
  
  @Override
  public E higher(final E e) {
    return this.innerSet.higher(e);
  }
  
  @Override
  public boolean isEmpty() {
    return this.innerSet.isEmpty();
  }
  
  @Override
  public UnmodifiableIterator<E> iterator() {
    return this.innerSet.iterator();
  }
  
  @Override
  public E last() {
    return this.innerSet.last();
  }
  
  @Override
  public E lower(final E e) {
    return this.innerSet.lower(e);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final E pollFirst() {
    return this.innerSet.pollFirst();
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final E pollLast() {
    return this.innerSet.pollLast();
  }
  
  public ImmutableSet<S> powerSet() {
    
    final Set<S> powerSet = new LinkedHashSet<>();
    
    if (this.isEmpty()) {
      powerSet.add(this.getThis());
    } else {
      final E firstElement = this.iterator().next();
      final Set<S> subPowerSet = this.without(firstElement).powerSet();
      powerSet.addAll(subPowerSet);
      powerSet.addAll(subPowerSet.stream().map(o -> o.with(firstElement)).collect(Collectors.toList()));
    }
    
    return ImmutableSet.copyOf(powerSet);
    
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean remove(final Object object) {
    return this.innerSet.remove(object);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean removeAll(final Collection<?> oldElements) {
    return this.innerSet.removeAll(oldElements);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean retainAll(final Collection<?> elementsToKeep) {
    return this.innerSet.retainAll(elementsToKeep);
  }
  
  @Override
  public int size() {
    return this.innerSet.size();
  }
  
  @Override
  public ImmutableSortedSet<E> subSet(
    final E fromElement,
    final boolean fromInclusive,
    final E toElement,
    final boolean toInclusive) {
    return this.innerSet.subSet(fromElement, fromInclusive, toElement, toInclusive);
  }
  
  @Override
  public ImmutableSortedSet<E> subSet(final E fromElement, final E toElement) {
    return this.innerSet.subSet(fromElement, toElement);
  }
  
  @Override
  public ImmutableSortedSet<E> tailSet(final E fromElement) {
    return this.innerSet.tailSet(fromElement);
  }
  
  @Override
  public ImmutableSortedSet<E> tailSet(final E fromElement, final boolean inclusive) {
    return this.innerSet.tailSet(fromElement, inclusive);
  }
  
  @Override
  public final Object[] toArray() {
    return this.innerSet.toArray();
  }
  
  @Override
  public final <T> T[] toArray(final T[] other) {
    return this.innerSet.toArray(other);
  }
  
  public S with(final E element) {
    
    final Set<E> elements = new LinkedHashSet<>(this);
    elements.add(element);
    
    return this.construct(elements);
    
  }
  
  public S without(final E E) {
    
    final Set<E> elements = new LinkedHashSet<>(this);
    elements.remove(E);
    
    return this.construct(elements);
    
  }
  
}
