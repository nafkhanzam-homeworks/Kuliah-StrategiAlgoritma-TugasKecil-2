package com.nafkhanzam.stima.tucil2.multiplysamedegree;

import com.nafkhanzam.stima.tucil2.Polynom;

public abstract class BasePolynomSameDegreeMultiplyOperation {

    protected Polynom a, b;
    protected int degree;

    public BasePolynomSameDegreeMultiplyOperation(Polynom a, Polynom b) {
        int aDegree = a.getArray().length - 1, bDegree = b.getArray().length - 1;
        if (aDegree != bDegree) {
            throw new RuntimeException("The polynoms' degree aren't the same!");
        }
        degree = aDegree;
        if (degree > Integer.MAX_VALUE / 2) {
            throw new RuntimeException("The polynoms' degree are too high that the multiply result will overflow!");
        }
        this.a = a;
        this.b = b;
    }

    public int getDegree() {
        return degree;
    }

    public abstract Polynom multiply();

}