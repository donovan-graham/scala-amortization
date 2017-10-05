package com.platform7.controllers

import com.platform7.models.{AmortizationRequest, AmortizationResponse}
import com.platform7.utils.Amortization.calculateMonthlyRepayments
import com.platform7.utils.Format.roundAmount
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller


// curl http://localhost:8080/amortization?principal=1250000&rate=9.25&years=20
// curl -H 'Accept-Encoding:gzip' 'http://localhost:8080/static/data.json' > data.json.gz

class MainController extends Controller {

  get("/health") { request: Request =>
    response.ok.plain("OK")
  }


  get("/static/:*") { request: Request =>
    response.ok.file(request.params("*"))
  }

  get("/amortization") { request: AmortizationRequest =>
    val principalValue = BigDecimal(request.principal)
    val annualInterestRate = BigDecimal(request.rate / 100)
    val years = request.years
    val ma = calculateMonthlyRepayments(principalValue, annualInterestRate, years, None)

    AmortizationResponse(
      ma.map(x => roundAmount(x.openingBalance)),
      ma.map(x => roundAmount(x.loanRepayment)),
      ma.map(x => roundAmount(x.interestCharged)),
      ma.map(x => roundAmount(x.capitalRepaid)),
      ma.map(x => roundAmount(x.closingBalance))
    )
  }
}
