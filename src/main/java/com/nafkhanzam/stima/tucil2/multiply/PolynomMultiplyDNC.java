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
        Polynom y = _calculate(a0.add(a1), b0.add(b1));
        Polynom u = _calculate(a0, b0);
        Polynom z = _calculate(a1, b1);
        return u.add(y.substract(u).substract(z).multiplyDegree(n / 2)).add(z.multiplyDegree(n / 2 * 2));
    }

}
