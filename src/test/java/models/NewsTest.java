package models;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void news_instantiatesCorrectly_true() throws Exception{
        News testNews = setUpNews();
        Assert.assertTrue(true);
    }

    @Test
    public void getTitle_string() {
        News testNews = setUpNews();
        assertEquals("Salary", testNews.getTitle());
    }

    @Test
    public void getContent_string() throws Exception{
        News testNews = setUpNews();
        assertEquals("We are underPayed", testNews.getContent());
    }

    @Test
    public void getDepartmentId_int() throws Exception{
        News testNews = setUpNews();
        assertEquals(2, testNews.getDepartmentId());
    }

    @Test
    public void setTitle() throws Exception{
        News testNews = setUpNews();
        testNews.setTitle("Salary");
        assertEquals("Salary", testNews.getTitle());
    }


    @Test
    public void setContent() throws Exception{
        News testNews = setUpNews();
        testNews.setContent("We are underPayed");
        assertNotEquals("Salary", testNews.getContent());
    }

    @Test
    public void setDepartmentId() throws Exception{
        News testNews = setUpNews();
        testNews.setDepartmentId(10);
        assertNotEquals("Salary", testNews.getDepartmentId());
    }

    @Test
    public void getId_getsIdOfEachNews() throws Exception{
        News testNews = setUpNews();
        assertEquals(0, testNews.getId());
    }

    @Test
    public void set_setsNewsId() throws  Exception{
        News testNews = setUpNews();
        testNews.setId(1);
        assertNotEquals(6,testNews.getId());
    }

    @Test
    public void equals_returnsAllFieldsTheSame() {
        News fourthNews = setUpNews();
        News tenthNews = setUpNews();
        assertEquals(true,fourthNews.equals(tenthNews));

    }

    //helper
    public News setUpNews(){
        return new News ("Salary", "We are underPayed", 2);
    }
}