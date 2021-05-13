package pt.ulusofona.aed.deisiRockstar2021;


import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class TesteScanner {
    public static void main(String[] args) {
        ArrayList<Utilizador> teste5  = new ArrayList<Utilizador>();
        String nomeFicheiro = "C:/Users/telmo/IdeaProjects/projeto2/src/pt/aed/deisiRockstar2021/dados.txt";
        try {
            File ficheiro = new File(nomeFicheiro);
            FileInputStream fis = new FileInputStream(ficheiro);
            Scanner leitorFicheiro = new Scanner(fis);

            // enquanto o ficheiro tiver linhas não-lidas
                while(leitorFicheiro.hasNextLine()) {
            // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();
            // partir a linha no caractere separador
                String dados[] = linha.split(":");
            // converter o ID de String para int
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
            // criar o obj Utilizador
                Utilizador utilizador = new Utilizador(id, nome);
                /*
                De seguida seria necessário guardar o objecto
                Utilizador numa estrutura de dados apropriada
                (p.e. array, lista, etc).
                */
                teste5.add(utilizador);

                }
            leitorFicheiro.close();
        }
        catch(FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " não foi encontrado.";
            System.out.println(mensagem);
        }
        System.out.println(teste5.toString());
    }

}