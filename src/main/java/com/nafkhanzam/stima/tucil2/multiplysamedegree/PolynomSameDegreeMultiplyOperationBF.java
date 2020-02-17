package com.nafkhanzam.stima.tucil2.multiplysamedegree;

import com.nafkhanzam.stima.tucil2.Polynom;

public class PolynomSameDegreeMultiplyOperationBF extends BasePolynomSameDegreeMultiplyOperation {

    public PolynomSameDegreeMultiplyOperationBF(Polynom a, Polynom b) {
        super(a, b);
    }

    @Override
    public Polynom multiply() {
        int[] res = new int[degree * 2 + 1];
        for (int i = 0; i <= degree; ++i) {
            for (int j = 0; j <= degree; ++j) {
                res[i + j] = a.getCoef(i) * b.getCoef(j);
            }
        }
        return new Polynom(res);
    }

}