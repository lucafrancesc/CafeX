package billingService

import scala.math.BigDecimal.RoundingMode


sealed trait BillError
case class ItemNotFound(name: String) extends BillError
object BillingService {

  def calculateBill(itemNames: List[String]): Either[BillError, BigDecimal] = {

    val initial: Either[BillError, List[MenuItem]] = Right(Nil)

    val itemsEither = itemNames.foldLeft(initial) { (acc, name) =>
      for {
        list <- acc
        item <- Menu.items.get(name).toRight(ItemNotFound(name))
      } yield list :+ item
    }

    itemsEither.map { itemsFound =>
      val totalBeforeCharge = itemsFound.map(_.price).sum
      val serviceCharge = calculateServiceCharge(itemsFound, totalBeforeCharge)
      totalBeforeCharge + serviceCharge
    }
  }

  private[billingService] def calculateServiceCharge(itemsFound: List[MenuItem], total: BigDecimal): BigDecimal = {
    val hasFood = itemsFound.exists(_.itemType == Food)
    val hasHotFood = itemsFound.exists(item => item.itemType == Food && item.isHot)

    (hasHotFood, hasFood) match {
      case (true, _) => (total * 0.20).min(BigDecimal(20))
      case (_, true) => (total * 0.10).setScale(2, RoundingMode.HALF_UP)
      case _ => BigDecimal(0)
    }
  }
}