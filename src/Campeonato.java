import java.util.ArrayList;

public class Campeonato {

    private String nomeDoCampeonato;
    private ArrayList<Time> listaDeTimes = new ArrayList<>();
    private ArrayList<Partida> listaDePartidas = new ArrayList<>();


    public Campeonato(){

    }

    public Campeonato(String nomeDoCampeonato) {
      this.nomeDoCampeonato = nomeDoCampeonato;
    }

    public void setNomeDoCampeonato(String nomeDoCampeonato) {
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

    public String getNomeDoCampeonato() {
        return nomeDoCampeonato;
    }

    public void adicionarPartida(Partida partida){

    }

    public void iniciarRodada(ArrayList<Partida> listaDePartidas){

    }

    public void adicionarTime(Time time){
        this.listaDeTimes.add(time);
    }

    @Override
    public String toString() {
        return "Campeonato{" +
                "listaDePartidas=" + listaDePartidas +
                '}';
    }
}
