package com.raspberryward.raspberryward.Dto;

import java.io.Serializable;
import java.util.Objects;

public class AwardDto implements Serializable {
    private String producer;

    private Integer interval;

    private Integer previousWin;

    private Integer followingWin;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardDto awardDto = (AwardDto) o;
        return Objects.equals(producer, awardDto.producer) &&
                Objects.equals(interval, awardDto.interval) &&
                Objects.equals(previousWin, awardDto.previousWin) &&
                Objects.equals(followingWin, awardDto.followingWin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producer, interval, previousWin, followingWin);
    }

    @Override
    public String toString() {
        return "Awards{" +
                "producer='" + producer + '\'' +
                ", interval=" + interval +
                ", previousWin=" + previousWin +
                ", followingWin=" + followingWin +
                '}';
    }
}
