/**
 * @version 14/11/2000 <BR>
 * @author Setpoint Informática Ltda./Fernando Oliveira da Silva <BR>
 *
 * Projeto: Freedom <BR>
 * Pacote: org.freedom.telas <BR>
 * Classe: @(#)FLogin.java <BR>
 * 
 * Este arquivo é parte do sistema Freedom-ERP, o Freedom-ERP é um software livre; você pode redistribui-lo e/ou <BR>
 * modifica-lo dentro dos termos da Licença Pública Geral GNU como publicada pela Fundação do Software Livre (FSF); <BR>
 * na versão 2 da Licença, ou (na sua opnião) qualquer versão. <BR>
 * Este programa é distribuido na esperança que possa ser  util, mas SEM NENHUMA GARANTIA; <BR>
 * sem uma garantia implicita de ADEQUAÇÂO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. <BR>
 * Veja a Licença Pública Geral GNU para maiores detalhes. <BR>
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU junto com este programa, se não, <BR>
 * escreva para a Fundação do Software Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA <BR> <BR>
 *
 * Comentários para a classe...
 */

package org.freedom.library.swing.frame;

import java.awt.BorderLayout;

import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JPanelPad;
import org.freedom.library.swing.dialog.FFDialogo;
import org.freedom.library.swing.util.SwingParams;

public class FAtalhos extends FFDialogo {
	private static final long serialVersionUID = 1L;

	private JPanelPad pnAtalhos = new JPanelPad(JPanelPad.TP_JPANEL, new BorderLayout());

	public FAtalhos() {

		super(Aplicativo.telaPrincipal);
		setTitulo("Raccourcis");
		setAtribos(380, 550);

		setToFrameLayout();

		c.add(pnAtalhos);

		JLabelPad lbAtalhos = new JLabelPad();
		StringBuilder atalhos = new StringBuilder();

		atalhos.append("<HTML><BODY>");
		atalhos.append("<UL>");
		atalhos.append("<LI><STRONG>CTRL + N</STRONG> - <EM>Nouveau record</EM>");
		atalhos.append("<LI><STRONG>CTRL + S</STRONG> - <EM>Enregistrer les modifications</EM>");
		atalhos.append("<LI><STRONG>CTRL + D</STRONG> - <EM>Supprimer la fiche</EM>");
		atalhos.append("<LI><STRONG>CTRL + E</STRONG> - <EM>Editer</EM>");
		atalhos.append("<LI><STRONG>CTRL + W</STRONG> - <EM>Annuler les modifications</EM>");
		atalhos.append("<LI><STRONG>CTRL + P</STRONG> - <EM>Imprimer l'Enregistrement</EM>");
		atalhos.append("<LI><STRONG>CTRL + R</STRONG> - <EM>Aperçu avant impression</EM>");
		atalhos.append("<LI><STRONG>CTRL + O</STRONG> - <EM>remarques</EM>");
		atalhos.append("<LI><STRONG>CTRL + I</STRONG> - <EM>Imprimer</EM>");
		atalhos.append("<LI><STRONG>CTRL + P</STRONG> - <EM>aperçu avant impression</EM>");
		atalhos.append("<LI><STRONG>TAB</STRONG> - <EM>Aller au champ suivant</EM>");
		atalhos.append("<LI><STRONG>SHIFT + TAB</STRONG> - <EM>Retour au champ précédent</EM>");
		atalhos.append("<LI><STRONG>SHIFT + F4</STRONG> - <EM>Fermer l'écran</EM>");
		atalhos.append("<LI><STRONG>Espace</STRONG> - <EM>Appuyez sur un bouton</EM>");
		atalhos.append("<LI><STRONG>CTRL + PAGE UP</STRONG> - <EM>Aller à la première Enregistrement</EM>");
		atalhos.append("<LI><STRONG>PAGE UP</STRONG> - <EM>Aller à l'enregistrement précédentee</EM>");
		atalhos.append("<LI><STRONG>PAGE DOWN</STRONG> - <EM>Aller au Document suivant</EM>");
		atalhos.append("<LI><STRONG>CTRL + PAGE DOWN</STRONG> - <EM>aller à la fiche Dernière</EM>");
		atalhos.append("<LI><STRONG>F1</STRONG> - <EM>Raccourcis</EM>");
		atalhos.append("<LI><STRONG>F2</STRONG> - <EM>Recherche</EM>");
		atalhos.append("<LI><STRONG>F3</STRONG> - <EM>Trouver similaire</EM>");
		atalhos.append("<LI><STRONG>F4</STRONG> - <EM>Remplissez le budget</EM>");
		atalhos.append("<LI><STRONG>F4</STRONG> - <EM>Fermer l'Achat</EM>");
		atalhos.append("<LI><STRONG>F4</STRONG> - <EM>Fermer le Vente</EM>");
		atalhos.append("<LI><STRONG>F5</STRONG> - <EM>Consulter les paiements</EM>");
		atalhos.append("<LI><STRONG>F6</STRONG> - <EM>Ouvrir l'Écran d'enregistrement</EM>");
		atalhos.append("<LI><STRONG>ESC</STRONG> - <EM>Fermer la fenêtre </EM>");
		atalhos.append("</UL>");
		atalhos.append("</BODY>");
		atalhos.append("</HTML>");

		lbAtalhos.setText(atalhos.toString());
		lbAtalhos.setFont(SwingParams.getFontbold());

		pnAtalhos.add(lbAtalhos);

	}
}
