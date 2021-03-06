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

	super( "iconfnc.png", "splashFNC.png", 1, "RedTech", 6, "Gestion Commerciale", null, new FPrincipalPD( null, "bgFreedom2.jpg" ), LoginPD.class );

		addOpcao( -1, TP_OPCAO_MENU, "Fichier", "", 'A', 100000000, 0, false, null );
		addOpcao( 100000000, TP_OPCAO_MENU, "Tables", "", 'T', 100100000, 1, false, null );
		addOpcao( 100100000, TP_OPCAO_MENU, "Client", "", 'C', 100101000, 2, false, null );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Secteur", "Secteur", 'S', 100101010, 3, true, FSetor.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Command�", "Command�", 's', 100101020, 3, true, FVendedor.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Types de clients", "TypeCli", 'T', 100101030, 3, true, FTipoCli.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Classification des clients", "Classification des clients", 'f', 100101040, 3, true, FClasCli.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Clients", "Clients", 'C', 100101050, 3, true, FCliente.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Cr�dit par client", "Cr�dit par client", 'r', 100101060, 3, true, FCredCli.class );

		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_ITEM, "devis", "devis", 'M', 100102000, 2, true, FMoeda.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Banque", "Banque", 'B', 100103000, 2, true, FBanco.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Type de facture", "TypeCol", 'T', 100104000, 2, true, FTipoCob.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "portefeuille de facture", "portefeuille de facture", 'C', 100105000, 2, true, FCartCob.class );
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
		addOpcao( 100300000, TP_OPCAO_ITEM, "Pr�f�rences Commerciale", "Pr�f�rences Commerciale", 'P', 100310000, 2, true, FPrefereFNC.class );
		addOpcao( 100300000, TP_OPCAO_ITEM, "Pr�f�rences ban", "Pr�f�rences ban", 'F', 100310000, 2, true, FPrefereFBB.class );
		// addSeparador( 100100000 );
		addOpcao( 100000000, TP_OPCAO_MENU, "R�glages", "", 'C', 100400000, 1, false, null );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Poste de travail", "Poste de travail", 't', 100401000, 2, true, FEstacao.class );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Imprimante", "Imprimantes", 'I', 100402000, 2, true, FImpressora.class );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Papier", "Papiers", 'P', 100403000, 2, true, FPapel.class );
		addSeparador( 100400000 );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Entreprise", "Entreprise", 'E', 100404000, 2, true, FEmpresa.class );

		addOpcao( -1, TP_OPCAO_MENU, "Payer", "", 'P', 200000000, 0, false, null );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Maintenance", "Factures � payer", 'M', 200100000, 1, true, FManutPag.class );
		addOpcao( 200000000, TP_OPCAO_MENU, "Listes", "", 'L', 200200000, 1, false, null );
		addOpcao( 200200000, TP_OPCAO_ITEM, "Recevoir/payer", "Recevoir/payer", 'P', 200201000, 2, true, FRPagar.class );
		addOpcao( 200200000, TP_OPCAO_ITEM, "Justification", "Justification", 'R', 200202000, 2, true, FRRazFor.class );
		addSeparador( 200000000 );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Emission des ch�ques", "Emission des ch�ques", 'm', 200300000, 2, true, FPagCheque.class );

		addOpcao( -1, TP_OPCAO_MENU, "Recevoir", "", 'R', 300000000, 0, false, null );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Maintenance", "Maintenance des comptes � recevoir", 'M', 300100000, 1, true, FManutRec.class );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Bordero", "Bordero", 'B', 300200000, 1, true, FBordero.class );
		addOpcao( 300000000, TP_OPCAO_MENU, "Febraban", "", 'F', 300300000, 1, false, null );
		addOpcao( 300300000, TP_OPCAO_MENU, "SIACC", "SIACC", 'S', 300301000, 2, false, null );
		addOpcao( 300301000, TP_OPCAO_ITEM, "R�mitance", "R�mitance Siacc", 'm', 300301010, 2, true, FRemSiacc.class );
		addOpcao( 300301000, TP_OPCAO_ITEM, "Retour", "etour Siacc", 't', 300301020, 2, true, FRetSiacc.class );
		addOpcao( 300300000, TP_OPCAO_MENU, "CNAB", "CNAB", 'C', 300302000, 2, false, null );
		addOpcao( 300302000, TP_OPCAO_ITEM, "R�mitance", "R�mitance Cnab", 'm', 300302010, 2, true, FRemCnab.class );
		addOpcao( 300302000, TP_OPCAO_ITEM, "Retour", "Retour Cnab", 't', 300302020, 2, true, FRetCnab.class );
		addOpcao( 300000000, TP_OPCAO_MENU, "Listes", "", 'L', 300400000, 1, false, null );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Recevoir/Re�u", "Recevoir/Re�u", 'R', 300401000, 2, true, FRReceber.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "abandons", "abandons", 'I', 300402000, 2, true, FRInadimplentes.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "facture Bordero", "facture Bordero", 'B', 300403000, 2, true, FRBordero.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Remises par secteur", "Remises par secteur", 'D', 300404000, 2, true, FRReceberSetor.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Justification", "Justification", 'R', 300405000, 2, true, FRRazCli.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Re�us par mois", "Re�us par mois", 'M', 300407000, 2, true, FRReceberMes.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Rapport sur la facturation", "Rapport sur la facturation", 'o', 300408000, 2, true, FRCobranca.class );
		addOpcao( 300400000, TP_OPCAO_ITEM, "Lettre de facturation", "Lettre de facturation", 'C', 300409000, 2, true, FRCartaCobranca.class );

		addSeparador( 300000000 );
		addOpcao( 300000000, TP_OPCAO_MENU, "Projets/Contrats", "", 'P', 300500000, 1, false, null );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Projets", "Projets/Contrats", 'P', 300501000, 2, true, FContrato.class );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Projets/Contrats finis", "Projets/Contrats finis", 'P', 300502000, 2, true, FFinalizaProjeto.class );
		addSeparador( 300500000 );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Marqueur", "Marqueur", 'P', 300503000, 2, true, FMarcador.class );
		addSeparador( 300500000 );
		addOpcao( 300500000, TP_OPCAO_ITEM, "T�ches/Sous-t�ches", "T�ches/Sous-t�ches", 'T', 300504000, 2, true, FTarefa.class );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Situation des Projets/Contrats", "Situation Projeto/Contratos", 'S', 300506000, 2, true, FSitContr.class );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Gestion des Projets/Contrats", "Gestion des Projets/Contrats", 'G', 300507000, 2, true, FGestaoProj.class );
		addOpcao( 300500000, TP_OPCAO_ITEM, "Mod�le de contrat", "Mod�le de contrat", 'M', 300508000, 2, true, FModContr.class );
		addOpcao( -1, TP_OPCAO_MENU, "Commerciale", "", 'F', 400000000, 0, false, null );
		addOpcao( 400000000, TP_OPCAO_MENU, "Billet/re�u", "", 'o', 400100000, 1, false, null );
		addOpcao( 400100000, TP_OPCAO_ITEM, "Mod�le", "Mod�le de Billet/re�u", 'M', 400101000, 2, true, FModBoleto.class );
		addOpcao( 400100000, TP_OPCAO_ITEM, "Imprimer", "Billet/re�u", 'I', 400101000, 2, true, FRBoleto.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Banque", "Banque", 'B', 400200000, 1, true, FBanco.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Planification", "Planification", 'P', 400300000, 1, true, FPlanejamento.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Centre de co�ts", "Centre de co�ts", 'C', 400400000, 1, true, FCentroCusto.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Comptes", "Comptes", 't', 400500000, 1, true, FConta.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Historique", "Historique", 't', 400600000, 1, true, FHistPad.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Liberation", "Liberation", 'L', 400700000, 1, true, FLanca.class );

		addSeparador( 400000000 );

		addOpcao( 400000000, TP_OPCAO_ITEM, "Type de cr�dit", "Type de cr�dit", 'T', 400800000, 1, true, FTipoCred.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Liberation de cr�dit", "Liberation de cr�dit", 'b', 400900000, 1, true, FLiberaCredito.class );

		addOpcao( 400000000, TP_OPCAO_ITEM, "Type de Restriction", "Type de Restriction", 's', 401000000, 1, true, FTipoRestr.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Restriction des clients", "Restriction des clients", 'r', 401100000, 1, true, FRestrCli.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Montant � recevoir/� payer", "Montant � recevoir/� payer", 'g', 401400000, 1, true, FRRecPag.class );

		addSeparador( 400000000 );

		addOpcao( 400000000, TP_OPCAO_ITEM, "solde de retraitement", "solde de retraitement", 'R', 401000000, 1, true, FProcessaSL.class );
		addSeparador( 400000000 );

		addOpcao( 400000000, TP_OPCAO_MENU, "Lists", "", 's', 401200000, 1, false, null );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Extraire", "Extraire", 'E', 401201000, 2, true, FRExtrato.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "extrait Provisionn�", "extrait Provisionn�", 'P', 401210000, 2, true, FRExtrato.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Bilan", "Bilan", 'B', 401202000, 2, true, FRBalancete.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Rapport financier par C.C.", "Rapport financier par C.C.", 'R', 401203000, 2, true, FRCentroCusto.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Justification financi�re", "Justification financi�re", 'z', 401204000, 2, true, FRRazaoFin.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Flux d Argent", "Flux d Argent", 'F', 401205000, 2, true, FRFluxoCaixa.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "flux d Argent r�sum�", "flux d Argent r�sum�", 'F', 401207000, 2, true, FRFluxoCaixaRes.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Restriction/Clients", "Restriction/Clients", 'R', 401206000, 2, true, FRRestricao.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "Point d'�quilibre", "Point d'�quilibre", 'P', 401208000, 2, true, FRPontoEqui.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "flux d Argent r�alis�", "flux d Argent r�alis�", 'c', 401209000, 2, true, FRFluxoCaixaReal.class );
		addOpcao( 401200000, TP_OPCAO_ITEM, "flux de tr�sorerie par p�riode", "flux de tr�sorerie par p�riode", 'p', 401210000, 2, true, FRFluxoCaixaPeriodo.class );

		addOpcao( 400000000, TP_OPCAO_MENU, "Diagramme", "", 'G', 401300000, 1, false, null );
		addOpcao( 401300000, TP_OPCAO_ITEM, "Diagramme de Solde", "Diagramme de Solde", 'B', 401301000, 2, true, FRBalanceteGrafico.class );
		addOpcao( 401300000, TP_OPCAO_ITEM, "Diagramme Financiere par C.C", "Diagramme Financiere par por C.C", 'F', 401302000, 2, true, FRGraficoCC.class );

		addSeparador( 400000000 );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Type de facture", "Type de facture", 'o', 401400000, 2, true, FTipoCob.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Carnet de ch�que", "Carnet de ch�que", 'h', 401500000, 1, true, FTalaoCheq.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Ch�ques", "Ch�ques", 'h', 401600000, 1, true, FCheque.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Consultation des ch�ques", "Consultation des ch�ques", 's', 401700000, 1, true, FConsultaCheque.class );
		addSeparador( 400000000 );
		addOpcao( 400000000, TP_OPCAO_ITEM, "flags", "flags", 'S', 401700000, 1, true, FSinalizadores.class );

		addBotao( "barraUsuario.png", "Client", "Clients", 100101050, FCliente.class );
		addBotao( "btContaPagar.png", "Factures � payer", "Factures � payer", 200100000, FManutPag.class );
		addBotao( "btContaReceber.png", "Factures � recevoir", "Factures � recevoir", 300100000, FManutRec.class );
		addBotao( "btLancamentoFin.png", "bilan financi�re ", "bilan", 400600000, FLanca.class );
		addBotao( "btRemessaCNAB.png", "R�mitance CNAB", "R�mitance CNAB", 300302010, FRemCnab.class );
		addBotao( "btRetornoCNAB.png", "Retour CNAB", "Retour CNAB", 300302020, FRetCnab.class );
		
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
