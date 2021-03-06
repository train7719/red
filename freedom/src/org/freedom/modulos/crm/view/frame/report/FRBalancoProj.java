/**
 * @version 11/02/2013 <BR>
 * @author Setpoint Inform�tica Ltda.<BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.crm.view.frame.report <BR>
 *         Classe: @(#)FRBalancoProj.java <BR>
 * 
 *         Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 *         modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 *         na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 *         Este programa � distribuido na esperan�a que possa ser util, mas SEM NENHUMA GARANTIA; <BR>
 *         sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 *         Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 *         Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 *         de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 *         Balan�o de contrato ou projeto
 * 
 */

package org.freedom.modulos.crm.view.frame.report;

import java.awt.Component;

import org.freedom.acao.CheckBoxEvent;
import org.freedom.acao.CheckBoxListener;
import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.library.functions.Funcoes;
import org.freedom.library.persistence.GuardaCampo;
import org.freedom.library.persistence.ListaCampos;
import org.freedom.library.swing.component.JCheckBoxPad;
import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JTextFieldFK;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.library.swing.frame.FPrinterJob;
import org.freedom.library.swing.frame.FRelatorio;
import org.freedom.library.type.TYPE_PRINT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;

public class FRBalancoProj extends FRelatorio implements CheckBoxListener {

	private static final long serialVersionUID = 1L;

	private JTextFieldPad txtCodCli = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtCodContr = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldFK txtRazCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 60, 0 );

	private JTextFieldFK txtDescContr = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldFK txtNomeCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldFK txtDDDCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 4, 0 );

	private JTextFieldFK txtFoneCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 12, 0 );

	private JTextFieldFK txtEmailCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldFK txtContatoCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private JTextFieldFK txtEndCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldFK txtCidCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 30, 0 );

	private JTextFieldFK txtUfCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 2, 0 );

	private JTextFieldFK txtNumCli = new JTextFieldFK( JTextFieldPad.TP_INTEGER, 10, 0 );

	private JCheckBoxPad cbResumoPgto = new JCheckBoxPad( "Resumo de pagamentos de colaboradores", "S", "N" );

	private JTextFieldPad txtDataini = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private JTextFieldPad txtDatafim = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private ListaCampos lcCli = new ListaCampos( this );

	private ListaCampos lcContr = new ListaCampos( this );

	private boolean bComp = false;

	private Component tela = null;

	public FRBalancoProj() {

		setTitulo( "Balan�o de projeto" );
		setAtribos( 80, 80, 400, 240 );

		montaListaCampos();
		montaTela();
		iniPeriodo();
		tela = this;

	}

	public void setParametros( Integer codcli, Date dtini, Date dtfim ) {

		txtCodCli.setVlrInteger( codcli );
	}

	private void montaTela() {

		JLabelPad lbLinha = new JLabelPad();
		lbLinha.setBorder( BorderFactory.createEtchedBorder() );

		adic( new JLabelPad( "C�d.Cli" ), 7, 3, 80, 20 );
		adic( txtCodCli, 7, 23, 80, 20 );
		adic( new JLabelPad( "Raz�o social do cliente" ), 90, 3, 250, 20 );
		adic( txtRazCli, 90, 23, 225, 20 );

		adic( new JLabelPad( "C�d.Contr." ), 7, 43, 80, 20 );
		adic( txtCodContr, 7, 63, 80, 20 );
		adic( new JLabelPad( "Descri��o do contrato" ), 90, 43, 250, 20 );
		adic( txtDescContr, 90, 63, 225, 20 );
		adic( cbResumoPgto, 7, 83, 300, 30);
		adic( new JLabelPad("Per�odo"), 7, 113, 100, 20);
		adic( txtDataini, 7, 133, 90, 20);
		adic( txtDatafim, 100, 133, 90, 20);
		
		cbResumoPgto.addCheckBoxListener( this );
		habilitaPeriodo( false );
	}

	private void iniPeriodo() {
		Calendar cal = Calendar.getInstance();
		Date dtfim = cal.getTime();
		cal.add( Calendar.MONTH, -1 );
		Date dtini = cal.getTime();
		txtDataini.setVlrDate( dtini );
		txtDatafim.setVlrDate( dtfim );
	}
	
	private void montaListaCampos() {

		// Cliente
		lcCli.add( new GuardaCampo( txtCodCli, "CodCli", "C�d.cli.", ListaCampos.DB_PK, false ) );
		lcCli.add( new GuardaCampo( txtRazCli, "RazCli", "Raz�o social do cliente", ListaCampos.DB_SI, false ) );
		lcCli.montaSql( false, "CLIENTE", "VD" );
		lcCli.setReadOnly( true );
		txtCodCli.setTabelaExterna( lcCli, null );
		txtCodCli.setFK( true );
		txtCodCli.setNomeCampo( "CodCli" );

		// Contrato

		lcContr.add( new GuardaCampo( txtCodContr, "CodContr", "C�d.Contr.", ListaCampos.DB_PK, false ) );
		lcContr.add( new GuardaCampo( txtDescContr, "DescContr", "Descri��o do contrato", ListaCampos.DB_SI, false ) );
		lcContr.montaSql( false, "CONTRATO", "VD" );
		lcContr.setReadOnly( true );
		lcContr.setDinWhereAdic( "CODCLI=#N ", txtCodCli );
		txtCodContr.setTabelaExterna( lcContr, null );
		txtCodContr.setFK( true );
		txtCodContr.setNomeCampo( "CodContr" );

	}

	public void imprimir( TYPE_PRINT bVisualizar ) {

		

		if ( txtCodCli.getVlrInteger().intValue() == 0 ) {
			Funcoes.mensagemInforma( this, "Selecione um cliente !" );
			return;
		}

		if ( txtCodContr.getVlrInteger().intValue() == 0 ) {
			Funcoes.mensagemInforma( this, "Selecione um contrato" );
			return;
		}

		if ( "S".equals(cbResumoPgto.getVlrString()) ) {
			if ("".equals(txtDataini.getVlrString().trim()) || "".equals(txtDatafim.getVlrString().trim()) ) {
				Funcoes.mensagemInforma( this, "Preecha o per�odo para o resumo de pagamentos !" );
				return;
			}
			if (txtDataini.getVlrDate().compareTo( txtDatafim.getVlrDate() )>0) {
				Funcoes.mensagemInforma( this, "Per�odo inv�lido !" );
				return;
			}
		}
		StringBuilder sql = new StringBuilder();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int iparam = 1;
		
		/*CODEMP INTEGER NOT NULL,
        CODFILIAL INTEGER NOT NULL,
        CODCONTR INTEGER NOT NULL,
        CODITCONTR INTEGER NOT NULL,
        CODEMPOR INTEGER NOT NULL,
        CODFILIALOR INTEGER NOT NULL,
        TIPOORC CHAR(1) NOT NULL,
        CODORC INTEGER NOT NULL,
        CODITORC INTEGER NOT NULL,*/

		sql.append("select 'Z' tipo, 'O' tiporecdesp,  o.dtorc data ");
		sql.append(", co.codcontr, cl.codcli, cl.razcli ");
		sql.append(", ic.coditcontr, ct.desccontr, ic.descitcontr ");
		sql.append(", cast('Previs�o/or�amento - or�amento: '||o.codorc as varchar(200)) descricao ");
		sql.append(", ioc.qtditorc qtdade, (ioc.vlrliqitorc / case when ioc.qtditorc=0 then 1 else ioc.qtditorc end) vlrunit ");
		sql.append(", ioc.vlrliqitorc as vlrtotal ");
		sql.append("from vdcontrorc co, vditorcamento ioc, vdorcamento o ");
		sql.append(", vdcontrato ct, vditcontrato ic, vdcliente cl ");
		sql.append("where ct.codemp=co.codemp and ct.codfilial=co.codfilial and ct.codcontr=co.codcontr ");
		sql.append("and ic.codemp=ct.codemp and ic.codfilial=ct.codfilial and ic.codcontr=ct.codcontr ");
		sql.append("and ic.coditcontr=co.coditcontr ");
		sql.append("and cl.codemp=ct.codempcl and cl.codfilial=ct.codfilialcl and cl.codcli=ct.codcli ");
		sql.append("and co.codemp=? and co.codfilial=? and co.codcontr=? ");
		sql.append("and ioc.codemp=co.codempor and ioc.codfilial=co.codfilialor and ioc.tipoorc=co.tipoorc ");
		sql.append("and ioc.codorc=co.codorc and ioc.coditorc=co.coditorc ");
		sql.append("and o.codemp=ioc.codemp and o.codfilial=ioc.codfilial and o.tipoorc=ioc.tipoorc ");
		sql.append("and o.codorc=ioc.codorc and ioc.qtditorc>0 ");

		sql.append("union all ");
		
		sql.append("select 'R' tipo, 'V' tiporecdesp,  vd.dtemitvenda data ");
		sql.append(", vc.codcontr, cl.codcli, cl.razcli ");
		sql.append(", vc.coditcontr, ct.desccontr, ic.descitcontr ");
		sql.append(", cast('Faturamento/venda - pedido: '||vd.codvenda||' - serie/doc: '||vd.serie||'/'||vd.docvenda as varchar(200)) descricao ");
		sql.append(", iv.qtditvenda qtdade, (iv.vlrliqitvenda / case when iv.qtditvenda=0 then 1 else iv.qtditvenda end) vlrunit ");
		sql.append(", iv.vlrliqitvenda as vlrtotal ");
		sql.append("from vditvendavditcontr vc, vditvenda iv, vdvenda vd ");
		sql.append(", vdcontrato ct, vditcontrato ic, vdcliente cl ");
		sql.append("where ct.codemp=vc.codempct and ct.codfilial=vc.codfilialct and ct.codcontr=vc.codcontr ");
		sql.append("and ic.codemp=ct.codemp and ic.codfilial=ct.codfilial and ic.codcontr=ct.codcontr ");
		sql.append("and ic.coditcontr=vc.coditcontr ");
		sql.append("and cl.codemp=ct.codempcl and cl.codfilial=ct.codfilialcl and cl.codcli=ct.codcli ");
		sql.append("and vc.codempct=? and vc.codfilialct=? and vc.codcontr=? ");
		sql.append("and iv.codemp=vc.codemp and iv.codfilial=vc.codfilial and iv.tipovenda=vc.tipovenda ");
		sql.append("and iv.codvenda=vc.codvenda and iv.coditvenda=vc.coditvenda ");
		sql.append("and vd.codemp=iv.codemp and vd.codfilial=iv.codfilial and vd.tipovenda=iv.tipovenda ");
		sql.append("and vd.codvenda=iv.codvenda and iv.qtditvenda>0 ");
		
		sql.append("union all ");
		
		sql.append("select 'D' tipo, cp.tipocusto tiporecdesp,  cp.data ");
		sql.append(", cp.codcontr, cl.codcli, cl.razcli ");
		sql.append(", cp.coditcontr, ct.desccontr, ic.descitcontr ");
		sql.append(", cp.desccusto descricao ");
		sql.append(", cp.qtdcusto qtdade, cp.vlrcusto vlrunit ");
		sql.append(", (cp.qtdcusto * cp.vlrcusto) as vlrtotal ");
		sql.append("from vwcustoproj01 cp, vdcontrato ct, vditcontrato ic, vdcliente cl ");
		sql.append("where ct.codemp=cp.codemp and ct.codfilial=cp.codfilial and ct.codcontr=cp.codcontr ");
		sql.append("and ic.codemp=ct.codemp and ic.codfilial=ct.codfilial and ic.codcontr=ct.codcontr ");
		sql.append("and ic.coditcontr=cp.coditcontr ");
		sql.append("and cl.codemp=ct.codempcl and cl.codfilial=ct.codfilialcl and cl.codcli=ct.codcli ");
		sql.append("and cp.codemp=? and cp.codfilial=? and cp.codcontr=? ");
		sql.append("order by 5, 4, 7, 1 desc, 2, 3 ");


		try {

			ps = con.prepareStatement( sql.toString() );
			ps.setInt( iparam++, Aplicativo.iCodEmp );
			ps.setInt( iparam++, ListaCampos.getMasterFilial( "VDCONTRATO" ) );
			ps.setInt( iparam++, txtCodContr.getVlrInteger() );
			ps.setInt( iparam++, Aplicativo.iCodEmp );
			ps.setInt( iparam++, ListaCampos.getMasterFilial( "VDCONTRATO" ) );
			ps.setInt( iparam++, txtCodContr.getVlrInteger() );
			ps.setInt( iparam++, Aplicativo.iCodEmp );
			ps.setInt( iparam++, ListaCampos.getMasterFilial( "VDCONTRATO" ) );
			ps.setInt( iparam++, txtCodContr.getVlrInteger() );
			rs = ps.executeQuery();

		} catch ( SQLException err ) {

			err.printStackTrace();
			Funcoes.mensagemErro( this, " Erro na consulta da tabela de atendimentos" );
		}

		imprimiGrafico( rs, bVisualizar );

	}

	private void imprimiGrafico( final ResultSet rs, final TYPE_PRINT bVisualizar ) {

		FPrinterJob dlGr = null;
		HashMap<String, Object> hParam = new HashMap<String, Object>();

		hParam.put( "CODEMP", Aplicativo.iCodEmp );
		hParam.put( "CODFILIAL", ListaCampos.getMasterFilial( "SGPREFERE1" ) );
		hParam.put( "CODFILIALSL", ListaCampos.getMasterFilial( "FNSUBLANCA" ) );
		hParam.put( "CODFILIALEM", ListaCampos.getMasterFilial( "RHEMPREGADO" ) );
		hParam.put( "CODFILIALAO", ListaCampos.getMasterFilial( "ATATENDIMENTO" ) );
		hParam.put( "CODFILIALCT", ListaCampos.getMasterFilial( "VDCONTRATO" ) );
		hParam.put( "CODCONTR", txtCodContr.getVlrInteger() );
		hParam.put( "RAZAOEMP", Aplicativo.empresa.toString() );
		hParam.put( "SUBREPORT_DIR", "org/freedom/layout/rel/" );
		hParam.put( "RESUMOPGTO", cbResumoPgto.getVlrString() );
		hParam.put( "DATAINI", txtDataini.getVlrDate() );
		hParam.put( "DATAFIM", txtDatafim.getVlrDate() );
		hParam.put( "CONEXAO", con.getConnection() );

		dlGr = new FPrinterJob( "layout/rel/REL_BAL_PROJ.jasper", "Balan�o de projeto/contrato", "", rs, hParam, this );

		if ( bVisualizar==TYPE_PRINT.VIEW ) {
			dlGr.preview();
		}
		else {
			dlGr.print(true);
		}
	}

	public void setConexao( DbConnection cn ) {

		super.setConexao( cn );
		lcCli.setConexao( cn );
		lcContr.setConexao( cn );
	}

	public void valorAlterado( CheckBoxEvent evt ) {

		if (evt.getCheckBox()==cbResumoPgto) {
			if ("S".equals( cbResumoPgto.getVlrString()) ) {
				habilitaPeriodo( true );
			} else {
				habilitaPeriodo( false );
			}
		}
		
	}

	private void habilitaPeriodo(boolean habilita) {
		txtDataini.setAtivo( habilita );
		txtDatafim.setAtivo( habilita );
	}
}
