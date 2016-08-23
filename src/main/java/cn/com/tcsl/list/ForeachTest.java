/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.list;


/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年6月12日下午4:57:38
 */
public class ForeachTest {

    public static void main(String[] args) {
        // List<Person> persons = new ArrayList<Person>();
        // persons.add(new Person("Lee"));
        // persons.add(new Person("Hao"));
        // for (Person person : persons) {
        // System.out.println(person);
        // }
        // Iterator<Person> iter = persons.iterator();
        // while (iter.hasNext()) {
        // System.out.println(iter.next());
        // }
        // for (int i = 0; i < persons.size(); i++) {
        // System.out.println(persons.get(i));
        // }
    }

}

class Person {

    String name;

    Person(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
