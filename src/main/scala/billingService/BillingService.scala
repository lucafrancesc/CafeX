package billingService

import scala.math.BigDecimal.RoundingMode

object BillingService {

  def calculateBill(itemNames: List[String]): BigDecimal = {
    val itemsFound: List[MenuItem] = itemNames.flatMap { name =>
      Menu.items.get(name)
    }

    if (itemsFound.isEmpty) return BigDecimal(0)

    val totalBeforeServiceCharge: BigDecimal = itemsFound.map(_.price).sum
    val serviceCharge = calculateServiceCharge(itemsFound, totalBeforeServiceCharge)

    totalBeforeServiceCharge + serviceCharge
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