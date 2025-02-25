package com.example.musarobots.robots;

public class Parent_Transformers {
    public String name ;
    public int energy_volume;
    public int lasers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLasers() {
        return lasers;
    }

    public void setLasers(int lasers) {
        this.lasers = lasers;
    }

    public int getEnergy_volume() {
        return energy_volume;
    }

    public void setEnergy_volume(int energy_volume) {
        this.energy_volume = energy_volume;
    }

    public Parent_Transformers(String name, int energy_volume, int lasers){
        this.name=name;
        this.energy_volume=energy_volume;
        this.lasers=lasers;

    }
}
