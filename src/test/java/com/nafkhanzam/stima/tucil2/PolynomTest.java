package com.nafkhanzam.stima.tucil2;

import static org.junit.Assert.assertEquals;

import com.nafkhanzam.stima.tucil2.multiply.PolynomMultiplyBF;
import com.nafkhanzam.stima.tucil2.multiply.PolynomMultiplyDNC;

import org.junit.Test;

public class PolynomTest {

    int[] arr = new int[] { 6, 11, 6, 1 };
    String str = "6 + 11x + 6x^2 + x^3";
    String str2 = "-4x^3+6x^2+2x";
    String mulres = "-4x^6-18x^5-6x^4+54x^3+58x^2+12x";

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
        Polynom p1 = new Polynom(str), p2 = new Polynom(str2);
        Polynom resBF = new PolynomMultiplyBF(p1, p2).multiply();
        Polynom resDNC = new PolynomMultiplyDNC(p1, p2).multiply();
        assertEquals(new Polynom(mulres), resBF);
        assertEquals(new Polynom("25x^2"), new PolynomMultiplyDNC(new Polynom("5x^2"), new Polynom("5")).multiply());
        assertEquals(new Polynom(mulres), resDNC);
        assertEquals(new Polynom(), new PolynomMultiplyDNC(new Polynom(), new Polynom()).multiply());
        assertEquals(new Polynom(), new PolynomMultiplyBF(new Polynom(), new Polynom()).multiply());
    }

    @Test
    public void testHalf() {
        Polynom p = new Polynom(str);
        assertEquals(new Polynom("6 + 11x"), p.getHalfPolynom(true));
        assertEquals(new Polynom("6 + x"), p.getHalfPolynom(false));
        p = new Polynom("-4x^6-18x^5-6x^4+54x^3+58x^2+1234+12x");
        assertEquals(new Polynom("12x + 58x^2 + 54x^3 + 1234"), p.getHalfPolynom(true));
        assertEquals(new Polynom("-6 - 18x -4x^2"), p.getHalfPolynom(false));
    }

    @Test
    public void testMultiplyDegree() {
        Polynom p = new Polynom(str), p2 = new Polynom("-4x^30+2x");
        assertEquals(new Polynom("6x + 11x^2 + 6x^3 + x^4"), p.multiplyDegree(1));
        assertEquals(new Polynom(str), p.multiplyDegree(0));
        assertEquals(new Polynom("-4x^60+2x^31"), p2.multiplyDegree(30));
    }

}
