// For week 9
// sestoft@itu.dk * 2015-10-29

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class TestHashedList {
  public static void main(String[] args) {
    

  }
}

class HashedList<T> {
  // Invariant: The list contains no duplicates and itemSet is exactly
  // the set of items present in the list; hence itemSet.size()==itemList.size() 
  private final Set<T> itemSet = new HashSet<>();
  private final List<T> itemList = new ArrayList<>();

  public boolean contains(T x) {
    return itemSet.contains(x);
  }

  public T get(int i) {
    return itemList.get(i);
  }

  public boolean add(T x) {
    if (itemSet.contains(x))
      return false;
    else {
      itemList.add(x);
      itemSet.add(x);
      return true;
    }
  }

  public boolean remove(T x) {
    if (itemSet.contains(x))
      return false;
    else {
      itemList.remove(x);
      itemSet.remove(x);
      return true;
    }
  }

  public T set(int i, T x) {
    T old = itemList.get(i);
    itemSet.remove(old);
    itemSet.add(x);
    itemList.set(i, x);
    return old;
  }

  public void forEach(Consumer<T> cons) {
    for (T x : itemList)
      cons.accept(x);
  }

  public void addAllTo(HashedList<T> other) {
    forEach(other::add);
  }

  // Internal utility to check consistency
  public boolean check() {
    if (itemSet.size() != itemList.size())
      return false;
    for (T x : itemList) 
      if (!itemSet.contains(x))
	return false;
    return true;
  }
}
