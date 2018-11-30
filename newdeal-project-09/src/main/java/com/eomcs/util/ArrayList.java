package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<T> {
  // T : 어떤 타입
  final int DEFAULT_CAPACITY = 10;
  Object[] elementData;
  // 여기는 T로 쓸 수 없고 Object로 배열 만들기
  int size = 0;
  
  public ArrayList() {
    elementData  = new Object[DEFAULT_CAPACITY];
  }
  
  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY) 
      elementData = new Object[initialCapacity];
    else
      elementData = new Object[DEFAULT_CAPACITY];
  }
  
  @SuppressWarnings("unchecked") // 에러 체크하지 않는다
  public T[] toArray(T[] a) {
    if (a.length < size) {
      return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    }
    System.arraycopy(elementData, 0, a, 0, size);
    if (a.length > size)
      a[size] = null;
    return a;
  }
  
  public void add(T obj) {
    if (size >= elementData.length) {
      int oldCapacity = elementData.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    elementData[size++] = obj;
  }

  public int size() {
    return this.size;
  }
}
