import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.geomBar
import org.jetbrains.letsPlot.geom.geomQQ
import org.jetbrains.letsPlot.geom.geomQQLine
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import kotlin.math.exp
import kotlin.math.pow
import kotlin.random.Random

fun main() {
    Q1main()
    Q2main()
    Q3main()
}

fun getRandomBetween0And1(): Double {
    return Random.nextDouble(0.0, 101.0) / 100.0
}

fun plotQQ(xSize: Int, ys: List<Int>, outputName: String, title: String = "", subTitle: String = "") {
    val data = mapOf<String, Any>("x" to List(xSize){ it+1 }, "y" to ys)
    val fig = letsPlot(data) {sample = ys} + geomQQ(size = 3, alpha = .3) + geomQQLine(size = 1) + ggtitle(title, subTitle)
    ggsave(fig, "$outputName.png")
}

fun plotBar(dataList: List<Double>, outputName: String, title: String = "", subTitle: String = "") {
    val dataMap = mapOf("x" to List(dataList.size) { it+1 }, "y" to dataList)
    val fig = ggplot(dataMap) + geomBar() + ggtitle(title, subTitle)
    ggsave(fig, "$outputName.png")
}

fun poisson(k: Int, lambda: Double): Double {
    return (exp(-lambda) * lambda.pow(k)) / (1..k).fold(1.0) { acc, i -> acc * i }
}

fun cumulativeProbabilities(lambda: Double, maxEvents: Int): DoubleArray {
    val probs = DoubleArray(maxEvents + 1)
    var sum = 0.0
    for (k in 0..maxEvents) {
        val prob = poisson(k, lambda)
        probs[k] = prob
        sum += prob
    }

    for (i in probs.indices) {
        probs[i] /= sum
    }

    for (i in 1..< probs.size) {
        probs[i] += probs[i - 1]
    }
    return probs
}