public class Time {

    // Declaração de Variaveis

    private String nomeDoTime;
    private Integer pontosGanhos;
    private Integer golsMarcados;
    private Integer golsSofridos;
    private Integer saldoDeGols;
    private Integer numeroDeVitorias;
    private double mediaDeGols;

    // Métodos

    // Construtores, Getters & Setters

    public Time(String nomeDoTime) {
        this.nomeDoTime = nomeDoTime;
        this.pontosGanhos = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
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
        return golsMarcados;
    }

    public void setGolsMarcados(Integer golsMarcados) {
        this.golsMarcados += golsMarcados;
    }

    public Integer getGolsSofridos() {
        return golsSofridos;
    }

    public void setGolsSofridos(Integer golsSofridos) {
        this.golsSofridos += golsSofridos;
    }

    public Integer getSaldoDeGols() {
        return saldoDeGols;
    }

    public void setSaldoDeGols() {
        this.saldoDeGols = this.golsMarcados - this.golsSofridos;
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

    public void setMediaDeGols() {
        this.mediaDeGols = this.golsMarcados/this.golsSofridos;
    }

}
