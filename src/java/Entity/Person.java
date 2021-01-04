/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author artur
 */
public class Person {
    private long id;
    private String name;
    private String lastname;
    private String phone;

    public Person() {
    }

    public Person(long id, String name, String lastname, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id 
                + ", name=" + name 
                + ", lastname=" + lastname 
                + ", phone=" + phone 
                + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
