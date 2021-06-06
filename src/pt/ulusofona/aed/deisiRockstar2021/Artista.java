package pt.ulusofona.aed.deisiRockstar2021;


public class Artista {
    String id;
    String nome;
    int numeroDeMusicas = 0;

    Artista(String ID, String Nome) {
        this.id = ID;
        this.nome = Nome;

    }
    Artista(){

    }

    public static int numeroMusicasDoArtista(String artista)
    {
        int numMusica = 0;

        for (int i = 0; i < Main.songsTxtFinal.size(); i++) {
            if (Main.songsTxtFinal.get(i).artistas != null) {
                for (int k = 0; k < Main.songsTxtFinal.get(i).artistas.length; k++) {
                    if (Main.songsTxtFinal.get(i).artistas[k].nome.equals(artista)) {
                        numMusica++;
                    }
                }
            }
        }


        return numMusica;
    }
    public String toString(){
        return nome;
    }
}
