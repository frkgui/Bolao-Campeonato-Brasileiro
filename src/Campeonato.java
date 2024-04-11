import java.util.ArrayList;
import java.util.List;

public class Campeonato {

    private String nomeDoCampeonato;
    private ArrayList<Time> listaDeTimes = new ArrayList<Time>();
    private ArrayList<Partida> listaDePartidas;
    private Integer totalDePartidas;


    public Campeonato(String nomeDoCampeonato) {
      this.nomeDoCampeonato = nomeDoCampeonato;
    }



    public ArrayList<Time> getListaDeTimes() {
        return listaDeTimes;
    }

    public void setListaDeTimes(ArrayList<Time> listaDeTimes) {
        this.listaDeTimes = listaDeTimes;
    }

    public ArrayList<Partida> getListaDePartidas() {
        return listaDePartidas;
    }

    public void setListaDePartidas(ArrayList<Partida> listaDePartidas) {
        this.listaDePartidas = listaDePartidas;
    }

    public Integer getTotalDePartidas() {
        return totalDePartidas;
    }

    public void setTotalDePartidas(Integer totalDePartidas) {
        this.totalDePartidas = totalDePartidas;
    }

    public void adicionarPartida(Partida partida){

    }

    // métodos

    public void iniciarRodada(ArrayList<Partida> listaDePartidas){

    }

    public void adicionarTime(Time time){
        this.listaDeTimes.add(time);
    }
}
