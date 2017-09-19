package com.platform7.serializers

import com.platform7.Format
import com.platform7.models.MonthlyAmortization
import net.liftweb.json.{Formats, Serializer, TypeInfo, JDouble, JValue, JArray}

class MonthlyAmortizationSerializer extends Serializer[MonthlyAmortization]{
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), MonthlyAmortization] = ???

  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: MonthlyAmortization => JArray(List(
      JDouble(Format.toDouble(x.openingBalance)),
      JDouble(Format.toDouble(x.loanRepayment)),
      JDouble(Format.toDouble(x.interestCharged)),
      JDouble(Format.toDouble(x.capitalRepaid)),
      JDouble(Format.toDouble(x.closingBalance))
    ))
  }
}