package com.platform7

import com.platform7.Format.roundAmount


object Ammortization {
  //  val principalValue = 1250000 // principla or present value
  //  val annualInterestRate = 0.0925
  //  val numberOfYears = 20
  //  val repaymentPeriodsPerYear = 12 // monthly
  //
  //  val rate = annualInterestRate / repaymentPeriodsPerYear
  //  val totalRepaymentPeriods = numberOfYears / repaymentPeriodsPerYear

  def monthlyRepayment(principalValue: BigDecimal,
                       annualInterestRate: BigDecimal,
                       years: Int,
                       repaymentPeriodsPerYear: Int = 12): BigDecimal = {
    val rate = annualInterestRate / repaymentPeriodsPerYear
    val amount = (principalValue * rate) / (1 - Math.pow(1 + rate.toDouble, -1 * years * repaymentPeriodsPerYear))

    roundAmount(amount)
  }

  def totalRepayment(monthlyRepayment: BigDecimal,
                     years: Int,
                     repaymentPeriodsPerYear: Int = 12): BigDecimal =
    roundAmount(monthlyRepayment * years * repaymentPeriodsPerYear)



}
