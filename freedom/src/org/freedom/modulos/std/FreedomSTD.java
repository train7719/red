/**
 * @version 02/02/2000 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.std <BR>
 *         Classe:
 * @(#)Freedomstd.java <BR>
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
 *                     Tela principal do m�dulo standard.
 * 
 */

package org.freedom.modulos.std;

import java.io.File;

import org.freedom.library.functions.Funcoes;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.library.swing.frame.AplicativoPD;
import org.freedom.library.swing.frame.FPrincipal;
import org.freedom.library.swing.frame.FPrincipalPD;
import org.freedom.library.swing.frame.Login;
import org.freedom.library.swing.frame.LoginPD;
import org.freedom.modulos.atd.view.frame.crud.plain.FTipoAtend;
import org.freedom.modulos.atd.view.frame.crud.tabbed.FAtendente;
import org.freedom.modulos.cfg.view.frame.crud.plain.FBairro;
import org.freedom.modulos.cfg.view.frame.crud.plain.FCnae;
import org.freedom.modulos.cfg.view.frame.crud.plain.FMunicipio;
import org.freedom.modulos.cfg.view.frame.crud.plain.FPais;
import org.freedom.modulos.cfg.view.frame.crud.plain.FUF;
import org.freedom.modulos.cfg.view.frame.crud.tabbed.FFeriados;
import org.freedom.modulos.crm.agenda.FTipoAgenda;
import org.freedom.modulos.crm.view.frame.utility.FConsultaCli;
import org.freedom.modulos.fnc.view.frame.crud.detail.FBordero;
import org.freedom.modulos.fnc.view.frame.crud.detail.FCheque;
import org.freedom.modulos.fnc.view.frame.crud.plain.FBanco;
import org.freedom.modulos.fnc.view.frame.crud.plain.FCartCob;
import org.freedom.modulos.fnc.view.frame.crud.plain.FSinalizadores;
import org.freedom.modulos.fnc.view.frame.crud.plain.FTalaoCheq;
import org.freedom.modulos.fnc.view.frame.crud.tabbed.FConta;
import org.freedom.modulos.fnc.view.frame.crud.tabbed.FHistPad;
import org.freedom.modulos.fnc.view.frame.report.FRBordero;
import org.freedom.modulos.fnc.view.frame.report.FRCartaCobranca;
import org.freedom.modulos.fnc.view.frame.report.FRCobranca;
import org.freedom.modulos.fnc.view.frame.report.FRExtratoPrevisto;
import org.freedom.modulos.fnc.view.frame.report.FRFluxoCaixa;
import org.freedom.modulos.fnc.view.frame.report.FRFluxoCaixaRes;
import org.freedom.modulos.fnc.view.frame.report.FRPagar;
import org.freedom.modulos.fnc.view.frame.report.FRPagarAberto;
import org.freedom.modulos.fnc.view.frame.report.FRRecPag;
import org.freedom.modulos.fnc.view.frame.report.FRReceber;
import org.freedom.modulos.fnc.view.frame.report.FRReceberMes;
import org.freedom.modulos.fnc.view.frame.report.FRReceberSetor;
import org.freedom.modulos.fnc.view.frame.utility.FConsultaCheque;
import org.freedom.modulos.fnc.view.frame.utility.FManutPag;
import org.freedom.modulos.fnc.view.frame.utility.FManutRec;
import org.freedom.modulos.fnc.view.frame.utility.FPagCheque;
import org.freedom.modulos.gms.view.frame.crud.detail.FCompra;
import org.freedom.modulos.gms.view.frame.crud.detail.FConhecFrete;
import org.freedom.modulos.gms.view.frame.crud.detail.FImportacao;
import org.freedom.modulos.gms.view.frame.crud.detail.FOrdemCompra;
import org.freedom.modulos.gms.view.frame.crud.plain.FSecaoProd;
import org.freedom.modulos.gms.view.frame.crud.special.FGrupoProd;
import org.freedom.modulos.gms.view.frame.crud.tabbed.FProduto;
import org.freedom.modulos.gms.view.frame.crud.tabbed.FTipoMov;
import org.freedom.modulos.gms.view.frame.report.FRFreteExpedicao;
import org.freedom.modulos.gms.view.frame.report.FRFreteRecMerc;
import org.freedom.modulos.gms.view.frame.report.FROCEntregaPrevista;
import org.freedom.modulos.gms.view.frame.report.FRValorEstoque;
import org.freedom.modulos.grh.view.frame.crud.plain.FFuncao;
import org.freedom.modulos.grh.view.frame.crud.special.FTabelaINSS;
import org.freedom.modulos.grh.view.frame.crud.special.FTabelaIRRF;
import org.freedom.modulos.lvf.view.frame.crud.detail.FCLFiscal;
import org.freedom.modulos.lvf.view.frame.crud.detail.FNBM;
import org.freedom.modulos.lvf.view.frame.crud.detail.FNCM;
import org.freedom.modulos.lvf.view.frame.crud.plain.FModDocFisc;
import org.freedom.modulos.lvf.view.frame.crud.plain.FServico;
import org.freedom.modulos.lvf.view.frame.crud.plain.FSitTrib;
import org.freedom.modulos.lvf.view.frame.crud.plain.FTabICMS;
import org.freedom.modulos.lvf.view.frame.crud.plain.FTratTrib;
import org.freedom.modulos.lvf.view.frame.report.FRIcms;
import org.freedom.modulos.lvf.view.frame.report.FRIcmsNcm;
import org.freedom.modulos.lvf.view.frame.report.FRIpi;
import org.freedom.modulos.lvf.view.frame.report.FRMovPisCofins;
import org.freedom.modulos.lvf.view.frame.report.FRPisCofins;
import org.freedom.modulos.lvf.view.frame.report.FRProdICMS;
import org.freedom.modulos.lvf.view.frame.utility.FSintegra;
import org.freedom.modulos.pcp.view.frame.report.FRInventario;
import org.freedom.modulos.std.view.frame.crud.detail.FCalcCusto;
import org.freedom.modulos.std.view.frame.crud.detail.FEmpresa;
import org.freedom.modulos.std.view.frame.crud.detail.FModGrade;
import org.freedom.modulos.std.view.frame.crud.detail.FOrcamento;
import org.freedom.modulos.std.view.frame.crud.detail.FPlanoPag;
import org.freedom.modulos.std.view.frame.crud.detail.FSimilar;
import org.freedom.modulos.std.view.frame.crud.detail.FTabJuros;
import org.freedom.modulos.std.view.frame.crud.detail.FVenda;
import org.freedom.modulos.std.view.frame.crud.detail.FVendaConsig;
import org.freedom.modulos.std.view.frame.crud.plain.FAlmox;
import org.freedom.modulos.std.view.frame.crud.plain.FCLComis;
import org.freedom.modulos.std.view.frame.crud.plain.FCaixa;
import org.freedom.modulos.std.view.frame.crud.plain.FCategoriaImg;
import org.freedom.modulos.std.view.frame.crud.plain.FClasCli;
import org.freedom.modulos.std.view.frame.crud.plain.FEntrega;
import org.freedom.modulos.std.view.frame.crud.plain.FEstacao;
import org.freedom.modulos.std.view.frame.crud.plain.FFrete;
import org.freedom.modulos.std.view.frame.crud.plain.FImagem;
import org.freedom.modulos.std.view.frame.crud.plain.FImpressora;
import org.freedom.modulos.std.view.frame.crud.plain.FInventario;
import org.freedom.modulos.std.view.frame.crud.plain.FLiberaCredito;
import org.freedom.modulos.std.view.frame.crud.plain.FMarca;
import org.freedom.modulos.std.view.frame.crud.plain.FMensagem;
import org.freedom.modulos.std.view.frame.crud.plain.FModEtiqueta;
import org.freedom.modulos.std.view.frame.crud.plain.FModNota;
import org.freedom.modulos.std.view.frame.crud.plain.FMotorista;
import org.freedom.modulos.std.view.frame.crud.plain.FNatoPer;
import org.freedom.modulos.std.view.frame.crud.plain.FPapel;
import org.freedom.modulos.std.view.frame.crud.plain.FPrazoEnt;
import org.freedom.modulos.std.view.frame.crud.plain.FProxyWeb;
import org.freedom.modulos.std.view.frame.crud.plain.FRegraComissaoDesconto;
import org.freedom.modulos.std.view.frame.crud.plain.FSerie;
import org.freedom.modulos.std.view.frame.crud.plain.FSetor;
import org.freedom.modulos.std.view.frame.crud.plain.FTabPreco;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoCli;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoCob;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoCred;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoFisc;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoFor;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoRestr;
import org.freedom.modulos.std.view.frame.crud.plain.FTipoVend;
import org.freedom.modulos.std.view.frame.crud.plain.FUnidade;
import org.freedom.modulos.std.view.frame.crud.plain.FVariantes;
import org.freedom.modulos.std.view.frame.crud.plain.FVeiculo;
import org.freedom.modulos.std.view.frame.crud.special.FCentroCusto;
import org.freedom.modulos.std.view.frame.crud.special.FGrade;
import org.freedom.modulos.std.view.frame.crud.special.FLanca;
import org.freedom.modulos.std.view.frame.crud.special.FManutComis;
import org.freedom.modulos.std.view.frame.crud.special.FManutConFrete;
import org.freedom.modulos.std.view.frame.crud.special.FPlanejamento;
import org.freedom.modulos.std.view.frame.crud.tabbed.FCliente;
import org.freedom.modulos.std.view.frame.crud.tabbed.FCredCli;
import org.freedom.modulos.std.view.frame.crud.tabbed.FFornecedor;
import org.freedom.modulos.std.view.frame.crud.tabbed.FModBoleto;
import org.freedom.modulos.std.view.frame.crud.tabbed.FMoeda;
import org.freedom.modulos.std.view.frame.crud.tabbed.FPrefereConsig;
import org.freedom.modulos.std.view.frame.crud.tabbed.FTransp;
import org.freedom.modulos.std.view.frame.crud.tabbed.FVendedor;
import org.freedom.modulos.std.view.frame.report.FRAcompMensalVendas;
import org.freedom.modulos.std.view.frame.report.FRBalancete;
import org.freedom.modulos.std.view.frame.report.FRBalanceteGrafico;
import org.freedom.modulos.std.view.frame.report.FRBoleto;
import org.freedom.modulos.std.view.frame.report.FRCarteiraComissionado;
import org.freedom.modulos.std.view.frame.report.FRCentroConta;
import org.freedom.modulos.std.view.frame.report.FRCentroCusto;
import org.freedom.modulos.std.view.frame.report.FRClientesSemVendas;
import org.freedom.modulos.std.view.frame.report.FRCodbarProd;
import org.freedom.modulos.std.view.frame.report.FRCodficProd;
import org.freedom.modulos.std.view.frame.report.FRComissoes;
import org.freedom.modulos.std.view.frame.report.FRCompras;
import org.freedom.modulos.std.view.frame.report.FRComprasFor;
import org.freedom.modulos.std.view.frame.report.FRComprasForProd;
import org.freedom.modulos.std.view.frame.report.FRComprasMedia;
import org.freedom.modulos.std.view.frame.report.FRConfEstoq;
import org.freedom.modulos.std.view.frame.report.FRContaEstoque;
import org.freedom.modulos.std.view.frame.report.FRCpItem;
import org.freedom.modulos.std.view.frame.report.FRCpMunicipio;
import org.freedom.modulos.std.view.frame.report.FRCpProd;
import org.freedom.modulos.std.view.frame.report.FRCpTipoMov;
import org.freedom.modulos.std.view.frame.report.FRDemanda;
import org.freedom.modulos.std.view.frame.report.FRDesempVend;
import org.freedom.modulos.std.view.frame.report.FRDevolucao;
import org.freedom.modulos.std.view.frame.report.FREstoqueLiquido;
import org.freedom.modulos.std.view.frame.report.FREstoqueMin;
import org.freedom.modulos.std.view.frame.report.FREtiqueta;
import org.freedom.modulos.std.view.frame.report.FREvoluVendas;
import org.freedom.modulos.std.view.frame.report.FREvolucaoAnualVendas;
import org.freedom.modulos.std.view.frame.report.FRExtrato;
import org.freedom.modulos.std.view.frame.report.FRFechaDiario;
import org.freedom.modulos.std.view.frame.report.FRGerContas;
import org.freedom.modulos.std.view.frame.report.FRGiroEstoque;
import org.freedom.modulos.std.view.frame.report.FRGiroEstoquePeriodo;
import org.freedom.modulos.std.view.frame.report.FRGraficoCC;
import org.freedom.modulos.std.view.frame.report.FRImpServ;
import org.freedom.modulos.std.view.frame.report.FRInadimplentes;
import org.freedom.modulos.std.view.frame.report.FRInvPeps;
import org.freedom.modulos.std.view.frame.report.FRLancCategoria;
import org.freedom.modulos.std.view.frame.report.FRListaPreco;
import org.freedom.modulos.std.view.frame.report.FRMediaItem;
import org.freedom.modulos.std.view.frame.report.FRMovProd;
import org.freedom.modulos.std.view.frame.report.FRMovProdCont;
import org.freedom.modulos.std.view.frame.report.FROrcamento;
import org.freedom.modulos.std.view.frame.report.FROrcamentoCliente;
import org.freedom.modulos.std.view.frame.report.FROrcamentoProduto;
import org.freedom.modulos.std.view.frame.report.FRPontoEqui;
import org.freedom.modulos.std.view.frame.report.FRProdGrup;
import org.freedom.modulos.std.view.frame.report.FRRazCli;
import org.freedom.modulos.std.view.frame.report.FRRazFor;
import org.freedom.modulos.std.view.frame.report.FRRazaoFin;
import org.freedom.modulos.fnc.view.frame.report.FRRecebAberto;
import org.freedom.modulos.std.view.frame.report.FRRegDuplicatas;
import org.freedom.modulos.std.view.frame.report.FRRestricao;
import org.freedom.modulos.std.view.frame.report.FRResumoDiario;
import org.freedom.modulos.std.view.frame.report.FRSaldoLote;
import org.freedom.modulos.std.view.frame.report.FRUltimaVenda;
import org.freedom.modulos.std.view.frame.report.FRVencLote;
import org.freedom.modulos.std.view.frame.report.FRVendaSetor;
import org.freedom.modulos.std.view.frame.report.FRVendasCFOP;
import org.freedom.modulos.std.view.frame.report.FRVendasCanc;
import org.freedom.modulos.std.view.frame.report.FRVendasCli;
import org.freedom.modulos.std.view.frame.report.FRVendasCliProd;
import org.freedom.modulos.std.view.frame.report.FRVendasContrato;
import org.freedom.modulos.std.view.frame.report.FRVendasDet;
import org.freedom.modulos.std.view.frame.report.FRVendasFisico;
import org.freedom.modulos.std.view.frame.report.FRVendasGeral;
import org.freedom.modulos.std.view.frame.report.FRVendasGrupo;
import org.freedom.modulos.std.view.frame.report.FRVendasItem;
import org.freedom.modulos.std.view.frame.report.FRVendasPlanoPag;
import org.freedom.modulos.std.view.frame.report.FRVendasTipoCli;
import org.freedom.modulos.std.view.frame.report.FRVendasTipoMov;
import org.freedom.modulos.std.view.frame.report.FRVendasVend;
import org.freedom.modulos.std.view.frame.report.FRVolVendasProd;
import org.freedom.modulos.std.view.frame.report.FRegraComiss;
import org.freedom.modulos.std.view.frame.report.FRegraFiscal;
import org.freedom.modulos.std.view.frame.report.FRestrCli;
import org.freedom.modulos.std.view.frame.report.FRomaneio;
import org.freedom.modulos.std.view.frame.utility.FAlteraCliRec;
import org.freedom.modulos.std.view.frame.utility.FAlteraRecibo;
import org.freedom.modulos.std.view.frame.utility.FAprovCancOrc;
import org.freedom.modulos.std.view.frame.utility.FBloqCompra;
import org.freedom.modulos.std.view.frame.utility.FBloqVenda;
import org.freedom.modulos.std.view.frame.utility.FCancCompra;
import org.freedom.modulos.std.view.frame.utility.FCancVenda;
import org.freedom.modulos.std.view.frame.utility.FCancVendaOrc;
import org.freedom.modulos.std.view.frame.utility.FConsEstoque;
import org.freedom.modulos.std.view.frame.utility.FConsPreco;
import org.freedom.modulos.std.view.frame.utility.FConsProd;
import org.freedom.modulos.std.view.frame.utility.FCpProd;
import org.freedom.modulos.std.view.frame.utility.FExpImpEstoq;
import org.freedom.modulos.std.view.frame.utility.FExporta;
import org.freedom.modulos.std.view.frame.utility.FGeraFiscal;
import org.freedom.modulos.std.view.frame.utility.FImpTabFor;
import org.freedom.modulos.std.view.frame.utility.FKardex;
import org.freedom.modulos.std.view.frame.utility.FManutPreco;
import org.freedom.modulos.std.view.frame.utility.FPesquisaOrc;
import org.freedom.modulos.std.view.frame.utility.FProcessaEQ;
import org.freedom.modulos.std.view.frame.utility.FProcessaSL;
import org.freedom.modulos.std.view.frame.utility.FSVV;
import org.freedom.modulos.std.view.frame.utility.FStatusItOrc;
import org.freedom.modulos.std.view.frame.utility.FTransfEstoque;
import org.freedom.modulos.std.view.frame.utility.FTrocaDoc;
import org.freedom.modulos.std.view.frame.utility.FTrocaRefprod;
import org.freedom.modulos.std.view.frame.utility.FTrocaSeqItens;

public class FreedomSTD extends AplicativoPD {

	public FreedomSTD() {

		super( "iconstd.png", "splashSTD.png", 1, "Freedom", 1, "Standard", null, new FPrincipalPD( null, "bgFreedom2.jpg" ), LoginPD.class );
		this.montaMenu();
	}

	public FreedomSTD( String sIcone, String sSplash, int iCodSis, String sDescSis, int iCodModu, String sDescModu, String sDirImagem, final FPrincipal telaP, Class<? extends Login> cLogin ) {

		super( sIcone, sSplash, iCodSis, sDescSis, iCodModu, sDescModu, sDirImagem, telaP, cLogin );
	}

	protected void montaMenu() {

		addOpcao( -1, TP_OPCAO_MENU, "Arquivo", "", 'A', 100000000, 0, false, null );
		addOpcao( 100000000, TP_OPCAO_MENU, "Tabelas", "", 'T', 100100000, 1, false, null );
		addOpcao( 100100000, TP_OPCAO_MENU, "Cliente", "", 'C', 100101000, 2, false, null );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Tipo de cliente", "TipoCli", 'T', 100101010, 3, true, FTipoCli.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Classifica��o de cliente", "Classifica��o de Clientes", 'f', 100101020, 3, true, FClasCli.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Atividade CNAE", "Atividade CNAE", 'a', 100101060, 3, true, FCnae.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Cliente", "Clientes", 'C', 100101030, 3, true, FCliente.class );
		addOpcao( 100101000, TP_OPCAO_ITEM, "Cr�dito por cliente", "Cr�dito por cliente", 'r', 100101050, 3, true, FCredCli.class );
		addOpcao( 100100000, TP_OPCAO_MENU, "Comissionado", "", 'C', 100102000, 2, false, null );
		addOpcao( 100102000, TP_OPCAO_ITEM, "Setor", "Setor", 'S', 100102010, 3, true, FSetor.class );
		addOpcao( 100102000, TP_OPCAO_ITEM, "Comissionado", "Comissionado", 'i', 100102020, 3, true, FVendedor.class );
		addOpcao( 100102000, TP_OPCAO_ITEM, "Classif. de Comiss�es", "Classifica��o de Comiss�es", 'P', 100102030, 3, true, FCLComis.class );
		addOpcao( 100102000, TP_OPCAO_ITEM, "Fun��es", "Fun��es", 'F', 100102040, 3, true, FFuncao.class );
		addOpcao( 100102000, TP_OPCAO_ITEM, "Tipo de Comissionado", "Tipo de Comissionado", 'F', 100102050, 3, true, FTipoVend.class );
		addOpcao( 100102000, TP_OPCAO_ITEM, "Regras de Comissionamento", "Regras de Comissionamento", 'R', 100102060, 3, true, FRegraComiss.class );
		addOpcao( 100102000, TP_OPCAO_ITEM, "Regras de Desconto/Comiss�o", "Regras de Desconto/Comiss�o", 'R', 100102070, 3, true, FRegraComissaoDesconto.class );
		addOpcao( 100100000, TP_OPCAO_MENU, "Atendente", "", 'A', 100103000, 2, false, null );
		addOpcao( 100103000, TP_OPCAO_ITEM, "Atendente", "Atendente", 'e', 100103010, 3, true, FAtendente.class );
		addOpcao( 100103000, TP_OPCAO_ITEM, "Tipo de Atendente", "Tipo de Atendente", 'i', 100103020, 4, true, FTipoAtend.class );
		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Moeda", "Moeda", 'M', 100112000, 2, true, FMoeda.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Plano de pagamento", "Plano de pagamento", 's', 100115000, 2, true, FPlanoPag.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Prazo de entrega", "Prazo de entrega", 'e', 100116000, 2, true, FPrazoEnt.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Feriados", "Feriados", 'e', 100117000, 2, true, FFeriados.class );
		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Transportadora", "Transportadora", 'p', 100117000, 2, true, FTransp.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Ve�culos", "Ve�culos", 'v', 100117100, 2, true, FVeiculo.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Motoristas", "Motoristas", 'M', 100117200, 2, true, FMotorista.class );

		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Tipo de fornecedor", "Tipo de fornecedor", 'e', 100118000, 2, true, FTipoFor.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Fornecedor", "Fornecedor", 'r', 100119000, 2, true, FFornecedor.class );
		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Pa�s", "Pa�s", '�', 100120010, 2, true, FPais.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Estado", "Estado", 's', 100120020, 2, true, FUF.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Municipio", "Municipio", 'o', 100120030, 2, true, FMunicipio.class );
		addOpcao( 100100000, TP_OPCAO_ITEM, "Bairro", "Bairro", 'o', 100120040, 2, true, FBairro.class );

		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_MENU, "Produto", "", 'u', 100130000, 2, false, null );
		addOpcao( 100130000, TP_OPCAO_ITEM, "Almoxarifado", "Almoxarifado", 'x', 100130030, 3, true, FAlmox.class );
		addOpcao( 100130000, TP_OPCAO_ITEM, "Grupo", "Grupos", 'r', 100130040, 3, true, FGrupoProd.class );
		addOpcao( 100130000, TP_OPCAO_ITEM, "Se��es", "Se��es", 'e', 100103080, 3, true, FSecaoProd.class );
		addOpcao( 100130000, TP_OPCAO_ITEM, "Marca", "Marcas", 'c', 100130050, 3, true, FMarca.class );
		addOpcao( 100130000, TP_OPCAO_ITEM, "Unidade", "Unidades", 'U', 100130060, 3, true, FUnidade.class );
		addOpcao( 100130000, TP_OPCAO_ITEM, "Produto", "Produtos", 'P', 100130070, 3, true, FProduto.class );
		addSeparador( 100130000 );
		addOpcao( 100130000, TP_OPCAO_ITEM, "Similaridade", "Similar", 'S', 100130080, 3, true, FSimilar.class );
		addOpcao( 100130000, TP_OPCAO_MENU, "Grade de produtos", "", 'G', 100130090, 3, false, null );
		addOpcao( 100130090, TP_OPCAO_ITEM, "Variantes", "Variantes", 'V', 100130091, 4, true, FVariantes.class );
		addOpcao( 100130090, TP_OPCAO_ITEM, "Modelo", "Modelo de Grade", 'M', 100130092, 4, true, FModGrade.class );
		addOpcao( 100130090, TP_OPCAO_ITEM, "Grade", "Grade", 'r', 100130093, 4, true, FGrade.class );
		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_MENU, "Pre�o", "", '�', 100140000, 2, false, null );
		addOpcao( 100140000, TP_OPCAO_ITEM, "Manuten��o de Pre�os", "Manuten��o de Pre�os", 'M', 100140010, 3, true, FManutPreco.class );
		addOpcao( 100140000, TP_OPCAO_ITEM, "Copia pre�o", "Copia Precos", 'i', 100140020, 3, true, FCpProd.class );
		addOpcao( 100140000, TP_OPCAO_ITEM, "Tabela de pre�o", "Tabelas de Pre�os", 'a', 100140030, 3, true, FTabPreco.class );
		addOpcao( 100140000, TP_OPCAO_ITEM, "Lista de pre�o", "Lista de Pre�os", 'l', 100140040, 3, true, FRListaPreco.class );
		addSeparador( 100100000 );
		addOpcao( 100100000, TP_OPCAO_MENU, "Imagem", "", 'g', 100150000, 2, false, null );
		addOpcao( 100150000, TP_OPCAO_ITEM, "Categoria", "Categoria de Imagens", 'C', 100150010, 2, true, FCategoriaImg.class );
		addOpcao( 100150000, TP_OPCAO_ITEM, "Imagem", "Imagens", 'a', 100150020, 2, true, FImagem.class );

		addOpcao( 100000000, TP_OPCAO_MENU, "Ferramentas", "", 'F', 100200000, 1, false, null );
		addOpcao( 100200000, TP_OPCAO_ITEM, "Altera��o de n�mero de doc.", "Altera��o de doc", 'A', 100201000, 2, true, FTrocaDoc.class );
		addOpcao( 100200000, TP_OPCAO_ITEM, "Altera��o de n�mero do recibo", "Altera��o do n�mero do recibo", 'P', 100202000, 2, true, FAlteraRecibo.class );
		addOpcao( 100200000, TP_OPCAO_ITEM, "Altera��o cliente/receber", "Altera��o cliente/receber", 'R', 100203000, 2, true, FAlteraCliRec.class );
		addOpcao( 100200000, TP_OPCAO_MENU, "Exportar", "Exportar", 'E', 100204000, 2, false, null );
		addOpcao( 100204000, TP_OPCAO_ITEM, "Contabil/Livros Fiscais", "Contabil/Livros Fiscais", 'C', 100204010, 3, true, FExporta.class );
		addOpcao( 100204000, TP_OPCAO_ITEM, "SVV", "SVV", 'S', 100204020, 3, true, FSVV.class );
		addOpcao( 100200000, TP_OPCAO_MENU, "Etiquetas", "", 't', 100205000, 2, false, null );
		addOpcao( 100205000, TP_OPCAO_ITEM, "Modelo", "Modelo de etiquetas", 'M', 100205010, 3, true, FModEtiqueta.class );
		addOpcao( 100205000, TP_OPCAO_ITEM, "Imprimir", "Etiquetas", 'I', 100205020, 3, true, FREtiqueta.class ); // LOM
		addOpcao( 100200000, TP_OPCAO_ITEM, "Imp. tabelas de fornecedores", "Imp. tabelas de fornecedores", 'I', 100206000, 2, true, FImpTabFor.class );
		addOpcao( 100200000, TP_OPCAO_ITEM, "Ajuste do item do or�amento", "Ajuste do item do or�amento", 'A', 100207000, 2, true, FStatusItOrc.class );
		addOpcao( 100200000, TP_OPCAO_MENU, "Bloqueios e desbloqueios", "", 'B', 100208000, 2, false, null );
		addOpcao( 100208000, TP_OPCAO_ITEM, "Compras", "Bloqueio e desbloqueio de compras", 'C', 100208010, 3, true, FBloqCompra.class );
		addOpcao( 100208000, TP_OPCAO_ITEM, "Vendas", "Bloqueio e desbloqueio de vendas", 'V', 100208020, 3, true, FBloqVenda.class );
		addOpcao( 100200000, TP_OPCAO_ITEM, "Canc. de v�nc. venda x or�amento", "Canc. de v�nc. venda x or�amento", 'v', 100209000, 2, true, FCancVendaOrc.class );
		addOpcao( 100200000, TP_OPCAO_ITEM, "Reorganiza��o de seq. de itens", "Reorganiza��o de seq. de itens", 'R', 100210000, 2, true, FTrocaSeqItens.class );
		addOpcao( 100200000, TP_OPCAO_ITEM, "Troca refer�ncia dos produtos", "Troca refer�ncia dos produtos", 'T', 100211000, 2, true, FTrocaRefprod.class );

		addOpcao( 100000000, TP_OPCAO_MENU, "Prefer�ncias", "", 'P', 100400000, 1, false, null );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Prefer�ncias gerais", "Prefer�ncias Gerais", 'g', 100410000, 2, true, FPrefereGeral.class );
		addOpcao( 100400000, TP_OPCAO_ITEM, "S�rie de NFs", "S�rie de NFs", 'N', 100420000, 2, true, FSerie.class );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Modelo de NFs", "Modelo de NFs", 'M', 100430000, 2, true, FModNota.class );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Prefer�ncias Vendas Consignadas", "Prefer�ncias Vendas Consignadas", 'V', 100440000, 2, true, FPrefereConsig.class );
		addOpcao( 100400000, TP_OPCAO_ITEM, "Custo de Aquisi��o", "Custo de aquisi��o", 'V', 100450000, 2, true, FCalcCusto.class );
		addOpcao( 100000000, TP_OPCAO_MENU, "Configura��es", "", 'C', 100500000, 1, false, null );
		addOpcao( 100500000, TP_OPCAO_ITEM, "Caixa PDV", "Configura��es", 'C', 100510000, 2, true, FCaixa.class );
		addOpcao( 100500000, TP_OPCAO_ITEM, "Impressora", "Impressoras", 'I', 100520000, 2, true, FImpressora.class );
		addOpcao( 100500000, TP_OPCAO_ITEM, "Papel", "Papeis", 'P', 100530000, 2, true, FPapel.class );
		addOpcao( 100500000, TP_OPCAO_ITEM, "Esta��o de trabalho", "Esta��es de trabalho", 'E', 100540000, 2, true, FEstacao.class );// lom
		addOpcao( 100500000, TP_OPCAO_ITEM, "Tipo de agendamento", "Tipo de agendamento", 'g', 100550000, 2, true, FTipoAgenda.class );
		addSeparador( 100500000 );
		addOpcao( 100500000, TP_OPCAO_ITEM, "Empresa", "Empresa", 'E', 100560000, 2, true, FEmpresa.class );
		addSeparador( 100500000 );
		addOpcao( 100500000, TP_OPCAO_ITEM, "Proxy web", "Proxy web", 'E', 100570000, 2, true, FProxyWeb.class );

		addOpcao( -1, TP_OPCAO_MENU, "Entrada", "", 'E', 200000000, 0, false, null );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Compra", "Compras", 'C', 200100000, 1, true, FCompra.class );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Compra (Importa��o)", "Compra (Importa��o)", 'F', 200900000, 1, true, FImportacao.class );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Cancela Compra", "Cancela Compra", 'C', 200200000, 1, true, FCancCompra.class );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Conhecimento de Frete", "Conhecimento de Frete", 'C', 200300000, 1, true, FConhecFrete.class );
		addOpcao( 200000000, TP_OPCAO_ITEM, "Ordem de Compra", "Ordem de Compra", 'O', 200500000, 1, true, FOrdemCompra.class );
		addOpcao( 200000000, TP_OPCAO_MENU, "Listes", "", 'L', 200400000, 1, false, null );
		addOpcao( 200400000, TP_OPCAO_ITEM, "Compras geral", "Compras geral", 'p', 200401000, 2, true, FRCompras.class );
		addOpcao( 200400000, TP_OPCAO_ITEM, "Compras por tipo de movimento ", "Compras por tipo de movimento", 'p', 200402000, 2, true, FRCpTipoMov.class );
		addOpcao( 200400000, TP_OPCAO_ITEM, "Compras por fornecedor", "Compras por Fornecedor", 'f', 200403000, 2, true, FRComprasFor.class );
		addOpcao( 200400000, TP_OPCAO_ITEM, "Compras por �tem ", "Compras por �tem", 'p', 200404000, 2, true, FRCpItem.class );
		addOpcao( 200400000, TP_OPCAO_ITEM, "Compras por munic�pio", "Compras por munic�pio", 'm', 200405000, 2, true, FRCpMunicipio.class );
		addSeparador( 200400000 );
		addOpcao( 200400000, TP_OPCAO_ITEM, "Fretes de Rec.Merc.", "Fretes de Rec.Merc.", 'c', 200405000, 2, true, FRFreteRecMerc.class );
		addOpcao( 200400000, TP_OPCAO_ITEM, "Fretes de Expedi��o", "Fretes de Expedi��o", 'x', 200406000, 2, true, FRFreteExpedicao.class );
		addSeparador( 200400000 );
		addOpcao( 200400000, TP_OPCAO_ITEM, "Ordens de compra pendentes", "Ordens de compra pendentes", 's', 200407000, 2, true, FROCEntregaPrevista.class );
		addOpcao( 200400000, TP_OPCAO_ITEM, "M�dia de compras por item", "M�dia de compras por item", 'i', 200408000, 2, true, FRComprasMedia.class );
		addOpcao( 200400000, TP_OPCAO_ITEM, "�ltimas compras por forn./produto", "�ltimas compras por fornecedor/produto", 'd', 200409000, 2, true, FRComprasForProd.class );

		addOpcao( -1, TP_OPCAO_MENU, "Sa�da", "", 'S', 300000000, 0, false, null );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Venda", "Venda", 'V', 300100000, 1, true, FVenda.class );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Cancela venda", "Cancelamento", 'C', 300200000, 1, true, FCancVenda.class );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Vendas Consignadas", "Vendas Consignadas", 'D', 300300000, 1, true, FVendaConsig.class );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Lan�amento de Frete", "Lan�amento de Frete", 'L', 300400000, 1, true, FFrete.class );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Lan�amento canhotos de entrega", "Lan�amento de canhotos de entrega", 'h', 300900000, 1, true, FEntrega.class );
		addSeparador( 300000000 );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Aprova or�amento", "Aprova Or�amento", 'A', 300500000, 1, true, FAprovCancOrc.class );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Or�amento", "Or�amento", 'O', 300600000, 1, true, FOrcamento.class );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Pesquisa Or�amento", "Pesquisa Or�amento", 'P', 300700000, 1, true, FPesquisaOrc.class );
		addSeparador( 300000000 );
		addOpcao( 300000000, TP_OPCAO_ITEM, "Romaneio", "Romaneio", 'R', 300800000, 1, true, FRomaneio.class );
		addOpcao( 300000000, TP_OPCAO_MENU, "Listes", "", 's', 301000000, 1, false, null );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Resumo di�rio", "Resumo Di�rio", 'R', 301000100, 2, true, FRResumoDiario.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas geral", "Vendas Geral", 'V', 301000200, 2, true, FRVendasGeral.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas f�sico", "F�sico de Vendas", 'd', 301000300, 2, true, FRVendasFisico.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas detalhado", "Vendas Detalhadas", 'n', 301000400, 2, true, FRVendasDet.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas por �tem", "Vendas por Item", 'e', 301000500, 2, true, FRVendasItem.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "M�dia de vendas por �tem", "M�dia de vendas por item", 'o', 301000600, 2, true, FRMediaItem.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Ultimas Vendas por Cliente", "Ultimas Vendas por Cliente", 'U', 301000700, 2, true, FRUltimaVenda.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas por Cliente", "Vendas por Cliente", 'C', 301000800, 2, true, FRVendasCli.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas por Setor", "Vendas por Setor", 't', 301000900, 2, true, FRVendaSetor.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas por CFOP", "Vendas por CFOP", 'F', 301001000, 2, true, FRVendasCFOP.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Gerenciamento de contas", "Gerenciamento de contas", 'i', 301001100, 2, true, FRGerContas.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Fechamento di�rio", "Fechamento di�rio", 'i', 301001200, 2, true, FRFechaDiario.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Relat�rio de devolu��o", "Relat�rio de devolu��o", 'd', 301001300, 2, true, FRDevolucao.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Ultimas Vendas por Cli/Produto", "Ultimas Vendas por Cliente/Produto", 'd', 301001400, 2, true, FRVendasCliProd.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Ultimas Vendas por Plano de Pagamento", "Ultimas Vendas por Plano de pagamento", 'd', 301001500, 2, true, FRVendasPlanoPag.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Desempenho por vendedor", "Desempenho por vendedor", 'v', 301001600, 2, true, FRDesempVend.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas por vendedor", "Vendas por vendedor", 'r', 301001700, 2, true, FRVendasVend.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas canceladas/denegadas", "Vendas canceladas e denegadas", 'e', 301001800, 2, true, FRVendasCanc.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Ultimas Vendas por Tipo de Movimento", "Ultimas Vendas por Tipo de Movimento", 'l', 301001900, 2, true, FRVendasTipoMov.class );
		addSeparador( 301000000 );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Volume de vendas por produto", "Volume de vendas por produto", 'r', 301002000, 2, true, FRVolVendasProd.class );
		addSeparador( 301000000 );
		addOpcao( 301000000, TP_OPCAO_MENU, "Or�amentos", "", 'O', 301002100, 2, false, null );
		addOpcao( 301002100, TP_OPCAO_ITEM, "Situa��o de Or�amentos", "Situa��o de Or�amentos", 'O', 301002110, 3, true, FROrcamento.class );
		addOpcao( 301002100, TP_OPCAO_ITEM, "Resumo por Clientes", "Resumo por Clientes", 'O', 301001720, 3, true, FROrcamentoCliente.class );
		addOpcao( 301002100, TP_OPCAO_ITEM, "Resumo por Produto", "Resumo por Produto", 'O', 301001730, 3, true, FROrcamentoProduto.class );
		addSeparador( 301000000 );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Clientes sem movimento", "Clientes sem movimento", 'm', 301002200, 2, true, FRClientesSemVendas.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Carteira de Clientes por Comissionado", "Carteira de Clientes por Comissionado", 'm', 301002300, 2, true, FRCarteiraComissionado.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas x Contratos", "Vendas x Contratos", 'x', 301002400, 2, true, FRVendasContrato.class );
		addSeparador( 301000000 );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas por tipo de cliente", "Vendas por tipo de cliente", 't', 301002500, 2, true, FRVendasTipoCli.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Vendas por grupo / comissionado", "Vendas por grupo / comissionado", 'g', 301002600, 2, true, FRVendasGrupo.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Acompanhamento mensal de vendas", "Acompanhamento mensal de vendas", 'A', 301002700, 2, true, FRAcompMensalVendas.class );
		addOpcao( 301000000, TP_OPCAO_ITEM, "Evolu��o anual de vendas", "Evolu��o anual de vendas", 'E', 301002800, 2, true, FREvolucaoAnualVendas.class );

		addOpcao( 300000000, TP_OPCAO_MENU, "Gr�ficos", "", 'G', 301100000, 1, false, null );
		addOpcao( 301100000, TP_OPCAO_ITEM, "Evolu��o de vendas", "Evolu��o de vendas", 'E', 301100100, 2, true, FREvoluVendas.class );
		addSeparador( 300000000 );
		addOpcao( 300000000, TP_OPCAO_MENU, "Consultas", "", 'n', 301200000, 1, false, null );
		addOpcao( 301200000, TP_OPCAO_ITEM, "Pre�os", "Consulta de pre�os", 'P', 301200100, 2, true, FConsPreco.class );
		addOpcao( 301200000, TP_OPCAO_ITEM, "Consulta de clientes", "Consulta de clientes", 'C', 301200200, 2, true, FConsultaCli.class );

		addOpcao( -1, TP_OPCAO_MENU, "Pagar", "", 'P', 400000000, 0, false, null );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Comiss�o", "Comiss�o", 'C', 400100000, 1, true, FManutComis.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Pagamento de Frete", "Pagamento de Frete", 'F', 400200000, 1, true, FManutConFrete.class );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Manuten��o", "Manuten��o de contas a pagar", 'M', 400300000, 1, true, FManutPag.class );
		addOpcao( 400000000, TP_OPCAO_MENU, "Listes", "", 's', 400400000, 1, false, null );
		addOpcao( 400400000, TP_OPCAO_ITEM, "Pagar/Pagas", "Relat�rio de contas a pagar/pagas", 'P', 400401000, 2, true, FRPagar.class );
		addOpcao( 400400000, TP_OPCAO_ITEM, "Comiss�es", "Comiss�o", 'C', 400402000, 2, true, FRComissoes.class );
		addOpcao( 400400000, TP_OPCAO_ITEM, "Raz�o", "Raz�o Fornecedores", 'R', 400403000, 2, true, FRRazFor.class );
		addOpcao( 400400000, TP_OPCAO_ITEM, "Pagamentos em aberto", "Pagamentos em aberto", 'a', 400404000, 2, true, FRPagarAberto.class );

		addSeparador( 400000000 );
		addOpcao( 400000000, TP_OPCAO_ITEM, "Emiss�o de cheques", "Emiss�o de cheques", 'm', 400500000, 2, true, FPagCheque.class );

		addOpcao( -1, TP_OPCAO_MENU, "Receber", "", 'R', 500000000, 0, false, null );
		addOpcao( 500000000, TP_OPCAO_ITEM, "Manuten��o", "Manuten��o de contas a receber", 'M', 500100000, 1, true, FManutRec.class );
		addOpcao( 500000000, TP_OPCAO_ITEM, "Bordero", "Bordero", 'B', 500400000, 1, true, FBordero.class );
		addOpcao( 500000000, TP_OPCAO_ITEM, "CNAB", "CNAB", 'N', 500200000, 1, true, null );
		addOpcao( 500000000, TP_OPCAO_MENU, "Listes", "", 'L', 500300000, 1, false, null );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Receber/Recebidas", "Receber/Recebidas", 'R', 500301000, 2, true, FRReceber.class );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Inadimplentes", "Inadimplentes", 'I', 500302000, 2, true, FRInadimplentes.class );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Bordero de cobran�a", "Bordero de cobran�a", 'B', 500303000, 2, true, FRBordero.class );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Descontos por setor", "Descontos por setor", 'D', 500304000, 2, true, FRReceberSetor.class );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Raz�o", "Raz�o Clientes", 'R', 500305000, 2, true, FRRazCli.class );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Recebimentos por M�s", "Recebimentos por M�s", 'M', 500306000, 2, true, FRReceberMes.class );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Relat�rio de cobran�a", "Relat�rio de cobran�a", 'o', 500307000, 2, true, FRCobranca.class );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Carta de cobran�a", "Carta de cobran�a", 'C', 500308000, 2, true, FRCartaCobranca.class );
		addOpcao( 500300000, TP_OPCAO_ITEM, "Recebimentos em aberto", "Recebimentos em aberto", 'A', 500309000, 2, true, FRRecebAberto.class );

		addOpcao( -1, TP_OPCAO_MENU, "Financeiro", "", 'F', 600000000, 0, false, null );
		addOpcao( 600000000, TP_OPCAO_MENU, "Boleto/Recibo", "", 'B', 600100000, 1, false, null );
		addOpcao( 600100000, TP_OPCAO_ITEM, "Modelo", "Modelo de boleto/recibo", 'M', 600101000, 2, true, FModBoleto.class );
		addOpcao( 600100000, TP_OPCAO_ITEM, "Imprimir", "Boleto/Recibo", 'I', 600102000, 2, true, FRBoleto.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Banco", "Banco", 'a', 600200000, 1, true, FBanco.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Carteira de cobran�a", "Carteira de cobran�a", 'n', 600300000, 1, true, FCartCob.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Tipo de cobran�a", "Tipo de cobran�a", 'o', 601600000, 2, true, FTipoCob.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Planejamento", "Planejamento", 'P', 600400000, 1, true, FPlanejamento.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Centro de custo", "Centro de Custos", 'C', 600500000, 1, true, FCentroCusto.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Contas", "Contas", 'o', 600600000, 1, true, FConta.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Hist�rico", "Hist�rico", 't', 600700000, 1, true, FHistPad.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Lan�amentos", "Lan�amentos", 'L', 600800000, 1, true, FLanca.class );
		addSeparador( 600000000 );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Tipo de cr�dito", "Tipo de cr�dito", 'L', 600900000, 1, true, FTipoCred.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Libera��o de cr�dito", "Libera��o de cr�dito", 'i', 601000000, 1, true, FLiberaCredito.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Tipo de Restri��o", "Tipo de Restri��o", 's', 601100000, 1, true, FTipoRestr.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Restri��o de clientes", "Restri��o de clientes", 'r', 601200000, 1, true, FRestrCli.class );
		addSeparador( 600000000 );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Tabela de juros", "Tabelas de juros", 'T', 601200000, 1, true, FTabJuros.class );
		addSeparador( 600000000 );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Reprocessa saldo", "Reprocessamento de saldos", 'R', 601300000, 1, true, FProcessaSL.class );
		addSeparador( 600000000 );
		addOpcao( 600000000, TP_OPCAO_MENU, "Listes", "", 'L', 601400000, 1, false, null );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Extraire", "Extraire", 'E', 601401000, 2, true, FRExtrato.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Extrato Previsionado", "Extrato Previsionado", 'P', 601409000, 2, true, FRExtratoPrevisto.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Balancete", "Balancete", 'B', 601402000, 2, true, FRBalancete.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Relat�rio financeiro por C.C.", "Relatorio Financeiro por C.C.", 'R', 601403000, 2, true, FRCentroCusto.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Raz�o financeiro", "Raz�o financeiro", 'z', 601404000, 2, true, FRRazaoFin.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Fluxo de caixa", "Fluxo de caixa", 'F', 601405000, 2, true, FRFluxoCaixa.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Fluxo de caixa resumido", "Fluxo de caixa resumido", 'F', 601409000, 2, true, FRFluxoCaixaRes.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Ponto de equil�brio", "Ponto de equil�brio", 'q', 601406000, 2, true, FRPontoEqui.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Lan�amentos por categoria", "Lan�amentos por categoria", 'q', 601407000, 2, true, FRLancCategoria.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Restri��o/clientes", "Restri��o/clientes", 'C', 601408000, 1, true, FRRestricao.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Receber/Pagar", "Receber/Pagar", 'g', 601409000, 1, true, FRRecPag.class );
		addOpcao( 601400000, TP_OPCAO_ITEM, "Centro de custo/Conta", "Centro de custo/Conta", 'u', 601410000, 2, true, FRCentroConta.class );
		addOpcao( 600000000, TP_OPCAO_MENU, "Gr�ficos", "Fluxo de caixa", 'G', 601500000, 1, false, null );
		addOpcao( 601500000, TP_OPCAO_ITEM, "Balancete Gr�fico", "Balancete Gr�fico", 'G', 601501000, 2, true, FRBalanceteGrafico.class );
		addOpcao( 601500000, TP_OPCAO_ITEM, "Gr�fico financeiro por C.C", "Gr�fico Financeiro por C.C", 'f', 601502000, 2, true, FRGraficoCC.class );

		addSeparador( 600000000 );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Talon�rio de cheques", "Talon�rio de cheques", 'h', 601503000, 1, true, FTalaoCheq.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Cheques", "Cheques", 'h', 601504000, 1, true, FCheque.class );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Consulta cheques", "Consulta cheques", 's', 601505000, 1, true, FConsultaCheque.class );
		addSeparador( 600000000 );
		addOpcao( 600000000, TP_OPCAO_ITEM, "Sinalizadores", "Sinalizadores", 'S', 601506000, 1, true, FSinalizadores.class );

		addOpcao( -1, TP_OPCAO_MENU, "Estoque", "", 'E', 700000000, 0, false, null );
		addOpcao( 700000000, TP_OPCAO_ITEM, "Kardex", "Kardex", 'K', 700100000, 1, true, FKardex.class );
		addOpcao( 700000000, TP_OPCAO_ITEM, "Invent�rio", "Invent�rio", 'I', 700200000, 1, true, FInventario.class );
		addOpcao( 700000000, TP_OPCAO_ITEM, "Consulta estoque", "Consulta estoque", 'C', 700300000, 1, true, FConsEstoque.class );
		addOpcao( 700000000, TP_OPCAO_ITEM, "Consulta produto", "Consulta produto", 'P', 700400000, 1, true, FConsProd.class );
		addOpcao( 700000000, TP_OPCAO_ITEM, "Tipos de movimentos", "Tipo de Movimento", 'M', 700500000, 1, true, FTipoMov.class );
		addSeparador( 700000000 );
		addOpcao( 700000000, TP_OPCAO_ITEM, "Reprocessa estoque", "Reprocessa estoque", 'R', 700600000, 1, true, FProcessaEQ.class );
		addSeparador( 700000000 );
		addOpcao( 700000000, TP_OPCAO_MENU, "Listes", "", 'L', 700700000, 1, false, null );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Estoque m�nimo", "Estoque M�nimo", 's', 700700100, 2, true, FREstoqueMin.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Produtos/Movimentos", "Listagem de Produtos", 'P', 700700200, 2, true, FRMovProd.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Vencimentos de lote", "Vencimento Lote", 'V', 700700300, 2, true, FRVencLote.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Saldos de lote", "Saldos de Lote", 'l', 700700400, 2, true, FRSaldoLote.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Demanda", "Demanda", 'D', 700705000, 2, true, FRDemanda.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Confer�ncia", "Confer�ncia de Estoque", 'C', 700700600, 2, true, FRConfEstoq.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Invent�rio", "Invent�rio", 'I', 700700700, 2, true, FRInvPeps.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Codifica��o de produto", "Codifica��o de produto", 'P', 700700800, 2, true, FRCodficProd.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Etiquetas de c�digo de barras", "Etiquetas de c�digo de barras", 'E', 700700900, 2, true, FRCodbarProd.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Ultimas compras/produto", "Ultimas compras/produto", 'E', 700701000, 2, true, FRCpProd.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Movimenta��o de Produto Controlado", "Movimenta��o de Produto Controlado", 'M', 700702000, 2, true, FRMovProdCont.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Estoque liquido", "Estoque liquido", 'L', 700703000, 2, true, FREstoqueLiquido.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Produtos por grupo", "Produtos por grupo", 'G', 700704000, 2, true, FRProdGrup.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Giro de estoque", "Giro de estoque", 'i', 700705000, 2, true, FRGiroEstoque.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Giro de estoque por per�odo", "Giro de estoque por per�odo", 'G', 700706000, 2, true, FRGiroEstoquePeriodo.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Contagem de estoque", "Contagem de estoque", 's', 700707000, 2, true, FRContaEstoque.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Valor em estoque", "Valor em estoque", 'v', 700708000, 2, true, FRValorEstoque.class );
		addOpcao( 700700000, TP_OPCAO_ITEM, "Invent�rio + OP", "Invent�rio + OP", 'O', 700709000, 2, true, FRInventario.class );

		addSeparador( 700000000 );
		addOpcao( 700000000, TP_OPCAO_ITEM, "Transfer�ncia de produtos", "Tranfer�ncia de produtos/almoxarifados", 'T', 700800000, 1, true, FTransfEstoque.class );
		addOpcao( 700000000, TP_OPCAO_ITEM, "Exportar/Importar Saldo", "Exportar/Importar Saldo", 'x', 700900000, 1, true, FExpImpEstoq.class );

		addOpcao( -1, TP_OPCAO_MENU, "Fiscal", "", 'F', 800000000, 0, false, null );

		addOpcao( 800000000, TP_OPCAO_ITEM, "Mensagens", "Mensagens", 'M', 800500000, 1, true, FMensagem.class );

		addSeparador( 800000000 );

		addOpcao( 800000000, TP_OPCAO_ITEM, "Tabela de Al�quotas", "Tabela de al�quotas", 'T', 800400000, 1, true, FTabICMS.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Natureza de opera��o (CFOP)", "Naturezas de opera��o(CFOP)", 'z', 80090000, 1, true, FNatoPer.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Regras CFOP", "Regras CFOP", 'R', 800200000, 1, true, FRegraFiscal.class );

		addOpcao( 800000000, TP_OPCAO_ITEM, "Tabela de IRRF", "Tabela de IRRF", 'I', 800900000, 1, true, FTabelaIRRF.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Tabela de INSS", "Tabela de INSS", 'S', 800160000, 1, true, FTabelaINSS.class );

		addOpcao( 800000000, TP_OPCAO_ITEM, "Modelo de Doc. Fiscais", "Modelo de Doc. Fiscais", 'i', 800170000, 2, true, FModDocFisc.class );

		addSeparador( 800000000 );

		addOpcao( 800000000, TP_OPCAO_ITEM, "Tratamento tribut�rio", "Tratamento Tribut�rio", 't', 800100000, 1, true, FTratTrib.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Situa��o tribut�ria", "Situa��o Tribut�ria", 'S', 800140000, 1, true, FSitTrib.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Servi�o", "Servi�o", '�', 800150000, 1, true, FServico.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Tabela NCM", "Tabela NCM", 'N', 800700000, 2, true, FNCM.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Tabela NBM", "Tabela NBM", 'B', 800800000, 2, true, FNBM.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Classifica��o fiscal", "Classifica��es", 'l', 800110000, 1, true, FCLFiscal.class );
		addOpcao( 800000000, TP_OPCAO_ITEM, "Tipos fiscais", "Tipos Fiscais", 'p', 800130000, 3, true, FTipoFisc.class );

		addSeparador( 800000000 );

		addOpcao( 800000000, TP_OPCAO_MENU, "Livros Fiscais/Sintegra", "", 'L', 800120000, 1, false, null );

		addOpcao( 800120000, TP_OPCAO_ITEM, "Gerar Informa��es Fiscais", "Gerar Informa��es Fiscais", 'G', 800100000, 1, true, FGeraFiscal.class );

		addOpcao( 800120000, TP_OPCAO_ITEM, "Exportar Sintegra", "Exportar Sintegra", 'S', 800300000, 1, true, FSintegra.class );

		addOpcao( 800120000, TP_OPCAO_ITEM, "Registro de Duplicatas", "Registro de Duplicatas", 'S', 800400000, 1, true, FRRegDuplicatas.class );

		addSeparador( 800000000 );

		addOpcao( 800000000, TP_OPCAO_MENU, "Listes", "", 'L', 800600000, 1, false, null );
		addOpcao( 800600000, TP_OPCAO_ITEM, "ICMS", "ICMS", 'I', 800601000, 2, true, FRIcms.class );
		addOpcao( 800600000, TP_OPCAO_ITEM, "ICMS por NCM/CFOP", "ICMS por NCM", 'N', 800604000, 2, true, FRIcmsNcm.class );
		addOpcao( 800600000, TP_OPCAO_ITEM, "IPI", "IPI", 'P', 800605000, 2, true, FRIpi.class );
		addOpcao( 800600000, TP_OPCAO_ITEM, "ISS", "ISS", 'S', 800602000, 2, true, FRImpServ.class );
		addOpcao( 800600000, TP_OPCAO_ITEM, "PIS/COFINS", "Mov. PIS/COFINS", 'C', 800603000, 2, true, FRPisCofins.class );
		addOpcao( 800600000, TP_OPCAO_ITEM, "Mov. PIS/COFINS", "Mov. PIS/COFINS", 'M', 800606000, 2, true, FRMovPisCofins.class );
		addOpcao( 800600000, TP_OPCAO_ITEM, "Produtos/ICMS", "Produtos/ICMS", 'o', 800607000, 2, true, FRProdICMS.class );
		

		addBotao( "btPrefere.png", "Prefer�ncias gerais", "Prefer�ncias Gerais", 100310000, FPrefereGeral.class );
		addBotao( "btCliente.png", "Cliente", "Clientes", 100101030, FCliente.class );
		addBotao( "btOrcamento.png", "Or�amento", "Or�amento", 300600000, FOrcamento.class );
		addBotao( "btSaida.png", "Venda", "Venda", 300100000, FVenda.class );
		addBotao( "btForneced.png", "Fornecedor", "Fornecedor", 100119000, FFornecedor.class );
		addBotao( "btEntrada.png", "Compra", "Compras", 200100000, FCompra.class );

		addBotao( "btTransp.png", "Transportadora", "Transportadora", 100117000, FTransp.class );
		addBotao( "btConFrete.png", "Conhecimento de Frete", "Conhecimento de Frete", 200300000, FConhecFrete.class );

		addBotao( "btContaPagar.png", "Contas a pagar", "Manuten��o de contas a pagar", 400200000, FManutPag.class );
		addBotao( "btContaReceber.png", "Contas a receber", "Manuten��o de contas a receber", 500100000, FManutRec.class );
		addBotao( "btLancamentoFin.png", "Lan�amentos financeiros", "Lan�amentos", 600600000, FLanca.class );
		addBotao( "btEstoque.png", "Consulta estoque", "Consulta", 700300000, FConsEstoque.class );
		addBotao( "btProduto.png", "Cadastro de produtos", "Produtos", 100130070, FProduto.class );
		addBotao( "btEstProduto.png", "Consulta produto", "Consulta produto", 700400000, FConsProd.class );
		addBotao( "btAprovaOrc2.png", "Libera��o de cr�dito", "Libera��o de cr�dito", 601100000, FLiberaCredito.class );
		addBotao( "btConsultaCli.png", "Consulta de Clientes", "Consulta de Clientes", 301200200, FConsultaCli.class );

		ajustaMenu();

		nomemodulo = "Standard";

	}

	public static void main( String sParams[] ) {

		try {
			File fileini = Aplicativo.loadIni( "ARQINI", "freedom.ini" );
			Aplicativo.setLookAndFeel( fileini );

			FreedomSTD freedom = new FreedomSTD();

			if ( FPrincipalPD.exibeAgendaFPrincipal() ) {
				telaPrincipal.adicAgenda();
				FPrincipalPD.carregaAgenda();

				if ( FPrincipalPD.getAtualizaAgenda() ) {
					telaPrincipal.criaThreadAtualiza();
				}
			}
			freedom.show();
		} catch ( Throwable e ) {
			Funcoes.criaTelaErro( "Erro de execu��o\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getLocalizedMessage() );
			e.printStackTrace();
		}
	}
}
