package demo;


import com.zn.Application;
import com.zn.biz.dao.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
* SimpleBookRepository Tester.
*
* @author <Authors name>
* @since <pre>四月 24, 2019</pre>
* @version 1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SimpleBookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: getByIsbn(String isbn)
    *
    */
    @Test
    public void testGetByIsbn() throws Exception {

    }

    /**
    *
    * Method: test(String isbn)
    *
    */
    @Test
    public void testTest() throws Exception {
    //TODO: Test goes here...
        bookRepository.test("3");
        bookRepository.test("4");
    }

    @Test
    public void testTest2() throws Exception {
    //TODO: Test goes here...
        bookRepository.test("2");
    }


        /**
    *
    * Method: simulateSlowService()
    *
    */
    @Test
    public void testSimulateSlowService() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = SimpleBookRepository.getClass().getMethod("simulateSlowService");
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

}
