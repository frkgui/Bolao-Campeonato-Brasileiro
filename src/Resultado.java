import java.util.ArrayList;
import java.util.Objects;

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
    public Bolao getBolao(String nome){
        for (int i = 0; i < getListaDeBoloes().size(); i++) {
            if(Objects.equals(nome, getListaDeBoloes().get(i).getNomeDoApostador())){
                return getListaDeBoloes().get(i);
            };
        }
        //se nao encontrar retornara null
        System.out.println("Bolao nao encontrado!");
        return null;

    }
    public Bolao encontrarBolaoVencedor(){
        int maiorPontucao = 0;
        for (int i = 0; i < getListaDeBoloes().size(); i++) {
            if(maiorPontucao < getListaDeBoloes().get(i).getPontuacaoTotal()){
                maiorPontucao = getListaDeBoloes().get(i).getPontuacaoTotal();
            }
        }
        for (int i = 0; i < getListaDeBoloes().size(); i++) {
            if(getListaDeBoloes().get(i).getPontuacaoTotal() == maiorPontucao){
                return getListaDeBoloes().get(i);
            }
        }
        System.out.println("Não encontramos um vencedor!!!");
        return  null;
    }

    public void vizualizarPalpites(Bolao bolao){
        System.out.println(bolao.getNomeDoApostador()+" seu bolão possui os seguintes palpites");
        System.out.println("\nPalpites: ");
        for (int i = 0; i < bolao.getPalpitePartidas().size(); i++) {
            System.out.println(bolao.getPartidasCampeonato().get(i).getTimeUm().getNomeDoTime()+" x "
                    +bolao.getPartidasCampeonato().get(i).getTimeDois().getNomeDoTime()+": "+bolao.getPalpitePartidas().get(i));
        }
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "timeVencedor=" + timeVencedor +
                ", listaDeBoloes=" + listaDeBoloes +
                ", bolaoVencedor=" + bolaoVencedor +
                '}';
    }
}
