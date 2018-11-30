package com.eomcs.util;

public class QueueIterator<E> implements Iterator<E>{
  Queue<E> queue;
  int count;
  int size;
  
  public QueueIterator(Queue<E> queue) {
    this.queue = queue;
    size = queue.size();
  }
  
  @Override
  public boolean hasNext() {
    return count < size;
  }

  @Override
  public E next() {
    count++;
    return queue.poll();
  }
  
}
