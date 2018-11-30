package com.eomcs.util;

public interface List<T> {
  
  T[] toArray(T[] a);
  void add(T obj);
  T get(int index);
  T set(int index, T obj);
  T remove(int index);
  int size();
  
}
