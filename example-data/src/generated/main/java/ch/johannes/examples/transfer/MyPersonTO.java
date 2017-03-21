package ch.johannes.examples.transfer;

import ch.johannes.example.data.dao.person.Gender;
import java.lang.String;

public class MyPersonTO {
  private Gender gender;

  private String firstname;

  private String lastname;

  public Gender getGender() {
    return this.gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

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
