import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Variáveis iniciais
        int opcaoMenuInicial = 0;
        String nomeDoCampeonato;


        System.out.println("-- Seja bem vindo ao Simulador & Bolão de Campeonatos! --");

        // Criação do campeonato
        System.out.print("\nDigite o nome do Campeonato a ser criado: ");
        nomeDoCampeonato = input.nextLine();
        Campeonato campeonato = new Campeonato(nomeDoCampeonato);
        System.out.println("\nCampeonato: " + nomeDoCampeonato + ", criado com sucesso!");

        // Menu que o usuario terá acesso!
        do{
            System.out.println("""
                    \nOpções Disponiveis:\n
                    (1) - Cadastrar Time; 
                    (2) - Iniciar campeonato;
                    (0) - Sair;
                    """);
            System.out.print("Digite a opção desejada: ");
            opcaoMenuInicial = input.nextInt();
            input.nextLine();

            switch (opcaoMenuInicial){

                case 1:
                    System.out.print("\nDigite o nome do time a ser cadastrado: ");
                    String nomeDoTime = input.nextLine();

                    Time time = new Time(nomeDoTime);
                    campeonato.adicionarTime(time);

                    System.out.println("\nTime: " + nomeDoTime + " adicionado ao campeonato!");
                    System.out.println("Numero de times cadastrados: " + campeonato.getListaDeTimes().size());

                    break;
                case 2:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Digite uma opção válida!");
                    break;

            }


        }while(opcaoMenuInicial != 0);


    }
}