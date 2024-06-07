fun Q2main() {
    val totalSims = 100

    val probabilidades = cumulativeProbabilities(2.6, 8)
    val clientesNoDia = List(totalSims) { getQntCliente(probabilidades, 8) }
    val produtosProduzidos = List(totalSims) { getProdutosProduzidos() }

    var estoque = 0
    var lucro = 0
    val lucroDiario: ArrayList<Int> = ArrayList()

    for(dia in 0..< totalSims) {
        val recebimento = produtosProduzidos[dia];
        estoque += recebimento
        var lucroDoDia = 0
        var vendas = 0
        for (cliente in 0..clientesNoDia[dia]) {
            if(estoque == 0)
                continue
            if (isClienteGettingProduto()) {
                estoque -= 1
                lucroDoDia += 100
                vendas += 1
            } else {
                lucroDoDia -= 40
            }
        }
        lucro+=lucroDoDia
        lucroDiario.add(lucroDoDia)
//        println("Recebido: $recebimento | Vendas: $vendas | Estoque: $estoque | Lucro: $lucro | Lucro Diario: $lucroDoDia | Clientes do dia: ${clientesNoDia[dia]}")
    }

    println("Média clientes: ${ clientesNoDia.reduce { acc, i -> acc + i } / totalSims }")

    plotQQ(totalSims,
        lucroDiario,
        "q2-lucroDiario",
        "Lucro Diário",
        "Estoque final de %d, Lucro diário médio de %d".format(estoque, lucro/totalSims))
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
