const celulas = document.querySelectorAll(".celula");
const mensagem = document.getElementById("mensagem");
const botaoReiniciar = document.getElementById("reiniciar");
const placarX = document.getElementById("placarX");
const placarO = document.getElementById("placarO");
const modal = document.getElementById("modal");
const vencedorMensagem = document.getElementById("vencedorMensagem");
const fecharModal = document.getElementById("fecharModal");

let jogadorAtual = "X";
let tabuleiro = ["", "", "", "", "", "", "", "", ""];
let jogoAtivo = true;
let vitoriasX = 0;
let vitoriasO = 0;

const condicoesVitoria = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6],
];

// verificar se há um vencedor
function verificarVencedor() {
    for (let i = 0; i < condicoesVitoria.length; i++) {
        const [a, b, c] = condicoesVitoria[i];
        if (tabuleiro[a] && tabuleiro[a] === tabuleiro[b] && tabuleiro[a] === tabuleiro[c]) {
            jogoAtivo = false;
            exibirModal(`Jogador ${tabuleiro[a]} venceu a rodada!`);
            if (tabuleiro[a] === "X") {
                vitoriasX++;
                placarX.textContent = vitoriasX;
            } else {
                vitoriasO++;
                placarO.textContent = vitoriasO;
            }
            return;
        }
    }
    if (!tabuleiro.includes("")) {
        jogoAtivo = false;
        exibirModal("Empate!");
    }
}

function exibirModal(mensagemTexto) {
    vencedorMensagem.textContent = mensagemTexto;
    modal.style.display = "flex";
}

fecharModal.addEventListener("click", () => {
    modal.style.display = "none";
    reiniciarRodada();
});


function reiniciarRodada() {
    tabuleiro = ["", "", "", "", "", "", "", "", ""];
    jogoAtivo = true;
    mensagem.textContent = `Vez do jogador ${jogadorAtual}`;
    celulas.forEach(celula => {
        celula.textContent = "";
        celula.classList.remove("X", "O");
    });
}


celulas.forEach(celula => {
    celula.addEventListener("click", () => {
        const index = celula.dataset.index;
        if (tabuleiro[index] || !jogoAtivo) return;

        tabuleiro[index] = jogadorAtual;
        celula.textContent = jogadorAtual;
        celula.classList.add(jogadorAtual);

        verificarVencedor();

        if (jogoAtivo) {
            jogadorAtual = jogadorAtual === "X" ? "O" : "X";
            mensagem.textContent = `Vez do jogador ${jogadorAtual}`;
        }
    });
});

// Reiniciar o jogo completo
botaoReiniciar.addEventListener("click", () => {
    vitoriasX = 0;
    vitoriasO = 0;
    placarX.textContent = vitoriasX;
    placarO.textContent = vitoriasO;
    reiniciarRodada();
});
