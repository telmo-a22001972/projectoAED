package pt.ulusofona.aed.deisiRockstar2021;

public class Tag {
    String tag;
    int numeroVezesUsada = 0;

    Tag(String tag, int vezes){
        this.numeroVezesUsada = vezes;
        this.tag = tag;
    }
}
