package com.example.jose.newsapp.utilities;

public class NewsItem {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlTOImage;
    private String publishedAt;

    public NewsItem(String author, String title,
                    String description, String url,
                    String urlTOImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlTOImage = urlTOImage;
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUrlTOImage() {
        return urlTOImage;
    }

    public void setUrlTOImage(String urlTOImage) {
        this.urlTOImage = urlTOImage;
    }

    public String getPublushedAt() {
        return publishedAt;
    }

    public void setPublushedAt(String publushedAt) {
        this.publishedAt = publushedAt;
    }

}
