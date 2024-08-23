package com.example.adivinheapalavra

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Esta é a palavra que o jogador precisa adivinhar
    private val palavraCorreta = "Kotlin"

    // Variáveis para controlar o número de tentativas do jogador
    private var tentativas = 0
    private val maxTentativas = 3

    // Variáveis para os elementos da tela (campo de texto, botões, texto de resultado)
    private lateinit var campoEntrada: EditText
    private lateinit var botaoAdivinhar: Button
    private lateinit var textoResultado: TextView
    private lateinit var botaoVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Conecta as variáveis aos elementos da tela
        campoEntrada = findViewById(R.id.campoEntrada)
        botaoAdivinhar = findViewById(R.id.botaoAdivinhar)
        textoResultado = findViewById(R.id.textoResultado)
        botaoVoltar = findViewById(R.id.botaoVoltar)

        // Quando o jogador clica no botão "Adivinhar"
        botaoAdivinhar.setOnClickListener {
            // Pega o texto que o jogador digitou
            val palavraDigitada = campoEntrada.text.toString()

            // Verifica se o que o jogador digitou é igual à palavra correta
            if (palavraDigitada.equals(palavraCorreta, ignoreCase = true)) {
                // Se o jogador acertou, mostra uma mensagem de parabéns
                textoResultado.text = "Parabéns! Você adivinhou a palavra correta!"
                finalizarJogo() // Chama a função que finaliza o jogo (desativa o campo de texto e botão de adivinhar)
            } else {
                // Se o jogador errou, aumenta o número de tentativas
                tentativas++

                // Verifica se o jogador usou todas as tentativas
                if (tentativas >= maxTentativas) {
                    // Se o jogador usou todas as tentativas, mostra uma mensagem de que ele perdeu
                    textoResultado.text = "Você não conseguiu adivinhar a palavra! A palavra correta era '$palavraCorreta'."
                    finalizarJogo() // Finaliza o jogo
                } else {
                    // Se o jogador ainda tem tentativas, mostra quantas tentativas restam
                    textoResultado.text = "Tente novamente! Tentativas restantes: ${maxTentativas - tentativas}"
                }
            }
        }

        // Quando o jogador clica no botão "Voltar"
        botaoVoltar.setOnClickListener {
            reiniciarJogo() // Chama a função que reinicia o jogo para o estado inicial
        }

        // Esconde o botão de voltar, pois ele só deve aparecer quando o jogo termina
        botaoVoltar.visibility = View.GONE
    }

    // Esta função desativa o campo de texto e o botão de adivinhar e mostra o botão de voltar
    private fun finalizarJogo() {
        campoEntrada.isEnabled = false
        botaoAdivinhar.isEnabled = false
        botaoVoltar.visibility = View.VISIBLE
    }

    // Esta função reinicia o jogo: reseta as tentativas e reativa o campo de texto e o botão de adivinhar
    private fun reiniciarJogo() {
        tentativas = 0 // Reseta o número de tentativas
        campoEntrada.isEnabled = true // Reativa o campo onde o jogador digita
        botaoAdivinhar.isEnabled = true // Reativa o botão de adivinhar
        campoEntrada.text.clear() // Limpa o que o jogador digitou antes
        textoResultado.text = "Tente adivinhar a palavra!" // Reseta o texto de instrução
        botaoVoltar.visibility = View.GONE // Esconde o botão de voltar
    }
}
