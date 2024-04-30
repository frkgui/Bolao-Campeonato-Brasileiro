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


    public ArrayList<String> getPalpitePartidas() {
        return palpitePartidas;
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

    public String getPartidaString(int indexPartida){
        String timeCasa = this.partidasCampeonato.get(indexPartida).getTimeUm().getNomeDoTime();
        String timeVisitante = this.partidasCampeonato.get(indexPartida).getTimeDois().getNomeDoTime();
        return timeCasa + " " + palpitePartidas.get(indexPartida) + " " + timeVisitante;
    }
}
