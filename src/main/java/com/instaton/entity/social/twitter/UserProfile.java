package com.instaton.entity.social.twitter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId"})})
public class UserProfile {

  @Id private String userId;

  private String name;

  private String firstName;

  private String lastName;

  private String email;

  private String username;

  public UserProfile() {}

  public UserProfile(final String userId, final org.springframework.social.connect.UserProfile up) {
    this.userId = userId;
    this.name = up.getName();
    this.firstName = up.getFirstName();
    this.lastName = up.getLastName();
    this.email = up.getEmail();
    this.username = up.getUsername();
  }

  public UserProfile(
      final String userId,
      final String name,
      final String firstName,
      final String lastName,
      final String email,
      final String username) {
    this.userId = userId;
    this.name = name;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;

    this.fixName();
  }

  private void fixName() {
    // Is the name null?
    if (this.name == null) {

      // Ok, lets try with first and last name...
      this.name = this.firstName;

      if (this.lastName != null) {
        if (this.name == null) {
          this.name = this.lastName;
        } else {
          this.name += " " + this.lastName;
        }
      }

      // Try with email if still null
      if (this.name == null) {
        this.name = this.email;
      }

      // Try with username if still null
      if (this.name == null) {
        this.name = this.username;
      }

      // If still null set name to UNKNOWN
      if (this.name == null) {
        this.name = "UNKNOWN";
      }
    }
  }

  public String getEmail() {
    return this.email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getName() {
    return this.name;
  }

  public String getUserId() {
    return this.userId;
  }

  public String getUsername() {
    return this.username;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setUserId(final String userId) {
    this.userId = userId;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "name = "
        + this.name
        + ", firstName = "
        + this.firstName
        + ", lastName = "
        + this.lastName
        + ", email = "
        + this.email
        + ", username = "
        + this.username;
  }
}
