package logicsim;
import java.util.*;


class logicsim{
    public static void main(String[] args) {
        GateConstructor GCS = new GateConstructor();
        GateHolder GH = new GateHolder();
        Processor PCS = new Processor();
        GH.createNewstack(50);//50 is the stacksize,give anything you want
        Scanner scn=new Scanner(System.in);
        String choice = "1";
        String newGateParam;
        System.out.println("Hello, Welcome to logicsim");
        while( !( choice.equals("0") ) ){
          System.out.println("1: Add new Gate 2: Connect pins 3: See connections \n "
            +"4: Set input pins 5:Set final output pins "
            +"6: Generate Truth Table 0: Exit ");
            choice = scn.next();
            switch(choice){
              case("1"):
                  GH.addGateToStack(GCS.gateBuilder(GH.MGStackPtr) );
                  break;

              case("2"):
                System.out.println("Enter Connection String ");
                String connString = scn.next();
                if(GCS.connect(connString,GH) )System.out.println("Successfully Connected "+connString);
                break;
              case ("3"):
                GH.showConns();
                break;
              case("4"):
                System.out.println("Enter input pins");
                boolean conneted_bool = GH.setTrigPins( scn.next().split("/") );
                if( conneted_bool == true )
                  System.out.println("Input pins set");
                else if(conneted_bool == false)
                    System.out.println("Cannot set input pins");
                break;
              case("5"):
                System.out.println("Enter final output pin's gateID");
                GH.setResultpins( scn.next() );
                break;
              case("6"):
                PCS._GH = GH;
                PCS.process();
                break;
              case("0"):
                System.out.println("Closing logicsim");
                System.exit(0);
                break;
              default:
                System.out.println("Invalid Choice");
                break;

            }//switch ends

        }//while ends(menu loop)
    }//main ends
}//end of logicsim
