package pt.ulusofona.aed.deisiRockstar2021;


public class Artista {
    String id;
    String nome;

    Artista(String ID, String Nome) {
        this.id = ID;
        this.nome = Nome;
    }
    Artista(){

    }
    public String toString(){
        return nome;
    }
}
