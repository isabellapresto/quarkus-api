package org.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabell") // Namn på tabell i databas
public class User implements Comparable<User> {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id") // Kolumnnamn i din databas
 private int id;

 @Column(name = "namn", insertable = false, updatable = false)
 private String firstName;
 @Column(name = "namn", insertable = false, updatable = false)
 private String lastName;
 

 @Column(name = "land") // Kolumnnamn i din databas
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
