package com.platform7

import scala.math.BigDecimal.RoundingMode


object Format {
  def roundAmount(value: BigDecimal, scale: Int = 2): BigDecimal = value.setScale(scale, RoundingMode.HALF_UP)
  def toDouble(value: BigDecimal): Double = roundAmount(value, 2).toDouble
}
