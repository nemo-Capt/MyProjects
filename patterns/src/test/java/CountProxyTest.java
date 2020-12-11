import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CountProxyTest {

    @Test
    void countProxyTest() {
        CountInterface test = new CountProxy();
        assertEquals("5 + 7 = 12", test.count(5, 7));
    }


}
