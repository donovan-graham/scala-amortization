package com.platform7

import scala.math.BigDecimal.RoundingMode


object Format {
  def roundAmount(value: BigDecimal): BigDecimal = value.setScale(2, RoundingMode.HALF_UP)
}
