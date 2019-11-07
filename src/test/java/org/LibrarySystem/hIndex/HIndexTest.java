package test.java.org.LibrarySystem.hIndex;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.nypl.journalsystem.hindex.CitationCalculator;
import org.nypl.journalsystem.hindex.test.CitationCalculatorTest;

import java.security.InvalidParameterException;

public class HIndexTest extends CitationCalculatorTest {
    private CitationCalculator cCalculator;

    @Override
    @BeforeEach
    public void setup() {
        cCalculator = new CitationCalculator();
    }

    @Override
    @Test
    public void testCalculateHIndexWithNullAuthor() {
    }
    
    @Override
    @Test
    public void testCalculateHIndexWithNullCitationsPerArticle() {
        System.out.println(cCalculator);
        assertThrows(InvalidParameterException.class, () -> cCalculator.calculateHIndex(null));
    }

    @Override
    @Test
    public void testCalculateHIndexWithNoArticles() {
    }
    
    @Override
    @Test
    public void testCalculateHIndex1() {
    }
    
    @Override
    @Test
    public void testCalculateHIndex2() {
    }
    
    @Override
    @Test
    public void testCalculateHIndex3() {
    }
    
    @Override
    @Test
    public void testCalculateHIndex4() {
    }
    
    @Override
    @Test
    public void testCalculateHIndex5() {
    }
    
    @Override
    @Test
    public void testCalculateHIndexWithAllArticles() {
    }
}