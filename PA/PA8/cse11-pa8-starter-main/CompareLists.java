import tester.*;
import java.util.List;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

// Comparators
class PointCompare implements Comparator<Point> {
  public int compare(Point p1, Point p2) {
    if(p1.y < p2.y) {
      return -1;
    } else if(p1.y > p2.y) {
      return 1;
    } else {
      if(p1.x < p2.x) {
        return -1;
      } else if(p1.x > p2.x) {
        return 1;
      } else {
        return 0;
      }
    }
  }
}

class PointDistanceCompare implements Comparator<Point> {
  public int compare(Point p1, Point p2) {
    if(Math.pow(p1.x, 2) + Math.pow(p1.y, 2) < Math.pow(p2.x, 2) + Math.pow(p2.y, 2)) {
      return -1;
    } else if(Math.pow(p1.x, 2) + Math.pow(p1.y, 2) > Math.pow(p2.x, 2) + Math.pow(p2.y, 2)) {
      return 1;
    } else {
      return 0;
    }
  }
}

class StringCompare implements Comparator<String> {
  public int compare(String s1, String s2) {
    return s1.compareTo(s2);
  }
}

class StringLengthCompare implements Comparator<String> {
  public int compare(String s1, String s2) {
    if(s1.length() < s2.length()) {
      return -1;
    } else if(s1.length() > s2.length()) {
      return 1;
    } else {
      return 0;
    }
  }
}

class BooleanCompare implements Comparator<Boolean> {
  public int compare(Boolean b1, Boolean b2) {
    if(b1 == true && b2 == false) {
      return 1;
    } else if(b1 == false && b2 == true) {
      return -1;
    } else {
      return 0;
    }
  }
}

class TestComparators {
  void testComparators(Tester t) {
    Point p1 = new Point(5, 8);
    Point p2 = new Point(9, 4);

    String s1 = "kasdjnu";
    String s2 = "sfigshoru";

    boolean b1 = true;
    boolean b2 = false;

    PointCompare t1 = new PointCompare();
    t.checkExpect(t1.compare(p1, p2), 1);
    t.checkExpect(t1.compare(p1, p1), 0);
    t.checkExpect(t1.compare(p2, p1), -1);
    t.checkExpect(t1.compare(p2, p2), 0);
    
    PointDistanceCompare t2 = new PointDistanceCompare();
    t.checkExpect(t2.compare(p1, p2), -1);
    t.checkExpect(t2.compare(p1, p1), 0);
    t.checkExpect(t2.compare(p2, p1), 1);
    t.checkExpect(t2.compare(p2, p2), 0);

    StringCompare t3 = new StringCompare();
    t.checkExpect(t3.compare(s1, s2), -8);
    t.checkExpect(t3.compare(s1, s1), 0);
    t.checkExpect(t3.compare(s2, s1), 8);
    t.checkExpect(t3.compare(s2, s2), 0);

    StringLengthCompare t4 = new StringLengthCompare();
    t.checkExpect(t4.compare(s1, s2), -1);
    t.checkExpect(t4.compare(s1, s1), 0);
    t.checkExpect(t4.compare(s2, s1), 1);
    t.checkExpect(t4.compare(s2, s2), 0);

    BooleanCompare t5 = new BooleanCompare();
    t.checkExpect(t5.compare(b1, b2), 1);
    t.checkExpect(t5.compare(b1, b1), 0);
    t.checkExpect(t5.compare(b2, b1), -1);
    t.checkExpect(t5.compare(b2, b2), 0);
  }
}

// List Methods
class CompareLists {
  <E> E minimum(List<E> list, Comparator<E> comp) {
    if(list.size() == 0) {
      return null;
    } else {
      E smallest = list.get(0);
      for(E e : list) {
        if(comp.compare(e, smallest) < 0) {
          smallest = e;
        }
      }
      return smallest;
    }
  }

  <E> E minimum(E[] array, Comparator<E> comp) {
    if(array.length == 0) {
      return null;
    } else {
      E smallest = array[0];
      for(E e : array) {
        if(comp.compare(e, smallest) < 0) {
          smallest = e;
        }
      }
      return smallest;
    }
  }

  <E> List<E> greaterThan(List<E> list, Comparator<E> comp, E compareTo) {
    if(list.size() == 0) {
      return null;
    } else {
      List<E> greater = new ArrayList<>();
      for(E e : list) {
        if(comp.compare(e, compareTo) > 0) {
          greater.add(e);
        }
      }
      return greater;
    }
  }

  <E> boolean inOrder(List<E> list, Comparator<E> comp) throws IllegalArgumentException {
    if(list.size() < 2) {
      return false;
    } else {
      if(list.contains(null)) {
        throw new IllegalArgumentException("null value in list");
      }
      int t = 1;
      for(int i = 0; i < list.size() - 1; i ++) {
        if(comp.compare(list.get(i), list.get(i + 1)) > 0) {
          t = 0;
        }
      }
      if(t == 1) {
        return true;
      } else {
        return false;
      }
    }
  }

  <E> boolean inOrder(E[] array, Comparator<E> comp) throws IllegalArgumentException {
    if(array.length < 2) {
      return false;
    } else {
      if(Arrays.asList(array).contains(null)) {
        throw new IllegalArgumentException("null value in array");
      }
      int t = 1;
      for(int i = 0; i < array.length - 1; i ++) {
        if(comp.compare(array[i], array[i + 1]) > 0) {
          t = 0;
        }
      }
      if(t == 1) {
        return true;
      } else {
        return false;
      }
    }
  }

  <E> List<E> merge(Comparator<E> comp, List<E> list1, List<E> list2) throws IllegalArgumentException {
    List<E> merged = new ArrayList<>();
    int j = 0;
    int k = 0;
    for(int i = 0; i < list1.size() + list2.size(); i ++) {
      while(j < list1.size() && k < list2.size()) {
        if(list1.contains(null)) {
          throw new IllegalArgumentException("null value in first list");
        } else if(list2.contains(null)) {
          throw new IllegalArgumentException("null value in second list");
        } else {
          if(comp.compare(list1.get(j), list2.get(k)) > 0) {
            merged.add(list2.get(k));
            k ++;
          } else {
            merged.add(list1.get(j));
            j ++;
          }
        }
      }
      if(j == list1.size()) {
        while(k < list2.size()) {
          merged.add(list2.get(k));
          k ++;
        }
      } else if(k == list2.size()) {
        while(j < list1.size()){
          merged.add(list1.get(j));
          j ++;
        }
      }
    }
    return merged;
  }
}

class TestListMethods {
  void testListMethods(Tester t) {
    // Build basic objects
    Point p1 = new Point(5, 8);
    Point p2 = new Point(9, 4);
    Point p3 = new Point(8, 8);
    String s1 = "dlfkjrov";
    String s2 = "csbdiu";
    String s3 = "asdffghjkjhjk";
    boolean b1 = true;
    boolean b2 = false;

    // Build lists
    List<Point> l1 = new ArrayList<>();
    l1.add(p1);
    l1.add(p2);
    l1.add(p3);
    List<String> l2 = new ArrayList<>();
    l2.add(s1);
    l2.add(s2);
    l2.add(s3);
    List<Boolean> l3 = new ArrayList<>();
    l3.add(b1);
    l3.add(b2);
    l3.add(b1);
    List<Point> l4 = new ArrayList<>();
    l4.add(p3);
    l4.add(p2);
    l4.add(p1);
    List<String> l5 = new ArrayList<>();
    l5.add(s3);
    l5.add(s2);
    l5.add(s1);
    List<Boolean> l6 = new ArrayList<>();
    l6.add(b2);
    l6.add(b2);
    l6.add(b1);

    List<Point> lwe = new ArrayList<>();
    lwe.add(p1);
    lwe.add(null);
    lwe.add(p2);
    lwe.add(p3);

    // Build arrays
    Point[] a1 = {p1, p2, p3};
    String[] a2 = {s1, s2, s3};
    Boolean[] a3 = {b1, b2, b1};

    String[] awe = {s1, null, s2, s3};
    List<String[]> awel = new ArrayList<>();
    awel.add(awe);

    // Build comparator<E> objects
    Comparator<Point> comp1 = new PointCompare();
    Comparator<Point> comp2 = new PointDistanceCompare();
    Comparator<String> comp3 = new StringCompare();
    Comparator<String> comp4 = new StringLengthCompare();
    Comparator<Boolean> comp5 = new BooleanCompare();

    // Test minimum (for list)
    CompareLists t1 = new CompareLists();
    CompareLists t2 = new CompareLists();
    CompareLists t3 = new CompareLists();

    t.checkExpect(t1.minimum(l1, comp2), p1);
    t.checkExpect(t2.minimum(l2, comp3), s3);
    t.checkExpect(t3.minimum(l3, comp5), b2);

    // Test minimum (for array)
    CompareLists t4 = new CompareLists();
    CompareLists t5 = new CompareLists();
    CompareLists t6 = new CompareLists();

    t.checkExpect(t4.minimum(a1, comp1), p2);
    t.checkExpect(t5.minimum(a2, comp4), s2);
    t.checkExpect(t6.minimum(a3, comp5), b2);

    // Test greaterThan
    CompareLists t7 = new CompareLists();
    CompareLists t8 = new CompareLists();
    CompareLists t9 = new CompareLists();

    List<Point> expectedList1 = new ArrayList<>();
    expectedList1.add(p1);
    expectedList1.add(p3);
    List<String> expectedList2 = new ArrayList<>();
    expectedList2.add(s1);
    List<Boolean> expectedList3 = new ArrayList<>();
    expectedList3.add(b1);
    expectedList3.add(b1);

    t.checkExpect(t7.greaterThan(l1, comp1, p2), expectedList1);
    t.checkExpect(t8.greaterThan(l2, comp3, s2), expectedList2);
    t.checkExpect(t9.greaterThan(l3, comp5, b2), expectedList3);

    // Test inOrder (for list)
    CompareLists t10 = new CompareLists();
    CompareLists t11 = new CompareLists();
    CompareLists t12 = new CompareLists();

    t.checkExpect(t10.inOrder(l1, comp2), true);
    t.checkExpect(t11.inOrder(l5, comp4), false);
    t.checkExpect(t12.inOrder(l6, comp5), true);
    
    CompareLists te1 = new CompareLists();
    t.checkException(new IllegalArgumentException("null value in list"), te1, "inOrder", lwe, comp2);

    // Test inOrder (for array)
    CompareLists t13 = new CompareLists();
    CompareLists t14 = new CompareLists();
    CompareLists t15 = new CompareLists();

    t.checkExpect(t13.inOrder(a1, comp2), true);
    t.checkExpect(t14.inOrder(a2, comp4), false);
    t.checkExpect(t15.inOrder(a3, comp5), false);

    CompareLists te2 = new CompareLists();
    t.checkException(new IllegalArgumentException("null value in array"), te2, "inOrder", awe, comp4);

    // Test merge
    CompareLists t16 = new CompareLists();
    CompareLists t17 = new CompareLists();
    CompareLists t18 = new CompareLists();

    Point p4 = new Point(12, 10);
    String s4 = "zkjf";

    List<Point> l7 = new ArrayList<>();
    l7.add(p2);
    l7.add(p1);
    l7.add(p3);
    List<String> l8 = new ArrayList<>();
    l8.add(s2);
    l8.add(s1);
    l8.add(s4);
    List<Boolean> l9 = new ArrayList<>();
    l9.add(b2);
    l9.add(b2);
    l9.add(b1);
    List<Point> l10= new ArrayList<>();
    l10.add(p1);
    l10.add(p3);
    l10.add(p4);
    List<String> l11 = new ArrayList<>();
    l11.add(s3);
    l11.add(s2);
    l11.add(s1);
    List<Boolean> l12 = new ArrayList<>();
    l12.add(b2);
    l12.add(b1);
    l12.add(b1);

    List<Point> expectedList4 = new ArrayList<>();
    expectedList4.add(p2);
    expectedList4.add(p1);
    expectedList4.add(p1);
    expectedList4.add(p3);
    expectedList4.add(p3);
    expectedList4.add(p4);
    List<String> expectedList5 = new ArrayList<>();
    expectedList5.add(s3);
    expectedList5.add(s2);
    expectedList5.add(s2);
    expectedList5.add(s1);
    expectedList5.add(s1);
    expectedList5.add(s4);
    List<Boolean> expectedList6 = new ArrayList<>();    
    expectedList6.add(b2);
    expectedList6.add(b2);
    expectedList6.add(b2);
    expectedList6.add(b1);
    expectedList6.add(b1);
    expectedList6.add(b1);

    t.checkExpect(t16.merge(comp1, l7, l10), expectedList4);
    t.checkExpect(t17.merge(comp3, l8, l11), expectedList5);
    t.checkExpect(t18.merge(comp5, l9, l12), expectedList6);

    CompareLists te3 = new CompareLists();
    t.checkException(new IllegalArgumentException("null value in first list"), te3, "merge", comp1, lwe, l7);
  }
}

/*
class ExceptionAndStacks {
  public static void main(String[] args) {
    Point p1 = new Point(5, 8);
    Point p2 = new Point(9, 4);
    Point p3 = new Point(8, 8);

    Comparator<Point> comp1 = new PointCompare();
    
    List<Point> lwe = new ArrayList<>();
    lwe.add(p1);
    lwe.add(null);
    lwe.add(p2);
    lwe.add(p3);

    List<Point> l7 = new ArrayList<>();
    l7.add(p2);
    l7.add(p1);
    l7.add(p3);

    CompareLists te3 = new CompareLists();

    te3.merge(comp1, lwe, l7);
  }
}
*/