package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TesteBufferedReader {


    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader("dados.txt");
            BufferedReader reader = new BufferedReader(fr);

            String linha = null;

            while ((linha = reader.readLine()) != null) {
                System.out.println("Linha =  " + linha);

                //partir a linha no caractere separador
                String dados[] = linha.split(":");

                //converter o ID de String para Int
                int id = Integer.parseInt(dados[0]);

                String nome = dados[1];

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);

                Utilizador utilizador = new Utilizador(id,nome);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro durante a leitura");
        }
    }
}
