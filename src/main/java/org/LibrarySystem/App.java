package main.java.org.LibrarySystem;

import java.util.HashMap;
import java.util.List;

import org.nypl.journalsystem.core.IAuthor;
import org.nypl.journalsystem.core.ILibrarySystem;
import org.nypl.journalsystem.hindex.CitationCalculator;
import org.nypl.journalsystem.ui.LibrarySystemApplicationBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import main.java.org.LibrarySystem.author.Author;
import main.java.org.LibrarySystem.library.LibrarySystem;
import main.java.org.LibrarySystem.mutableInt.MutableInt;

public class App extends LibrarySystemApplicationBase {

    @Override
    protected PieChart createCoAuthorsChart(IAuthor arg0) {
        Author arg = (Author) arg0;
        LibrarySystem lib = (LibrarySystem) getLibrarySystem();
        HashMap<Author, MutableInt> coAuthors = lib.getCoAuthors().get(arg);
        PieChart chart = new PieChart();
        chart.setTitle("Co-Authors");
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (Author a : coAuthors.keySet()) {
            data.add(new PieChart.Data(a.getName(), coAuthors.get(a).getValue()));
        }
        chart.setData(data);
        return chart;
    }

    @Override
    protected BarChart<String, Number> createHIndexChart(List<IAuthor> arg0) {
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Authors");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("H-Index");
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        XYChart.Series<String, Number> data1 = new XYChart.Series<>();
        data1.setName("H-Index");
        CitationCalculator cCalculator = new CitationCalculator();
        for (IAuthor a : arg0) {
            int hIndex = cCalculator.calculateHIndex(a, getLibrarySystem());
            data1.getData().add(new XYChart.Data<String, Number>(a.getName(), hIndex));
        }
        barChart.getData().add(data1);
        return barChart;
    }

    @Override
    protected ILibrarySystem loadLibrarySystem() throws Exception {
        LibrarySystem ls = new LibrarySystem();
        return ls;
    }
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}