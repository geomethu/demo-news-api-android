package com.newsapp.base.methu.demo_news_app.models;

/**
 * Created by Methu on 4/24/2017.
 */

public class Source_Model {
    String id, name, description, url,urlsToLogos_Medium, category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlsToLogos_Medium() {
        return urlsToLogos_Medium;
    }

    public void setUrlsToLogos_Medium(String urlsToLogos_Medium) {
        this.urlsToLogos_Medium = urlsToLogos_Medium;
    }
}
