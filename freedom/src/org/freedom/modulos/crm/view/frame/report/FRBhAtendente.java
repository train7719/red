/**
 * @version 01/06/2011 <BR>
 * @author Setpoint Inform�tica Ltda./Robson Sanchez<BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.crm.view.frame.report <BR>
 *         Classe: @(#)FRBhAtendente.java <BR>
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
 *         Relat�rio de banco de horas por atendente
 * 
 */

package org.freedom.modulos.crm.view.frame.report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.library.functions.Funcoes;
import org.freedom.library.persistence.GuardaCampo;
import org.freedom.library.persistence.ListaCampos;
import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JTextFieldFK;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.library.swing.frame.FPrinterJob;
import org.freedom.library.swing.frame.FRelatorio;
import org.freedom.library.type.TYPE_PRINT;
import org.freedom.modulos.atd.view.frame.crud.tabbed.FAtendente;

public class FRBhAtendente extends FRelatorio {

	private static final long serialVersionUID = 1L;

	private JTextFieldPad txtDataini = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private JTextFieldPad txtDatafim = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );
	
	private ListaCampos lcAtendente = new ListaCampos( this, "AE" );
	
	private JTextFieldPad txtCodAtend = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldFK txtNomeAtend = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	
	public FRBhAtendente() {

		setTitulo( "Banco de horas por atendente" );
		
		setAtribos( 80, 80, 450	, 250 );

		montaListaCampos();
		montaTela();

	}

	public void setParametros( Integer codcli, Date dtini, Date dtfim ) {

		txtDataini.setVlrDate( dtini );
		txtDatafim.setVlrDate( dtfim );
		
	}

	private void montaTela() {

		JLabelPad lbLinha = new JLabelPad();
		lbLinha.setBorder( BorderFactory.createEtchedBorder() );
		JLabelPad lbPeriodo = new JLabelPad( "Per�odo:", SwingConstants.CENTER );
		lbPeriodo.setOpaque( true );

		adic( lbPeriodo, 7, 1, 80, 20 );
		adic( lbLinha, 5, 10, 300, 45 );
		
		adic( txtDataini, 38, 25, 95, 20 );		
		adic( txtDatafim, 178, 25, 95, 20 );	
		
		adic (txtCodAtend, 7, 85, 80, 20, "C�d.Atend.");
		adic (txtNomeAtend, 90, 85, 215, 20, "Nome do atendente");
		
		Calendar cPeriodo = Calendar.getInstance();
		txtDatafim.setVlrDate( cPeriodo.getTime() );
	
		cPeriodo.set( Calendar.DAY_OF_MONTH, cPeriodo.get( Calendar.DAY_OF_MONTH ) - 30 );
		txtDataini.setVlrDate( cPeriodo.getTime() );

	}

	private void montaListaCampos() {
		
		txtCodAtend.setTabelaExterna( lcAtendente, FAtendente.class.getCanonicalName() );
		txtCodAtend.setFK( true );
		txtCodAtend.setNomeCampo( "CodAtend" );
		lcAtendente.add( new GuardaCampo( txtCodAtend, "CodAtend", "C�d.atend.", ListaCampos.DB_PK, false ) );
		lcAtendente.add( new GuardaCampo( txtNomeAtend, "NomeAtend", "Nome", ListaCampos.DB_SI, false ) );
		lcAtendente.montaSql( false, "ATENDENTE", "AT" );
		lcAtendente.setReadOnly( true );

	}

	public void imprimir( TYPE_PRINT bVisualizar ) {

		StringBuilder sql = new StringBuilder();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int iparam = 1;

		if ( txtDatafim.getVlrDate().before( txtDataini.getVlrDate() ) ) {
			Funcoes.mensagemInforma( this, "Data final maior que a data inicial!" );
			return;
		}
		
		sql.append( "select matempr, nomeempr, codatend, nomeatend, anoatendo, mesatendo,");
		sql.append( "codturno, descturno, horasexped,");
		sql.append( "dataatendo, horaatendo, horaatendofin, codespec, descespec, " );
		sql.append( "totalgeral, totalbh ");
		sql.append( "from atatendimentovw03 a ");
		sql.append( "where ");
		sql.append( "a.codemp=? and a.codfilial=? and a.dataatendo between ? and ? ");
		if(txtCodAtend.getVlrInteger()>0) {
			sql.append( "and a.codempae=? and a.codfilialae=? and a.codatend=? " );
		}
		sql.append( "order by nomeempr, dataatendo, horaatendo, horaatendofin, descespec ");

		System.out.println( "SQL:" + sql.toString() );

		try {

			ps = con.prepareStatement( sql.toString() );
			ps.setInt( iparam++, Aplicativo.iCodEmp );
			ps.setInt( iparam++, ListaCampos.getMasterFilial( "ATATENDIMENTO" ) );
			ps.setDate( iparam++, Funcoes.strDateToSqlDate( txtDataini.getVlrString() ) );
			ps.setDate( iparam++, Funcoes.strDateToSqlDate( txtDatafim.getVlrString() ) );
			
			
			if(txtCodAtend.getVlrInteger()>0) {
				
				ps.setInt( iparam++, Aplicativo.iCodEmp );
				ps.setInt( iparam++, ListaCampos.getMasterFilial( "ATATENDENTE" ) );
				ps.setInt( iparam++, txtCodAtend.getVlrInteger() );
				
			}
			
			rs = ps.executeQuery();
		
		} 
		catch ( SQLException err ) {

			err.printStackTrace();
			Funcoes.mensagemErro( this, " Erro na consulta da view de resumo de atendimentos" );
			
		}

		imprimiGrafico( rs, bVisualizar );

	}

	private void imprimiGrafico( final ResultSet rs, final TYPE_PRINT bVisualizar ) {

		FPrinterJob dlGr = null;
		HashMap<String, Object> hParam = new HashMap<String, Object>();

		hParam.put( "CODEMP", Aplicativo.iCodEmp );
		hParam.put( "CODFILIAL", ListaCampos.getMasterFilial( "ATATENDIMENTO" ) );
		hParam.put( "RAZAOEMP", Aplicativo.empresa.toString() );
		hParam.put( "SUBREPORT_DIR", "org/freedom/relatorios/" );
		hParam.put( "DTINI", txtDataini.getVlrDate() );
		hParam.put( "DTFIM", txtDatafim.getVlrDate() );
		hParam.put( "CONEXAO", con.getConnection() );

		dlGr = new FPrinterJob( "layout/rel/REL_CRM_BH_ATENDENTE.jasper", "Banco de horas por atendente (Detalhado)", "", rs, hParam, this );

		if ( bVisualizar==TYPE_PRINT.VIEW ) {
			dlGr.preview();
		}
		else {
			dlGr.print(true);
		}
	}

	public void setConexao( DbConnection cn ) {

		super.setConexao( cn );
		
		lcAtendente.setConexao( cn );
		
	}

}
