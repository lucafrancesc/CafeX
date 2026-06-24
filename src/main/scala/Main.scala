import billingService.{BillingService, ItemNotFound, Menu}

object Main {

  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      printUsage()
    } else {
      val itemNames = args.toList

      BillingService.calculateBill(itemNames) match {
        case Right(totalBill) =>
          println(s"Successfully processed order!")
          println(s"---------------------------")
          println(s"Total Bill: £${totalBill.setScale(2, scala.math.BigDecimal.RoundingMode.HALF_UP)}")

        case Left(ItemNotFound(missingItem)) =>
          System.err.println(s"Error: The item '$missingItem' is not on our menu.")
          System.err.println("Please check spelling and try again.")
          sys.exit(1)
      }
    }
  }

  private def printUsage(): Unit = {
    println("Cafe Billing Service CLI")
    println("========================")
    println("Usage: run [item1] [item2] ... [itemN]")
    println("\nAvailable Menu Items:")
    Menu.items.keys.toList.sorted.foreach(name => println(s" - $name"))
    println("\nExample:")
    println("  run \"Cola\" \"Steak Sandwich\"")
  }
}
