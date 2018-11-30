package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Lesson;

public class LessonList {
  
  static final int LENGTH = 10;
  private Lesson[] list;
  private int size = 0;

  public Lesson[] toArray() {
    return Arrays.copyOf(list, size);
  }
  
  void add(Lesson lesson) {
    if (size >= list.length) {
      int oldLength = list.length;
      // int newCapacity = oldLength + (int)(oldLength + 0.5);
      // 만약 배열의 크기만큼 데이터가 찼다면
      // 새 배열을 만들어서 복제를 해야함
      // 그래서 새 배열을 기존 배열의 1.5배로 늘려준다
      // cpu는 부동 소수점 연산이 복잡함
      // 그래서 이렇게 쓴다
      int newCapacity = oldLength + oldLength >> 1;
      // 소수점을 좌측으로 이동해 우측의 소수점을 버린다
      // 비트 이동 연산자 사용
      list = Arrays.copyOf(list, newCapacity);
    }
    list[size++] = lesson;
  }
  
  public LessonList() {
    list = new Lesson[LENGTH];
  }
  
  public LessonList(int initialCapacity) {
    if (initialCapacity > LENGTH) {
      list = new Lesson[initialCapacity];
    } else {
      list = new Lesson[LENGTH];
    }
  }

}
