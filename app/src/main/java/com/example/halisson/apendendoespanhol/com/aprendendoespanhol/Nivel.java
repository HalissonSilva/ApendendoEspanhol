package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

/**
 * Created by Halisson on 23/10/2018.
 */

public class Nivel {
    private String codJogador;
    private NIVEL_LEVEL nivelLevel;
    private int pontos;

    public String getCodJogador() {
        return codJogador;
    }

    public void setCodJogador(String codJogador) {
        this.codJogador = codJogador;
    }

    public NIVEL_LEVEL getNivelLevel() {
        return nivelLevel;
    }

    public void setNivelLevel(NIVEL_LEVEL nivelLevel) {
        this.nivelLevel = nivelLevel;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
