package com.bluejtitans.smarttradebackend.users.model;

public interface IUser {
    public String getEmail();
    public void setEmail(String email);
    public String getName();
    public void setName(String name);
    public String getSurname();
    public void setSurname(String surname);
    public String getPassword();
    public void setPassword(String password);
}
