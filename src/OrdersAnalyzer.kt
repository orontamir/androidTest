import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

class OrdersAnalyzer{
    data class Order(

        val creationDate: LocalDateTime,
        val orderId: Int,
        val orderLines: List<OrderLine>

    )


    data class OrderLine(
        val productId: Int,
        val name: String,
        val quantity: Int,
        val unitPrice: BigDecimal
    )


    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {

        val ans = HashMap<DayOfWeek,Int>()
        for (order in orders)
        {
            for (orderline in order.orderLines)
            {
                if (!ans.containsKey(order.creationDate.dayOfWeek))
                    ans[order.creationDate.dayOfWeek] = orderline.quantity
                else

                    ans[order.creationDate.dayOfWeek] =   ans[order.creationDate.dayOfWeek].hashCode() + orderline.quantity

            }

        }
        return ans
    }






}



