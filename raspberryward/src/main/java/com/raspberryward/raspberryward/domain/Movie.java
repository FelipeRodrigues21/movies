package com.raspberryward.raspberryward.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "title")
    private String title;

    @Column(name = "studio")
    private String studio;

    @Column(name = "producers")
    private String producers;

    @Column(name = "year")
    private String year;

    @Column(name = "winner")
    private String winner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(studio, movie.studio) &&
                Objects.equals(producers, movie.producers) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(winner, movie.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, studio, producers, year, winner);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", studio='" + studio + '\'' +
                ", producers='" + producers + '\'' +
                ", year=" + year +
                ", winner='" + winner + '\'' +
                '}';
    }
}
