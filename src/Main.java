import java.util.ArrayList;
import java.util.Objects;
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
                        (2) - Ver Tabela
                        (3) - Mostrar Classificação
                        (0) - Voltar
                        """);
                        System.out.print("Digite a opção desejada: ");
                        opcaoMenuPartidas = input.nextInt();
                        input.nextLine();

                        switch (opcaoMenuPartidas){
                            case 1:
                                System.out.print("Para a partida escolha o time principal: ");
                                String time1 = input.nextLine();
                                Time time1Encontrado = new Time();


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

                                Time time2Encontrado = new Time();

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
                                    int placarTime1 = random.nextInt(3)+1;
                                    partida.setPlacarTimeUm(placarTime1);
                                    int placarTime2 = random.nextInt(3)+1;
                                    partida.setPlacarTimeDois(placarTime2);
                                }

                                //ARRUMAR DEPOIS
                                //setando os atributos dos times para depois salavar no campeonato
                                time1Encontrado.setGolsMarcados(partida.getPlacarTimeUm());
                                time1Encontrado.setGolsSofridos(partida.getPlacarTimeDois());
                                time1Encontrado.setSaldoDeGols(time1Encontrado.getGolsMarcados() - time1Encontrado.getGolsSofridos());
                                time1Encontrado.setMediaDeGols((double) time1Encontrado.getGolsMarcados()/time1Encontrado.getGolsSofridos());

                                //time de casa ganhou -> entao ele ganha 1 vitoria e 3 pontos
                                if (partida.getPlacarTimeUm()>partida.getPlacarTimeDois()){
                                    time1Encontrado.setNumeroDeVitorias(time1Encontrado.getNumeroDeVitorias()+1);
                                    time1Encontrado.setPontosGanhos(time1Encontrado.getPontosGanhos()+3);
                                }

                                //time de fora ganhou -> entao ele ganha 1 vitoria e 3 pontos
                                if (partida.getPlacarTimeDois()> partida.getPlacarTimeUm()) {
                                    time2Encontrado.setNumeroDeVitorias(time2Encontrado.getNumeroDeVitorias()+1);
                                    time2Encontrado.setPontosGanhos(time2Encontrado.getPontosGanhos()+3);
                                }

                                //deu empate na partida -> entao ambos times ganham 1 ponto
                                if (partida.getPlacarTimeDois()== partida.getPlacarTimeUm()){
                                    time1Encontrado.setPontosGanhos(time1Encontrado.getPontosGanhos()+1);
                                    time2Encontrado.setPontosGanhos(time2Encontrado.getPontosGanhos()+1);

                                }

                                time2Encontrado.setGolsMarcados(partida.getPlacarTimeDois());
                                time2Encontrado.setGolsSofridos(partida.getPlacarTimeUm());
                                time2Encontrado.setSaldoDeGols(time2Encontrado.getGolsMarcados() - time2Encontrado.getGolsSofridos());
                                time2Encontrado.setMediaDeGols((double) time2Encontrado.getGolsMarcados()/time2Encontrado.getGolsSofridos());

                                for (int i = 0; i < campeonato.getListaDeTimes().size(); i++) {
                                    if (Objects.equals(campeonato.getListaDeTimes().get(i).getNomeDoTime(), time1Encontrado.getNomeDoTime())){
                                        campeonato.getListaDeTimes().set(i, time1Encontrado);
                                        System.out.println("Alteracao feita");
                                    }
                                }

                                for (int i = 0; i < campeonato.getListaDeTimes().size(); i++) {
                                    if (Objects.equals(campeonato.getListaDeTimes().get(i).getNomeDoTime(), time2Encontrado.getNomeDoTime())){
                                        campeonato.getListaDeTimes().set(i, time2Encontrado);
                                    }
                                }


                                System.out.print("\nPartida criada: "+ time1Encontrado.getNomeDoTime() + " x "+ time2Encontrado.getNomeDoTime());
                                System.out.print("\nPlacar criado: "+ partida.getPlacarTimeUm() + " x "+ partida.getPlacarTimeDois()+"\n");


                                //setando a partida na lista de partidas do campeonato
                                campeonato.getListaDePartidas().add(partida);

                                break;
                            case 2:
//                                System.out.println("TIME | PG | GM | GS | S | V | GA |");
                                System.out.println("|           Time  |    PG |    GM |    GS |     S |     V |  GA |");
                                for (int i = 0; i < campeonato.getListaDeTimes().size(); i++) {
                                    Time timeASerMostrado = campeonato.getListaDeTimes().get(i);

                                    System.out.printf("| %15s | %5d | %5d | %5d | %5d | %5d | %.1f |\n",timeASerMostrado.getNomeDoTime()
                                            ,timeASerMostrado.getPontosGanhos()
                                            ,timeASerMostrado.getGolsMarcados()
                                            ,timeASerMostrado.getGolsSofridos()
                                            ,timeASerMostrado.getSaldoDeGols()
                                            ,timeASerMostrado.getNumeroDeVitorias()
                                            ,timeASerMostrado.getMediaDeGols());
                                }
                                break;
                            case 3:
                                //lista com ordem de pontuacao de maior para menor
                                int tamanhoDaListaDeTimes = campeonato.getListaDeTimes().size();

                                ArrayList<Time> listaDeTimePorClassificacao = new ArrayList<>();
                                while (listaDeTimePorClassificacao.size() != tamanhoDaListaDeTimes){
                                    //variavel auxiliar para pegar o time com mais pontos
                                    int maioresPontos = -1;
                                    Time timeComMaiorPontucao = new Time();
                                    boolean adicionou = false;
                                    //iterando a lista de times para ver qual tem a maior pontucao na tabela
                                    for (int i = 0; i < campeonato.getListaDeTimes().size(); i++) {
                                        //se tiverem a mesma pontucao
                                        if (Objects.equals(timeComMaiorPontucao.getPontosGanhos(), campeonato.getListaDeTimes().get(i).getPontosGanhos())){
                                            int saldoDoTimeMaiorPontucao = timeComMaiorPontucao.getSaldoDeGols();
                                            int saldoDoTimeDaLista = campeonato.getListaDeTimes().get(i).getSaldoDeGols();
                                            if (saldoDoTimeMaiorPontucao > saldoDoTimeDaLista){
                                                listaDeTimePorClassificacao.add(timeComMaiorPontucao);
                                                adicionou = true;
                                            } else if (saldoDoTimeMaiorPontucao < saldoDoTimeDaLista) {
                                                timeComMaiorPontucao = campeonato.getListaDeTimes().get(i);
                                                listaDeTimePorClassificacao.add(timeComMaiorPontucao);
                                                adicionou = true;
                                            }else {
                                                //se tiverem a mesma pontuacao && saldo de gols
                                                System.out.println("Times "+timeComMaiorPontucao.getNomeDoTime()+" e "+campeonato.getListaDeTimes().get(i).getNomeDoTime()+" estao com a mesma pontução e saldo de gols...");
                                                System.out.println("Vamos fazer um confronto direto!!");

                                                Partida partidaConfrontoDireto = new Partida(timeComMaiorPontucao,campeonato.getListaDeTimes().get(i));
                                                System.out.println("Defina o placar do time "+partidaConfrontoDireto.getTimeUm().getNomeDoTime()+" :");
                                                partidaConfrontoDireto.setPlacarTimeUm(input.nextInt());

                                                System.out.println("Defina o placar do time "+partidaConfrontoDireto.getTimeDois().getNomeDoTime()+" :");
                                                partidaConfrontoDireto.setPlacarTimeDois(input.nextInt());

                                                if (partidaConfrontoDireto.getPlacarTimeUm() > partidaConfrontoDireto.getPlacarTimeDois()){
                                                    listaDeTimePorClassificacao.add(timeComMaiorPontucao);
                                                    adicionou = true;
                                                } else if (partidaConfrontoDireto.getPlacarTimeUm() < partidaConfrontoDireto.getPlacarTimeDois()) {
                                                    timeComMaiorPontucao =campeonato.getListaDeTimes().get(i);
                                                    listaDeTimePorClassificacao.add(timeComMaiorPontucao);
                                                    adicionou = true;
                                                }

                                                campeonato.getListaDePartidas().add(partidaConfrontoDireto);

                                            }
                                        }

                                        if (campeonato.getListaDeTimes().get(i).getPontosGanhos() > maioresPontos){
                                            maioresPontos = campeonato.getListaDeTimes().get(i).getPontosGanhos();
                                            timeComMaiorPontucao=campeonato.getListaDeTimes().get(i);
                                        }

                                        if (adicionou){
                                            campeonato.getListaDeTimes().remove(timeComMaiorPontucao);
                                        }
                                    }
                                    if (adicionou == false){
                                        listaDeTimePorClassificacao.add(timeComMaiorPontucao);
                                        campeonato.getListaDeTimes().remove(timeComMaiorPontucao);
                                    }
                                }
                                System.out.println("|                         CLASSIFICAÇÃO                         |");
                                System.out.println("|           Time  |    PG |    GM |    GS |     S |     V |  GA |");
                                for (int i = 0; i < listaDeTimePorClassificacao.size(); i++) {

                                    System.out.printf("| %15s | %5d | %5d | %5d | %5d | %5d | %.1f |\n",listaDeTimePorClassificacao.get(i).getNomeDoTime()
                                            ,listaDeTimePorClassificacao.get(i).getPontosGanhos()
                                            ,listaDeTimePorClassificacao.get(i).getGolsMarcados()
                                            ,listaDeTimePorClassificacao.get(i).getGolsSofridos()
                                            ,listaDeTimePorClassificacao.get(i).getSaldoDeGols()
                                            ,listaDeTimePorClassificacao.get(i).getNumeroDeVitorias()
                                            ,listaDeTimePorClassificacao.get(i).getMediaDeGols());
                                }
                                for (int i = 0; i < listaDeTimePorClassificacao.size(); i++) {
                                    campeonato.getListaDeTimes().add(listaDeTimePorClassificacao.get(i));
                                }
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