package main.java.org.LibrarySystem.journal;

import java.util.ArrayList;
import java.util.Collection;

import org.nypl.journalsystem.core.IArticle;
import org.nypl.journalsystem.core.IJournal;
import org.nypl.journalsystem.core.IPublisher;

import main.java.org.LibrarySystem.article.Article;
import main.java.org.LibrarySystem.publisher.Publisher;

public class Journal implements IJournal {
    private String name;
    private Publisher publisher;
    private String ISSN;
    private Collection<Article> articles;
    private Boolean fullIssue;

    public Journal(String name, Publisher publisher, String ISSN) {
        this.name = name;
        this.publisher = publisher;
        this.ISSN = ISSN;
        this.articles = new ArrayList<>();
        this.fullIssue = false;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
        setFullIssue(isFullIssue());
    }

    @Override
    public boolean isFullIssue() {
        if (this.articles.size() >= 3) {
            return true;
        } else {
            return false;
        }
    }

    public Collection<? extends IArticle> getArticles() {
        return (Collection<? extends IArticle>) this.articles;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IPublisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getIssn() {
        return this.ISSN;
    }

    public void setIssn(String ISSN) {
        this.ISSN = ISSN;
    }

    public Boolean getFullIssue() {
        return this.fullIssue;
    }

    public void setFullIssue(Boolean fullIssue) {
        this.fullIssue = fullIssue;
    }

    @Override
    public String toString() {
        return "{" + "\n\tname='" + getName() + "'" + ", \n\tarticles='" + getArticles() + "'" + ", \n\tpublisher='"
                + getPublisher() + "'" + ", \n\tISSN='" + getIssn() + "'" + ", \n\tfullIssue='" + getFullIssue() + "'"
                + "}";
    }


}