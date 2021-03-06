/**
 * @version 24/09/2013 <BR>
 * @author Setpoint Tecnologia em Inform�tica Ltda./Robson Sanchez/Heraldo Luciano<BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.fnc.view.frame.report <BR>
 *         Classe: @(#)FRRecebAberto.java <BR>
 * 
 *         Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 *         modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 *         na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 *         Este programa � distribuido na esperan�a que possa ser util, mas SEM NENHUMA GARANTIA; <BR>
 *         sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 *         Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 *         Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 *         escreva para a Funda��o do Software Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA <BR>
 * <BR>
 * 
 *         Relat�rio de recebimentos em aberto
 * 
 */

package org.freedom.modulos.fnc.view.frame.report;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JasperPrintManager;

import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.library.functions.Funcoes;
import org.freedom.library.persistence.ListaCampos;
import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.library.swing.frame.FPrinterJob;
import org.freedom.library.swing.frame.FRelatorio;
import org.freedom.library.type.TYPE_PRINT;

public class FRRecebAberto extends FRelatorio implements FocusListener {

	private static final long serialVersionUID = 1L;

	private JTextFieldPad txtDatafim = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );
	
	private JTextFieldPad txtMes = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 2, 0);
	
	private JTextFieldPad txtAno = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 4, 0);

	private Vector<String> vVals1 = new Vector<String>();

	private Vector<String> vLabs1 = new Vector<String>();

	public FRRecebAberto() {
		setTitulo( "Recebimentos em aberto" );
		setAtribos( 80, 80, 350, 180 );
		GregorianCalendar cPeriodo = new GregorianCalendar();
		txtMes.setVlrInteger( cPeriodo.get(GregorianCalendar.MONTH) + 1 );
		txtAno.setVlrInteger( cPeriodo.get( GregorianCalendar.YEAR ) );
		montaTela();
		setPeriodo(txtMes.getVlrInteger(), txtAno.getVlrInteger());
	}

	public void montaTela() {
		txtDatafim.setAtivo( false );
		JLabelPad lbLinha = new JLabelPad();
		lbLinha.setBorder( BorderFactory.createEtchedBorder() );
		JLabelPad lbPeriodo = new JLabelPad( "Corre��o para:", SwingConstants.CENTER );
		lbPeriodo.setOpaque( true );
		adic( lbPeriodo, 7, 1, 100, 20 );
		adic( lbLinha, 5, 10, 300, 45 );
		adic( new JLabelPad("M�s e Ano: "), 10, 25, 70, 20);
		adic( txtMes, 90, 25, 30, 20);
		adic( new JLabelPad("/"), 126, 25, 70, 20);
		adic( txtAno, 140, 25, 50, 20);
		adic( new JLabelPad( "Recebimentos em aberto at�:" ), 10, 60, 200, 20 );
		adic( txtDatafim, 200, 60, 90, 20 );
		txtMes.addFocusListener( this );
		txtAno.addFocusListener( this );
	}

	public void setConexao( DbConnection cn ) {
		super.setConexao( cn );
	}

	public void imprimir( TYPE_PRINT bVisualizar ) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("select ir.codemp, ir.codfilial, ir.codrec, ir.nparcitrec ");
		sql.append(", ir.dtitrec, ir.dtvencitrec, r.codcli, c.razcli, r.docrec ");
		sql.append(", r.codvenda, coalesce(ir.vlrparcitrec,0) vlrparcitrec");
		sql.append(", coalesce(ir.vlrdescitrec,0) vlrdescitrec, coalesce(ir.vlrjurositrec,0) vlrjurositrec");
		sql.append(", coalesce(ir.vlritrec,0) vlritrec ");
		sql.append(",coalesce(sum(sl.vlrsublanca*-1),0) vlrpagoitrec ");
		sql.append(", max(datasublanca) dtpagoitrec ");
		sql.append("from fnreceber r, vdcliente c, fnitreceber ir ");
		sql.append("left outer join fnsublanca sl ");
		sql.append("on sl.codemprc=ir.codemp and sl.codfilialrc=ir.codfilial and sl.codrec=ir.codrec ");
		sql.append("and sl.nparcitrec=ir.nparcitrec and sl.datasublanca<=? and sl.codsublanca<>0 ");
		sql.append("where r.codemp=ir.codemp and r.codfilial=ir.codfilial and r.codrec=ir.codrec ");
		sql.append("and ir.codemp=? and ir.codfilial=? and ir.dtitrec <= ? ");
		sql.append("and c.codemp=r.codempcl and c.codfilial=r.codfilialcl and c.codcli=r.codcli ");
		sql.append("and cast(ir.vlritrec as decimal(15,2))>cast(coalesce((select sum(sl2.vlrsublanca*-1) ");
		sql.append("from fnsublanca sl2 ");
		sql.append("where sl2.codemprc=ir.codemp and sl2.codfilialrc=ir.codfilial and sl2.codrec=ir.codrec ");
		sql.append("and sl2.nparcitrec=ir.nparcitrec and sl2.datasublanca<=? and sl2.codsublanca<>0),0) as decimal(15,2)) ");
		sql.append("group by ir.codemp, ir.codfilial, ir.codrec, ir.nparcitrec ");
		sql.append(", ir.dtitrec, ir.dtvencitrec, r.codcli, c.razcli, r.docrec ");
		sql.append(", r.codvenda, ir.vlrparcitrec, ir.vlrdescitrec, ir.vlrjurositrec, ir.vlritrec ");
		sql.append("order by ir.dtvencitrec");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement( sql.toString() );
			int param = 1;
			ps.setDate( param++, Funcoes.dateToSQLDate( txtDatafim.getVlrDate() ) );
			ps.setInt( param++, Aplicativo.iCodEmp );
			ps.setInt( param++, ListaCampos.getMasterFilial( "FNRECEBER" ) );
			ps.setDate( param++, Funcoes.dateToSQLDate( txtDatafim.getVlrDate() ) );
			ps.setDate( param++, Funcoes.dateToSQLDate( txtDatafim.getVlrDate() ) );
			rs = ps.executeQuery();
			imprimiGrafico( rs, bVisualizar, "Corre��o para: " + txtDatafim.getVlrString()  );

		} catch ( SQLException err ) {
			Funcoes.mensagemErro( this, "Erro consulta ao relat�rio de recebimentos em aberto!\n" + err.getMessage(), true, con, err );
		}


	}

	private void imprimiGrafico( final ResultSet rs, final TYPE_PRINT bVisualizar, final String sCab ) {

		FPrinterJob dlGr = null;
		HashMap<String, Object> hParam = new HashMap<String, Object>();

		hParam.put( "CODEMP", Aplicativo.iCodEmp );
		hParam.put( "CODFILIAL", ListaCampos.getMasterFilial( "FNITPAGAR" ) );
		hParam.put( "RAZAOEMP", Aplicativo.empresa.toString() );
		hParam.put( "FILTROS", sCab );

		dlGr = new FPrinterJob( "relatorios/FRRecebAberto.jasper", "Relat�rio de Recebimentos em aberto", sCab, rs, hParam, this );

		if ( bVisualizar==TYPE_PRINT.VIEW ) {
			dlGr.preview();
		}
		else {
			try {
				dlGr.print(true);
			} catch ( Exception err ) {
				Funcoes.mensagemErro( this, "Erro na impress�o de relat�rio de Recebimentos em aberto!" + err.getMessage(), true, con, err );
			}
		}
	}

	public void focusGained( FocusEvent e ) {

	}

	public void focusLost( FocusEvent e ) {
		if ( e.getSource()==txtMes || e.getSource()==txtAno) {
			setPeriodo(txtMes.getVlrInteger(), txtAno.getVlrInteger());
		} 
	}
	
	private void setPeriodo(int mes, int ano) {
		// Objetivo de pegar o �ltimo dia do m�s
		GregorianCalendar cPeriodo = new GregorianCalendar();
		// Seta o calend�rio para 1 m�s a mais, pois o �ndice come�a em zero e o par�metro em 1
		cPeriodo.set( GregorianCalendar.MONTH, mes );
		// Seta o ano para o calend�rio
		cPeriodo.set( GregorianCalendar.YEAR, ano );
		// Set o primeiro dia do m�s
		cPeriodo.set( GregorianCalendar.DAY_OF_MONTH, 1 );
		// Subtrai um dia na data, o qual ser� igual ao �ltimo dia do m�s
		cPeriodo.add( GregorianCalendar.DAY_OF_MONTH, -1 );
		// Atribui a data calculada no campo de filtro
		txtDatafim.setVlrDate( cPeriodo.getTime() );
	}

}
