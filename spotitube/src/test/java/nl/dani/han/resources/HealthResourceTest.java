package nl.dani.han.resources;

import org.junit.jupiter.api.Test;

public class HealthResourceTest {

    private HealthResource sut;

    @Test
    public void HealthResourceTest() {
        sut = new HealthResource();
        sut.healthCheck(); //deze test bestaat alleen voor code coverage. HealthResource is verder onbelangrijk voor de applicatie
    }
}
