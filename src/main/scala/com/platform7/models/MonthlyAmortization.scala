package com.platform7.models

import com.platform7.Format.roundAmount

case class MonthlyAmortization(openingBalance: BigDecimal,
                               loanRepayment: BigDecimal,
                               interestCharged: BigDecimal,
                               capitalRepaid: BigDecimal,
                               closingBalance: BigDecimal) {
  override def toString() =
    s"MonthlyAmortization,${roundAmount(openingBalance)},${roundAmount(loanRepayment)},${roundAmount(interestCharged)},${roundAmount(capitalRepaid)},${roundAmount(closingBalance)}"
}

