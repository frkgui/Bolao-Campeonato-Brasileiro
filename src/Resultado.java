import java.util.ArrayList;

public class Resultado {
    private Time timeVencedor;
    private ArrayList<Bolao> listaDeBoloes = new ArrayList<>();
    private Bolao bolaoVencedor;

    public Resultado() {}

    public Time getTimeVencedor() {
        return timeVencedor;
    }

    public void setTimeVencedor(Time timeVencedor) {
        this.timeVencedor = timeVencedor;
    }

    public ArrayList<Bolao> getListaDeBoloes() {
        return listaDeBoloes;
    }

    public String getNomesDosBoloes(int index){
        return "Bolao de " + listaDeBoloes.get(index).getNomeDoApostador();
    }

    public void setListaDeBoloes(ArrayList<Bolao> listaDeBoloes) {
        this.listaDeBoloes = listaDeBoloes;
    }

    public Bolao getBolaoVencedor() {
        return bolaoVencedor;
    }

    public void setBolaoVencedor(Bolao bolaoVencedor) {
        this.bolaoVencedor = bolaoVencedor;
    }

    // Método que retorna um bolão específico...
    public void visualizarBolao(int idDoBolao){
        for (int i = 0; i < getListaDeBoloes().get(idDoBolao).getPalpitePartidas().size(); i++) {
            System.out.println(getListaDeBoloes().get(idDoBolao).getPartidaString(i));
        }
    }

}
