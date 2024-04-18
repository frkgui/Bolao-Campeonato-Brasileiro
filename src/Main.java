import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    private static Campeonato campeonato = new Campeonato();

    public static void main(String[] args) {

        // Variáveis iniciais...
        int opcaoMenuInicial = 0;
        int opcaoMenuPartidas = 0;
        String nomeDoCampeonato;

        // Inicialização do campeonato...
        System.out.println("-- Seja bem vindo ao Simulador & Bolão de Campeonatos! --");
        System.out.print("\nDigite o nome do Campeonato a ser criado: ");
        nomeDoCampeonato = input.nextLine();
        campeonato.setNomeDoCampeonato(nomeDoCampeonato);
        System.out.println("\nCampeonato: " + nomeDoCampeonato + ", criado com sucesso!");

        // Menu de criação de times & campeonatos...
        do{
            System.out.println("""
                    \nOpções Disponiveis:\n
                    (1) - Cadastrar Time; 
                    (2) - Iniciar campeonato;
                    (3) - Carregar arquivo de campeonato;
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

                    // É necessário no mínimo 4 time para a inicialização de um campeonato...
                    if (campeonato.getListaDeTimes().size() < 4){
                        System.out.println("\nNumero de times minimo para o campeonato não foi a atingido!");
                        System.out.print("Faltam "+ (4-campeonato.getListaDeTimes().size())+" times para iniciar o campeonato\n");
                        break;
                    }

                    do {
                        // Menu de criação de partidas & visualização de tabelas...
                        System.out.println("""
                        \nOpções Disponiveis:\n
                        (1) - Criar Partidas;
                        (2) - Ver Tabela;
                        (3) - Mostrar Classificação;
                        (0) - Voltar;
                        """);
                        System.out.print("Digite a opção desejada: ");
                        opcaoMenuPartidas = input.nextInt();
                        input.nextLine();

                        switch (opcaoMenuPartidas){
                            case 0:
                                break;
                            case 1:
                                criarPartidas();
                                break;
                            case 2:
                                verTabela();
                                break;
                            case 3:
                                ArrayList<Time> listaDeTimePorClassificacao = mostrarClassificacao();
                                System.out.println("\n|                         CLASSIFICAÇÃO                         |");
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
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + opcaoMenuPartidas);
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

    public static void criarPartidas(){

        ArrayList<Time> listaDeTimes = campeonato.getListaDeTimes();

        for (int i = 0; i < listaDeTimes.size(); i++) {
            Time timeDaCasa = listaDeTimes.get(i);

            for (int j = 0; j < listaDeTimes.size(); j++) {
                Time timeVisitante = listaDeTimes.get(j);

                if(listaDeTimes.get(i)!=listaDeTimes.get(j)){
                    Partida partida = new Partida(timeDaCasa,timeVisitante);


                    System.out.print("\nVocê deseja escolher o resultado da partida? S ou N: ");
                    String opcaoEscolhaDeResultado = input.nextLine();

                    //se for S podera escolher o placar

                    if (opcaoEscolhaDeResultado.equalsIgnoreCase("S")) {
                        System.out.print("Defina o placar de "+timeDaCasa.getNomeDoTime()+": ");
                        partida.setPlacarTimeUm(input.nextInt());
                        System.out.print("Defina o placar de "+timeVisitante.getNomeDoTime()+": ");
                        partida.setPlacarTimeDois(input.nextInt());
                        input.nextLine();

                    //se for N o sistemas vai gerar numeros aleatorios entre o 1 a 10 para ambos placares
                    } else if (opcaoEscolhaDeResultado.equalsIgnoreCase("N")) {
                        System.out.print("Estamos gerando o resultado para você...\n");
                        Random random = new Random();
                        int placarTimeDaCasa = random.nextInt(3)+1;
                        partida.setPlacarTimeUm(placarTimeDaCasa);
                        int placarTimeVisitante = random.nextInt(3)+1;
                        partida.setPlacarTimeDois(placarTimeVisitante);
                    }

                    // Setando todos os dados de ambos os times...
                    timeDaCasa.setarDados(partida.getPlacarTimeUm(),partida.getPlacarTimeDois());
                    verificaResultadoDaPartida(partida,timeDaCasa,timeVisitante);
                    timeVisitante.setarDados(partida.getPlacarTimeDois(),partida.getPlacarTimeUm());

                    for (int f = 0; f < campeonato.getListaDeTimes().size(); f++) {
                        if (Objects.equals(campeonato.getListaDeTimes().get(i).getNomeDoTime(), timeDaCasa.getNomeDoTime())){
                            campeonato.getListaDeTimes().set(i, timeDaCasa);
                        }
                    }

                    for (int f = 0; f < campeonato.getListaDeTimes().size(); f++) {
                        if (Objects.equals(campeonato.getListaDeTimes().get(i).getNomeDoTime(), timeVisitante.getNomeDoTime())){
                            campeonato.getListaDeTimes().set(i, timeVisitante);
                        }
                    }

                    System.out.print("\nPartida criada: "+ timeDaCasa.getNomeDoTime() + " x "+ timeVisitante.getNomeDoTime());
                    System.out.print("\nPlacar criado: "+ partida.getPlacarTimeUm() + " x "+ partida.getPlacarTimeDois()+"\n");

                    //setando a partida na lista de partidas do campeonato
                    campeonato.getListaDePartidas().add(partida);
                }
            }
        }


    }

    public static void verTabela(){
        System.out.println("\n|           Time  |    PG |    GM |    GS |     S |     V |  GA |");
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

    }

    public static ArrayList<Time> mostrarClassificacao(){
// ...

        ArrayList<Time> listaDeTimesClassificada = campeonato.getListaDeTimes();

        Collections.sort(listaDeTimesClassificada, new Comparator<Time>() {
            @Override
            public int compare(Time t1, Time t2) {
                if (t1.getPontosGanhos().equals(t2.getPontosGanhos())) {
                    if (t1.getSaldoDeGols().equals(t2.getSaldoDeGols())) {
                        // Em caso de empate nos pontos e no saldo de gols, usa o confronto direto
                        return verificarConfrontoDireto(t2, t1);
                    } else {
                        return t2.getSaldoDeGols().compareTo(t1.getSaldoDeGols()); // Em caso de empate nos pontos, compara o saldo de gols
                    }
                } else {
                    return t2.getPontosGanhos().compareTo(t1.getPontosGanhos()); // Compara os pontos ganhos
                }
            }
        });

        return listaDeTimesClassificada;
    }

    public static int verificarConfrontoDireto(Time time1, Time time2){
        for(Partida partida : campeonato.getListaDePartidas()){
            if((partida.getTimeUm().equals(time1) && partida.getTimeDois().equals(time2)) ||
                    (partida.getTimeUm().equals(time2) && partida.getTimeDois().equals(time1))){
                if(partida.getPlacarTimeUm() > partida.getPlacarTimeDois()){return 1;}
                if(partida.getPlacarTimeUm() < partida.getPlacarTimeDois()){return -1;}
                return 0;
            }
        }
        return 0;
    }

    public static void verificaResultadoDaPartida(Partida partida, Time timeDaCasa, Time timeVisitante){

        //time de casa ganhou -> entao ele ganha 1 vitoria e 3 pontos
        if (partida.getPlacarTimeUm()>partida.getPlacarTimeDois()){
            timeDaCasa.setNumeroDeVitorias(timeDaCasa.getNumeroDeVitorias()+1);
            timeDaCasa.setPontosGanhos(timeDaCasa.getPontosGanhos()+3);
        }

        //time de fora ganhou -> entao ele ganha 1 vitoria e 3 pontos
        if (partida.getPlacarTimeDois()> partida.getPlacarTimeUm()) {
            timeVisitante.setNumeroDeVitorias(timeVisitante.getNumeroDeVitorias()+1);
            timeVisitante.setPontosGanhos(timeVisitante.getPontosGanhos()+3);
        }

        //deu empate na partida -> entao ambos times ganham 1 ponto
        if (partida.getPlacarTimeDois()== partida.getPlacarTimeUm()){
            timeDaCasa.setPontosGanhos(timeDaCasa.getPontosGanhos()+1);
            timeVisitante.setPontosGanhos(timeVisitante.getPontosGanhos()+1);

        }

    }

/*    public static void mostrarClassificacao2(){
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
    }*/


}