package main.java.org.LibrarySystem;

import java.util.List;

import org.nypl.journalsystem.core.IAuthor;
import org.nypl.journalsystem.core.ILibrarySystem;
import org.nypl.journalsystem.ui.LibrarySystemApplicationBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import main.java.org.LibrarySystem.library.LibrarySystem;

public class App extends LibrarySystemApplicationBase {

    @Override
    protected PieChart createCoAuthorsChart(IAuthor arg0) {
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                new PieChart.Data("GrapeFruit", 20),
                new PieChart.Data("Banana", 10),
                new PieChart.Data("Apple", 15),
                new PieChart.Data("Pears", 30),
                new PieChart.Data("Plums", 25)
            );

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Fruits");
        return chart;
    }

    @Override
    protected BarChart<String, Number> createHIndexChart(List<IAuthor> arg0) {
        // TODO Auto-generated method stub
        return null;
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