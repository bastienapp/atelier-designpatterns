package fr.wildcodeschool.atelierdesignpatterns;

class NewsModel {

    private String headline;
    private String newsContent;

    public NewsModel() {
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newContent) {
        this.newsContent = newContent;
    }
}
