package org.example.paisesdeeuropa;

public class Pais {
    private String name;
    private int flagResourceId;
    private String capital;
    private int population;
    private int surface;
    private String continent;

    public Pais(String name, int flagResourceId, String capital, int population, int surface, String continent) {
        this.name = name;
        this.flagResourceId = flagResourceId;
        this.capital = capital;
        this.population = population;
        this.surface = surface;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public int getFlagResourceId() {
        return flagResourceId;
    }

    public String getCapital() {
        return capital;
    }

    public int getPopulation() {
        return population;
    }

    public int getSurface() {
        return surface;
    }

    public String getContinent() {
        return continent;
    }
}
