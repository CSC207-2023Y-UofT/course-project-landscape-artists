package tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloWorldTest {

    @Test
    public void HelloWorldNormalNumbers() {

        HelloWorld hw = new HelloWorld();
        Assertions.assertEquals("1", HelloWorld.convert(1));
        Assertions.assertEquals("2", HelloWorld.convert(2));
    }

    @Test
    public void HelloWorldThreeNumbers() {

        HelloWorld fb = new HelloWorld();
        Assertions.assertEquals("Hello", HelloWorld.convert(3));
    }

    @Test
    public void HelloWorldFiveNumbers() {

        HelloWorld hw = new HelloWorld();
        Assertions.assertEquals("World", HelloWorld.convert(5));
    }

    @Test
    public void HelloWorldThreeAndFiveNumbers() {

        HelloWorld hw = new HelloWorld();
        Assertions.assertEquals("World", HelloWorld.convert(5));
    }
}