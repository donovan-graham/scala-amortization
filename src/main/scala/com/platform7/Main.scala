package com.platform7

import com.platform7.serializers.{BigDecimalSerializer, MonthlyAmortizationSerializer}
import net.liftweb.json.{DefaultFormats, Serialization}

object Main extends App {

  val principalValue = BigDecimal(1250000)
  val years = 20
  val annualInterestRate = BigDecimal(0.0925)
  val monthlyRepayment = Amortization.calculateMonthlyRepayment(principalValue, annualInterestRate, years)
  val totalRepayment = Amortization.calculateTotalRepayment(monthlyRepayment, years)
  val totalInterest = totalRepayment - principalValue


  val monthlyRepayments = Amortization.calculateMonthlyRepayments(principalValue, annualInterestRate, years, Some(monthlyRepayment))

  println(s"The total bond amount is R$principalValue")
  println(s"The monthly repayment is R$monthlyRepayment")
  println(s"The total interest repaid is R$totalInterest")
  println(s"The total bond repayment is R$totalRepayment")

//  monthlyRepayments.foreach { println }

//  implicit val formats = DefaultFormats + new MonthlyAmortizationSerializer
  implicit val formats = DefaultFormats + new BigDecimalSerializer
  val jsonString = Serialization.writePretty(monthlyRepayments)
  println(jsonString)

}