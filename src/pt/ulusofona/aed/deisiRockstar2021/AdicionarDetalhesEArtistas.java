package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;

import static pt.ulusofona.aed.deisiRockstar2021.Main.*;
import static pt.ulusofona.aed.deisiRockstar2021.Queries.*;
import static pt.ulusofona.aed.deisiRockstar2021.LerFicheiros.*;


public class AdicionarDetalhesEArtistas {
    public static void main(String[] args) {

    }

    public static int adicionarDetails(String[] dados){
        String idTemaMusical = null;
        int duracao = 0;
        int letraExplicita = 0;
        int popularidade = 0;
        double dancabilidade = 0;
        double vivacidade = 0;
        double volumeMedio = 0;
        boolean boolLetraExplicita = false;

        for (int i = 0; i < dados.length; i++) {

            switch (i){

                case 0:
                    idTemaMusical = dados[i];
                    if (!existeID(hashMapComMusicasFinal, idTemaMusical)) {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 1:
                    if (numeroInteiro(dados[i])) {
                        duracao = Integer.parseInt(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 2:
                    if (numeroInteiro(dados[i])) {
                        letraExplicita = Integer.parseInt(dados[i]);
                        if (letraExplicita == 1) {
                            boolLetraExplicita = true;
                        }else if (letraExplicita == 0) {
                            boolLetraExplicita = false;
                        }else {
                            parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                            return 0;
                        }
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 3:
                    if (numeroInteiro(dados[i])) {
                        popularidade = Integer.parseInt(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 4:
                    if (numeroDoubble(dados[i])) {
                        dancabilidade = Double.parseDouble(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 5:
                    if (numeroDoubble(dados[i])) {
                        vivacidade = Double.parseDouble(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 6:
                    if (numeroDoubble(dados[i])) {
                        volumeMedio = Double.parseDouble(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
            }
        }
        if (existeID(hashMapComMusicasFinal, idTemaMusical)) {
            Song musica = hashMapComMusicasFinal.get(idTemaMusical);
            if (musica.detalhesAdicionados) {
                parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                return 0;
            }


            parseInfoSongsDetailsTxT.numLinhasOk += 1;
            musica.duracaoDoTema = duracao;
            musica.letraExplicita = boolLetraExplicita;
            musica.popularidade = popularidade;
            musica.dancabilidade = dancabilidade;
            musica.vivacidade = vivacidade;
            musica.volumeMedio = volumeMedio;
            musica.detalhesAdicionados = true;
            hashMapComMusicasFinal.put(idTemaMusical, musica);
            songsTxtFinal.set(musica.posicaoDaMusica, musica);
            Song.fazerToString(musica);
        }

        return 0;
    }

    public static int separarArtistas(String artistas, String idTemaMusical) {
        String[] arrayArtistas = artistas.split(",");

        //Aqui v??o ficar os artistas depois do trim
        String[] artistasTrim = new String[arrayArtistas.length];
        Artista[] artistasSong = new Artista[arrayArtistas.length];
        ArrayList<String> artistasQueTiverMusicasAumentado = new ArrayList<>();
        int musicas = 0;

        boolean artistaVazio = false;


        //Aqui vai ser feito o trim dos artistas 1 a 1
        for (int i = 0; i < artistasTrim.length; i++){
            artistasTrim[i] = arrayArtistas[i].trim();
        }


        //Aqui ?? feito a remo????o dos caracteres desnecess??rios nos artistas

        for (int u = 0; u < artistasTrim.length; u++) {

            if (artistasTrim[u].length() == 0) {
                //Este artistaVazio vai ser usado para saber se se utiliza a linha ou n??o
                artistaVazio = true;
                break;
            }

            if (u == 0) {
                //Vai remover os caracteres do ??nicio que sejam ([, ' , ")
                while (artistasTrim[u].charAt(0) == '"' || artistasTrim[u].charAt(0) == 39 || artistasTrim[u].charAt(0) == ' ') {
                    //Elimina o caracter
                    artistasTrim[u] = artistasTrim[u].substring(1);

                    //V?? o tamanho da string para n??o eliminar o caracter de uma string vazia
                    if (artistasTrim[u].isEmpty()) {
                        //Este artistaVazio vai ser usado para saber se se utiliza a linha ou n??o
                        artistaVazio = true;
                        break;
                    }

                }
            }
            if (u != 0 || artistasTrim.length == 1) {
                //Vai remover caracteres desnecess??rios no final a string dos artistas
                if (!artistasTrim[u].isEmpty()) {
                    //S?? entra se a string n??o for vazia para n??o dar cras
                    while (artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 39 || artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 34 || artistasTrim[u].charAt(artistasTrim[u].length() - 1) == ' ') {
                        artistasTrim[u] = artistasTrim[u].substring(0, artistasTrim[u].length() - 1);
                        if (artistasTrim[u].isEmpty()) {
                            artistaVazio = true;
                            break;
                        }
                    }
                }
            }
            //Remover o "["
            if (!artistasTrim[u].isEmpty()) {
                if (artistasTrim[u].charAt(0) == 91) {
                    artistasTrim[u] = artistasTrim[u].substring(1);
                    if (artistasTrim[u].isEmpty()) {
                        artistaVazio = true;
                        break;
                    }
                }
            }
            //Remover o "]"
            if (!artistasTrim[u].isEmpty()) {
                if (artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 93) {
                    artistasTrim[u] = artistasTrim[u].substring(0, artistasTrim[u].length() - 1);
                    if (artistasTrim[u].isEmpty()) {
                        artistaVazio = true;
                        break;
                    }
                }
            }

            //V?? se come??a com plica e remove
            if (!artistasTrim[u].isEmpty()) {
                if (artistasTrim[u].charAt(0) == 39) {
                    artistasTrim[u] = artistasTrim[u].substring(1);
                    if (artistasTrim[u].isEmpty()) {
                        artistaVazio = true;
                        break;
                    }
                }
            }
            //V?? se acaba com plica e remove
            if (!artistasTrim[u].isEmpty()) {
                if (artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 39) {
                    artistasTrim[u] = artistasTrim[u].substring(0, artistasTrim[u].length() - 1);
                    if (artistasTrim[u].isEmpty()) {
                        artistaVazio = true;
                        break;
                    }
                }
            }
            //Quando come??a com duas aspas
            if (!artistasTrim[u].isEmpty()) {
                if (artistasTrim[u].charAt(0) == 34 && artistasTrim[u].charAt(0) == artistasTrim[u].charAt(1)) {
                    artistasTrim[u] = artistasTrim[u].substring(2);
                    if (artistasTrim[u].isEmpty()) {
                        artistaVazio = true;
                        break;
                    }
                    artistasTrim[u] = artistasTrim[u].substring(0, artistasTrim[u].length() - 2);
                    if (artistasTrim[u].isEmpty()) {
                        artistaVazio = true;
                        break;
                    }
                }
            }
        }


        //Aqui v??-se se foi detetado algum artista vazio
        //E se tal acontecer ent??o n??o utiliza a linha

        if (!artistaVazio) {
            String[] artistasFinais = veSeHaArtistasRepetidosNaMusica(artistasTrim);

            for (int count = 0; count < artistasFinais.length; count++){
                //Cria array de artistas
                artistasSong[count] = new Artista(idTemaMusical, artistasFinais[count]);
                hashSetComTodosOsArtistas.add(artistasFinais[count]);
            }
            if (existeID(hashMapComMusicasFinal, idTemaMusical)){

                parseInfoSongsArtistsTxT.numLinhasOk += 1;

                //Aumenta o n??mero de m??sicas a cada artista
                for (Artista artistaParaMusica : artistasSong){
                    if (artistaParaMusica != null) {
                        if (hashMapComArtistasESuasMusicasInicial.get(artistaParaMusica.nome) != null) {
                            musicas = hashMapComArtistasESuasMusicasInicial.get(artistaParaMusica.nome);

                        }
                        //Mete a m??sica no hashMapDosArtistas, com o n?? de m??sicas atualizado
                        hashMapComArtistasESuasMusicasInicial.put(artistaParaMusica.nome, musicas + 1);
                        musicas = 0;
                    }
                }
                Song musicaNova = hashMapComMusicasFinal.get(idTemaMusical);
                musicaNova.artistas = artistasSong;
                musicaNova.temArtistas = true;
                hashMapComMusicasFinal.put(idTemaMusical,musicaNova);

                return 0;

            }

        } else {
            parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
            return 0;
        }


        return 0;
    }
}
