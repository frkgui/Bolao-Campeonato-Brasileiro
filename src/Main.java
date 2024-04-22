import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    private static Campeonato campeonato = new Campeonato();
    private static Resultado resultado = new Resultado();

    public static void main(String[] args) throws IOException {
        // Variáveis iniciais...
        int opcaoMenuCampeonato = 0;
        int opcaoMenuInicial = 0;
        int opcaoMenuBolao = 0;
        int opcaoMenuPartidas = 0;
        String nomeDoCampeonato;

        // Inicialização do campeonato...
        System.out.println("-- Seja bem vindo ao Simulador & Bolão de Campeonatos! --");

        // Menu principal
        do{
            System.out.println("""
                    \nOpções Disponiveis:\n
                    (1) - Campeonato;
                    (2) - Bolão;
                    (3) - Carregar dados
                    (0) - Sair;
                    """);
            System.out.print("Digite a opção desejada: ");
            opcaoMenuInicial = input.nextInt();
            input.nextLine();

            switch (opcaoMenuInicial){
                case 0:
                    break;
                // Menu do campeonato
                case 1:
                    if(campeonato.getNomeDoCampeonato()==null) {
                        System.out.print("\nDigite o nome do Campeonato a ser criado: ");
                        nomeDoCampeonato = input.nextLine();
                        campeonato.setNomeDoCampeonato(nomeDoCampeonato);
                        System.out.println("\nCampeonato: " + nomeDoCampeonato + ", criado com sucesso!");
                    }
                    do{

                        System.out.println("""
                    \nOpções Disponiveis:\n
                    (1) - Cadastrar Time; 
                    (2) - Iniciar campeonato;
                    (0) - Voltar ao menu principal;
                    """);
                        System.out.print("Digite a opção desejada: ");
                        opcaoMenuCampeonato = input.nextInt();
                        input.nextLine();

                        switch (opcaoMenuCampeonato){

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
                                // Menu de criação de partidas & visualização de tabelas...
                                do {
                                    System.out.println("""
                                    \nOpções Disponiveis:\n
                                    (1) - Criar Partidas;
                                    (2) - Ver Tabela;
                                    (3) - Mostrar Classificação;
                                    (4) - Salvar dados do Campeonato;
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
                                        case 4:
                                            criarArquivo();
                                            //salvar campeonato
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


                    }while(opcaoMenuCampeonato != 0);
                    break;
                // Menu dos bolões
                case 2:
                    do {
                        System.out.println("""
                                    \nOpções Disponiveis:\n
                                    (1) - Criar bolão;
                                    (2) - Visualizar bolão;
                                    (3) - Ver resultados dos bolões;
                                    (0) - Voltar;
                                    """);
                        System.out.print("Digite a opção desejada: ");
                        opcaoMenuBolao = input.nextInt();
                        input.nextLine();

                        switch (opcaoMenuBolao){
                            case 0:
                                break;
                            case 1:
                                System.out.print("Digite seu nome: ");
                                String nomeDoApostador= input.nextLine();
                                criarNovoBolao(nomeDoApostador);

                                break;
                            case 2:
                                System.out.print("Digite o nome do seu bolão para visualizar: ");
                                String nomeBolao = input.nextLine();

                                Bolao bolaoEncontrado = resultado.getBolao(nomeBolao);
                                resultado.vizualizarPalpites(bolaoEncontrado);
                                break;
                            case 3:
                                Bolao bolaoVencedor = resultado.encontrarBolaoVencedor();
                                resultado.setBolaoVencedor(bolaoVencedor);
                                System.out.println("De acordo com o campeonato "+ campeonato.getNomeDoCampeonato()+", o bolão vencedor: "+bolaoVencedor.getNomeDoApostador()+" com a pontucao de "+bolaoVencedor.getPontuacaoTotal()+" pontos!");
                                System.out.println("A pontução dos outros bolões aqui:");
                                for (int i = 0; i < resultado.getListaDeBoloes().size(); i++) {
                                    if(!Objects.equals(resultado.getListaDeBoloes().get(i),resultado.getBolaoVencedor())){
                                        System.out.println("Bolão: "+resultado.getListaDeBoloes().get(i).getNomeDoApostador()
                                                +", com "+resultado.getListaDeBoloes().get(i).getPontuacaoTotal()+" pontos...");

                                    }
                                }
                        }

                    }while (opcaoMenuBolao != 0);
                    break;
                case 3:
                    //carregar dados
                    System.out.print("Qual é o nome do campeonato: ");
                    String nomeCampeonato = input.nextLine();
                    ArrayList<Time> timesDoCampeonato = carregarArquivo(nomeCampeonato);
                    if(timesDoCampeonato != null){
                        //continue...
                    }

                    break;
            }


        }while (opcaoMenuInicial != 0);

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

                    //se for N o sistema vai gerar numeros aleatorios entre o 1 a 3 para ambos placares
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

    public static Bolao criarNovoBolao(String nomeDoApostador){
        Bolao bolao = new Bolao(nomeDoApostador);
        bolao.setPartidasCampeonato(campeonato.getListaDePartidas());

        for (int i =0; i < campeonato.getListaDePartidas().size(); i++) {
            Time time1 = campeonato.getListaDePartidas().get(i).getTimeUm();
            Time time2 = campeonato.getListaDePartidas().get(i).getTimeDois();

            System.out.println("\nPartida: " +time1.getNomeDoTime()+" x "+time2.getNomeDoTime());
            System.out.print("Defina o placar de "+time1.getNomeDoTime()+": ");
            int placarDotime1 = input.nextInt();
            System.out.print("Defina o placar de "+time2.getNomeDoTime()+": ");
            int placarDotime2 = input.nextInt();
            String palpite = placarDotime1+"x"+placarDotime2;

            //insercao de pontos no bolao
            if (campeonato.getListaDePartidas().get(i).getPlacarTimeUm() == placarDotime1 && campeonato.getListaDePartidas().get(i).getPlacarTimeDois()==placarDotime2){
                bolao.setPontuacaoTotal(bolao.getPontuacaoTotal()+5);
            } else if (campeonato.getListaDePartidas().get(i).getPlacarTimeUm() >= campeonato.getListaDePartidas().get(i).getPlacarTimeDois() && placarDotime1 >= placarDotime2 ) {
                bolao.setPontuacaoTotal(bolao.getPontuacaoTotal()+3);
            } else if (campeonato.getListaDePartidas().get(i).getPlacarTimeDois() >= campeonato.getListaDePartidas().get(i).getPlacarTimeUm() && placarDotime2 >= placarDotime1 ) {
                bolao.setPontuacaoTotal(bolao.getPontuacaoTotal()+3);
            }

            bolao.getPalpitePartidas().add(palpite);
            System.out.println();
            System.out.println((bolao.getPartidaString(i))+ " - Palpite adicionado!\n");
        }

        resultado.getListaDeBoloes().add(bolao);

        return bolao;
    }

    public static void criarArquivo() throws IOException {
        ArrayList<Time> listaDeTimesClassificados = mostrarClassificacao();
        String nomeCampeonato = campeonato.getNomeDoCampeonato();
        //Criando o arquivo na pasta "arquivos" dentro desse projeto
        File file = new File("C:\\Users\\Admin\\IdeaProjects\\Bolao-Campeonato-Brasileiro\\src\\arquivos\\"+nomeCampeonato+".csv");
        FileWriter escreverArquivo = new FileWriter(file);
        for (int i = 0; i < listaDeTimesClassificados.size(); i++) {
            escreverArquivo.write((String.valueOf(listaDeTimesClassificados.get(i).getNomeDoTime()))+",");
            escreverArquivo.write((String.valueOf(listaDeTimesClassificados.get(i).getPontosGanhos()))+",");
            escreverArquivo.write((String.valueOf(listaDeTimesClassificados.get(i).getGolsMarcados()))+",");
            escreverArquivo.write((String.valueOf(listaDeTimesClassificados.get(i).getGolsSofridos()))+",");
            escreverArquivo.write((String.valueOf(listaDeTimesClassificados.get(i).getSaldoDeGols()))+",");
            escreverArquivo.write((String.valueOf(listaDeTimesClassificados.get(i).getNumeroDeVitorias()))+",");
            escreverArquivo.write((String.valueOf(listaDeTimesClassificados.get(i).getMediaDeGols()))+"\n");
        }
        escreverArquivo.close();
        System.out.println("Arquivo salvo!");
    }
    public static ArrayList<Time> carregarArquivo(String nomeDoCampeonato) throws FileNotFoundException {
        File file = new File("C:\\Users\\Admin\\IdeaProjects\\Bolao-Campeonato-Brasileiro\\src\\arquivos\\"+nomeDoCampeonato+".csv");
        ArrayList<Time> listaDeTimesDoCampeonato = new ArrayList<>();
        if (!file.exists()){
            System.out.println("Arquivo NÃO encontrado!!");
            return null;
        }else {
            Scanner inputDeDados = new Scanner(file);
            while (inputDeDados.hasNextLine()){
                String linhaDoArquivo = inputDeDados.nextLine();
                String[] coluna = linhaDoArquivo.split(",");
                Time time = new Time();
                time.setNomeDoTime(coluna[0]);
                time.setPontosGanhos(Integer.parseInt(coluna[1]));
                time.setGolsMarcadosArquivo(Integer.parseInt(coluna[2]));
                time.setGolsSofridosArquivo(Integer.parseInt(coluna[3]));
                time.setSaldoDeGols(Integer.parseInt(coluna[4]));
                time.setNumeroDeVitorias(Integer.parseInt(coluna[5]));
                time.setMediaDeGols(Double.parseDouble(coluna[6]));
                listaDeTimesDoCampeonato.add(time);
                System.out.print("Time "+time.getNomeDoTime()+" adcionado...\n");
            }
            return listaDeTimesDoCampeonato;
        }
    }


}