package BillingService

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BillingServiceSpec extends AnyFlatSpec with Matchers {

  "An empty list of items" should "total to 0" in {
    BillingService.calculateBill(Nil) shouldBe BigDecimal(0)
  }
}