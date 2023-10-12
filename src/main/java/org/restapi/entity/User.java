package org.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "user_info")
public class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private int age;


 public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
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

 public int getAge() {
     return age;
 }

 public void setAge(int age) {
     this.age = age;
 }

 @Override
 public int compareTo(User o) {
     return id - o.getId();
 }
}
