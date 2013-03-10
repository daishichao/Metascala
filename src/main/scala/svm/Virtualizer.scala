package svm

import imm.Type
import virt.Obj

object Virtualizer {
  def fromVirtual[T](x: Any): T = {
    def cloneArray[T](x: Array[T]): Array[T] = {
      val newArray = x.clone()
      for(i <- 0 until x.length){
        newArray(i) = fromVirtual[T](newArray(i))
      }
      newArray
    }
    x match{
      case null => null
      case x: Boolean => x
      case x: Byte => x
      case x: Char => x
      case x: Short => x
      case x: Int => x
      case x: Long => x
      case x: Float => x
      case x: Double => x
      case x: Array[Any] => cloneArray(x)
      case x: Array[Boolean] => cloneArray(x)
      case x: Array[Byte] => cloneArray(x)
      case x: Array[Char] => cloneArray(x)
      case x: Array[Short] => cloneArray(x)
      case x: Array[Int] => cloneArray(x)
      case x: Array[Long] => cloneArray(x)
      case x: Array[Float] => cloneArray(x)
      case x: Array[Double] => cloneArray(x)
      case virt.Obj("java/lang/String", members) => new String(members(0)("value").asInstanceOf[Array[Char]])
      case virt.Obj("java/lang/Integer", members) => members(0)("value").asInstanceOf[Int]
      case virt.Obj("java/lang/Double", members) => members(0)("value").asInstanceOf[Double]
      case virt.Obj(cls, members) =>
    }
  }.asInstanceOf[T]


  def toVirtual[T](x: Any)(implicit vm: VM): T = {
    def cloneArray  [T](x: Array[T]): Array[T] = {
      val newArray = x.clone()
      for(i <- 0 until x.length){
        newArray(i) = toVirtual[T](newArray(i))
      }
      newArray
    }
    x match{
      case null => null
      case x: Boolean => x
      case x: Byte => x
      case x: Char => x
      case x: Short => x
      case x: Int => x
      case x: Long => x
      case x: Float => x
      case x: Double => x
      case x: Array[Any] => cloneArray(x)
      case x: Array[Boolean] => cloneArray(x)
      case x: Array[Byte] => cloneArray(x)
      case x: Array[Char] => cloneArray(x)
      case x: Array[Short] => cloneArray(x)
      case x: Array[Int] => cloneArray(x)
      case x: Array[Long] => cloneArray(x)
      case x: Array[Float] => cloneArray(x)
      case x: Array[Double] => cloneArray(x)
      case x: String => virt.Obj("java/lang/String", "value" -> x.toCharArray)
    }
  }.asInstanceOf[T]
}
