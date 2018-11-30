package com.eomcs.util;

public interface Iterator<E> {
  boolean hasNext();
  // 두 개의 메서드 필요 꺼낼 거 있는지 확인
  // 꺼내기
  E next();
}
