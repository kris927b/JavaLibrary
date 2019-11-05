package main.java.org.LibrarySystem.article;

import java.util.List;
import java.util.Objects;

import org.nypl.journalsystem.core.IArticle;

import main.java.org.LibrarySystem.author.Author;
import main.java.org.LibrarySystem.journal.Journal;

public class Article implements IArticle {
    String title;
    int Id;
    List<Author> authors;
    Journal journal;
    List<Integer> papers;

    public Article(int id, String title, List<Author> authors, Journal journal, List<Integer> papers) {
        this.Id = id;
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.papers = papers;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Journal getJournal() {
        return this.journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public List<Integer> getPapers() {
        return this.papers;
    }

    public void setPapers(List<Integer> papers) {
        this.papers = papers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Article)) {
            return false;
        }
        Article article = (Article) o;
        return Objects.equals(title, article.title) && Id == article.Id && Objects.equals(authors, article.authors) && Objects.equals(journal, article.journal) && Objects.equals(papers, article.papers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, Id, authors, journal, papers);
    }

    @Override
    public String toString() {
        return "\n\t\t{" +
            " \n\t\t title='" + getTitle() + "'" +
            ", \n\t\t Id='" + getId() + "'" +
            ", \n\t\t authors='" + getAuthors() + "'" +
            ", \n\t\t papers='" + getPapers() + "'" +
            "}";
    }

}