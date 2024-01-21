package com.hillel.glovo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GlovoApplicationTests {

    /*@Test
    void contextLoads() {
    }*/

    @InjectMocks
    private GlovoApplication glovoApplication;

    @Test
    void onStartup() {
        assertNotNull(glovoApplication);
    }

}
