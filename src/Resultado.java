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

    public void setListaDeBoloes(ArrayList<Bolao> listaDeBoloes) {
        this.listaDeBoloes = listaDeBoloes;
    }

    public Bolao getBolaoVencedor() {
        return bolaoVencedor;
    }

    public void setBolaoVencedor(Bolao bolaoVencedor) {
        this.bolaoVencedor = bolaoVencedor;
    }
}
