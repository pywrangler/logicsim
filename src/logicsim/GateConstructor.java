package logicsim;
import java.util.*;

class GateConstructor{
  Scanner scn=new Scanner(System.in);
  String choice;
  Gate gateBuilder(int MGSptr){
    System.out.println("1:AND 2:OR 3:NOT 4:NAND 5:NOR 6:XOR 7:XNOR  0: Go Back ");
    choice = scn.next();

    switch(choice){
      case("1"):
        ANDgate _ANDgate = new ANDgate();
        System.out.println("Enter Gate ID");
        _ANDgate.gateid = scn.next();
        _ANDgate.gateIndex = MGSptr+1;
        _ANDgate.gateType = "AND";
        return _ANDgate;
      case("2"):
        ORgate _ORgate = new ORgate();
        System.out.println("Enter Gate ID");
        _ORgate.gateid = scn.next();
        _ORgate.gateIndex = MGSptr+1;
        _ORgate.gateType = "OR";
        return _ORgate;
      case("3"):
        NOTgate _NOTgate = new NOTgate();
        System.out.println("Enter Gate ID");
        _NOTgate.gateid = scn.next();
        _NOTgate.gateIndex = MGSptr+1;
        _NOTgate.gateType = "NOT";
        return _NOTgate;
      case("4"):
        NANDgate _NANDgate = new NANDgate();
        System.out.println("Enter Gate ID");
        _NANDgate.gateid = scn.next();
        _NANDgate.gateIndex = MGSptr+1;
        _NANDgate.gateType = "NAND";
        return _NANDgate;
      case("5"):
        NORgate _NORgate = new NORgate();
        System.out.println("Enter Gate ID");
        _NORgate.gateid = scn.next();
        _NORgate.gateIndex = MGSptr+1;
        _NORgate.gateType = "NOR";
        return _NORgate;
      case("6"):
        XORgate _XORgate = new XORgate();
        System.out.println("Enter Gate ID");
        _XORgate.gateid = scn.next();
        _XORgate.gateIndex = MGSptr+1;
        _XORgate.gateType = "XOR";
        return _XORgate;
      case("7"):
        XNORgate _XNORgate = new XNORgate();
        System.out.println("Enter Gate ID");
        _XNORgate.gateid = scn.next();
        _XNORgate.gateIndex = MGSptr+1;
        _XNORgate.gateType = "XNOR";
        return _XNORgate;
      case("0"):
        Nullgate _nullGate = new Nullgate();
        return _nullGate;
      default:
         System.out.println("Invalid Choice");
         Nullgate defGate = new Nullgate();
         return defGate;
       }

  }

  // void connPins(GateHolder _GH){
  //   do{
  //     System.out.println("1:Show connections 2: Connect Pins 3:Go Back 4:Set triggers");
  //   choice = scn.next();
  //   switch(choice){
  //   case("1"):
  //     _GH.showConns();
  //     break;
  //   case("2"):
  //     System.out.println("Enter Connection String ");
  //     String connString = scn.next();
  //     if(connect(connString, _GH) )System.out.println("Successfully Connected "+connString);
  //     break;
  //   case("4"):
  //     _GH.setTrigPins();
  //     break;
  //   case("3"):
  //     break;
  //   default:
  //     System.out.println("Invalid Choice");
  //     break;
  //     }
  //
  //   }while( !( choice.equals("3") ) );
  // }

  boolean connect(String connstr,GateHolder _GH){ //return true on success
    String srcGateID,srcGatePin, inpGateID, inpGatePin; //the ID of the gate from where output is coming
     //the ID of the gate which takes the above output as input
    String[] parts = connstr.split("-");
    parts = parts[0].split("/");
    srcGateID = parts[0];
    srcGatePin = parts[1];
    parts = connstr.split("-");
    parts = parts[1].split("/");
    inpGateID = parts[0];
    inpGatePin = parts[1];

    int srcGateIndex = _GH.fgiByID(srcGateID);
    int inpGateIndex = _GH.fgiByID(inpGateID);

    if(srcGateIndex == -1 || inpGateIndex == -1){
      // System.out.println("Failed to connect pins, invalid gate ID or pin no.");
      System.out.println("Failed to connect: gate not found");
      return false;
    }

    if( (_GH.MGS[inpGateIndex].gateType).equals("NOT") ){
      if ( inpGatePin.equals("2") ){
          System.out.println("Input 2 not found as "+inpGateID+" is a NOT gate");
      return false;
    }
    }
    if( (_GH.MGS[srcGateIndex].gateType).equals("NOT") ){
      if ( srcGatePin.equals("2") ){
          System.out.println("Input 2 not found as "+srcGateID+" is a NOT gate");
      return false;
    }
    }

    if( (inpGateID).equals(srcGateID) && (inpGatePin).equals(srcGatePin) ){
      System.out.println("Invalid connection: Both pins are same");
      return false;
    }
    _GH.MGS[srcGateIndex].crossConn(_GH, srcGatePin, inpGatePin, inpGateID);
    _GH.MGS[inpGateIndex].crossConn(_GH, inpGatePin, srcGatePin, srcGateID);
    _GH.clearPending();
    // switch(srcGatePin){
    //   case("0"):
    //     if( !(_GH.MGS[srcGateIndex].pin0.contains(inpGateID+"."+inpGatePin) ) )
    //     // _GH.MGS[srcGateIndex].pin0.add(inpGateID+"."+inpGatePin);
    //
    //     break;
    //   case("1"):
    //       if( !(_GH.MGS[srcGateIndex].pin1.contains(inpGateID+"."+inpGatePin) ) )
    //     _GH.MGS[srcGateIndex].pin1.add(inpGateID+"."+inpGatePin);
    //     break;
    //   case("2"):
    //     if( !(_GH.MGS[srcGateIndex].pin2.contains(inpGateID+"."+inpGatePin) ) )
    //     _GH.MGS[srcGateIndex].pin2.add(inpGateID+"."+inpGatePin);
    //     break;
    //   default:
    //     System.out.println(srcGateID+"."+srcGatePin+" Not Found");
    //     return false;
    // }
    // switch(inpGatePin){
    //   case("0"):
    //     if( !(_GH.MGS[inpGateIndex].pin0.contains(srcGateID+"."+srcGatePin) ) )
    //     _GH.MGS[inpGateIndex].pin0.add(srcGateID+"."+srcGatePin);
    //     break;
    //   case("1"):
    //     if( !(_GH.MGS[inpGateIndex].pin1.contains(srcGateID+"."+srcGatePin) ) )
    //     _GH.MGS[inpGateIndex].pin1.add(srcGateID+"."+srcGatePin);
    //     break;
    //   case("2"):
    //     if( !(_GH.MGS[inpGateIndex].pin2.contains(srcGateID+"."+srcGatePin) ) )
    //     _GH.MGS[inpGateIndex].pin2.add(srcGateID+"."+srcGatePin);
    //     break;
    //   default:
    //     System.out.println(inpGateID+"."+inpGatePin+" Not Found");
    //     return false;
    // }
    return true;
  }//CONNECT METHOD ends

}//GATE CONSTRUCTOR CLASS ENDS
