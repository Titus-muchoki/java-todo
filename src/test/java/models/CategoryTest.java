package models;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryTest {
    @Test
    public void find_returnsNullWhenNoTaskFound_null() {
        assertTrue(Category.find(999) == null);
    }
}
