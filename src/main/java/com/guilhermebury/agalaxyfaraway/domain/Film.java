package com.guilhermebury.agalaxyfaraway.domain;

import java.util.List;

/**
 * Created by Guilherme Bury on 20/04/2017.
 */

public class Film {

    //API
    public String title;
    public int episodeId;
    public String openingCrawl;
    public String director;
    public String producer;
    public String releaseDate;
    public List<Character> characters;
    public List<Planet> planets;
    public List<Starship> starships;
    public List<Vehicle> vehicles;
    public List<Specie> species;

    //Local
    public int cover;

    public Film(String title, String releaseDate, int cover) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.cover = cover;
    }

    //Getters and Setters
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public int getEpisodeId() {return episodeId;}
    public void setEpisodeId(int episodeId) {this.episodeId = episodeId;}
    public String getOpeningCrawl() {return openingCrawl;}
    public void setOpeningCrawl(String openingCrawl) {this.openingCrawl = openingCrawl;}
    public String getDirector() {return director;}
    public void setDirector(String director) {this.director = director;}
    public String getProducer() {return producer;}
    public void setProducer(String producer) {this.producer = producer;}
    public String getReleaseDate() {return releaseDate;}
    public void setReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}
    public List<Character> getCharacters() {return characters;}
    public void setCharacters(List<Character> characters) {this.characters = characters;}
    public List<Planet> getPlanets() {return planets;}
    public void setPlanets(List<Planet> planets) {this.planets = planets;}
    public List<Starship> getStarships() {return starships;}
    public void setStarships(List<Starship> starships) {this.starships = starships;}
    public List<Vehicle> getVehicles() {return vehicles;}
    public void setVehicles(List<Vehicle> vehicles) {this.vehicles = vehicles;}
    public List<Specie> getSpecies() {return species;}
    public void setSpecies(List<Specie> species) {this.species = species;}
    public int getCover() {return cover;}
    public void setCover(int cover) {this.cover = cover;}
}
