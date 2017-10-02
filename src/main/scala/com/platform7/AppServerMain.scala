package com.platform7

import com.platform7.Amortization.calculateMonthlyRepayments
import com.platform7.Format.roundAmount
import com.platform7.models.AmortizationResponse
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.request.QueryParam
import com.twitter.finatra.validation.{Max, Min, Range}



object AppServerMain extends AppServer

class AppServer extends HttpServer {

  override protected def defaultFinatraHttpPort: String = ":8080"

  override protected def defaultHttpServerName: String = "RestServer"

  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add(new MainController)
  }
}



case class AmortizationRequest(@Min(1) @QueryParam principal: Int = 1000000,
                               @QueryParam rate: Double = 10.50,
                               @Range(min = 1, max = 100) @QueryParam years: Int = 20)
//    http://localhost:8080/amortization?principal=1250000&rate=9.25&years=20


class MainController extends Controller {

  get("/health") { request: Request =>
    response.ok.plain("OK")
  }

  // curl -H 'Accept-Encoding:gzip' 'http://localhost:8080/static/data.json' > data.json.gz
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
