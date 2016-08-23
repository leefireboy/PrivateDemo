/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.comparable;

/**
 * <P>
 * Description:Comparable 测试类
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月22日上午9:29:11
 */
public class Name implements Comparable<Name> {

    String firstName;
    String lastName;

    public Name() {
        super();
    }

    public Name(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + "·" + lastName;
    }

    @Override
    public int compareTo(Name o) {
        if (this.firstName.equals(o.firstName)) {
            return this.lastName.compareTo(o.lastName);
        } else {
            return this.firstName.compareTo(o.firstName);
        }
    }

    @Override
    public int hashCode() {
        return this.firstName.hashCode() + this.lastName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Name) {
            if (this.firstName.equals(((Name) obj).getFirstName())
                    && this.lastName.equals(((Name) obj).getLastName())) {
                return true;
            } else {
                return false;
            }
        } else {
            return super.equals(obj);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
