package com.platform7.utils

import com.platform7.models.CashFlow

object Distrubition {
  private def periodToYear(period: Int): Int = Math.floor((period / 12).toDouble).toInt

  private def balanceFlows(cashflows: Seq[CashFlow], openingBalance: BigDecimal): (Seq[CashFlow], BigDecimal) = {
    val inflows = cashflows.filter(_.amount >= 0)
    val outflows = cashflows.filter(_.amount < 0)

    val totalIn = inflows.map(_.amount).sum
    val totalOut = outflows.map(_.amount).sum
    val totalAvailable = openingBalance + totalIn

    if (totalAvailable < totalOut) {
      val groupCount = outflows.map(_.group).distinct.length
      val monthlyOutflow = totalAvailable / BigDecimal(12 * groupCount)
      val balancedOutflows = outflows.map(_.copy(amount = monthlyOutflow))
      val closingBalance = totalAvailable - balancedOutflows.map(_.amount).sum
      (inflows ++ balancedOutflows, closingBalance)
    } else {
      (cashflows, totalAvailable - totalOut)
    }
  }

  def calculate(cashflows: Seq[CashFlow], balance: BigDecimal): Seq[CashFlow] = {

    def annualFlows(flows: List[(Int, Seq[CashFlow])], openingBalance: BigDecimal): Seq[CashFlow] = flows match {
      case head :: tail =>
        val (flow, closingBalance) = balanceFlows(head._2, openingBalance)
        flow ++ annualFlows(tail, closingBalance)
      case _ => Nil
    }

    annualFlows(cashflows.groupBy(c => periodToYear(c.period)).toList, balance)
  }
}
