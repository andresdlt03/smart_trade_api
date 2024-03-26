package com.bluejtitans.smarttradebackend;

public abstract class Technology extends Product{
    private int energyDrain;
    private string Model;

    public int getEnergyDrain() {
        return energyDrain;
    }

    public void setEnergyDrain(int energyDrain) {
        this.energyDrain = energyDrain;
    }

    public string getModel() {
        return Model;
    }

    public void setModel(string model) {
        Model = model;
    }
}
