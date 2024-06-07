fun Q2main() {
    val totalSims = 100

    val probabilidades = cumulativeProbabilities(3.0, 7)
    val clientes = List(totalSims) { getQntCliente(probabilidades, 8) }
    val produtosProduzidos = List(totalSims) { getProdutosProduzidos() }

    var estoque = 0
    var lucroMedio = 0
    val lucroDiario: ArrayList<Int> = ArrayList()

    for(index in 0..< totalSims) {
        estoque += produtosProduzidos[index];
        var lucro = 0
        for (cliente in 0..clientes[index]) {
            if(estoque == 0)
                continue
            if (isClienteGettingProduto()) {
                estoque -= 1
                lucro += 100
            } else {
                lucro -= 40
            }
        }
        lucroMedio+=lucro
        lucroDiario.add(lucro)
    }
    plotQQ(totalSims, lucroDiario, "q2-lucroDiario", "Lucro Diário", "Estoque médio de %d, Lucro médio de %d".format(estoque/totalSims, lucroMedio/totalSims))
}

fun isClienteGettingProduto(): Boolean {
    val prob = getRandomBetween0And1()
    if(prob <= 0.7)
        return true
    return false
}

fun getProdutosProduzidos(): Int{
    val prob = getRandomBetween0And1()
    if(prob <= 0.15)
        return 10
    if(prob <= 0.35)
        return 14
    if(prob <= 0.65)
        return 18
    if(prob <= 0.85)
        return 22
    return 26
}

fun getQntCliente(listaProbs: DoubleArray, repeats: Int): Int {
    return List(repeats) { listaProbs.indexOfFirst { getRandomBetween0And1() < it } + 1 }.reduce { acc, i -> i+acc }
}
