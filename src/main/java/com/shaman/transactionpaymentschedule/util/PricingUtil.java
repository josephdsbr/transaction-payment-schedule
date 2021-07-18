package com.shaman.transactionpaymentschedule.util;

import java.math.BigDecimal;

public class PricingUtil {
    public static BigDecimal withMathRound(double value, int places) {
        double scale = Math.pow(10, places);
        return BigDecimal.valueOf(Math.round(value * scale) / scale);
    }

    public static BigDecimal defineAmount(int totalInstallment, int currentInstallment, double amount) {
        var price = withMathRound(totalInstallment > 0 ? amount / totalInstallment : amount, 2);
        var serializedTotalAmount = BigDecimal.valueOf(amount);
        var totalPrice = price.multiply(BigDecimal.valueOf(totalInstallment));
        var isFirstInstallment = currentInstallment == 1;
        if (!totalPrice.equals(serializedTotalAmount) & isFirstInstallment) {
            var difference = totalPrice.subtract(serializedTotalAmount);
            if (difference.doubleValue() < 0 ) {
                price =  price.add(difference.abs());
            } else {
                price = price.subtract(difference.abs());
            }
        }
        return price;
    }

    public static BigDecimal defineNetAmount(double amount, double fee) {
        var serializedAmount = BigDecimal.valueOf(amount);
        var totalNetAmountNotTreated = serializedAmount.multiply(BigDecimal.valueOf(1 - fee/100)).doubleValue();
        return withMathRound(totalNetAmountNotTreated, 2);
    }
}
