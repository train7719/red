/**
 * @version 29/12/2003 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.fnc <BR>
 *         Classe:
 * @(#)FreedomFNC.java <BR>
 * 
 *                     Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 *                     modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 *                     na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 *                     Este programa � distribuido na esperan�a que possa ser util, mas SEM NENHUMA GARANTIA; <BR>
 *                     sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 *                     Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 *                     Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 *                     de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 *                     Tela principal do m�dulo financeiro.
 * 
 */

package org.freedom.modulos.fnc;

import java.io.File;

import org.freedom.library.functions.Funcoes;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.library.swing.frame.AplicativoPD;
import org.freedom.library.swing.frame.FPrincipalPD;
import org.freedom.library.swing.frame.LoginPD;
import org.freedom.modulos.cfg.view.frame.crud.tabbed.FFeriados;
import org.freedom.modulos.crm.view.frame.crud.detail.FContrato;
import org.freedom.modulos.crm.view.frame.crud.detail.FFinalizaProjeto;
import org.freedom.modulos.crm.view.frame.crud.plain.FMarcador;
import org.freedom.modulos.crm.view.frame.crud.plain.FModContr;
import org.freedom.modulos.crm.view.frame.crud.plain.FSitContr;
import org.freedom.modulos.crm.view.frame.crud.plain.FTarefa;
import org.freedom.modulos.crm.view.frame.utility.FGestaoProj;
import org.freedom.modulos.fnc.library.swing.frame.FRetCnab;
import org.freedom.modulos.fnc.library.swing.frame.FRetSiacc;
import org.freedom.modulos.fnc.view.frame.crud.detail.FBordero;
import org.freedom.modulos.fnc.view.frame.crud.detail.FCheque;
import org.freedom.modulos.fnc.view.frame.crud.plain.FBanco;
import org.freedom.modulos.fnc.view.frame.crud.plain.FCartCob;
import org.freedom.modulos.fnc.view.frame.crud.plain.FCodRetorno;
import org.freedom.modulos.fnc.view.frame.crud.plain.FManutCli;
import org.freedom.modulos.fnc.view.frame.crud.plain.FSinalizadores;
import org.freedom.modulos.fnc.view.frame.crud.plain.FTalaoCheq;
import org.freedom.modulos.fnc.view.frame.crud.tabbed.FConta;
import org.freedom.modulos.fnc.view.frame.crud.tabbed.FHistPad;
import org.freedom.modulos.fnc.view.frame.crud.tabbed.FPrefereFBB;
import org.freedom.modulos.fnc.view.frame.crud.tabbed.FPrefereFNC;
import org.freedom.modulos.fnc.view.frame.report.FRBordero;
import org.freedom.modulos.fnc.view.frame.report.FRCartaCobranca;
import org.freedom.modulos.fnc.view.frame.report.FRCobranca;
import org.freedom.modulos.fnc.view.frame.report.FRFluxoCaixa;
import org.freedom.modulos.fnc.view.frame.report.FRFluxoCaixaPeriodo;
import org.freedom.modulos.fnc.view.frame.report.FRFluxoCaixaReal;
import org.freedom.modulos.fnc.view.frame.report.FRFluxoCaixaRes;
import org.freedom.modulos.fnc.view.frame.report.FRPagar;
import org.freedom.modulos.fnc.view.frame.report.FRRecPag;
import org.freedom.modulos.fnc.view.frame.report.FRReceber;
import org.freedom.modulos.fnc.view.frame.report.FRReceberMes;
import org.freedom.modulos.fnc.view.frame.report.FRReceberSetor;
import org.freedom.modulos.fnc.view.frame.utility.FConsultaCheque;
import org.freedom.modulos.fnc.view.frame.utility.FManutPag;
import org.freedom.modulos.fnc.view.frame.utility.FManutRec;
import org.freedom.modulos.fnc.view.frame.utility.FPagCheque;
import org.freedom.modulos.fnc.view.frame.utility.FRemCnab;
import org.freedom.modulos.fnc.view.frame.utility.FRemSiacc;
import org.freedom.modulos.fnc.view.frame.utility.FTrnsLancCat;
import org.freedom.modulos.std.view.frame.crud.detail.FEmpresa;
import org.freedom.modulos.std.view.frame.crud.detail.FPlanoPag;
import org.freedom.modulos.std.view.frame.crud.plain.FClasCli;
import org.freedom.modulos.std.view.frame.crud.plain.FEstacao;
import org.freedom.modulos.std.view.frame.crud.plain.FImpressora;
import org.freedom.modulos.std.view.frame.crud.plain.FLiberaCredito;
import org.freedom.modulos.std.view.frame.crud.plain.FPapel;
import org.freedom.modulos.std.view.frame.crud.plain.FSetor;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoCli;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoCob;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoCred;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoFor;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoRestr;
import org.freedom.modulos.std.view.frame.crud.special.FCentroCusto;
import org.freedom.modulos.std.view.frame.crud.special.FLanca;
import org.freedom.modulos.std.view.frame.crud.special.FPlanejamento;
import org.freedom.modulos.std.view.frame.crud.tabbed.FCliente;
import org.freedom.modulos.std.view.frame.crud.tabbed.FCredCli;
import org.freedom.modulos.std.view.frame.crud.tabbed.FFornecedor;
import org.freedom.modulos.std.view.frame.crud.tabbed.FModBoleto;
import org.freedom.modulos.std.view.frame.crud.tabbed.FMoeda;
import org.freedom.modulos.std.view.frame.crud.tabbed.FVendedor;
import org.freedom.modulos.std.view.frame.report.FRBalancete;
import org.freedom.modulos.std.view.frame.report.FRBalanceteGrafico;
import org.freedom.modulos.std.view.frame.report.FRBoleto;
import org.freedom.modulos.std.view.frame.report.FRCentroCusto;
import org.freedom.modulos.std.view.frame.report.FRExtrato;
import org.freedom.modulos.std.view.frame.report.FRGraficoCC;
import org.freedom.modulos.std.view.frame.report.FRInadimplentes;
import org.freedom.modulos.std.view.frame.report.FRPontoEqui;
import org.freedom.modulos.std.view.frame.report.FRRazCli;
import org.freedom.modulos.std.view.frame.report.FRRazFor;
import org.freedom.modulos.std.view.frame.report.FRRazaoFin;
import org.freedom.modulos.std.view.frame.report.FRRestricao;
import org.freedom.modulos.std.view.frame.report.FRestrCli;
import org.freedom.modulos.std.view.frame.utility.FProcessaSL;

public class FreedomFNC extends AplicativoPD {

	public FreedomFNC() {

		super( "iconfnc.png", "splashFNC.png", 1, "RedTech", 6, "Gestion Financi�re", null, new FPrincipalPD( null, "bgFreedom2.jpg" ), LoginPD.class );

		addOpcao( -1, TP_OPCAO_MENU, "Fichier", "", 'A', 100000000, 0, false, null );
		addOpcao( 100000000, TP_OPCAO_MENU, "Tables", "", 'T', 100100000, 1, false, null );
		addOpcao( 100100000, TP_OPCAO_MENU, "Client", "", 'C', 100101000, 2, false, null );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Secteur", "Secteur", 'S', 100101010, 3, true, FSetor.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Command�", "Command�o", 's', 100101020, 3, true, FVendedor.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Types de clients", "TypeCli", 'T', 100101030, 3, true, FTipoCli.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Classification des clients", "Classification des clients", 'f', 100101040, 3, true, FClasCli.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Clients", "Clients", 'C', 100101050, 3, true, FCliente.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Cr�dit par client", "Cr�dit par client", 'r', 100101060, 3, true, FCredCli.class );

		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_ITEM, "devis", "devis", 'M', 100102000, 2, true, FMoeda.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Banque", "Banque", 'B', 100103000, 2, true, FBanco.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Type de collection", "TypeCol", 'T', 100104000, 2, true, FTipoCob.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "portefeuille de collection", "portefeuille de collection", 'C', 100105000, 2, true, FCartCob.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Plan de paiement", "PlanPaie", 'P', 100106000, 2, true, FPlanoPag.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Vacances", "Vacances", 'F', 100107000, 2, true, FFeriados.class );
		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Type de fournisseur", "TypeFor", 'i', 100107000, 2, true, FTipoFor.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Fournisseur", "Fournisseur", 'F', 100108000, 2, true, FFornecedor.class );
		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_MENU, "ban", "", 'b', 100109000, 2, false, null );
		addOpcao( 100109000, TP_OPCAO_ITEM, "Codes de retour", "Codes de retour", 'C', 100109010, 3, true, FCodRetorno.class );
		addOpcao( 100109000, TP_OPCAO_ITEM, "Fid�lisation des clients", "Fid�lisation des clients", 'M', 100109020, 3, true, FManutCli.class );
		addOpcao( 100000000, TP_OPCAO_MENU, "Outils", "", 'F', 100200000, 1, false, null );
		addOpcao( 100200000, TP_OPCAO_ITEM, "convertir entre les cat�gories", "convertir entre les cat�gories", 'F', 100200010, 1, true, FTrnsLancCat.class );
		addOpcao( 100000000, TP_OPCAO_MENU, "Pr�f�rences", "", 'P', 100300000, 1, false, null );
		addOpcao( 100300000, TP_OPCAO_ITEM, "Pr�f�rences financi�res", "Pr�f�rences financi�res", 'P', 100310000, 2, true, FPrefereFNC.class );
		addOpcao( 100300000, TP_OPCAO_ITEM, "Pr�f�rences ban", "Pr�f�rences ban", 'F', 100310000, 2, true, FPrefereFBB.class );
		// addSeparador( 100100000 );
		addOpcao( 100000000, TP_OPCAO_MENU, "R�glages", "", 'C', 100400000, 1, false, null );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Poste de travail", "Poste de travail", 't', 100401000, 2, true, FEstacao.class );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Imprimante", "Imprimantes", 'I', 100402000, 2, true, FImpressora.class );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Papier", "Papiers", 'P', 100403000, 2, true, FPapel.class );
		addSeparador( 100400000 );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Entreprise", "Entreprise", 'E', 100404000, 2, true, FEmpresa.class );

		addOpcao( -1, TP_OPCAO_MENU, "Payer", "", 'P', 200000000, 0, false, null );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Maintenance", "comptes de maintenance payables", 'M', 200100000, 1, true, FManutPag.class );
		addOpcao( 200000000, TP_OPCAO_MENU, "Listes", "", 'L', 200200000, 1, false, null );
		addOpcao( 200200000, TP_OPCAO_ITEM, "Pagar/Pagas", "Pagar/Pagas", 'P', 200201000, 2, true, FRPagar.class );
		addOpcao( 200200000, TP_OPCAO_ITEM, "Raz�o", "Raz�o", 'R', 200202000, 2, true, FRRazFor.class );
		addSeparador( 200000000 );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Emiss�o de cheques", "Emiss�o de cheques", 'm', 200300000, 2, true, FPagCheque.class );

		addOpcao( -1, TP_OPCAO_MENU, "Recevoir", "", 'R', 300000000, 0, false, null );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Maintenance", "Manuten��o de contas a receber", 'M', 300100000, 1, true, FManutRec.class );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Bordero", "Bordero", 'B', 300200000, 1, true, FBordero.class );
		addOpcao( 300000000, TP_OPCAO_MENU, "Febraban", "", 'F', 300300000, 1, false, null );
		addOpcao( 300300000, TP_OPCAO_MENU, "SIACC", "SIACC", 'S', 300301000, 2, false, null );
		addOpcao( 300301000, TP_OPCAO_ITEM, "Remessa", "Remessa Siacc", 'm', 300301010, 2, true, FRemSiacc.class );
		addOpcao( 300301000, TP_OPCAO_ITEM, "Retorno", "Retorno Siacc", 't', 300301020, 2, true, FRetSiacc.class );
		addOpcao( 300300000, TP_OPCAO_MENU, "CNAB", "CNAB", 'C', 300302000, 2, false, null );
		addOpcao( 300302000, TP_OPCAO_ITEM, "Remessa", "Remessa Cnab", 'm', 300302010, 2, true, FRemCnab.class );
		addOpcao( 300302000, TP_OPCAO_ITEM, "Retorno", "Retorno Cnab", 't', 300302020, 2, true, FRetCnab.class );
		addOpcao( 300000000, TP_OPCAO_MENU, "Listes", "", 'L', 300400000, 1, false, null );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Receber/Recebidas", "Receber/Recebidas", 'R', 300401000, 2, true, FRReceber.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Inadimplentes", "Inadimplentes", 'I', 300402000, 2, true, FRInadimplentes.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Bordero de cobran�a", "Bordero de cobran�a", 'B', 300403000, 2, true, FRBordero.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Descontos por setor", "Descontos por setor", 'D', 300404000, 2, true, FRReceberSetor.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Raz�o", "Raz�o", 'R', 300405000, 2, true, FRRazCli.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Recebimentos por M�s", "Recebimentos por M�s", 'M', 300407000, 2, true, FRReceberMes.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Relat�rio de cobran�a", "Relat�rio de cobran�a", 'o', 300408000, 2, true, FRCobranca.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Carta de cobran�a", "Carta de cobran�a", 'C', 300409000, 2, true, FRCartaCobranca.class );

		addSeparador( 300000000 );
		addOpcao( 300000000, TP_OPCAO_MENU, "Projetos/Contratos", "", 'P', 300500000, 1, false, null );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Projetos", "Projetos/Contratos", 'P', 300501000, 2, true, FContrato.class );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Finaliza Projetos/Contratos", "Finaliza Projetos/Contratos", 'P', 300502000, 2, true, FFinalizaProjeto.class );
		addSeparador( 300500000 );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Marcador", "Marcador", 'P', 300503000, 2, true, FMarcador.class );
		addSeparador( 300500000 );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Tarefas/Subtarefas", "Tarefas/Subtarefas", 'T', 300504000, 2, true, FTarefa.class );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Situa��o Projeto/Contratos", "Situa��o Projeto/Contratos", 'S', 300506000, 2, true, FSitContr.class );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Gest�o de Projetos/Contratos", "Gest�o de Projetos/Contratos", 'G', 300507000, 2, true, FGestaoProj.class );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Modelo do Contrato", "Modelo do Contrato", 'M', 300508000, 2, true, FModContr.class );
		addOpcao( -1, TP_OPCAO_MENU, "financi�re", "", 'F', 400000000, 0, false, null );
		addOpcao( 400000000, TP_OPCAO_MENU, "Boleto/Recibo", "", 'o', 400100000, 1, false, null );
		addOpcao( 400100000, TP_OPCAO_ITEM, "Modelo", "Modelo de boleto/recibo", 'M', 400101000, 2, true, FModBoleto.class );
		addOpcao( 400100000, TP_OPCAO_ITEM, "Imprimir", "Boleto/Recibo", 'I', 400101000, 2, true, FRBoleto.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Banco", "Banco", 'B', 400200000, 1, true, FBanco.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Planejamento", "Planejamento", 'P', 400300000, 1, true, FPlanejamento.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Centro de custo", "Centro de Custos", 'C', 400400000, 1, true, FCentroCusto.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Contas", "Contas", 't', 400500000, 1, true, FConta.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Hist�rico", "Hist�rico", 't', 400600000, 1, true, FHistPad.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Lan�amentos", "Lan�amentos", 'L', 400700000, 1, true, FLanca.class );

		addSeparador( 400000000 );

		addOpcao( 400000000, TP_OPCAO_ITEM, "Tipo de cr�dito", "Tipo de cr�dito", 'T', 400800000, 1, true, FTipoCred.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Libera��o de cr�dito", "Libera��o de cr�dito", 'b', 400900000, 1, true, FLiberaCredito.class );

		addOpcao( 400000000, TP_OPCAO_ITEM, "Tipo de Restri��o", "Tipo de Restri��o", 's', 401000000, 1, true, FTipoRestr.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Restri��o de clientes", "Restri��o de clientes", 'r', 401100000, 1, true, FRestrCli.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Receber/Pagar", "Receber/Pagar", 'g', 401400000, 1, true, FRRecPag.class );

		addSeparador( 400000000 );

		addOpcao( 400000000, TP_OPCAO_ITEM, "Reprocessa saldo", "Reprocessamento de saldos", 'R', 401000000, 1, true, FProcessaSL.class );
		addSeparador( 400000000 );

		addOpcao( 400000000, TP_OPCAO_MENU, "Listes", "", 's', 401200000, 1, false, null );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Extraire", "Extraire", 'E', 401201000, 2, true, FRExtrato.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Extrato Previsionado", "Extrato Previsionado", 'P', 401210000, 2, true, FRExtrato.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Balancete", "Balancete", 'B', 401202000, 2, true, FRBalancete.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Relat�rio financeiro por C.C.", "Relatorio Financeiro por C.C.", 'R', 401203000, 2, true, FRCentroCusto.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Raz�o financeiro", "Raz�o financeiro", 'z', 401204000, 2, true, FRRazaoFin.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Fluxo de caixa", "Fluxo de caixa", 'F', 401205000, 2, true, FRFluxoCaixa.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Fluxo de caixa resumido", "Fluxo de caixa resumido", 'F', 401207000, 2, true, FRFluxoCaixaRes.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Restri��o/clientes", "Restri��o/clientes", 'R', 401206000, 2, true, FRRestricao.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Ponto de equilibrio", "Ponto de equilibrio", 'P', 401208000, 2, true, FRPontoEqui.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Fluxo de caixa realizado", "Fluxo de caixa realizado", 'c', 401209000, 2, true, FRFluxoCaixaReal.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Fluxo de caixa por per�odo", "Fluxo de caixa por per�odo", 'p', 401210000, 2, true, FRFluxoCaixaPeriodo.class );

		addOpcao( 400000000, TP_OPCAO_MENU, "Gr�ficos", "", 'G', 401300000, 1, false, null );
		addOpcao( 401300000, TP_OPCAO_ITEM, "Balancete Gr�fico", "Balancete Gr�fico", 'B', 401301000, 2, true, FRBalanceteGrafico.class );
		addOpcao( 401300000, TP_OPCAO_ITEM, "Gr�fico financeiro por C.C", "Gr�fico Financeiro por C.C", 'F', 401302000, 2, true, FRGraficoCC.class );

		addSeparador( 400000000 );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Tipo de cobran�a", "Tipo de cobran�a", 'o', 401400000, 2, true, FTipoCob.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Talon�rio de cheques", "Talon�rio de cheques", 'h', 401500000, 1, true, FTalaoCheq.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Cheques", "Cheques", 'h', 401600000, 1, true, FCheque.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Consulta cheques", "Consulta cheques", 's', 401700000, 1, true, FConsultaCheque.class );
		addSeparador( 400000000 );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Sinalizadores", "Sinalizadores", 'S', 401700000, 1, true, FSinalizadores.class );

		addBotao( "barraUsuario.png", "Client", "Clients", 100101050, FCliente.class );
		addBotao( "btContaPagar.png", "Contas a pagar", "comptes de maintenance payables", 200100000, FManutPag.class );
		addBotao( "btContaReceber.png", "Contas a receber", "Manuten��o de contas a receber", 300100000, FManutRec.class );
		addBotao( "btLancamentoFin.png", "Lan�amentos financeiros", "Lan�amentos", 400600000, FLanca.class );
		addBotao( "btRemessaCNAB.png", "Remessa CNAB", "Remessa CNAB", 300302010, FRemCnab.class );
		addBotao( "btRetornoCNAB.png", "Retorno CNAB", "Retorno CNAB", 300302020, FRetCnab.class );

		ajustaMenu();

		nomemodulo = "Financeiro";

	}

	public static void main( String sParams[] ) {

		try {
			File fileini = Aplicativo.loadIni( "ARQINI", "freedom.ini" );
			Aplicativo.setLookAndFeel( fileini );
			FreedomFNC freedom = new FreedomFNC();
			freedom.show();
		} catch ( Throwable e ) {
			Funcoes.criaTelaErro( "Erreur d'ex�cution" );
			e.printStackTrace();
		}
	}
}
