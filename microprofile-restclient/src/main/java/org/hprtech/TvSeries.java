package org.hprtech;

public class TvSeries {
    private int id;
    private String url;
    private  String name;
    private String type;
    private String language;

    public TvSeries(int id, String url, String name, String type, String language) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.type = type;
        this.language = language;
    }

    public TvSeries() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
