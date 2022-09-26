package container;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Collection接口下的三大子接口:
 * 1)List
 * 2)Set
 * 3)Queue
 */

public class SetTests {
    static void hashSetTest(){
        //1.HashSet不允许元素重复,不能保证key的顺序
        HashSet<String> set=new HashSet<>();
        set.add("AD");
        set.add("BC");
        set.add("AA");
        System.out.println(set);
    }
    static void linkedHashSetTest(){
        //LinkedHashSet用于记录元素的添加顺序
        LinkedHashSet<String> set=new LinkedHashSet<>();
        set.add("AD");
        set.add("BC");
        set.add("AA");
        System.out.println(set);
    }

    static void treeSetTest(){
        //TreeSet可以对放入集合的元素进行排序(底层是红黑树)
        TreeSet<Integer> set=new TreeSet<>();
        set.add(100);
        set.add(500);
        set.add(300);
        set.add(100);
        System.out.println(set);

        TreeSet<Employee> empSet=new TreeSet<>();
        empSet.add(new Employee(8000));
        empSet.add(new Employee(6000));
        empSet.add(new Employee(9000));
        System.out.println(empSet);
    }
    public static void main(String[] args) {
        treeSetTest();
    }
}
class Employee implements Comparable<Employee>{
    private Integer salary;
    public Employee(Integer salary){
        this.salary=salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return salary.equals(employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary);
    }

    @Override
    public int compareTo(Employee o) {
        return this.salary-o.salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                '}';
    }
}
