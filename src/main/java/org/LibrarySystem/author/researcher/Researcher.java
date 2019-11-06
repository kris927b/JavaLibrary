package main.java.org.LibrarySystem.author.researcher;

import java.util.List;

import main.java.org.LibrarySystem.article.Article;
import main.java.org.LibrarySystem.author.Author;

public class Researcher extends Author {
    private Title title;

    public Researcher(String name, Title title) {
        super(name);
        this.title = title;
    }

    public Researcher(String name, Title title, List<Article> articles) {
        super(name, articles);
        this.title = title;
    }

    public Title getTitle() {
        return this.title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getName();
    }
    

}