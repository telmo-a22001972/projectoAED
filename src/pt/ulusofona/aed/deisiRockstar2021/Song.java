package pt.ulusofona.aed.deisiRockstar2021;


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
    Boolean detalhesAdicionados;
    String nMusicas;
    String toString;
    Boolean temArtistas = false;
    int posicaoDaMusica;

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
        this.detalhesAdicionados = false;
    }
    Song(String ID, String Titulo, int anoLancamento) {
        this.anoLancamento = anoLancamento;
        this.id = ID;
        this.titulo = Titulo;
        this.detalhesAdicionados = false;

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
        String nomesArtistas;
        if (artistas == null){
            return "null";
        }else {

            nomesArtistas = artistas[0].nome;

            for (int k = 1; k < artistas.length; k++) {
                if (artistas[k] != null) {
                    if (!artistas[k].nome.isEmpty()) {
                        nomesArtistas += (" / " + artistas[k].nome);
                    } else {

                    }
                }
            }
        }
        return nomesArtistas;
    }

    public static void meterNmusicasNaString(Song musica){
        int nMusicas = 0;
        String nMusicasString = "";
        if (musica.artistas != null) {
            nMusicasString = String.valueOf(Main.hashMapComArtistasESuasMusicasFinal.get(musica.artistas[0].nome));

            for (int i = 1; i < musica.artistas.length; i++) {
                if (musica.artistas[i] != null) {
                    nMusicas = Main.hashMapComArtistasESuasMusicasFinal.get(musica.artistas[i].nome);
                    nMusicasString += " / " + String.valueOf(nMusicas);
                }
            }
        }
        musica.nMusicas = nMusicasString;
    }


    public String toString(){

        return toString;
    }

    public static void fazerToString(Song musica){
        String toString = "";
        String duracaoTema;

        toString += musica.id;
        toString += " | "+musica.titulo;
        toString += " | "+String.valueOf(musica.anoLancamento);

        toString += " | "+String.valueOf((musica.duracaoDoTema/1000) / 60);
        toString += ":"+String.valueOf((musica.duracaoDoTema/1000) % 60);

        toString += " | "+String.valueOf(Double.valueOf(musica.popularidade));
        toString += " | "+obterNomeArtistas(musica.artistas);
        if (musica.artistas != null) {
            int[] nMusicas = new int[musica.artistas.length];
            for (int k = 0; k < nMusicas.length; k++) {
                if (musica.artistas[k] != null) {
                    nMusicas[k] = Main.hashMapComArtistasESuasMusicasFinal.get(musica.artistas[k].nome); //musica.artistas[k].numeroDeMusicas;
                }
            }
            for (int u = 0; u < musica.artistas.length; u++) {
                if (musica.artistas[u] != null) {
                    musica.artistas[u].numeroDeMusicas = nMusicas[u];
                }
            }
        }
        meterNmusicasNaString(musica);
        toString += " | ("+musica.nMusicas+")";

        musica.toString = toString;
    }

    public String toStringDancabilidade(){

        return titulo + " : " + anoLancamento + " : " + dancabilidade;
    }


}
