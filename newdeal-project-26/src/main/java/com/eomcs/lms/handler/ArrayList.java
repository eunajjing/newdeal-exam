package com.eomcs.lms.handler;

import java.util.Arrays;

public class ArrayList {
  final int DEFAULT_CAPACITY = 10;
  int size = 0;
  // 배열은 어떻게 처리하지?
  // 다형적 변수 사용!
  Object[] list;
  
  public ArrayList() {
    list  = new Object[DEFAULT_CAPACITY];
  }
  
  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY) 
      list = new Object[DEFAULT_CAPACITY];
    else
      list = new Object[DEFAULT_CAPACITY];
  }
  
  public Object[] toArray() {
    return Arrays.copyOf(list, size); 
  }
  
  public void add(Object item) {
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }
    
    list[size++] = item;
  }
  
}
