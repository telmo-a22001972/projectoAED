package pt.ulusofona.aed.deisiRockstar2021;


import java.util.Arrays;

public class Song {
    String id;
    String titulo;
    Artista[] artistas;
    int anoLancamento;
    int duracaoDoTema;
    Boolean letraExplicita;
    int popularidade;
    double dancabilidade;
    double vivacidade;
    double volumeMedio;

    Song(String ID, String Titulo, Artista[] Artista, int AnoLancamento, int duracaoDoTema, boolean letraExplicita,
         int popularidade, double dancabilidade, double vivacidade, double volumeMedio){

        this.id = ID;
        this.titulo = Titulo;
        this.artistas = Artista;
        this.anoLancamento = AnoLancamento;
        this.duracaoDoTema = duracaoDoTema;
        this.letraExplicita = letraExplicita;
        this.popularidade = popularidade;
        this.dancabilidade = dancabilidade;
        this.vivacidade = vivacidade;
        this.volumeMedio = volumeMedio;
    }
    Song(String ID, String Titulo, int anoLancamento) {
        this.anoLancamento = anoLancamento;
        this.id = ID;
        this.titulo = Titulo;

    }
    Song(int duracao, boolean letraExplicita, int popularidade,double dancabilidade, double vivacidade, double volumeMedio){
        this.duracaoDoTema = duracao;
        this.letraExplicita = letraExplicita;
        this.popularidade = popularidade;
        this.dancabilidade = dancabilidade;
        this.vivacidade = vivacidade;
        this.volumeMedio = volumeMedio;

    }
    public static String obterNomeArtistas(Artista[] artistas){
        String nomesArtistas = artistas[0].nome;

        for (int k = 1; k < artistas.length; k++) {
                nomesArtistas += (" / "+artistas[k].nome);
            }

        return nomesArtistas.toString();
    }
    public String toString(){

        return id+" | "+ titulo +" | "+ anoLancamento +" | "+duracaoDoTema+" | "+popularidade+" | "+ obterNomeArtistas(artistas);
    }


}
