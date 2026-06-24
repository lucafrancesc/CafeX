import billingService.{BillingService, InMemoryMenuItemsRepo, ItemNotFound, MenuItemsRepo}

object Main {

  private val menuItemRepo: MenuItemsRepo = InMemoryMenuItemsRepo

  private val billingService = new BillingService(menuItemRepo)

  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      printUsage()
    } else {
      billingService.calculateBill(args.toList) match {
        case Right(totalBill) =>
          println("Successfully processed order!")
          println("---------------------------")
          println(s"Total Bill: £${totalBill.setScale(2, scala.math.BigDecimal.RoundingMode.HALF_UP)}")

        case Left(ItemNotFound(missingItem)) =>
          System.err.println(s"Error: The item '$missingItem' is not on our menu.")
          sys.exit(1)
      }
    }
  }

  private def printUsage(): Unit = {
    println("CafeX Billing Service CLI")
    println("========================")
    println("Usage: run [item1] [item2] ... [itemN]\n")
    println("Available Menu Items:")
    menuItemRepo.listItems().sorted.foreach(name => println(s" - $name"))
  }
}
