/**
 * @version 14/06/2005 <BR>
 * @author Setpoint Inform�tica Ltda / Robson Sanchez.
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.std <BR>
 *         Classe:
 * @(#)DLRResgate.java <BR>
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
 *                     ? <BR>
 * 
 */
package org.freedom.modulos.fnc.view.dialog.report;

import java.awt.Component;
import java.util.Vector;

import org.freedom.library.functions.Funcoes;
import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JRadioGroup;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.dialog.FFDialogo;

public class DLRResgate extends FFDialogo {

	private static final long serialVersionUID = 1L;

	private JRadioGroup<?, ?> rgOrdem = null;

	private JTextFieldPad txtDataini = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private JTextFieldPad txtDatafim = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private JTextFieldPad txtNome = new JTextFieldPad( JTextFieldPad.TP_STRING, 50, 0 );

	private JLabelPad lbOrdem = new JLabelPad( "Ordenar por:" );

	private Vector<String> vLabs = new Vector<String>();

	private Vector<String> vVals = new Vector<String>();

	// private Vector vStrTipo = new Vector();

	public DLRResgate( Component cOrig ) {

		super( cOrig );
		setTitulo( "Relat�rio de Retorno de Cheque" );
		setAtribos( 291, 239 );

		adic( new JLabelPad( "Periodo:" ), 7, 5, 125, 20 );
		adic( new JLabelPad( "De:" ), 7, 25, 35, 20 );
		adic( txtDataini, 32, 25, 100, 20 );
		adic( new JLabelPad( "At�:" ), 140, 25, 25, 20 );
		adic( txtDatafim, 170, 25, 100, 20 );

		vLabs.addElement( "Data" );
		vLabs.addElement( "Nome" );
		vVals.addElement( "D" );
		vVals.addElement( "N" );
		rgOrdem = new JRadioGroup<String, String>( 1, 2, vLabs, vVals );
		rgOrdem.setVlrString( "D" );
		adic( lbOrdem, 7, 50, 80, 15 );
		adic( rgOrdem, 7, 70, 264, 30 );

		adic( new JLabelPad( "Cliente" ), 7, 100, 75, 20 );
		adic( txtNome, 7, 120, 264, 20 );

	}

	public String getCNome() {

		return txtNome.getVlrString();
	}

	public JTextFieldPad GetDataini() {

		return txtDataini;
	}

	public JTextFieldPad GetDatafim() {

		return txtDatafim;
	}

	public boolean CompData() {

		if ( txtDatafim.getVlrDate().before( txtDataini.getVlrDate() ) ) {
			Funcoes.mensagemInforma( this, "Data final maior que a data inicial!" );
			return false;
		}
		else
			return true;
	}

	public String getValor() {

		String sRetorno = "";
		if ( rgOrdem.getVlrString().compareTo( "D" ) == 0 )
			sRetorno = "sgrescheque.DTINS";
		else if ( rgOrdem.getVlrString().compareTo( "N" ) == 0 )
			sRetorno = "vdcliente.NOMECLI";
		return sRetorno;
	}

}
