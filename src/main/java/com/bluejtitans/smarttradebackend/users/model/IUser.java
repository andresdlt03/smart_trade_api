package com.bluejtitans.smarttradebackend.users.model;

public interface IUser {
    public String getEmail();
    public String getName();
    public String getSurname();
    public String getPassword();
    public void setEmail(String email);
    public void setName(String name);
    public void setSurname(String surname);
    public void setPassword(String password);
}
