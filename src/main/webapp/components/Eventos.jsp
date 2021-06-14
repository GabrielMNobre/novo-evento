<%-- 
    Document   : eventos
    Created on : 30/05/2021, 15:57:13
    Author     : gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="/Eventos/css/eventos.css">
        <title>Novo+Evento</title>
    </head>
    <body>
        <div class="navbar-fixed">
            <nav>
                <div class="nav-wrapper blue darken-4">
                    <a href="/Eventos" style="margin-left: 4rem;" class="brand-logo left">
                        Novo+Evento
                    </a>
                    <ul class="right hide-on-med-and-down">
                        <li>
                            <a href="#modalEvento" class="btn modal-trigger blue darken-4" onclick="limparNovoEvento()">
                                <i class="material-icons left">add</i>Novo Evento
                            </a>
                            <a href="#modalTipoEvento" class="btn modal-trigger blue darken-4">
                                <i class="material-icons left">add</i>Tipo de Evento
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div id="modalEvento" class="modal">
            <div class="modal-content">
                <h4>
                    Novo Evento
                    <span class="right modal-close">
                        <a href="#">
                            <i class="material-icons black-text">close</i>
                        </a>
                    </span>
                </h4>
                <div class="row">
                    <form id="formNovoEvento">
                        <div class="col s6">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">format_quote</i>
                                <input type="text" id="nome" name="nome" class="autocomplete">
                                <input type="hidden" id="idEvento" name="idEvento" value="0">
                                <label for="nome" id="n">Nome do evento</label>
                            </div>
                        </div>
                        <div class="col s6">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">format_list_bulleted</i>
                                <select name="tipo" id="tipo">
                                    <option value="" disabled selected>Escolha sua opção</option>
                                </select>
                                <label for="tipo">Tipo de evento</label>
                            </div>
                        </div>
                        <div class="col s6">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">date_range</i>
                                <input type="date" id="dataEvento" name="dataEvento" class="autocomplete">
                                <label for="dataEvento">Dia do evento</label>
                            </div>
                        </div>
                        <div class="col s6">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">person_pin</i>
                                <input type="number" id="qtdParticipantes" min="1" name="qtdParticipantes" class="autocomplete">
                                <label for="qtdParticipantes" id='q'>Quantidade de convidados</label>
                            </div>
                        </div>
                        <div class="center">
                            <button class="btn waves-effect waves-light" id="subNovoEvento">salvar
                                <i class="material-icons left">add</i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="modalTipoEvento" class="modal">
            <div class="modal-content">
                <h4>
                    Novo Tipo de Evento
                    <span class="right modal-close">
                        <a href="#">
                            <i class="material-icons black-text">close</i>
                        </a>
                    </span>
                </h4>
                <div class="row">
                    <form id="formTipoEvento">
                        <div class="col s12">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">format_quote</i>
                                <input type="text" id="tipoEvento" name="tipoEvento" class="autocomplete">
                                <label for="tipoEvento">Nome do tipo de evento</label>
                            </div>
                        </div>
                        <div class="center">
                            <button class="btn waves-effect waves-light" id="subTipoEvento">adicionar
                                <i class="material-icons left">add</i>
                            </button>
                        </div>
                    </form>
                </div>
                <div class="row" style="margin-bottom: 0">
                    <div class="center">
                        <h5>Tipos de evento cadastrados</h5>
                    </div>
                </div>
                <div class="row">
                    <div class="center">
                        <ul id="badgeTipos"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row" id="content"></div>
            <div id="modalExcluir" class="modal">
                <div class="modal-content">
                    <h4>
                        Deseja excluir o evento?
                        <span class="right modal-close">
                            <a href="#">
                                <i class="material-icons black-text">close</i>
                            </a>
                        </span>
                    </h4>
                    <p id="excNome" style="margin: 0;"></p>
                    <p id="excId" style="display: none"></p>
                    <p id="excQtd" style="margin: 0;"></p>
                    <p id="excData" style="margin-top: 0;"></p>
                    <div class="center">
                        <a class="waves-effect waves-light red btn" onclick="excluirEvento($('#excId').text())">
                            <i class="material-icons left">cancel</i>Excluir
                        </a>
                    </div>
                </div>
            </div>
            <div id="modalListaConvidados" class="modal">
                <div class="modal-content">
                    <h4>
                        Lista de convidados
                        <span class="right modal-close">
                            <a href="#">
                                <i class="material-icons black-text">close</i>
                            </a>
                        </span>
                    </h4>
                    <table class="responsive-table striped">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Telefone</th>
                                <th>Tipo de convidado</th>
                                <th>Quantidade</th>
                            </tr>
                        </thead>
                        <tbody id="listaConvidados">

                        </tbody>
                    </table>
                </div>
            </div>
            <div id="modalAdd" class="modal">
                <div class="modal-content">
                    <h5>
                        Adicionar convidado ao evento: <span id="titleEvento"></span>
                        <span class="right modal-close">
                            <a href="#">
                                <i class="material-icons black-text">close</i>
                            </a>
                        </span>
                    </h5>
                    <p>
                        <span style="display:block;">Tipo de convidado</span>
                        <label style="margin-right: 1rem;">
                            <input class="with-gap" name="tipoConvidado" type="radio" value="1" checked />
                            <span>Individual</span>
                        </label>
                        <label>
                            <input class="with-gap" name="tipoConvidado" type="radio" value="2" />
                            <span>Família</span>
                        </label>
                    </p>
                    <form id="formAddConvidado">
                        <div class="col s6">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">person_pin</i>
                                <input type="text" id="nomeConvidado" name="nome" class="autocomplete">
                                <input type="hidden" id="idEventoAdd" name="idEvento">
                                <input type="hidden" id="idConvidadoAdd" name="idConvidado" value="0">
                                <label for="nome" id="nm">Nome Convidado</label>
                            </div>
                        </div>
                        <div class="col s6">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">phone</i>
                                <input type="text" id="telefone" name="telefone" class="autocomplete">
                                <label for="telefone" id="tel">Telefone para contato</label>
                            </div>
                        </div>
                        <div class="col s6" id="familia">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">person_pin</i>
                                <input type="number" id="qtdPessoa" min="1" name="qtdPessoa" class="autocomplete">
                                <label for="qtdPessoa" id="qtd">Quantidade de pessoas da família</label>
                            </div>
                        </div>
                        <div class="center">
                            <button class="btn waves-effect waves-light" id="subAddConvidado">salvar
                                <i class="material-icons left">add</i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js" integrity="sha512-0XDfGxFliYJPFrideYOoxdgNIvrwGTLnmK20xZbCAvPfLGQMzHUsaqZK8ZoH+luXGRxTrS46+Aq400nCnAT0/w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="/Eventos/js/eventos.js"></script>
</html>
