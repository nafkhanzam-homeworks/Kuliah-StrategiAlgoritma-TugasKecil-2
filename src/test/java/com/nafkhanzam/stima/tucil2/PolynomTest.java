package com.nafkhanzam.stima.tucil2;

import static org.junit.Assert.assertEquals;

import com.nafkhanzam.stima.tucil2.multiply.PolynomSameDegreeMultiplyOperationBF;

import org.junit.Test;

public class PolynomTest {

    int[] arr = new int[] { -1, 2, 3, 4, 0, 1, 7, 0, 0, -10, 0, 0, 13, 0, 0 };
    String str = "-1 + 2x + 3x^2 + 4x^3 + x^5 + 7x^6 - 10x^9 + 13x^12";
    String str2 = "2x^5+x^4-2x";
    String mulres = "26x^17+13x^16-20x^14-36x^13+14x^11+29x^10+x^9+8x^8-4x^7+5x^6-9x^4-6x^3-4x^2+2x";

    @Test
    public void testToString() {
        assertEquals(new Polynom(arr).toString(), str);
    }

    @Test
    public void testConvertEquals() {
        assertEquals(new Polynom(arr), new Polynom(str));
    }

    @Test
    public void testMultiply() {
        Polynom p1 = new Polynom(str), p2 = new Polynom(str2),
                res = new PolynomSameDegreeMultiplyOperationBF(p1, p2).multiply();
        assertEquals(res, new Polynom(mulres));
    }

}
