import java.util.Scanner;

public class jogoDaVelha {

    public static void main(String[] args) {
        char[][] tabuleiro = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        char jogadorAtual = 'X';
        boolean jogoAtivo = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo da Velha!");

        while (jogoAtivo) {
            imprimirTabuleiro(tabuleiro);
            System.out.println("Jogador " + jogadorAtual + ", é a sua vez!");

            int linha, coluna;
            while (true) {
                System.out.print("Digite a linha (0-2): ");
                linha = scanner.nextInt();
                System.out.print("Digite a coluna (0-2): ");
                coluna = scanner.nextInt();

                if (linha >= 0 && linha <= 2 && coluna >= 0 && coluna <= 2 && tabuleiro[linha][coluna] == ' ') {
                    break;
                } else {
                    System.out.println("Posição inválida. Tente novamente.");
                }
            }

            tabuleiro[linha][coluna] = jogadorAtual;

            if (verificarVencedor(tabuleiro, jogadorAtual)) {
                imprimirTabuleiro(tabuleiro);
                System.out.println("Parabéns! Jogador " + jogadorAtual + " venceu!");
                jogoAtivo = false;
            } else if (tabuleiroCheio(tabuleiro)) {
                imprimirTabuleiro(tabuleiro);
                System.out.println("O jogo empatou!");
                jogoAtivo = false;
            } else {
                jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }

    public static void imprimirTabuleiro(char[][] tabuleiro) {
        System.out.println("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
        System.out.println("\n");
    }

    public static boolean verificarVencedor(char[][] tabuleiro, char jogador) {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) ||
                (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador)) {
                return true;
            }
        }
        // Verifica diagonais
        return (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) ||
               (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador);
    }

    public static boolean tabuleiroCheio(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
