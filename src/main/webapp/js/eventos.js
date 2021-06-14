//INICIALIZAÇÃO
$(document).ready(function () {
    $('.modal').modal();
    carregarTipoEvento();
    carregarEventos();
    $('select').formSelect();
    $('#familia').hide();
    $("#telefone").mask("(00) 00000-0000");
});

$('input[name="tipoConvidado"]').change(function () {
    const value = parseInt($('input[name="tipoConvidado"]:checked').val());
    value === 1 ?
        (
            $('#familia').fadeOut(),
            $('#nm').html('Nome Convidado')
        ) :
        (
            $('#familia').fadeIn(),
            $('#nm').html('Sobrenome da família')
        );
});

//SUBMIT DOS FORMULÁRIOS
$("#subTipoEvento").click(function (e) {
    e.preventDefault();
    $.ajax({
        method: "POST",
        url: "CadastrarTipoEventoServlet",
        dataType: 'json',
        data: $('form#formTipoEvento').serialize(),
        success: function (data) {
            if (data === "true") {
                alert("Tipo de Evento cadastrado!");
            } else if (data === "invalid") {
                alert("Este evento já existe!");
            } else {
                alert("Ocorreu um erro, tente novamente!", 2000);
            }
            $("#tipoEvento").val("");
            carregarTipoEvento();
        },
        error: function () {
            console.log("Deu erro");
        }
    });
});

$("#subAddConvidado").click(function (e) {
    e.preventDefault();
    $.ajax({
        method: "POST",
        url: "SalvarConvidadoServlet",
        dataType: 'json',
        data: $('form#formAddConvidado').serialize(),
        success: function (data) {
            console.log(data);
            if (data) {
                alert("Informações do convidado foram salvas!");
                window.location.href = '/Eventos';
            } else {
                alert("Ocorreu um erro, tente novamente!");
            }
        },
        error: function () {
            console.log("Deu erro");
        }
    });
});

$("#subNovoEvento").click(function (e) {
    console.log("function add evento");
    e.preventDefault();
    let nome = $("#nome").val();
    let dataEvento = $("#dataEvento").val();
    let qtdParticipantes = $("#qtdParticipantes").val();
    let tipo = $("#tipo").val();
    let data = dataEvento.split('-');

    if (nome === "") {
        alert("É necessário adicionar um nome ao evento");
        return false;
    }
    if (dataEvento === "") {
        alert("É necessário adicionar uma data ao evento");
        return false;
    }
    if (new Date(data) <= new Date()) {
        alert("Só podemos marcar um evento que ocorra após o dia de hoje!");
        return false;
    }
    if (qtdParticipantes === "") {
        alert("É necessário adicionar uma quantidade máxima de participantes ao evento");
        return false;
    }
    if (tipo === null) {
        alert("É necessário adicionar um tipo ao evento");
        return false;
    }


    $.ajax({
        method: "POST",
        url: "SalvarEventoServlet",
        dataType: 'json',
        data: $('form#formNovoEvento').serialize(),
        success: function (data) {
            console.log("entrou aqui");
            console.log(data);
            if (data) {
                $("#modalEvento").modal("close");
                alert("As informações do evento foram salvas!");
            } else {
                $("#modalEvento").modal("close");
                alert("Ocorreu um erro, tente novamente!");
            }
            window.location.href = "/Eventos";
        },
        error: function () {
            console.log("Deu erro");
        }
    });
});

//EXCLUSÃO 
function excluirEvento(id) {
    $.ajax({
        method: "GET",
        url: "ExcluirEventoServlet?id=" + id,
        success: function (data) {
            data ? alert("Evento excluído!") : alert("Tivemos um problema, tente novamente.");
            window.location.href = '/Eventos';
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

function excluirConvidado(id) {
    $.ajax({
        method: "GET",
        url: "ExcluirConvidadoServlet?id=" + id,
        success: function (data) {
            data ? alert("Convidado excluído!") : alert("Tivemos um problema, tente novamente.");
            window.location.href = '/Eventos';
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

function excluirTipoEvento(id) {
    $.ajax({
        method: "GET",
        url: "ExcluirTipoEventoServlet?id=" + id,
        success: function (data) {
            data ? alert("Tipo de evento excluído!") : alert("Tivemos um problema, tente novamente.");
            carregarTipoEvento();
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

//LIMPEZA
function limparNovoEvento() {
    $("#idEvento").val(0);
    $("#nome").val("");
    $("#qtdParticipantes").val("");
    $("#dataEvento").val("");
}

function limparAddConvidado() {
    $("#idEventoAdd").val(0);
    $("#nomeConvidado").val("");
    $("#qtdPessoa").val("");
    $("#telefone").val("");
}

//MONTAR MODAIS DE EDIÇÃO
function carregarTipoEvento() {
    $.ajax({
        method: "GET",
        url: "ListarTipoEventoServlet",
        success: function (data) {
            let option = '';
            data.map((tipo) => {
                option += "<option value='" + tipo.idTipoEvento + "'>" + tipo.tipoEvento + "</option>";
            });
            $('select').formSelect('destroy');
            $("#tipo").html(option);
            $('select').formSelect();

            let li = '';
            if (data.length === 0) {
                li = '<span>Não existe nenhum tipo de evento cadastrado!<span>';
            } else {
                data.map((tipo) => {
                    li += '<li id="' + tipo.idTipoEvento + '">' + tipo.tipoEvento +
                        ' <button onclick="excluirTipoEvento(' + tipo.idTipoEvento + ')">x</button></li>';
                });
            }
            $('#badgeTipos').html(li);
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

function carregarEventos() {
    $.ajax({
        method: "GET",
        url: "ListarEventoServlet?id=0",
        success: function (data) {
            montaEventos(data);
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

function carregarConvidado(idEvento, idConvidado) {
    $.ajax({
        method: "GET",
        url: "ListarConvidadoServlet?idEvento=" + idEvento + "&idConvidado=" + idConvidado,
        success: function (data) {
            if (data.length === 0) {
                let tr = '<tr><td class="center" colspan="4">Ainda não há convidados para esse evento</td></tr>';
                $("#listaConvidados").html(tr);
            } else {
                let tr = "";
                data.map((convidado) => {
                    let tipo = convidado.qtdPessoas === 1 ? 'Individual' : 'Família';
                    tr += '<tr><td>' + convidado.nome + '</td><td>' + convidado.telefone +
                        '</td><td>' + tipo +
                        '</td><td>' + convidado.qtdPessoas +
                        '</td><td><a onclick="recuperarConvidadoEdita(' + convidado.idConvidado + ')" href="#modalAdd" class="modal-trigger"><i class="material-icons center black-text">edit</i></a></td>' +
                        '<td><a onclick="excluirConvidado(' + convidado.idConvidado + ')"><i class="material-icons center red-text text-darken-4">delete_forever</i></a></td></tr>';
                });
                $("#listaConvidados").html(tr);
            }
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

function recuperarConvidadoEdita(id) {
    $.ajax({
        method: "GET",
        url: "ListarConvidadoServlet?idEvento=0&idConvidado=" + id,
        success: function (data) {
            limparAddConvidado();
            $("#idEventoAdd").val(data.idEvento);
            $("#idConvidadoAdd").val(data.idConvidado);
            $("#nomeConvidado").val(data.nome);
            $("#qtdPessoa").val(data.qtdPessoas);
            $("#telefone").val(data.telefone);
            $("#qtd").addClass('active');
            $("#tel").addClass('active');
            $("#nm").addClass('active');

            if (data.qtdPessoas > 1) {
                $('input[name="tipoConvidado"][value=2]').prop("checked", "checked");
                $('#familia').fadeIn();
            } else {
                $('input[name="tipoConvidado"][value=1]').prop("checked", "checked");
            }
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

function recuperarEventoEdita(id) {
    $.ajax({
        method: "GET",
        url: "ListarEventoServlet?id=" + id,
        success: function (data) {
            limparNovoEvento();
            $("#formNovoEvento #nome").val(data.nome);
            $("#formNovoEvento #idEvento").val(data.idEvento);
            $("#n").addClass('active');
            $("#formNovoEvento #dataEvento").val(data.data);
            $("#formNovoEvento #qtdParticipantes").val(data.qtdParticipantes);
            $("#q").addClass('active');
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

function recuperarAddConvidado(id) {
    $.ajax({
        method: "GET",
        url: "ListarEventoServlet?id=" + id,
        success: function (data) {
            limparAddConvidado();
            $("#titleEvento").html('<strong>' + data.nome + '</strong>');
            $("#idEventoAdd").val(data.idEvento);
            $("#qtdPessoa").val(1);
            $("#qtd").addClass('active');
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

function recuperarEventoDeleta(id) {
    $.ajax({
        method: "GET",
        url: "ListarEventoServlet?id=" + id,
        success: function (data) {
            console.log(data)
            $("#excNome").html('<strong>Nome do evento: </strong>' + data.nome);
            $("#excQtd").html('<strong>Quantidade de convidados: </strong>' + data.qtdParticipantes);
            $("#excData").html('<strong>Data do evento: </strong>' + data.data.split('-').reverse().join('/'));
            $("#excId").html(id);
        },
        error: function () {
            console.log("Deu erro");
        }
    });
}

//MONTA LISTA DE EVENTOS CADASTRADOS NO BANCO
function montaEventos(data) {
    if (data.length === 0) {
        let col = document.createElement('div');
        col.classList.add('col', 's12', 'center');
        let p = document.createElement('p');
        p.classList.add('flow-text');
        let textp = document.createTextNode('Ainda não temos eventos cadastrados');
        p.appendChild(textp);
        col.appendChild(p);
        $('#content').append(col);
    } else {
        data.map((evento) => {
            let col = document.createElement('div');
            col.classList.add('col', 's6');
            let card = document.createElement('div');
            card.classList.add('card-panel', 'teal', 'lighten-2');

            let title = document.createElement('h5');
            title.style.marginTop = 0;
            let titulo = document.createTextNode(evento.nome);
            title.appendChild(titulo);

            let close = document.createElement('span');
            close.classList.add('right');
            let acl = document.createElement('a');
            acl.href = '#modalExcluir';
            acl.setAttribute("onclick", "recuperarEventoDeleta(" + evento.idEvento + ")");
            acl.classList.add('modal-trigger');
            let icl = document.createElement('i');
            icl.classList.add('material-icons', 'red-text', 'text-darken-4');
            let tcl = document.createTextNode('close');
            icl.appendChild(tcl);
            acl.appendChild(icl);
            close.appendChild(acl);
            title.appendChild(close);
            card.appendChild(title);

            let tipo = document.createElement('p');
            tipo.style.margin = 0;
            let strT = document.createElement('strong');
            let sxtT = document.createTextNode('Tipo de evento: ');
            strT.appendChild(sxtT);
            let txtT = document.createTextNode(evento.tipo);
            tipo.appendChild(strT);
            tipo.appendChild(txtT);
            card.appendChild(tipo);

            let qtd = document.createElement('p');
            qtd.style.margin = 0;
            let strQ = document.createElement('strong');
            let sxtQ = document.createTextNode('Quantidade máxima de convidados: ');
            strQ.appendChild(sxtQ);
            let txtQ = document.createTextNode(evento.qtdParticipantes);
            qtd.appendChild(strQ);
            qtd.appendChild(txtQ);
            card.appendChild(qtd);

            let dataE = document.createElement('p');
            dataE.style.marginTop = 0;
            let strD = document.createElement('strong');
            let sxtD = document.createTextNode('Data do evento: ');
            strD.appendChild(sxtD);
            let txtD = document.createTextNode(evento.data.split('-').reverse().join('/'));
            dataE.appendChild(strD);
            dataE.appendChild(txtD);
            card.appendChild(dataE);

            let aEdit = document.createElement('a');
            aEdit.classList.add('waves-effect', 'waves-light', 'btn', 'modal-trigger');
            aEdit.style.marginRight = '1rem';
            aEdit.href = '#modalEvento';
            aEdit.setAttribute("onclick", "recuperarEventoEdita(" + evento.idEvento + ")");
            let iEdit = document.createElement('i');
            iEdit.classList.add('material-icons', 'left');
            let tEdit = document.createTextNode('edit');
            let txtEdit = document.createTextNode('editar');
            iEdit.appendChild(tEdit);
            aEdit.appendChild(iEdit);
            aEdit.appendChild(txtEdit);
            card.appendChild(aEdit);

            let aVer = document.createElement('a');
            aVer.classList.add('waves-effect', 'waves-light', 'btn', 'modal-trigger');
            aVer.href = '#modalListaConvidados';
            aVer.setAttribute("onclick", "carregarConvidado(" + evento.idEvento + ", 0)");
            let iVer = document.createElement('i');
            iVer.classList.add('material-icons', 'left');
            let tVer = document.createTextNode('format_list_bulleted');
            let txtVer = document.createTextNode('ver convidados');
            iVer.appendChild(tVer);
            aVer.appendChild(iVer);
            aVer.appendChild(txtVer);
            card.appendChild(aVer);

            let cMais = document.createElement('div');
            cMais.classList.add('right');
            let aMais = document.createElement('a');
            aMais.classList.add('btn-floating', 'btn', 'waves-effect', 'waves-light', 'teal', 'modal-trigger');
            aMais.href = '#modalAdd';
            aMais.setAttribute("onclick", "recuperarAddConvidado(" + evento.idEvento + ")");
            let iMais = document.createElement('i');
            iMais.classList.add('material-icons');
            let tMais = document.createTextNode('add');;
            iMais.appendChild(tMais);
            aMais.appendChild(iMais);
            cMais.appendChild(aMais);
            card.appendChild(cMais);

            col.appendChild(card);
            $('#content').append(col);
        });
    }
}