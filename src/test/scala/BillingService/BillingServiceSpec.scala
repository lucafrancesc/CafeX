package BillingService

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BillingServiceSpec extends AnyFlatSpec with Matchers {

  "An empty list of items" should "total to 0" in {
    BillingService.calculateBill(Nil) shouldBe BigDecimal(0)
  }

  "A single item" should "add up to its price" in {
    BillingService.calculateBill(List("Cola")) shouldBe BigDecimal(0.5)
  }

  "A non existing item" should "return zero" in {
    BillingService.calculateBill(List("Soda")) shouldBe BigDecimal(0)
  }

  "A bill with multiple items" should "total purchased items" in {
    BillingService.calculateBill(List("Cola", "Coffee", "Cheese Sandwich")) shouldBe BigDecimal(3.5)
  }

  "A bill with all menu items" should "total purchased items" in {
    BillingService.calculateBill(List("Cola", "Coffee", "Cheese Sandwich", "Steak Sandwich")) shouldBe BigDecimal(8)
  }
}