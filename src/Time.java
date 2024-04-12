public class Time {

    // Declaração de Variaveis

    private String nomeDoTime;
    private Integer pontosGanhos;
    private Integer GolsMarcados;
    private Integer GolsSofridos;
    private Integer saldoDeGols;
    private Integer numeroDeVitorias;
    private double mediaDeGols;

    // Métodos

    // Construtores, Getters & Setters

    public Time(String nomeDoTime) {
        this.nomeDoTime = nomeDoTime;
        this.pontosGanhos = 0;
        this.GolsMarcados = 0;
        this.GolsSofridos = 0;
        this.saldoDeGols = 0;
        this.numeroDeVitorias = 0;
        this.mediaDeGols = 0;
    }

    public Time() {
    }

    public String getNomeDoTime() {
        return nomeDoTime;
    }

    public void setNomeDoTime(String nomeDoTime) {
        this.nomeDoTime = nomeDoTime;
    }

    public Integer getPontosGanhos() {
        return pontosGanhos;
    }

    public void setPontosGanhos(Integer pontosGanhos) {
        this.pontosGanhos = pontosGanhos;
    }

    public Integer getGolsMarcados() {
        return GolsMarcados;
    }

    public void setGolsMarcados(Integer golsMarcados) {
        GolsMarcados = golsMarcados;
    }

    public Integer getGolsSofridos() {
        return GolsSofridos;
    }

    public void setGolsSofridos(Integer golsSofridos) {
        GolsSofridos = golsSofridos;
    }

    public Integer getSaldoDeGols() {
        return saldoDeGols;
    }

    public void setSaldoDeGols(Integer saldoDeGols) {
        this.saldoDeGols = saldoDeGols;
    }

    public Integer getNumeroDeVitorias() {
        return numeroDeVitorias;
    }

    public void setNumeroDeVitorias(Integer numeroDeVitorias) {
        this.numeroDeVitorias = numeroDeVitorias;
    }

    public double getMediaDeGols() {
        return mediaDeGols;
    }

    public void setMediaDeGols(double mediaDeGols) {
        this.mediaDeGols = mediaDeGols;
    }
}
