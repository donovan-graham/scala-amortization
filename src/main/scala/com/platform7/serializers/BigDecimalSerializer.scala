package com.platform7.serializers

import com.platform7.Format
import net.liftweb.json.{Formats, Serializer, TypeInfo, JDouble, JValue}


class BigDecimalSerializer extends Serializer[BigDecimal]{
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), BigDecimal] = ???

  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: BigDecimal => JDouble(Format.toDouble(x))
  }
}