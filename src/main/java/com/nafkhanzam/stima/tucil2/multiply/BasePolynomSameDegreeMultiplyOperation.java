package com.nafkhanzam.stima.tucil2.multiply;

import com.nafkhanzam.stima.tucil2.Polynom;

public abstract class BasePolynomSameDegreeMultiplyOperation {

    protected Polynom a, b;

    public BasePolynomSameDegreeMultiplyOperation(Polynom a, Polynom b) {
        if (a.getMaxDegree() + b.getMaxDegree() < 0) {
            throw new RuntimeException("The polynoms' degree are too high that the multiply result will overflow!");
        }
        this.a = a;
        this.b = b;
    }

    public abstract Polynom multiply();

}