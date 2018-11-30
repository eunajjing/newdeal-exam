package com.eomcs.util;

public class Stack<E> extends LinkedList<E> implements Cloneable {
  // Cloneable는 clone을 쓰기 위함
  // clone은 object의 메서드이나 구현을 하기 위해서는 Cloneable을 상속 받아야 함
  // Cloneable에서 실제로 구현할 메서드는 없으나, clone이 된다는 표기 역할을 함
  private int maxSize;
  
  public Stack() {
    maxSize = 100;
  }
  
  public Stack(int maxSize) {
    this.maxSize = maxSize;
  }
  
  @Override
  public Stack<E> clone() {
    Stack<E> temp = new Stack<>();
    for (int i = 0; i < size(); i++) {
      temp.add(get(i));
    }
    return (Stack<E>) temp;
  }
  // 객체를 생성해서 반복문 돌려서 넣어준 것
  
  public void push(E value) {
    if (size() == maxSize)
      remove(0);
    add(value);
  }
  
  public E pop() {
    return remove(size() - 1);
  }
  
/*  public Iterator<E> iterator() {
    return new IteratorImpl<>() {
      // 이너 익명 클래스로도 만들 수 있다.
      // 슈퍼 클래스의 기본 생성자가 호출되며
      // 위에서 구현한 메서드 정의가 그대로 들어온다.
    }
  }*/
  
  public Iterator<E> iterator() {
    return new IteratorImpl<>(); // 중첩 클래스가 실행되기 전에 인스턴스 블록이 실행될 것이기 때문에
    // 게다가 이너 클래스는 외부 클래스에 접근이 가능하기 때문에 굳이 객체 클론해서 보내지 않아도 됨
  }
  
  // 중첩 클래스의 사용
  // 어차피 stack에서만 쓸 것이기 때문에 이렇게 구현
  class IteratorImpl<T> implements Iterator<T> {
    
    // 인스턴스 블록은
    Stack<?> stack;
    int count;
    int size;
    // 이너 클래스는 외부 클래스의 멤버 변수들과 메서드에게 접근이 가능하다
    
    
    {
      // 인스턴스 블록
      // 앞에 아무 것도 없는 것이 특징
      // 인스턴스 생성자 호출 전에 먼저 실행되는 블록
      // 클래스 안에서 {} 하면 인스턴스 블록
      
      // static 블록은 클래스가 로딩될 때 실행됨 
      // 이곳의 this는 이너 클래스의 객체 주소를 가리킨다.
      // 만약 외부 클래스의 객체 주소를 말하고자 한다면
      // 클래스명.this로 시작해야 한다.
      this.stack = Stack.this.clone();
    }
    
    @Override
    public boolean hasNext() {
      return count < Stack.this.size();
    }

    @Override
    public T next() {
      count++;
      return (T) this.stack.pop();
      // 중첩 클래스 안이라서 강제 형변환 시킴
    }
  }
  
  /*
  public static void main(String[] args) throws Exception {
    Stack<String> stack = new Stack<>();
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    stack.push("ddd");
    stack.push("eee");
    stack.push("fff");
    
    Stack<String> temp1 = stack.clone();
    while (temp1.size() > 0) {
      System.out.println(temp1.pop());
    }
    System.out.println("----------------------");
    
    Stack<String> temp2 = stack.clone();
    while (temp2.size() > 0) {
      System.out.println(temp2.pop());
    }
  }*/
}
