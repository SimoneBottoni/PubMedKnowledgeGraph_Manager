package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.util;

public class Title {

    private String title;
    private String book;
    private String part;
    private String sec;

    public Title() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getAllFields() {
        StringBuilder stringBuilder = new StringBuilder();
        if (book != null) {
            stringBuilder.append(book).append(":");
        }
        if (part != null) {
            stringBuilder.append(part).append(":");
        }
        if (sec != null) {
            stringBuilder.append(sec).append(":");
        }
        if (title != null) {
            stringBuilder.append(title);
        }
        return stringBuilder.toString();
    }
}
