package com.bluejtitans.smarttradebackend.products.model;

import java.util.List;

public interface IProduct {
    public String getName();
    public void setName(String name);
    public String getDescription();
    public void setDescription(String description);
    public String getDataSheet();
    public void setDataSheet(String dataSheet);
    public byte[] getPhoto();
    public void setPhoto(byte[] photo);
    public String getCategory();
    public void setCategory(String category);
}
