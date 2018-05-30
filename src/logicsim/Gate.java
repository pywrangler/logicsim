package logicsim;

import java.util.*;


public class Gate{
  String gateid;
  int gateIndex;
  String gateType;
  ArrayList<String> pin0 = new ArrayList<String>();
  ArrayList<String> pin1 = new ArrayList<String>();
  ArrayList<String> pin2 = new ArrayList<String>();
  ArrayList<String> pendpin0 = new ArrayList<String>();
  ArrayList<String> pendpin1 = new ArrayList<String>();
  ArrayList<String> pendpin2 = new ArrayList<String>();
  Boolean  inp1 = new Boolean(true), inp2 = new Boolean(true), output = new Boolean(true);
  // boolean inp1conn = false,inp2conn = false;
  // int oupInd = -1;

  void compute(){
    System.out.println("compute is doing nothing");
  }

  String extID(String tempgate){
    return  tempgate.split("\\.")[0] ;
  }
  String extPin(String tempgate){
    return tempgate.split("\\.")[1];
  }

  void addPin(ArrayList<String> newGates, int pin_num){
    switch(pin_num){
      case(0):
      for(String ng: newGates){
        if(!( pin0.contains(ng) ) )
          pendpin0.add(ng);
      }
      break;
      case(1):
      for(String ng: newGates){
        if(!( pin1.contains(ng) ) )
          pendpin1.add(ng);
      }
      break;
      case(2):
      for(String ng: newGates){
        if(!( pin2.contains(ng) ) )
          pendpin2.add(ng);
      }
      break;
      default:
        System.out.println("addPin error: Cannot add feedback pins to pinlist");
    }
  }
  ArrayList<String> crossConn(GateHolder cGH, String selfPin, String cPin, String cGate){
    switch(selfPin){
      case("0"):
        if(!( pin0.contains(cGate+"."+cPin) ) ){
          pin0.add(cGate+"."+cPin);
        for(String listGate : pin0){
          addPin(cGH.MGS[cGH.fgiByID( extID(listGate) ) ].crossConn(cGH, extPin(listGate), cPin, cGate),0);
          }
        }
        return pin0;
      case("1"):
        if(!( pin1.contains(cGate+"."+cPin) ) ){
          pin1.add(cGate+"."+cPin);
        for(String listGate : pin1){
          addPin(cGH.MGS[cGH.fgiByID( extID(listGate) ) ].crossConn(cGH, extPin(listGate), cPin, cGate),1);
          }
        }
        return pin1;
      case("2"):
        if(!( pin2.contains(cGate+"."+cPin) ) ){
          pin2.add(cGate+"."+cPin);
        for(String listGate : pin2){
          addPin(cGH.MGS[cGH.fgiByID( extID(listGate) ) ].crossConn(cGH, extPin(listGate), cPin, cGate),2);
          }
        }
        return pin2;
      default:
        System.out.println("cannot crossConn, Invalid pin = "+cPin);
        return  new ArrayList<String>();
    }//end of switch
  }

  boolean allPinConnected(){return true;};
  //TODO insert validation code in inherited classes

  void updatefeedbackpins(){
    for(String fbpin:pendpin0){
      if(!( pin0.contains(fbpin) ) )
        pin0.add(fbpin);
    }
    for(String fbpin:pendpin1){
      if(!( pin1.contains(fbpin) ) )
        pin1.add(fbpin);
    }
    for(String fbpin:pendpin2){
      if(!( pin2.contains(fbpin) ) )
        pin2.add(fbpin);
    }
    pendpin0 = new ArrayList<String>();
    pendpin1 = new ArrayList<String>();
    pendpin2 = new ArrayList<String>();
  }
}
