package com.platform7

import com.platform7.models.MonthlyAmortization


object Amortization {
  def calculateMonthlyRepayment(principalValue: BigDecimal,
                                annualInterestRate: BigDecimal,
                                years: Int): BigDecimal = {
    val rate = annualInterestRate / 12
    val amount = (principalValue * rate) / (1 - Math.pow(1 + rate.toDouble, -1 * years * 12))
    amount
  }

  def calculateTotalRepayment(monthlyRepayment: BigDecimal,
                              years: Int,
                              repaymentPeriodsPerYear: Int = 12): BigDecimal =
    monthlyRepayment * years * repaymentPeriodsPerYear


  def calculateMonthlyRepayments(openingBalance: BigDecimal, annualInterestRate: BigDecimal, years: Int, monthlyRepayment: Option[BigDecimal] = None, acc: Seq[MonthlyAmortization] = Nil): Seq[MonthlyAmortization] = {
    if (openingBalance <= 0) {
      acc
    } else {
      val interestCharged = openingBalance * annualInterestRate / 12

      val defaultRepayment = monthlyRepayment.getOrElse(calculateMonthlyRepayment(openingBalance, annualInterestRate, years))
      val loanRepayment = if (openingBalance < defaultRepayment) openingBalance + interestCharged else defaultRepayment

      val capitalRepaid = loanRepayment - interestCharged
      val closingBalance = openingBalance - capitalRepaid

      val ma = MonthlyAmortization(openingBalance, loanRepayment, interestCharged, capitalRepaid, closingBalance)
      calculateMonthlyRepayments(closingBalance, annualInterestRate, years, Some(loanRepayment), acc :+ ma)
    }
  }
}
