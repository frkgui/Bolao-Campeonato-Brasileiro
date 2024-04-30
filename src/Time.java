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
    public void setGolsMarcadosArquivo(Integer golsMarcados) {
        this.golsMarcados = golsMarcados;
    }
    public void setGolsSofridosArquivo(Integer golsSofridos) {
        this.golsSofridos = golsSofridos;
    }

    public Integer getGolsSofridos() {
        return golsSofridos;
    }

    public Integer getSaldoDeGols() {
        return saldoDeGols;
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

    public void setarDados(Integer golsMarcados, Integer golsSofridos){
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
        this.mediaDeGols = (double) this.golsMarcados /this.golsSofridos;
        this.saldoDeGols = this.golsMarcados - this.golsSofridos;
    }

    public void setSaldoDeGols(Integer saldoDeGols) {
        this.saldoDeGols = saldoDeGols;
    }

    public void setMediaDeGols(double mediaDeGols) {
        this.mediaDeGols = mediaDeGols;
    }
}
