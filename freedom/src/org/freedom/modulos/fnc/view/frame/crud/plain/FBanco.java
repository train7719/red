/**
 * @version 14/07/2003 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.std <BR>
 *         Classe: @(#)FBanco.java <BR>
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
 *         Coment�rios sobre a classe...
 */

package org.freedom.modulos.fnc.view.frame.crud.plain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.freedom.bmps.Icone;
import org.freedom.infra.functions.StringFunctions;
import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.library.component.ImprimeOS;
import org.freedom.library.functions.Funcoes;
import org.freedom.library.persistence.GuardaCampo;
import org.freedom.library.persistence.ListaCampos;
import org.freedom.library.swing.component.JButtonPad;
import org.freedom.library.swing.component.JTextAreaPad;
import org.freedom.library.swing.component.JTextFieldFK;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.component.PainelImagem;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.library.swing.frame.FDados;
import org.freedom.library.type.TYPE_PRINT;
import org.freedom.modulos.std.view.dialog.report.DLRBanco;
import org.freedom.modulos.std.view.frame.crud.tabbed.FModBoleto;

public class FBanco extends FDados implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JTextFieldPad txtCodBanco = new JTextFieldPad( JTextFieldPad.TP_STRING, 3, 0 );

	private JTextFieldPad txtNomeBanco = new JTextFieldPad( JTextFieldPad.TP_STRING, 40, 0 );

	private JTextFieldPad txtCodBancoBP = new JTextFieldPad( JTextFieldPad.TP_STRING, 3, 0 );

	private JTextFieldFK txtNomeBancoBP = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private JTextFieldPad txtCodModBol = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtSiteBanco = new JTextFieldPad( JTextFieldPad.TP_STRING, 80, 0 );

	private JTextFieldFK txtDescModBol = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldPad txtDigito = new JTextFieldPad( JTextFieldPad.TP_STRING, 1, 0 );
	
	private JTextAreaPad txaLayoutCheqBanco = new JTextAreaPad( 1000 );

	private JButtonPad btFirefox = new JButtonPad( Icone.novo( "chrome.png" ) );

	private PainelImagem imgBolBanco = new PainelImagem( 65000 );

	private PainelImagem imgBolBanco2 = new PainelImagem( 65000 );

	private ListaCampos lcModBol = new ListaCampos( this, "MB" );
	
	private ListaCampos lcBanco = new ListaCampos( this, "BP" );

	private String sURLBanco = null;

	public FBanco() {

		super();
		setTitulo( "Enregistrement de Banco" );
		setAtribos( 50, 50, 460, 440 );

		
		/*		txtCodTran.setNomeCampo( "CodTran" );

		lcTran.add( new GuardaCampo( txtCodTran, "CodTran", "C�d.tran.", ListaCampos.DB_PK, false ) );
		lcTran.add( new GuardaCampo( txtDescTran, "NomeTran", "Descri��o da transporatadora", ListaCampos.DB_SI, false ) );
		txtDescTran.setListaCampos( lcTran );
		txtCodTran.setTabelaExterna( lcTran, FTransp.class.getCanonicalName() );
		txtCodTran.setFK( true );
		lcTran.montaSql( false, "TRANSP", "VD" );
		lcTran.setQueryCommit( false );
		lcTran.setReadOnly( true );
*/
		
		lcBanco.add( new GuardaCampo( txtCodBancoBP, "CodBanco", "C�d.banco", ListaCampos.DB_PK, txtDescModBol, false ) );
		lcBanco.add( new GuardaCampo( txtNomeBancoBP, "NomeBanco", "Nome do banco cooperado", ListaCampos.DB_SI, null, false ) );
		txtNomeBancoBP.setListaCampos( lcBanco );
		txtCodBancoBP.setTabelaExterna( lcBanco, null );
		txtCodBancoBP.setFK( true );
		lcBanco.montaSql( false, "BANCO", "FN" );
		lcBanco.setQueryCommit( false );
		lcBanco.setReadOnly( true );
		

		lcModBol.add( new GuardaCampo( txtCodModBol, "CodModBol", "C�d.mod.bol.", ListaCampos.DB_PK, txtDescModBol, false ) );
		lcModBol.add( new GuardaCampo( txtDescModBol, "DescModBol", "Descri�ao do modelo de boleto", ListaCampos.DB_SI, null, false ) );
		lcModBol.montaSql( false, "MODBOLETO", "FN" );
		lcModBol.setQueryCommit( false );
		lcModBol.setReadOnly( true );
		txtCodModBol.setTabelaExterna( lcModBol, FModBoleto.class.getCanonicalName() );

		adicCampo( txtCodBanco, 7, 20, 70, 20, "CodBanco", "C�d.banco", ListaCampos.DB_PK, true );
		adicCampo( txtNomeBanco, 80, 20, 280, 20, "NomeBanco", "Nome do banco", ListaCampos.DB_SI, true );
		adicCampo( txtDigito, 363, 20, 50, 20, "DvBanco", "D�gito", ListaCampos.DB_SI, true );
		adicCampo( txtCodBancoBP, 7, 60, 70, 20, "CodBancoBP", "C�d.coop.", ListaCampos.DB_FK, txtNomeBancoBP, false );
		adicDescFK( txtNomeBancoBP, 80, 60, 333, 20, "NomeBanco", "Nome do banco cooperado" );

		adicCampo( txtSiteBanco, 7, 100, 380, 20, "SiteBanco", "Site ", ListaCampos.DB_SI, false );
		adic( btFirefox, 390, 99, 20, 20 );
		adicDB( imgBolBanco, 7, 140, 200, 30, "ImgBolBanco", "Primeira logo para boleto ", false );
		adicDB( imgBolBanco2, 210, 140, 200, 30, "ImgBolBanco2", "Segunda logo boleto ", false );
		adicDB( txaLayoutCheqBanco, 7, 190, 403, 160, "LayoutCheqBanco", "Layout de imp. de cheques: [LIN=?|COL=?|TAM=?|INI=?|COMPL=?|CAMPO=?|TEXTO=?]", false );

		setListaCampos( false, "BANCO", "FN" );
		txtCodBancoBP.setNomeCampo( "CodBanco" );
		btImp.addActionListener( this );
		btPrevimp.addActionListener( this );
		btFirefox.addActionListener( this );
		lcCampos.setQueryInsert( false );
		setImprimir( true );
		btFirefox.setToolTipText( "Acessar Site" );
	}

	public void actionPerformed( ActionEvent evt ) {

		if ( evt.getSource() == btPrevimp ) {
			imprimir( TYPE_PRINT.VIEW );
		}
		else if ( evt.getSource() == btImp ) {
			imprimir( TYPE_PRINT.PRINT);

		}
		super.actionPerformed( evt );

		if ( evt.getSource() == btFirefox ) {

			if ( !txtSiteBanco.getVlrString().equals( "" ) ) {

				sURLBanco = txtSiteBanco.getVlrString();
				Funcoes.executeURL( Aplicativo.strOS, Aplicativo.strBrowser, sURLBanco );
			}
			else
				Funcoes.mensagemInforma( this, "Informe o Site do banco! " );
		}
	}

	private void imprimir( TYPE_PRINT bVisualizar ) {

		ImprimeOS imp = new ImprimeOS( "", con );
		int linPag = imp.verifLinPag() - 1;
		imp.montaCab();
		imp.setTitulo( "Relat�rio de Bancos" );
		DLRBanco dl = new DLRBanco( this );
		dl.setVisible( true );
		if ( dl.OK == false ) {
			dl.dispose();
			return;
		}
		String sSQL = "SELECT CODBANCO,NOMEBANCO FROM FNBANCO ORDER BY " + dl.getValor();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement( sSQL );
			rs = ps.executeQuery();
			imp.limpaPags();
			while ( rs.next() ) {
				if ( imp.pRow() == 0 ) {
					imp.impCab( 80, false );
					imp.pulaLinha( 1, imp.normal() );
					imp.say( 2, "C�digo" );
					imp.say( 30, "Nome" );
					imp.pulaLinha( 1, imp.normal() );
					imp.say( 0, StringFunctions.replicate( "-", 79 ) );
				}
				imp.pulaLinha( 1, imp.normal() );
				imp.say( 2, rs.getString( "CodBanco" ) );
				imp.say( 30, rs.getString( "NomeBanco" ) );
				if ( imp.pRow() >= linPag ) {
					imp.incPags();
					imp.eject();
				}
			}

			imp.pulaLinha( 1, imp.normal() );
			imp.say( 0, StringFunctions.replicate( "=", 79 ) );
			imp.eject();

			imp.fechaGravacao();

			// rs.close();
			// ps.close();
			con.commit();
			dl.dispose();
		} catch ( SQLException err ) {
			Funcoes.mensagemErro( this, "Erro consulta tabela de bancos!\n" + err.getMessage(), true, con, err );
		}

		if ( bVisualizar==TYPE_PRINT.VIEW ) {
			imp.preview( this );
		}
		else {
			imp.print();
		}
	}

	public void setConexao( DbConnection cn ) {

		super.setConexao( cn );
		lcModBol.setConexao( cn );
		lcBanco.setConexao( cn );
	}
}
