package logicsim;
import java.util.*;

class GateHolder{
  Scanner scn=new Scanner(System.in);
  Gate[] MGS;
    int MGStackPtr = -1;
    List<String> triggerPins = new ArrayList<String>();
    List<String> foutPins = new ArrayList<String>();

  void createNewstack(int stacksize){
    MGS = new Gate[stacksize];
  }

  boolean addGateToStack(Gate _newGate){
    try{
      if(fgiByID(_newGate.gateid) != -1 ){
        System.out.println("Duplicate Gate ID entered, try again");
        return false;
      }
      if(_newGate.getClass() != Nullgate.class){
      MGS[++MGStackPtr]= _newGate;
      return true;
      }
      return false;
    }
    catch(Exception e){
      System.out.println("Failed to add new gate");
      return false;
    }
  }

  int fgiByID(String _gateID){

    for(int i = 0; i <= MGStackPtr; i++)
    {
      if( (MGS[i].gateid).equals(_gateID) ){
        return i;
      }
    }
    return -1;
  }
// LinkedList filterconnlist(LinkedList _connList){
//     for(int i = 0 ; i <= MGStackPtr; i++ ){
//       // connList.add( MGS[MGStackPtr].pin1 );
//       // connList.add( MGS[MGStackPtr].pin2 );
//     }
//
//   }
  String arlToStr(ArrayList<String> pinArl){
    String pinStr = "{ ";

    for (String s : pinArl)
    {
        pinStr += s+" ";
    }
    pinStr += "}";
    return pinStr;
  }

  void showConns(){

    Gate currGate = new Gate();
    List connList = new ArrayList();
      String _ConString;
      for(int i = 0 ; i <= MGStackPtr; i++ ){
        currGate = MGS[i];
        _ConString ="< "+" ID: "+currGate.gateid;
        _ConString += " Pin0: "+arlToStr(currGate.pin0);
        _ConString +=" Pin1: "+arlToStr(currGate.pin1);
        _ConString +=" Output: "+arlToStr(currGate.pin2)+" > \n";
        connList.add(_ConString);
      }

      System.out.println(connList);
      System.out.println("Trigger pins: "+triggerPins);
      System.out.println("Final output pins: "+foutPins);
    } //showConns method ends

    //setters

    boolean setTrigPins(String[] prts){
      // System.out.println("Enter trigger pins");
      // String[] prts = scn.next().split("/");
      if(fgiByID(prts[0]) == -1){
        System.out.println("Gate not found");
        return false;
      }
      if( (MGS[fgiByID(prts[0])].gateType).equals("NOT") ){
        if(prts[1].equals("2")){
          System.out.println("No pin 2 as "+prts[0]+" is a NOT gate");
          return false;
        }
      }
      if( !(triggerPins.contains(prts[0]+"."+prts[1]) ) ){
        System.out.println("Successfully set pin as main input");
      triggerPins.add(prts[0]+"."+prts[1]);
      return true;
    }
    else{
      System.out.println("Pin already set as input pin");
      return false;
       }

    }//setTrigPins ENDS

    boolean setResultpins(String gid){

      if(fgiByID(gid)==-1 ){
        System.out.println("Gate not found");
        return false;
      }

      if( !(foutPins.contains(gid) ) ){
      foutPins.add(gid);
      System.out.println("Gate "+gid+" set as final output gate");
      return true;
    }

    else{
      System.out.println("Gate already specified as a final output gate");
      return false;
      }

    }

    boolean resetGates(){
      for(int i = 0; i<=MGStackPtr; i++){
        MGS[i].inp1 = null;
        MGS[i].inp2 = null;
        MGS[i].output = null;
      }
      return true;
    }

  void clearPending(){
    for(int i = 0; i<=MGStackPtr; i++)
      MGS[i].updatefeedbackpins();
  }

  }//GateHolder class ends
