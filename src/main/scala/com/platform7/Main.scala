package com.platform7

object Main extends App {

  val principalValue = 1250000
  val years = 20
  val annualInterestRate = 0.0925
  val monthlyRepayment = Ammortization.monthlyRepayment(principalValue, annualInterestRate, years)
  val totalRepayment = Ammortization.totalRepayment(monthlyRepayment, years)
  val totalInterest = totalRepayment - principalValue

  println(s"The total bond amount is R${principalValue}")
  println(s"The monthly repayment is R${monthlyRepayment}")
  println(s"The total interest repaid is R${totalInterest}")
  println(s"The total bond repayment is R${totalRepayment}")
}