import com.fasterxml.jackson.annotation.JsonFormat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import com.google.gson.reflect.TypeToken
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

data class Order(
    val orderId: Int,
    val creationDate: String,
    val orderLines: List<OrderLine>
    )

data class OrderLine(
    val productId: Int,
    val name: String,
    val quantity: Int,
    val unitPrice: BigDecimal
)
fun main(args: Array<String>)
{
    val jsonString: String = File("./src/jsonTest.json").readText(Charsets.UTF_8)
    val gson = GsonBuilder().setPrettyPrinting().create()
   var mylist: List<Order> = gson.fromJson(jsonString, object : TypeToken<List<Order>>() {}.type)
    val checkAns = totalDailySales(mylist)
    println(checkAns)
}

fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {

    val ans = HashMap<DayOfWeek,Int>()
    for (order in orders)
    {
        for (orderline in order.orderLines)
        {
            var localDateTime = LocalDateTime.parse(order.creationDate)
            if (!ans.containsKey(localDateTime.dayOfWeek))
                ans[localDateTime.dayOfWeek] = orderline.quantity
            else

                ans[localDateTime.dayOfWeek] =   ans[localDateTime.dayOfWeek].hashCode() + orderline.quantity

        }

    }
    return ans
}