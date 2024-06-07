fun Q1main() {
    val diasLucroPopular = List(100) { getRandomBetween0And1() }
    val diasLucroLuxoAlto = List(100) { getRandomBetween0And1() }
    val diasLucroLuxoBaixo = List(100) { getRandomBetween0And1() }

    val lucroPopular = diasLucroPopular.reduce { acc, i -> getLucroPopular(i)+acc }
    val lucroLuxoAlto = diasLucroLuxoAlto.reduce { acc, i -> getLucroLuxoAlto(i)+acc }
    val lucroLuxoBaixo = diasLucroLuxoBaixo.reduce { acc, i -> getLucroLuxoBaixo(i)+acc }
    val lucroLuxo = 0.7*lucroLuxoAlto+0.3*lucroLuxoBaixo

    println("lucroPopular esperado: %.2f".format(lucroPopular))
    println("lucroLuxo esperado: %.2f".format(lucroLuxo))
    println()

    val escolhaLucroPopular = lucroPopular*1
    val escolhaLucroLuxo = lucroLuxoAlto*0.7+lucroLuxoBaixo*0.3

    println("escolhaLucroPopular: $escolhaLucroPopular")
    println("escolhaLucroLuxo: $escolhaLucroLuxo")

    val ydlp = diasLucroPopular.map { getLucroPopular(it) }
    val ydlla = diasLucroLuxoAlto.map { getLucroLuxoAlto(it) }
    val ydllb = diasLucroLuxoBaixo.map { getLucroLuxoBaixo(it) }

//    plotBar(listOf(lucroPopular, lucroLuxo), "teste")
    plotQQ(100, ydlp, "q1-plotPopular", "Modelo Popular", "Valor esperado: %.2f".format(lucroPopular))
    plotQQ(100, ydlla, "q1-plotLuxoAlto", "Modelo de Luxo com Alta Demanda", "Valor esperado:  %.2f".format(lucroLuxoAlto))
    plotQQ(100, ydllb, "q1-plotLuxoBaixo", "Modelo de Luxo com Baixa Demanda", "Valor esperado:  %.2f".format(lucroLuxoBaixo))
}

fun getLucroPopular (prob: Double): Int {
    if(prob <= 0.32){
        return 1000
    }else if(prob <= 0.6){
        return 1200
    }else if(prob <= 0.84){
        return 1400
    }
    return 1600
}

fun getLucroLuxoAlto (prob: Double): Int {
    if(prob <= 0.18){
        return 2500
    }else if(prob <= 0.4){
        return 2700
    }else if(prob <= 0.7){
        return 2900
    }else if(prob <= 0.92){
        return 3100
    }
    return 3300
}
fun getLucroLuxoBaixo (prob: Double): Int {
    if(prob <= 0.1){
        return -1000
    }else if(prob <= 0.3){
        return -500
    }else if(prob <= 0.8){
        return 0
    }else if(prob <= 0.95){
        return 500
    }
    return 1000
}