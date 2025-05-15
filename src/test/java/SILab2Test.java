import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void testEveryStatement() {
        // Тест 1 (allItems = null)
        RuntimeException ex1 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, "7777777777777777"));
        assertTrue(ex1.getMessage().contains("allItems list can't be null!"));

        // Тест 2 (item со валидни вредности без попуст)
        Item item2 = new Item("item2", 2, 100, 0.0);
        double result2 = SILab2.checkCart(List.of(item2), "7777777777777777");
        assertEquals(200, result2);

        // Тест 3 (item без име го активира 7)
        Item item3 = new Item(null, 1, 100, 0.0);
        RuntimeException ex3 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item3), "7777777777777777"));
        assertTrue(ex3.getMessage().contains("Invalid item!"));

        // Тест 4 (item со количина >10 или цена >300 или попуст >0)
        Item item4 = new Item("item4", 11, 400, 0.0);
        double result4 = SILab2.checkCart(List.of(item4), "7777777777777777");
        assertEquals(4370, result4);

        // Тест 5 (item со попуст >0)
        Item item5 = new Item("item5", 1, 100, 0.1);
        double result5 = SILab2.checkCart(List.of(item5), "7777777777777777");
        assertEquals(60, result5);

        // Тест 6 (item без cardNumber (може и length != 16))
        Item item6 = new Item("item6", 1, 50, 0.0);
        RuntimeException ex6 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item6), "123"));
        assertTrue(ex6.getMessage().contains("Invalid card number!"));

        // Тест 7 (item во кој не се броевите 0123456789)
        Item item7 = new Item("item7", 2, 100, 0.0);
        RuntimeException ex7 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item7), "ABCDEFGHIJKLMNOP"));
        assertTrue(ex7.getMessage().contains("Invalid character in card number!"));
    }

    @Test
    void testMultipleCondition() {
        // 1. T X X ---> price > 300
        Item item1 = new Item("item1", 1, 400, 0.0);
        double result1 = SILab2.checkCart(List.of(item1), "7777777777777777");
        assertEquals(370, result1);

        // 2. F T X ---> discount > 0
        Item item2 = new Item("item2", 1, 200, 0.5);
        double result2 = SILab2.checkCart(List.of(item2), "7777777777777777");
        assertEquals(70, result2);

        // 3. F F T ---> quantity > 10
        Item item3 = new Item("item3", 20, 200, 0.0);
        double result3 = SILab2.checkCart(List.of(item3), "7777777777777777");
        assertEquals(3970, result3);

        // 4. F F F ---> price < 300, discount = 0, quantity < 11
        Item item4 = new Item("item4", 1, 200, 0.0);
        double result4 = SILab2.checkCart(List.of(item4), "7777777777777777");
        assertEquals(200, result4);
    }
}
