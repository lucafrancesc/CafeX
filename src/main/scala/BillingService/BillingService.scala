package BillingService

object BillingService {

  def calculateBill(itemNames: List[String]): BigDecimal = {
    val itemsFound = itemNames.flatMap { name =>
      Menu.items.get(name)
    }

    itemsFound.map(_.price).sum
  }

}
