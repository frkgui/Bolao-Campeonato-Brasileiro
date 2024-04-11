import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Variáveis iniciais
        int opcaoMenuInicial = 0;
        int opcaoMenuPartidas = 0;
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
                    //o 4 é o numero minimo de times registrados para prosseguir
                    if (campeonato.getListaDeTimes().size() < 4){
                        System.out.println("\nNumero de times minimo para o campeonato não foi a atingido!");
                        System.out.print("Faltam "+ (4-campeonato.getListaDeTimes().size())+" times para iniciar o campeonato\n");
                        break;
                    }

                    do {
                        // Menu de partidas!
                        System.out.println("""
                        \nOpções Disponiveis:\n
                        (1) - Criar Partida
                        (0) - Voltar;
                        """);
                        System.out.print("Digite a opção desejada: ");
                        opcaoMenuPartidas = input.nextInt();
                        input.nextLine();

                        switch (opcaoMenuPartidas){
                            case 1:
                                System.out.print("Para a partida escolha o time principal: ");
                                String time1 = input.nextLine();
                                Time time1Encontrado = null;

                                for (int i = 0; i < campeonato.getListaDeTimes().size(); i++) {
                                    if(time1.equalsIgnoreCase(campeonato.getListaDeTimes().get(i).getNomeDoTime())){
                                        time1Encontrado = campeonato.getListaDeTimes().get(i);
                                    }
                                }
                                if (time1Encontrado == null) {
                                    System.out.println("Time não encontrado!");
                                }

                                System.out.print("Para a partida escolha o time visitante: ");
                                String time2 = input.nextLine();

                                Time time2Encontrado = null;

                                for (int i = 0; i < campeonato.getListaDeTimes().size(); i++) {
                                    if(time2.equalsIgnoreCase(campeonato.getListaDeTimes().get(i).getNomeDoTime())){
                                        time2Encontrado = campeonato.getListaDeTimes().get(i);
                                    }
                                }
                                if (time2Encontrado == null) {
                                    System.out.println("Time não encontrado!");
                                }

                                //instanciando a classe partida e colocando os times encontrados no construtor da classe
                                Partida partida = new Partida(time1Encontrado,time2Encontrado);

                                System.out.print("Você deseja escolher o resultado da partida? S ou N\n");
                                String opcaoEscolhaDeResultado = input.nextLine();

                                //se for S podera escolher o placar
                                if (opcaoEscolhaDeResultado.equalsIgnoreCase("S")) {
                                    System.out.print("Defina o placar de "+time1Encontrado.getNomeDoTime()+" :");
                                    partida.setPlacarTimeUm(input.nextInt());
                                    System.out.print("Defina o placar de "+time2Encontrado.getNomeDoTime()+" :");
                                    partida.setPlacarTimeDois(input.nextInt());
                                //se for N o sistemas vai gerar numeros aleatorios entre o 1 a 10 para ambos placares
                                } else if (opcaoEscolhaDeResultado.equalsIgnoreCase("N")) {
                                    System.out.print("Estamos gerando o resultado para você");
                                    Random random = new Random();
                                    int placarTime1 = random.nextInt(10)+1;
                                    partida.setPlacarTimeUm(placarTime1);
                                    int placarTime2 = random.nextInt(10)+1;
                                    partida.setPlacarTimeDois(placarTime2);
                                }

                                System.out.print("\nPartida criada: "+ time1Encontrado.getNomeDoTime() + " x "+ time2Encontrado.getNomeDoTime());
                                System.out.print("Placar criado: "+ partida.getPlacarTimeUm() + " x "+ partida.getPlacarTimeDois()+"\n");


                                //setando a partida na lista de partidas do campeonato
                                campeonato.getListaDePartidas().add(partida);

                                break;
                        }





                    }while (opcaoMenuPartidas != 0);


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