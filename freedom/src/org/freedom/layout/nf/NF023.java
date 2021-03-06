/**
 * @version 22/05/2006 <BR>
 * @author Setpoint Inform�tica Ltda./Alex Rodrigues <BR>
 * 
 *         Projeto: Freedom <BR>
 *         Pacote: layout <BR>
 *         Classe:
 * @(#)NFIswara.java <BR>
 * 
 *                   Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 *                   modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 *                   na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 *                   Este programa � distribuido na esperan�a que possa ser util, mas SEM NENHUMA GARANTIA; <BR>
 *                   sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 *                   Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 *                   Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 *                   de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 *                   Layout da nota fiscal para a empresa Iswara Ltda.
 */

package org.freedom.layout.nf;

import java.util.Vector;

import org.freedom.library.business.component.NF;
import org.freedom.library.component.ImprimeOS;
import org.freedom.library.component.Layout;
import org.freedom.library.functions.Extenso;
import org.freedom.library.functions.Funcoes;

public class NF023 extends Layout {

	public boolean imprimir( NF nf, ImprimeOS imp ) {

		boolean retorno = super.imprimir( nf, imp );
		boolean bjatem = false;
		boolean bjatem1 = false;
		boolean bjatem2 = false;
		final int MAXLINE = 33;
		int iLinPag = imp.verifLinPag( "NF" );
		int iNumNota = 0;
		int iItImp = 0;
		int iContaFrete = 0;
		int sizeObs = 0;
		int indexDescFisc = 0;
		int indexSigla = 0;
		int indexObs = 0;
		String sCodfisc = null;
		String sSigla = null;
		String sTemp = null;
		String sDescFisc = "";
		String[] sValsCli = new String[ 4 ];
		String[] sNat = new String[ 2 ];
		String[] sDuplics = new String[ 4 ];
		String[] sVencs = new String[ 4 ];
		String[] sVals = new String[ 4 ];
		Vector<String> vClfisc = new Vector<String>();
		Vector<String> vSigla = new Vector<String>();
		Vector<?> vObs = new Vector<Object>();
		Vector<String> vDescFisc = new Vector<String>();

		try {

			if ( cab.next() ) {

				iNumNota = cab.getInt( NF.C_DOC );
				vObs = Funcoes.strToVectorSilabas( cab.getString( NF.C_OBSPED ), 120 );
			}

			for ( int i = 0; i < 4; i++ ) {

				if ( parc.next() ) {

					sDuplics[ i ] = iNumNota + " / " + parc.getInt( NF.C_NPARCITREC );
					sVencs[ i ] = ( parc.getDate( NF.C_DTVENCTO ) != null ? Funcoes.dateToStrDate( parc.getDate( NF.C_DTVENCTO ) ) : "" );
					sVals[ i ] = Funcoes.strDecimalToStrCurrency( 12, 2, String.valueOf( parc.getFloat( NF.C_VLRPARC ) ) );
				}
				else {

					sDuplics[ i ] = "";
					sVencs[ i ] = "";
					sVals[ i ] = "";
				}
			}

			imp.limpaPags();

			vClfisc.addElement( "" );

			while ( itens.next() ) {

				sNat[ 0 ] = Funcoes.copy( itens.getString( NF.C_DESCNAT ).trim(), 32 );
				sNat[ 1 ] = Funcoes.setMascara( itens.getString( NF.C_CODNAT ), "#.###" );

				if ( adic.next() ) {

					sValsCli[ 0 ] = !adic.getString( NF.C_CPFEMITAUX ).equals( "" ) ? adic.getString( NF.C_CPFEMITAUX ) : cab.getString( NF.C_CPFEMIT );
					sValsCli[ 1 ] = !adic.getString( NF.C_NOMEEMITAUX ).equals( "" ) ? adic.getString( NF.C_NOMEEMITAUX ) : cab.getString( NF.C_RAZEMIT );
					sValsCli[ 2 ] = !adic.getString( NF.C_CIDEMITAUX ).equals( "" ) ? adic.getString( NF.C_CIDEMITAUX ) : cab.getString( NF.C_CIDEMIT );
					sValsCli[ 3 ] = !adic.getString( NF.C_UFEMITAUX ).equals( "" ) ? adic.getString( NF.C_UFEMITAUX ) : cab.getString( NF.C_UFEMIT );
				}
				else {

					sValsCli[ 0 ] = cab.getString( NF.C_CPFEMIT );
					sValsCli[ 1 ] = cab.getString( NF.C_RAZEMIT );
					sValsCli[ 2 ] = cab.getString( NF.C_CIDEMIT );
					sValsCli[ 3 ] = cab.getString( NF.C_UFEMIT );
				}

				if ( imp.pRow() == 0 ) {

					// Imprime cabe�alho da nota ...

					imp.pulaLinha( 1, imp.comprimido() );

					if ( nf.getTipoNF() == NF.TPNF_ENTRADA ) {

						imp.say( 103, "X" );
					}
					else {

						imp.say( 87, "X" );
					}
					imp.say( 128, cab.getString( NF.C_DOC ) );
					imp.pulaLinha( 4, imp.comprimido() );
					imp.say( 5, sNat[ 0 ] );
					imp.say( 48, sNat[ 1 ] );
					imp.pulaLinha( 3, imp.comprimido() );
					imp.say( 5, sValsCli[ 1 ] );
					imp.say( 91, !sValsCli[ 0 ].equals( "" ) ? Funcoes.setMascara( sValsCli[ 0 ], "###.###.###-##" ) : Funcoes.setMascara( cab.getString( NF.C_CNPJEMIT ), "##.###.###/####-##" ) );
					imp.say( 124, ( cab.getDate( NF.C_DTEMITPED ) != null ? Funcoes.dateToStrDate( cab.getDate( NF.C_DTEMITPED ) ) : "" ) );
					imp.pulaLinha( 2, imp.comprimido() );
					imp.say( 5, Funcoes.copy( cab.getString( NF.C_ENDEMIT ), 0, 50 ).trim() + ", " + Funcoes.copy( cab.getString( NF.C_NUMEMIT ), 0, 6 ).trim() + " - " + Funcoes.copy( cab.getString( NF.C_COMPLEMIT ), 0, 9 ).trim() );
					imp.say( 74, Funcoes.copy( cab.getString( NF.C_BAIREMIT ), 0, 23 ) );
					imp.say( 103, Funcoes.setMascara( cab.getString( NF.C_CEPEMIT ), "#####-###" ) );

					if ( !itens.getString( NF.C_IMPDTSAIDA ).equals( "N" ) ) {

						imp.say( 124, ( cab.getDate( NF.C_DTSAIDA ) != null ? Funcoes.dateToStrDate( cab.getDate( NF.C_DTSAIDA ) ) : "" ) );
					}

					imp.pulaLinha( 2, imp.comprimido() );
					imp.say( 5, sValsCli[ 2 ] );
					imp.say( 52, ( !cab.getString( NF.C_DDDEMIT ).equals( "" ) ? "(" + cab.getString( NF.C_DDDEMIT ) + ")" : "" ) + ( !cab.getString( NF.C_FONEEMIT ).equals( "" ) ? Funcoes.setMascara( cab.getString( NF.C_FONEEMIT ).trim(), "####-####" ) : "" ).trim() );
					imp.say( 83, sValsCli[ 3 ] );
					imp.say( 91, !cab.getString( NF.C_RGEMIT ).equals( "" ) ? cab.getString( NF.C_RGEMIT ) : cab.getString( NF.C_INSCEMIT ) );
					imp.pulaLinha( 3, imp.comprimido() );

					// Fim do cabe�alho ...

					// Imprime dados da fatura ...
					imp.say( 5, cab.getString( NF.C_DOC ) );
					imp.pulaLinha( 1, imp.comprimido() );
					imp.say( 93, sDuplics[ 0 ] );
					imp.say( 108, sVencs[ 0 ] );
					imp.say( 125, sVals[ 0 ] );
					imp.say( 110, sDuplics[ 1 ] );
					imp.say( 105, sVencs[ 1 ] );
					imp.say( 125, sVals[ 1 ] );
					imp.pulaLinha( 1, imp.comprimido() );
					imp.say( 6, sDuplics[ 2 ] );
					imp.say( 26, sVencs[ 2 ] );
					imp.say( 52, sVals[ 2 ] );
					imp.say( 93, sDuplics[ 3 ] );
					imp.say( 108, sVencs[ 3 ] );
					imp.say( 125, sVals[ 3 ] );
					imp.pulaLinha( 2, imp.comprimido() );
					String sValorTotLiqVenda = Extenso.extenso( itens.getFloat( NF.C_VLRLIQPED ), "real", "reais", "centavo", "centavos" ).toUpperCase();
					imp.say( 5, sValorTotLiqVenda );
					imp.pulaLinha( 3, imp.comprimido() );

					// Fim dos dados da fatura ...

				}

				// Monta a observa��o ...

				sTemp = itens.getString( NF.C_DESCFISC ).trim();
				if ( sDescFisc.length() > 0 ) {

					if ( sDescFisc.indexOf( sTemp ) > -1 ) {

						bjatem1 = true;
					}
					if ( !bjatem1 ) {

						vDescFisc.addElement( sDescFisc );
					}

					bjatem1 = false;
				}
				else {

					sDescFisc = sTemp;
				}

				sDescFisc = itens.getString( NF.C_DESCFISC2 ).trim();

				if ( sDescFisc.length() > 0 ) {

					if ( sDescFisc.indexOf( sTemp ) > -1 ) {

						bjatem2 = true;
					}
					if ( !bjatem2 ) {

						vDescFisc.addElement( sDescFisc );
					}

					bjatem2 = false;
				}
				else {

					sDescFisc = sTemp;
				}

				// Fim da observa��o ...

				// Defini��o da sigla para a classifica��o fiscal...

				sCodfisc = itens.getString( NF.C_CODFISC );

				if ( !sCodfisc.equals( "" ) ) {

					for ( int i = 0; i < vClfisc.size(); i++ ) {

						if ( vClfisc.elementAt( i ) != null ) {

							if ( sCodfisc.equals( vClfisc.elementAt( i ) ) ) {

								bjatem = true;
								sSigla = String.valueOf( (char) ( 64 + i ) );
							}
							else {

								bjatem = false;
							}
						}
					}

					if ( !bjatem ) {

						vClfisc.addElement( sCodfisc );
						sSigla = String.valueOf( (char) ( 63 + vClfisc.size() ) );
						vSigla.addElement( sSigla + " = " + sCodfisc );
					}
				}

				// Fim da classifica��o fiscal ...

				// Imprime os dados do item no corpo da nota ...

				imp.pulaLinha( 1, imp.comprimido() );
				imp.say( 4, Funcoes.alinhaCentro( itens.getInt( NF.C_CODPROD ), 5 ) );
				imp.say( 19, Funcoes.copy( itens.getString( NF.C_DESCPROD ).trim(), 23 ) );
				imp.say( 81, Funcoes.copy( itens.getString( NF.C_ORIGFISC ), 1 ) + Funcoes.copy( itens.getString( NF.C_CODTRATTRIB ), 2 ) );
				imp.say( 86, Funcoes.copy( itens.getString( NF.C_CODUNID ), 3 ) );
				imp.say( 88, Funcoes.strDecimalToStrCurrency( 9, 3, String.valueOf( itens.getFloat( NF.C_QTDITPED ) ) ) );
				imp.say( 103, Funcoes.strDecimalToStrCurrency( 10, 2, String.valueOf( itens.getFloat( NF.C_VLRPRODITPED ) / itens.getFloat( NF.C_QTDITPED ) ) ) );
				imp.say( 118, Funcoes.strDecimalToStrCurrency( 13, 2, String.valueOf( itens.getFloat( NF.C_VLRPRODITPED ) ) ) );
				imp.say( 135, ( (int) itens.getFloat( NF.C_PERCICMSITPED ) ) + "%" );

				// Fim da impress�o do item ...

				iItImp++;
				if ( ( iItImp == itens.getInt( NF.C_CONTAITENS ) ) || ( imp.pRow() == MAXLINE - 2 ) ) {

					if ( iItImp == itens.getInt( NF.C_CONTAITENS ) ) {

						// Imprime o desconto ...

						imp.pulaLinha( 1, imp.comprimido() );
						imp.say( 19, "Valor do desconto : " + Funcoes.strDecimalToStrCurrency( 9, 2, String.valueOf( cab.getFloat( NF.C_VLRDESCITPED ) ) ) );

						// Imprime observa��o no corpo da nota ...

						for ( int i = 0; i < vObs.size(); i++ ) {

							if ( imp.pRow() < MAXLINE ) {

							}
						}
					}

					imp.pulaLinha( ( MAXLINE + 2 ) - imp.pRow(), imp.comprimido() );

					// Imprime totais ...

					if ( iItImp == itens.getInt( NF.C_CONTAITENS ) ) {

						imp.pulaLinha( 10, imp.comprimido() );
						imp.say( 4, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( cab.getFloat( NF.C_VLRBASEICMSPED ) ) ) );
						imp.say( 32, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( cab.getFloat( NF.C_VLRICMSPED ) ) ) );
						imp.say( 114, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( itens.getFloat( NF.C_VLRPRODITPED ) ) ) );
						imp.pulaLinha( 2, imp.comprimido() );
						imp.say( 4, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( frete.getFloat( NF.C_VLRFRETEPED ) ) ) );
						imp.say( 58, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( itens.getFloat( NF.C_VLRADICITPED ) ) ) );
						imp.say( 87, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( cab.getFloat( NF.C_VLRIPIPED ) ) ) );
						imp.say( 114, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( cab.getFloat( NF.C_VLRLIQPED ) ) ) );
						iItImp = 0;
					}
					else {

						imp.pulaLinha( 1, imp.comprimido() );
						imp.say( 4, "********************" );
						imp.say( 32, "********************" );
						imp.say( 114, "********************" );
						imp.pulaLinha( 2, imp.comprimido() );
						imp.say( 4, "********************" );
						imp.say( 58, "********************" );
						imp.say( 87, "********************" );
						imp.say( 114, "********************" );
					}

					// Fim da impress�o dos totais ...

					// Imprime informa��es do frete ...

					if ( iContaFrete == 0 ) {

						frete.next();
						iContaFrete++;
					}

					imp.pulaLinha( 3, imp.comprimido() );
					imp.say( 6, frete.getString( NF.C_RAZTRANSP ) );
					imp.say( 81, frete.getString( NF.C_TIPOFRETE ).equals( "C" ) ? "1" : "2" );
					imp.say( 93, frete.getString( NF.C_PLACAFRETE ) );
					imp.say( 107, frete.getString( NF.C_UFFRETE ) );

					if ( frete.getString( NF.C_TIPOTRANSP ).equals( "C" ) ) {

						imp.say( 116, Funcoes.setMascara( cab.getString( NF.C_CNPJEMIT ), "##.###.###/####-##" ) );
					}
					else {

						imp.say( 116, Funcoes.setMascara( frete.getString( NF.C_CNPJTRANSP ), "##.###.###/####-##" ) );
					}

					imp.pulaLinha( 2, imp.comprimido() );
					imp.say( 6, frete.getString( NF.C_ENDTRANSP ).trim() + ", " + frete.getInt( NF.C_NUMTRANSP ) );
					imp.say( 77, frete.getString( NF.C_CIDTRANSP ) );
					imp.say( 107, frete.getString( NF.C_UFTRANSP ) );

					if ( frete.getString( NF.C_TIPOTRANSP ).equals( "C" ) ) {

						imp.say( 116, cab.getString( NF.C_INSCEMIT ) );
					}
					else {

						imp.say( 116, frete.getString( NF.C_INSCTRANSP ) );
					}

					imp.pulaLinha( 2, imp.comprimido() );
					imp.say( 6, Funcoes.strDecimalToStrCurrency( 10, 0, String.valueOf( frete.getString( NF.C_QTDFRETE ) ) ) );
					imp.say( 21, Funcoes.copy( frete.getString( NF.C_ESPFRETE ), 17 ) );
					imp.say( 50, frete.getString( NF.C_MARCAFRETE ) );
					imp.say( 79, Funcoes.copy( frete.getString( NF.C_CONHECFRETEPED ), 20 ) );
					imp.say( 87, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( frete.getFloat( NF.C_PESOBRUTO ) ) ) );
					imp.say( 114, Funcoes.strDecimalToStrCurrency( 20, 2, String.valueOf( frete.getString( NF.C_PESOLIQ ) ) ) );
					imp.pulaLinha( 13, imp.comprimido() );
					imp.say( 128, cab.getString( NF.C_DOC ) );

					// Fim da impress�o do frete ...

					// Imprime observa��o e classifica��es fiscais ...

					int aux = 0;
					for ( int i = indexObs; i < 5; i++ ) {

						if ( aux < sizeObs ) {

							imp.pulaLinha( 1, imp.comprimido() );

							if ( vSigla.size() > 0 && indexSigla < vSigla.size() ) {

								imp.say( 2, vSigla.elementAt( indexSigla++ ) );
							}
							if ( vDescFisc.size() > 0 && indexDescFisc < vDescFisc.size() ) {

							}
						}
						else {

						}
					}

					// Fim da observa��o ...

					// Imprime canhoto ...

					imp.pulaLinha( iLinPag - imp.pRow(), imp.comprimido() );

					imp.setPrc( 0, 0 );
					imp.incPags();

				}
			}

			imp.fechaGravacao();
			retorno = true;

		} catch ( Exception err ) {
			Funcoes.mensagemErro( null, "Erro ao montar nota \n" + err.getMessage() );
			err.printStackTrace();
		} finally {
			sValsCli = null;
			sNat = null;
			sVencs = null;
			sVals = null;
			System.gc();
		}

		return retorno;
	}
}
