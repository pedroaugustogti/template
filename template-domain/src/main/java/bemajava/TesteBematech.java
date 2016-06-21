package bemajava;
import bemajava.Bematech;



public class TesteBematech

{ 

  public static void main(String[] args)

  {

     int iRetorno;

     BemaInteger ACK, ST1, ST2, ST3;

     ACK = new BemaInteger();

     ST1 = new BemaInteger();

     ST2 = new BemaInteger();

     ST3 = new BemaInteger();

     // Habilitando o retorno estendido da impressora MFD
     iRetorno = Bematech.HabilitaDesabilitaRetornoEstendidoMFD( "1" );
 
     // Emitindo uma Leitura X
     iRetorno = Bematech.LeituraX();
     System.out.println( "Retorno: " + iRetorno );

     // Pegando o retorno da impressora MFD
     iRetorno = Bematech.RetornoImpressoraMFD( ACK, ST1, ST2, ST3 );

     String retorno = "ACK: " + ACK.number;
     retorno += "\nST1: " + ST1.number; 
     retorno += "\nST2: " + ST2.number; 
     retorno += "\nST3: " + ST3.number;

     System.out.println( "Retorno Impressora MFD: \n" + retorno );
  }
}