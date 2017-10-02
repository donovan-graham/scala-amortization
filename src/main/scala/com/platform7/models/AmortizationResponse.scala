package com.platform7.models


case class AmortizationResponse(openingBalance: Seq[BigDecimal],
                                loanRepayment: Seq[BigDecimal],
                                interestCharged: Seq[BigDecimal],
                                capitalRepaid: Seq[BigDecimal],
                                closingBalance: Seq[BigDecimal])

