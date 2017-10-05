package com.platform7.models

import com.twitter.finatra.request.QueryParam
import com.twitter.finatra.validation._

case class AmortizationRequest(@Min(1) @QueryParam principal: Int = 1000000,
                               @QueryParam rate: Double = 10.50,
                               @Range(min = 1, max = 100) @QueryParam years: Int = 20)
