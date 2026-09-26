const API = "http://localhost:8080/estacionamento";

const formVeiculo =
    document.getElementById("form-veiculo");

const formSaida =
    document.getElementById("form-saida");

const containerVagas =
    document.getElementById("vagas");

const containerVeiculos =
    document.getElementById("veiculos");

const dialog =
    document.getElementById("meu-dialog");


// ========================================
// CADASTRAR VEÍCULO
// ========================================

formVeiculo.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();


        const placa = document
            .getElementById("placa")
            .value
            .trim()
            .toUpperCase();


        const possuiSeloPcd =
            document.getElementById("pcd").checked;


        const veiculo = {
            placa: placa,
            possuiSeloPcd: possuiSeloPcd
        };


        try {

            const resposta = await fetch(
                `${API}/veiculos`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(veiculo)
                }
            );


            if (!resposta.ok) {

                const mensagem =
                    await resposta.text();

                alert(
                    "Erro ao cadastrar veículo: " +
                    mensagem
                );

                return;
            }


            alert(
                "Veículo cadastrado com sucesso!"
            );


            formVeiculo.reset();


        } catch (erro) {

            console.error(erro);

            alert(
                "Não foi possível conectar ao servidor."
            );

        }

    }
);


// ========================================
// ABRIR DIALOG DE ENTRADA
// ========================================

document
    .getElementById("abrir-dialog")
    .addEventListener(
        "click",
        async function () {

            // Busca os veículos antes
            // de abrir o dialog
            await carregarVeiculos();

            dialog.showModal();

        }
    );


// ========================================
// FECHAR DIALOG
// ========================================

document
    .getElementById("fechar-dialog")
    .addEventListener(
        "click",
        function () {

            dialog.close();

        }
    );


// ========================================
// REGISTRAR ENTRADA
// ========================================

async function estacionarVeiculo(
    placa,
    numeroVaga
) {

    const entrada = {
        placa: placa,
        numeroVaga: numeroVaga
    };


    try {

        const resposta = await fetch(
            `${API}/entrada`,
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(entrada)
            }
        );


        const mensagem =
            await resposta.text();


        if (!resposta.ok) {

            alert(
                "Erro ao registrar entrada: " +
                mensagem
            );

            return;
        }


        alert(mensagem);


        // Fecha o dialog
        dialog.close();


        // Atualiza as vagas
        await carregarVagas();


    } catch (erro) {

        console.error(erro);

        alert(
            "Não foi possível conectar ao servidor."
        );

    }

}


// ========================================
// REGISTRAR SAÍDA
// ========================================

formSaida.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();


        const placa = document
            .getElementById("placa-saida")
            .value
            .trim()
            .toUpperCase();


        try {

            const resposta = await fetch(
                `${API}/saida/${encodeURIComponent(placa)}`,
                {
                    method: "POST"
                }
            );


            const mensagem =
                await resposta.text();


            if (!resposta.ok) {

                alert(
                    "Erro ao registrar saída: " +
                    mensagem
                );

                return;
            }


            alert(mensagem);


            formSaida.reset();


            // Atualiza as vagas
            // depois da saída
            await carregarVagas();


        } catch (erro) {

            console.error(erro);

            alert(
                "Não foi possível conectar ao servidor."
            );

        }

    }
);


// ========================================
// BUSCAR VAGAS
// ========================================

async function carregarVagas() {

    try {

        const resposta =
            await fetch(`${API}/vagas`);


        if (!resposta.ok) {

            console.error(
                "Erro ao buscar vagas."
            );

            return;
        }


        const vagas =
            await resposta.json();


        mostrarVagas(vagas);


    } catch (erro) {

        console.error(
            "Erro ao conectar com o servidor:",
            erro
        );

    }

}


// ========================================
// BUSCAR VEÍCULOS
// ========================================

async function carregarVeiculos() {

    try {

        const resposta =
            await fetch(`${API}/veiculos`);


        if (!resposta.ok) {

            console.error(
                "Erro ao buscar veículos."
            );

            return;
        }


        const veiculos =
            await resposta.json();


        mostrarVeiculos(veiculos);


    } catch (erro) {

        console.error(
            "Erro ao conectar com o servidor:",
            erro
        );

    }

}


// ========================================
// MOSTRAR VEÍCULOS NO DIALOG
// ========================================

function mostrarVeiculos(veiculos) {

    // Limpa antes de desenhar novamente
    containerVeiculos.innerHTML = "";


    // Caso não exista nenhum veículo
    if (veiculos.length === 0) {

        containerVeiculos.innerHTML = `
            <p>
                Nenhum veículo cadastrado.
            </p>
        `;

        return;
    }


    veiculos.forEach(function (veiculo) {

        // ========================================
        // CRIAR CARD DO VEÍCULO
        // ========================================

        const elementoVeiculo =
            document.createElement("div");


        elementoVeiculo.classList.add(
            "veiculo"
        );


        // ========================================
        // TIPO DO VEÍCULO
        // ========================================

        if (veiculo.possuiSeloPcd) {

            elementoVeiculo.classList.add(
                "pcd"
            );

        }


        // ========================================
        // HTML DO VEÍCULO
        // ========================================

        elementoVeiculo.innerHTML = `

            <h3>
                Veículo ${veiculo.placa}
            </h3>


            <div class="selecionar-vaga">

                <label>
                    Número da vaga
                </label>


                <input
                    type="number"
                    class="numero-vaga-dialog"
                    placeholder="Vaga"
                    min="1"
                    max="20"
                >


                <button
                    type="button"
                    class="btn-estacionar"
                >
                    Estacionar
                </button>

            </div>
        `;


        // ========================================
        // PEGAR INPUT DESTE CARD
        // ========================================

        const inputVaga =
            elementoVeiculo.querySelector(
                ".numero-vaga-dialog"
            );


        // ========================================
        // PEGAR BOTÃO DESTE CARD
        // ========================================

        const botaoEstacionar =
            elementoVeiculo.querySelector(
                ".btn-estacionar"
            );


        // ========================================
        // CLIQUE EM ESTACIONAR
        // ========================================

        botaoEstacionar.addEventListener(
            "click",
            async function () {

                const numeroVaga =
                    Number(inputVaga.value);


                // ========================================
                // VALIDAR CAMPO
                // ========================================

                if (!numeroVaga) {

                    alert(
                        "Informe o número da vaga."
                    );

                    inputVaga.focus();

                    return;
                }


                // ========================================
                // VALIDAR NÚMERO DA VAGA
                // ========================================

                if (
                    numeroVaga < 1 ||
                    numeroVaga > 20
                ) {

                    alert(
                        "A vaga deve estar entre 1 e 20."
                    );

                    inputVaga.focus();

                    return;
                }


                // ========================================
                // REGISTRAR ENTRADA
                // ========================================

                await estacionarVeiculo(
                    veiculo.placa,
                    numeroVaga
                );

            }
        );


        // ========================================
        // ADICIONAR CARD NO DIALOG
        // ========================================

        containerVeiculos.appendChild(
            elementoVeiculo
        );

    });

}


// ========================================
// MOSTRAR VAGAS NA TELA
// ========================================

function mostrarVagas(vagas) {

    // Limpa antes de desenhar novamente
    containerVagas.innerHTML = "";


    vagas.forEach(function (vaga) {

        // ========================================
        // CRIAR CARD DA VAGA
        // ========================================

        const elementoVaga =
            document.createElement("div");


        elementoVaga.classList.add(
            "vaga"
        );


        // ========================================
        // LIVRE OU OCUPADA
        // ========================================

        if (vaga.veiculo === null) {

            elementoVaga.classList.add(
                "livre"
            );

        } else {

            elementoVaga.classList.add(
                "ocupada"
            );

        }


        // ========================================
        // TIPO DA VAGA
        // ========================================

        if (vaga.tipo === "PCD") {

            elementoVaga.classList.add(
                "pcd"
            );

        } else if (
            vaga.tipo === "EXTRA"
        ) {

            elementoVaga.classList.add(
                "extra"
            );

        }


        // ========================================
        // INFORMAÇÃO DO VEÍCULO
        // ========================================

        let informacaoVeiculo = "";


        if (vaga.veiculo !== null) {

            informacaoVeiculo = `
                <p>
                    Placa:
                    ${vaga.veiculo.placa}
                </p>
            `;

        } else {

            informacaoVeiculo = `
                <p>
                    Disponível
                </p>
            `;

        }


        // ========================================
        // HTML DA VAGA
        // ========================================

        elementoVaga.innerHTML = `

            <h3>
                Vaga ${vaga.numero}
            </h3>

            <p>
                ${vaga.tipo}
            </p>

            ${informacaoVeiculo}
        `;


        // ========================================
        // ADICIONAR VAGA NA TELA
        // ========================================

        containerVagas.appendChild(
            elementoVaga
        );

    });

}


// ========================================
// INICIAR PÁGINA
// ========================================

carregarVagas();