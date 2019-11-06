package main.java.org.LibrarySystem.author;

import java.util.ArrayList;
import java.util.List;

import org.nypl.journalsystem.core.IAuthor;

import main.java.org.LibrarySystem.article.Article;

public abstract class Author implements IAuthor {
    private String name;
    private int hIndex;
    private List<Article> articles;


    public Author(String name) {
        this.articles = new ArrayList<>();
        this.name = name;
    }

    public Author(String name, List<Article> articles) {
        this.name = name;
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
    }

    /**
     * Gets the authors current hIndex
     * 
     * @return the current hIndex of the author
     */
    public int getHIndex() {
        return this.hIndex;
    }

    /**
     * Set the hIndex of the author. You need to calculate the specific hIndex your self. 
     * 
     * @param hIndex Integer
     */
    public void setHIndex(int hIndex) {
        this.hIndex = hIndex;
    }

    /**
     * This function returns the given name of the author
     * 
     * @return the authors name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the authors name. You can do this if the author changes name or for any other reason.
     * 
     * @param name String containing the new name of the author.
     */
	public void setName(String name) {
        this.name = name;
    }
}