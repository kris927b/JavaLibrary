package main.java.org.LibrarySystem.library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.nypl.journalsystem.core.IArticle;
import org.nypl.journalsystem.core.IAuthor;
import org.nypl.journalsystem.core.IJournal;
import org.nypl.journalsystem.core.ILibrarySystem;

import main.java.org.LibrarySystem.article.Article;
import main.java.org.LibrarySystem.author.Author;
import main.java.org.LibrarySystem.author.researcher.Researcher;
import main.java.org.LibrarySystem.author.researcher.Title;
import main.java.org.LibrarySystem.author.student.Student;
import main.java.org.LibrarySystem.journal.Journal;
import main.java.org.LibrarySystem.publisher.Publisher;

public class LibrarySystem implements ILibrarySystem {
    private final String COMMA_SEPERATOR = ",";
    private final String SEMI_SEPERATOR = ";";
    private final String SQUARE_SEPERATOR = "\\[";
    private final String QUOTE_SEPERATOR = "(\", \")|(, \")|(\", )";
    HashMap<String, Publisher> publishers;
    HashMap<String, Journal> journals;
    HashMap<String, Author> authors;
    HashMap<Integer, Article> articles;

    public LibrarySystem() {
        File dataDir = new File("data/");
        publishers = new HashMap<>();
        journals = parseJournals(CSVReader(new File(dataDir, "Journals.csv")));
        authors = new HashMap<>();
        articles = parseArticles(CSVReader(new File(dataDir, "Basement.csv")));
    }

    private List<List<String>> CSVReader(File file) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header)
                    header = false;
                else {
                    String[] quoteSplit = line.split(QUOTE_SEPERATOR);
                    List<String> commaSplit = new ArrayList<>();
                    for (int i = 0; i < quoteSplit.length; i++) {
                        String part = quoteSplit[i];
                        if (i != 2) {
                            String[] splits = part.split(COMMA_SEPERATOR);
                            for (String split : splits) {
                                commaSplit.add(split.trim());
                            }
                        } else {
                            commaSplit.add(part);
                        }
                    }
                    records.add(commaSplit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private HashMap<String, Journal> parseJournals(List<List<String>> records) {
        HashMap<String, Journal> journals = new HashMap<String, Journal>();
        for (List<String> record : records) {
            Journal j;
            if (publishers.containsKey(record.get(1))) {
                j = new Journal(record.get(0).trim(), publishers.get(record.get(1)), record.get(3));
            } else {
                Publisher p = new Publisher(record.get(1), record.get(2));
                publishers.put(record.get(1), p);
                j = new Journal(record.get(0).trim(), publishers.get(record.get(1)), record.get(3));
            }
            journals.put(record.get(0), j);
        }
        return journals;
    }

    private HashMap<Integer, Article> parseArticles(List<List<String>> records) {
        HashMap<Integer, Article> articles = new HashMap<Integer, Article>();
        for (List<String> record : records) {
            Journal j = journals.get(record.get(3));

            int id;
            try {
                id = Integer.parseInt(record.get(0));
            } catch (NumberFormatException e) {
                id = records.size() + 1;
            }

            String[] authorString = record.get(2).split(SEMI_SEPERATOR);
            String[] papersString = record.get(4).split(SEMI_SEPERATOR);
            List<Author> authorList = new ArrayList<>();
            for (String author : authorString) {
                String[] authorTitle = author.split(SQUARE_SEPERATOR);
                Author a;
                if (authors.containsKey(authorTitle[0].trim())) {
                    a = authors.get(authorTitle[0].trim());
                } else {
                    try {
                        Title title = Title.valueOf(authorTitle[1].substring(0, authorTitle[1].length() - 1));
                        a = new Researcher(authorTitle[0].trim(), title);
                    } catch (IllegalArgumentException e) {
                        double gpa = Double.parseDouble(authorTitle[1].substring(0, authorTitle[1].length() - 1));
                        a = new Student(authorTitle[0].trim(), gpa);
                    }
                    authors.put(authorTitle[0].trim(), a);
                }
                authorList.add(a);
            }

            List<Integer> papers = new ArrayList<>();
            for (String paper : papersString) {
                papers.add(Integer.parseInt(paper));
            }

            Article a = new Article(id, record.get(1), authorList, j, papers);
            j.addArticle(a);
            articles.put(id, a);

            for (Author author : authorList) {
                author.addArticle(a);
            }
        }

        return articles;
    }

    private void printLibrary() {
        for (Author a : authors.values()) {
            System.out.println(a);
        }
    }

    /**
     * Get all the authors in the LibrarySystem
     * 
     * @return A collection of authors
     */
    @Override
    public Collection<? extends IAuthor> getAllAuthors() {
        Collection<Author> authorList = authors.values();
        return authorList;
    }

    /**
     * Get all the journals in the LibrarySystem
     * 
     * @return A Collection of journals
     */
    @Override
    public Collection<? extends IJournal> getAllJournals() {
        Collection<Journal> journalList = journals.values();
        return journalList;
    }

    /**
     * Get all the articles the author have written or co-authored
     * 
     * @param arg0 Author of the IAuthor type. This is the author whos articles you want to find. 
     * @return A collection of articles
     */
    @Override
    public Collection<? extends IArticle> getArticlesByAuthor(IAuthor arg0) {
        Author a = (Author) arg0;
        return a.getArticles();
    }

    /**
     * Get all articles cited in an article. 
     * 
     * @param arg0 Article of IArticle type. This is the article which reference list you'd like.
     * @return A collection of articles
     */
    @Override
    public Collection<? extends IArticle> getArticlesCitedByArticle(IArticle arg0) {
        Article a = (Article) arg0;
        List<Integer> paperIDs = a.getPapers();
        Collection<Article> articleList = new ArrayList<>();
        for (Integer paperID : paperIDs) {
            articleList.add(articles.get(paperID));
        }
        return articleList;
    }

    /**
     * Get all articles which cites the given article
     * 
     * @param arg0 Article of type IArticle. 
     * This is the article of which you would like to find which articles reference that. 
     * @return A collection of articles citing the given article
     */
    @Override
    public Collection<? extends IArticle> getArticlesCitingArticle(IArticle arg0) {
        // TODO Auto-generated method stub
        return null;
    }
}