package ch.johannes.examples.mapper.oneone;

import java.lang.String;

public class MyPersonTO {
  private String firstname;

  private String lastname;

  public String getFirstname() {
    return this.firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return this.lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
}
