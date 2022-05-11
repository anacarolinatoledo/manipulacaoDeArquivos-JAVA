package NIO2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


public class program {
    public static void main(String[] args) throws IOException {
        oscar_lista_atores();
        oscar_lista_atrizes();
        atrizVencedoraMaisVezes();
        AtrizesEAtores();

        System.out.println("Entre com o nome de um ator ou atriz: ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String name = reader.readLine();
        AtrizesEAtoresInput(name);

    }

    private static void oscar_lista_atores() throws IOException {
        FileReader ler = new FileReader("src/oscar_lista_atores.txt");
        BufferedReader leitor = new BufferedReader(ler);
        String linha;
        String AtorIdadeMenor = "";
        Integer idadeAtor = 500;
        leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            String[] linhaArray = linha.split(";");
            if (Integer.parseInt(linhaArray[2].trim()) < idadeAtor) {
                AtorIdadeMenor = linhaArray[3];
                idadeAtor = Integer.parseInt(linhaArray[2].trim());
            }
        }
        System.out.println("Resolucao 01: Quem foi o ator mais jovem a ganhar um Oscar: ");
        System.out.println(AtorIdadeMenor);
        System.out.println("Com que idade?");
        System.out.println(idadeAtor);
    }

    private static void oscar_lista_atrizes() throws IOException {
        FileReader ler = new FileReader("src/oscar_lista_atrizes.txt");
        BufferedReader leitor = new BufferedReader(ler);
        String linha;
        List<String> oscar_lista_atrizes = new ArrayList<>();
        leitor.readLine();


        while ((linha = leitor.readLine()) != null) {
            String[] linhaArray = linha.split(";");
            oscar_lista_atrizes.add(linhaArray[3]);
        }

        String maiorOcorrencia = oscar_lista_atrizes.stream()
                .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(oscar_lista_atrizes, o1) -
                        Collections.frequency(oscar_lista_atrizes, o2))).orElse(null);
        System.out.println("Resolucao 02: Quem foi a atriz que mais vezes foi premiada?");
        System.out.println(maiorOcorrencia);
    }

    private static void atrizVencedoraMaisVezes() throws IOException {
        FileReader ler = new FileReader("src/oscar_lista_atrizes.txt");
        BufferedReader leitor = new BufferedReader(ler);
        String linha;
        List<String> oscar_lista_atrizes = new ArrayList<>();
        leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            String[] linhaArray = linha.split(";");
            if (Integer.parseInt(linhaArray[2].trim()) > 20 && Integer.parseInt(linhaArray[2].trim()) < 30) {
//               System.out.println(linhaArray[2]);
                oscar_lista_atrizes.add(linhaArray[3]);
            }
        }
        String maiorOcorrencia = oscar_lista_atrizes.stream()
                .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(oscar_lista_atrizes, o1) -
                        Collections.frequency(oscar_lista_atrizes, o2))).orElse(null);

        System.out.println("Resolucao 03: Qual atriz entre 20 e 30 anos que mais vezes foi vencedora?");
        System.out.println(maiorOcorrencia);
    }

    private static void AtrizesEAtores() throws IOException {

        FileReader ler = new FileReader("src/oscar_lista_atrizes.txt");
        BufferedReader leitor = new BufferedReader(ler);

        String linha;
        List<String> listaVencedores = new ArrayList<>();
        List<String> listaAtores = new ArrayList<>();

        leitor.readLine(); //pulando a primeira linha do cabeçalho!
        while ((linha = leitor.readLine()) != null) {
            String[] linhaArray = linha.split(";");

        }

       FileReader ler2 = new FileReader("src/oscar_lista_atores.txt");
        BufferedReader leitor2 = new BufferedReader(ler2);

        String linha2;

        leitor2.readLine(); //pulando a primeira linha do cabeçalho!
        while ((linha2 = leitor2.readLine()) != null) {
            String[] linhaArray = linha2.split(";");
            listaAtores.add(linhaArray[3]);
        }
        List<String> maisOcorrencias2 = listaAtores.stream()
                .filter(i -> Collections.frequency(listaAtores, i) > 1)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Resolucao 04: Quais atores ou atrizes receberam mais de um Oscar? Elabore uma única estrutura contendo atores e atrizes.");
        System.out.println(maisOcorrencias2);
    }

    private static void AtrizesEAtoresInput(String atorEscolhido) throws IOException {

        FileReader ler = new FileReader("src/oscar_lista_atrizes.txt");
        BufferedReader leitor = new BufferedReader(ler);

        String linha;
        List<String> listaVencedores = new ArrayList<>();
        List<String> listaAtores = new ArrayList<>();
        leitor.readLine();
        while ((linha = leitor.readLine()) != null) {
            listaAtores.add(linha);
        }

        FileReader ler2 = new FileReader("src/oscar_lista_atores.txt");
        BufferedReader leitor2 = new BufferedReader(ler);

        String linha2;
        List<String> listaVencedores2 = new ArrayList<>();
        List<String> listaAtores2 = new ArrayList<>();
        leitor.readLine();
        while ((linha2 = leitor.readLine()) != null) {
            listaAtores.add(linha2);
        }

        List<String> listaAtorEscolhido = new ArrayList<>();
        for (
                String linhaAtor : listaAtores
        ) {
            if (linhaAtor.toUpperCase().contains(atorEscolhido.toUpperCase())) {
                listaAtorEscolhido.add(linhaAtor);
            }

        }
        System.out.println("Resolucao 05: Quando informado o nome de um ator ou atriz, dê um resumo de quantos prêmios ele/ela recebeu e liste ano, idade e nome de cada filme pelo qual foi premiado(a).");
        System.out.println("Quantidade de Oscars: " + listaAtorEscolhido.size());
        System.out.println(listaAtorEscolhido);
    }
}