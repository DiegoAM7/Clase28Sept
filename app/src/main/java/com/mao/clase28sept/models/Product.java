package com.mao.clase28sept.models;

public class Product {
  private int id;
  private String name;
  private String trademark;
  private String model;
  private int stock;
  private int price;
  private Category category;

  public Product(
      String name, String trademark, String model, int stock, int price, Category category) {
    this.name = name;
    this.trademark = trademark;
    this.model = model;
    this.stock = stock;
    this.price = price;
    this.category = category;
  }

  public Product(
      int id,
      String name,
      String trademark,
      String model,
      int stock,
      int price,
      Category category) {
    this.id = id;
    this.name = name;
    this.trademark = trademark;
    this.model = model;
    this.stock = stock;
    this.price = price;
    this.category = category;
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

  public String getTrademark() {
    return trademark;
  }

  public void setTrademark(String trademark) {
    this.trademark = trademark;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return this.name + " - " + this.trademark;
  }
}
