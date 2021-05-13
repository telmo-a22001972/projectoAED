package pt.ulusofona.aed.deisiRockstar2021;


public class Utilizador {
        int id;
        String nome;
        public Utilizador(int id, String nome)
        {
            this.id = id;
            this.nome = nome;
        }

    public String toString(){

        return "ID: "+id+", Nome: "+nome;
    }
}
