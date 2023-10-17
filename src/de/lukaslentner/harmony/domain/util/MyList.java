package de.lukaslentner.harmony.domain.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

public abstract class MyList<E, S extends MyList<E, S>> implements List<E> {
  
  protected final ImmutableList<E> innerList;
  
  public MyList(final ImmutableList<E> elements) {
    this.innerList = elements;
  }
  
  protected abstract S construct(final List<E> elements);
  
  protected abstract S getThis();
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean add(final E e) {
    return this.innerList.add(e);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public void add(final int index, final E element) {
    this.innerList.add(index, element);
    
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean addAll(final Collection<? extends E> newElements) {
    return this.innerList.addAll(newElements);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public boolean addAll(final int index, final Collection<? extends E> c) {
    return this.innerList.addAll(index, c);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final void clear() {
    this.innerList.clear();
  }
  
  @Override
  public boolean contains(final Object object) {
    return this.innerList.contains(object);
  }
  
  @Override
  public boolean containsAll(final Collection<?> c) {
    return this.innerList.containsAll(c);
  }
  
  @Override
  public E get(final int index) {
    return this.innerList.get(index);
  }
  
  @Override
  public int indexOf(final Object o) {
    return this.innerList.indexOf(o);
  }
  
  @Override
  public boolean isEmpty() {
    return this.innerList.isEmpty();
  }
  
  @Override
  public UnmodifiableIterator<E> iterator() {
    return this.innerList.iterator();
  }
  
  @Override
  public int lastIndexOf(final Object o) {
    return this.innerList.lastIndexOf(o);
  }
  
  @Override
  public ListIterator<E> listIterator() {
    return this.innerList.listIterator();
  }
  
  @Override
  public ListIterator<E> listIterator(final int index) {
    return this.innerList.listIterator(index);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public E remove(final int index) {
    return this.innerList.remove(index);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean remove(final Object object) {
    return this.innerList.remove(object);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean removeAll(final Collection<?> oldElements) {
    return this.innerList.removeAll(oldElements);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public final boolean retainAll(final Collection<?> elementsToKeep) {
    return this.innerList.retainAll(elementsToKeep);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public E set(final int index, final E element) {
    return this.innerList.set(index, element);
  }
  
  @Override
  public int size() {
    return this.innerList.size();
  }
  
  @Override
  public List<E> subList(final int fromIndex, final int toIndex) {
    return this.innerList.subList(fromIndex, toIndex);
  }
  
  @Override
  public final Object[] toArray() {
    return this.innerList.toArray();
  }
  
  @Override
  public final <T> T[] toArray(final T[] other) {
    return this.innerList.toArray(other);
  }
  
  public S append(final E element) {
    
    final List<E> elements = new LinkedList<>(this);
    elements.add(element);
    
    return this.construct(elements);
    
  }
  
}
