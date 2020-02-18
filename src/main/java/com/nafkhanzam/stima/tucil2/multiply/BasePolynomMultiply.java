package com.nafkhanzam.stima.tucil2.multiply;

import com.nafkhanzam.stima.tucil2.Polynom;

public abstract class BasePolynomMultiply {

    protected Polynom a, b;

    public BasePolynomMultiply(Polynom a, Polynom b) {
        int ad = a.getMaxDegree(), bd = b.getMaxDegree();
        if (ad + bd < 0 && ad > 0 && bd > 0) {
            throw new RuntimeException("The polynoms' degree are too high that the multiply result will overflow!");
        }
        this.a = a;
        this.b = b;
    }

    public abstract Polynom multiply();

}