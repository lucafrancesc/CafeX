package billingService

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BillingServiceSpec extends AnyFlatSpec with Matchers {

  private def bd(value: Double): BigDecimal = BigDecimal(value).setScale(2, BigDecimal.RoundingMode.HALF_UP)

  private val billingService = new BillingService(InMemoryMenuItemsRepo)

  "An empty list of items" should "total to 0" in {
    billingService.calculateBill(Nil) should ===(Right(bd(0.00)))
  }

  "A single drink item" should "add up to its price with no service charge" in {
    billingService.calculateBill(List("Cola")) should ===(Right(bd(0.50)))
  }

  "A list with a non-existing item" should "be safely ignored and return zero" in {
    billingService.calculateBill(List("Soda", "Cola")) should ===(Left(ItemNotFound("Soda")))
  }

  "A mix of cold food and drinks" should "apply a 10% service charge on the total" in {
    billingService.calculateBill(List("Cola", "Coffee", "Cheese Sandwich")) should ===(Right(bd(3.85)))
  }

  "A bill containing hot food" should "apply a 20% service charge on the total" in {
    billingService.calculateBill(List("Cola", "Coffee", "Cheese Sandwich", "Steak Sandwich")) should ===(Right(bd(9.60)))
  }

  "Only drinks" should "not apply any service charge" in {
    billingService.calculateBill(List("Cola", "Coffee", "Cola", "Coffee")) should ===(Right(bd(3.00)))
  }

  "Only buying cold food items" should "apply a 10% service charge" in {
    billingService.calculateBill(List("Cheese Sandwich", "Cheese Sandwich")) should ===(Right(bd(4.40)))
  }

  "Only buying hot food items" should "apply a 20% service charge" in {
    billingService.calculateBill(List("Steak Sandwich")) should ===(Right(bd(5.40)))
  }

  "A massive hot food order" should "cap the service charge at £20" in {
    val largeOrder = List.fill(30)("Steak Sandwich")
    billingService.calculateBill(largeOrder) should ===(Right(bd(155.00)))
  }
}