package com.example.halisson.apendendoespanhol.com.database;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.aprendendoespanhol.Pergunta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BancoPerguntas {

    public static List<Pergunta> NIVEL1 = new ArrayList<Pergunta>(Arrays.asList(
            new Pergunta(R.drawable.quantosovos, "Quantos ovos pôs a mamãe pato?", R.id.radio_respostas3, "A) cinco", "B) seis", "C) siete", "D) ocho"),
            new Pergunta(R.drawable.cisne, "O patinho feio era na verdade um?", R.id.radio_respostas4, "A) un pato", "B) una gallina", "C) un ganso", "D) un cisne"),
            new Pergunta(R.drawable.cordospatinhos, "Qual era a cor dos patinhos irmãos do patinho feio?", R.id.radio_respostas1, "A) amarillo", "B) blanca", "C) rojo", "D) grises")
    ));

    public static List<Pergunta> NIVEL2 = new ArrayList<Pergunta>(Arrays.asList(
            new Pergunta(R.drawable.cordopatinhofeio, "Qual era a cor do patinho feio assim que ele nasceu?", R.id.radio_respostas3, "A) amarillho", "B) blanco", "C) gris y blanco", "D) gris"),
            new Pergunta(R.drawable.procuramae, "O Patinho Feio ... quando sua mãe é levada?", R.id.radio_respostas2, "A) canta", "B) la busca", "C) llora", "D) se queda feliz"),
            new Pergunta(R.drawable.diferencaentreospatos, "Qual eram as diferenças entre o patinho feio e seus irmãos?", R.id.radio_respostas3, "A) solamente la color ", "B) solamente el tamaño", "C) el color y el tamaño", "D) ninguno")
    ));

    public static List<Pergunta> NIVEL3 = new ArrayList<Pergunta>(Arrays.asList(
            new Pergunta(R.drawable.riem, "... animais da granja riem do Patinho Feio", R.id.radio_respostas4, "A)ellos ", "B) las", "C) ellas", "D) los"),
            new Pergunta(R.drawable.animaisdagranja, "Com qual desses animais o Patinho Feio não falou quando procurava suas mãe?", R.id.radio_respostas1, "A) un cerdo", "B) un perro", "C) una gallina", "D) una vaca"),
            new Pergunta(R.drawable.mameindoembora, "O que separou a mamãe pato de seus filhotes?", R.id.radio_respostas2, "A)los hermanos de Patito Feo ", "B) los cazadores", "C) la gallina", "D) la vaca")
    ));

    public static String NIVEL_SELECIONADO = "";
    public static int INDICE_PERGUNTA = 0;

    public static int NUMERO_PERGUNTAS = 3;

    public static int PONTOS_NIVEL = 0;
    public static int ERROS_NIVEL = 0;

    public static int NUMERO_MIN_ACERTOS = 1;

    public static int PROGRESSO = 1;

}
