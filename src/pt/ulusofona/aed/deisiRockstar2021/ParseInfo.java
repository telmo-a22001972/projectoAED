package pt.ulusofona.aed.deisiRockstar2021;

public class ParseInfo {
    int numLinhasOk;
    int numLinhasIgnored;

    ParseInfo(int NUM_LINHAS_OK, int NUM_LINHAS_IGNORED) {
        this.numLinhasOk = NUM_LINHAS_OK;
        this.numLinhasIgnored = NUM_LINHAS_IGNORED;
    }
    ParseInfo() {

    }


    public String toString() {
        return "OK: "+numLinhasOk+", Ignored: "+numLinhasIgnored;
    }

    public ParseInfo (ParseInfo copiado) {
        this.numLinhasOk = copiado.numLinhasOk;
        this.numLinhasIgnored = copiado.numLinhasIgnored;
    }

    public void reset() {
        this.numLinhasOk = 0;
        this.numLinhasIgnored = 0;
    }

}

