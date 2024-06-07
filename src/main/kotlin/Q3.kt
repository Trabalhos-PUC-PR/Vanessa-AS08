fun Q3main() {
    val totalSims = 100
    val totalSemAtendimento = ArrayList<Int>()
    val totalComAtendimento = ArrayList<Int>()
    repeat (totalSims) {
        val horasDia = 4
        var tempoDia = horasDia * 60
        val totalAtendimentosPorDia = getNoAtendimentosDia(horasDia).reduce { acc, i -> i + acc }

        var semAtendimento = 0
        var comAtendimento = 0
        repeat(totalAtendimentosPorDia) {
            if (tempoDia > 5) {
                tempoDia -= getTempoAtendimento()
                comAtendimento++
            } else {
                semAtendimento++
            }
        }
        totalComAtendimento.add(comAtendimento)
        totalSemAtendimento.add(semAtendimento)
    }

    plotQQ(totalSims, totalSemAtendimento,
        "q3-sem-atendimentos-por-dia",
        "Pessoas Não Atendidas",
        "Média sem atendimento: %d".format(totalSemAtendimento.reduce { acc, i -> i+acc }/totalSims))
    plotQQ(totalSims, totalComAtendimento,
        "q3-com-atendimentos-por-dia",
        "Pessoas Com Atendimento",
        "Média com atendimento: %d".format(totalComAtendimento.reduce { acc, i -> i+acc }/totalSims))
}

fun getNoAtendimentosDia(horasDisp: Int): List<Int> {
    return List(horasDisp) { getNoAtendimentosHora() }
}
fun getNoAtendimentosHora(): Int {
    val prob = getRandomBetween0And1()
    if (prob < 0.02)
        return 0
    if (prob < 0.09)
        return 1
    if (prob < 0.24)
        return 2
    if (prob < 0.43)
        return 3
    if (prob < 0.63)
        return 4
    if (prob < 0.79)
        return 5
    if (prob < 0.89)
        return 6
    if (prob < 0.95)
        return 7
    if (prob < 0.98)
        return 8
    if (prob < 0.99)
        return 9
    return 10
}
fun getTempoAtendimento(): Int {
    val prob = getRandomBetween0And1()
    if (prob < 0.1)
        return 5
    if (prob < 0.3)
        return 10
    if (prob < 0.6)
        return 15
    if (prob < 0.85)
        return 20
    if (prob < 0.95)
        return 25
    return 30
}