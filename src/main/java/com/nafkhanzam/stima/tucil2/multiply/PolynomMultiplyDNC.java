package com.nafkhanzam.stima.tucil2.multiply;

import com.nafkhanzam.stima.tucil2.Polynom;

public class PolynomMultiplyDNC extends BasePolynomMultiply {

    public PolynomMultiplyDNC(Polynom a, Polynom b) {
        super(a, b);
    }

    @Override
    public Polynom multiply() {
        return _calculate(a, b);
    }

    private static Polynom _calculate(Polynom p1, Polynom p2) {
        int ad = p1.getLength(), bd = p2.getLength();
        int n = Math.max(ad, bd);
        if (n == 1) {
            return new Polynom(new int[] { p1.getCoef(0) * p2.getCoef(0) });
        }
        if (n % 2 == 1) {
            ++n;
        }
        if (ad < n) {
            p1 = p1.makeLength(n);
        }
        if (bd < n) {
            p2 = p2.makeLength(n);
        }
        Polynom a0 = p1.getHalfPolynom(true), a1 = p1.getHalfPolynom(false);
        Polynom b0 = p2.getHalfPolynom(true), b1 = p2.getHalfPolynom(false);
        Polynom a0b0 = _calculate(a0, b0);
        Polynom a0b1x = _calculate(a0, b1).multiplyDegree(n / 2);
        Polynom a1b0x = _calculate(a1, b0).multiplyDegree(n / 2);
        Polynom a1b1xx = _calculate(a1, b1).multiplyDegree(n);
        return a0b0.add(a0b1x).add(a1b0x).add(a1b1xx);
    }

}
