package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Halisson on 30/06/2018.
 */

public class Pergunta {

    private String pergunta;
    private int resposta;
    private List<String> alternativas = new ArrayList<String>();
    private int imgPergunta;

    public Pergunta(int imgPergunta, String pergunta, int resposta, String... alternativas){
        this.imgPergunta= imgPergunta;
        this.resposta = resposta;
        this.pergunta = pergunta;
        this.alternativas.add(alternativas[0]);
        this.alternativas.add(alternativas[1]);
        this.alternativas.add(alternativas[2]);
        this.alternativas.add(alternativas[3]);

    }

    public String getPergunta(){ return this.pergunta; }

    public List<String> getAlternativas(){ return this.alternativas; }

    public int getResposta(){ return this.resposta; }

    public int getImgPergunta() { return this.imgPergunta; }
}
