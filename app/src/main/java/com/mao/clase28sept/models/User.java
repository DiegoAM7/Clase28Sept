package com.mao.clase28sept.models;

public class User {
  private int id;
  private String name;
  private String lastname;
  private String email;
  private String password;
  private String type;

  public User(String name, String lastname, String email, String password, String type) {
    this.name = name;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.type = type;
  }

  public User(int id, String name, String lastname, String email, String password, String type) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
