package com.helloworld;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
    private Math math = new Math();

    @Test
    public void testCong2So(){
        assertEquals(5, math.cong2so(2, 3), "2 + 3 phai bang 5");
    }

    @Test
    public void testCong2So2(){
        assertEquals(-4, math.cong2so(2, -6), "2 - 6 phai bang -4");
    }

    @Test
    public void testGiaithua0(){
        assertEquals(1, math.giaithua(0), "0! phai bang 1");
    }

    @Test
    public void testGiaithua1(){
        assertEquals(1, math.giaithua(1), "1! phai bang 1");
    }

    @Test
    public void testGiaithua2(){
        assertEquals(120, math.giaithua(5), "5! phai bang 120");
    }
}
