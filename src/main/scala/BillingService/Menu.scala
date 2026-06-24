package BillingService

object Menu {

  private val Cola =
    MenuItem("Cola", 0.50, Drink, isHot = false)

  private val Coffee =
    MenuItem("Coffee", 1.00, Drink, isHot = true)

  private val CheeseSandwich =
    MenuItem("Cheese Sandwich", 2.00, Food, isHot = false)

  private val SteakSandwich =
    MenuItem("Steak Sandwich", 4.50, Food, isHot = true)

  val items: Map[String, MenuItem] =
    List(
      Cola,
      Coffee,
      CheeseSandwich,
      SteakSandwich
    ).map(i => i.name -> i).toMap
}


sealed trait ItemType

case object Food extends ItemType

case object Drink extends ItemType

case class MenuItem(name: String, price: BigDecimal, itemType: ItemType, isHot: Boolean)