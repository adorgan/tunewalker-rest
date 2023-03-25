package com.tunewalker.rest.model;

public class AlbumArtDetails {
    private String title;
    private String band;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getChartPosition() {
        return chartPosition;
    }

    public void setChartPosition(String chartPosition) {
        this.chartPosition = chartPosition;
    }

    private String releaseDate;
    private String chartPosition;
}
