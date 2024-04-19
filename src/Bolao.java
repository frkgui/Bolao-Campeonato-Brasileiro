import java.util.ArrayList;

public class Bolao {
    private String nomeDoApostador;
    private ArrayList<String> palpitePartidas = new ArrayList<>();

    private ArrayList<Partida> partidasCampeonato = new ArrayList<>();
    private Integer pontuacaoTotal = 0;

    public Bolao(String nomeDoApostador) {
        this.nomeDoApostador = nomeDoApostador;
    }

    public String getNomeDoApostador() {
        return nomeDoApostador;
    }

    public void setNomeDoApostador(String nomeDoApostador) {
        this.nomeDoApostador = nomeDoApostador;
    }

    public ArrayList<String> getPalpitePartidas() {
        return palpitePartidas;
    }

    public void setPalpitePartidas(ArrayList<String> palpitePartidas) {
        this.palpitePartidas = palpitePartidas;
    }

    public ArrayList<Partida> getPartidasCampeonato() {
        return partidasCampeonato;
    }

    public void setPartidasCampeonato(ArrayList<Partida> partidasCampeonato) {
        this.partidasCampeonato = partidasCampeonato;
    }

    public Integer getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(Integer pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }
}
