package logicsim;
import java.util.*;

public class Processor extends GateHolder {
  Map<String, ArrayList<Boolean> > truthTable = new HashMap<String, ArrayList<Boolean> >();
  GateHolder _GH;
  int total_combs;
  // int get_total_combs(){
  //    return 2^(GH.triggerPins.size());
  //  }
  // truthTable.put("A",new ArrayList());
  // truthTable.get("A").add(false);
  // System.out.println(truthTable);



    boolean initTT(){
      total_combs = (int) java.lang.Math.pow(2, (_GH.triggerPins.size()) );
      int cturns = total_combs;

      for(String inp : _GH.triggerPins){
        truthTable.put(inp,new ArrayList());

        cturns= cturns/2;
        Boolean bull = true;
        for( int k=1;k<=total_combs;k++ ){
          if((k-1)%cturns == 0)
            bull = !bull;
          truthTable.get(inp).add(bull);
        }
      }
      for(String out : _GH.foutPins){
        truthTable.put(out,new ArrayList());
      }
      System.out.println(truthTable);
      return true;
    }//end of initTT




    Gate setBool(Gate _cgate, String _pin,  Boolean boo){
      switch(_pin){
        case("0"):
          if( _cgate.inp1 == null)
          _cgate.inp1 = boo;
          return _cgate;
        case("1"):
          if( _cgate.inp2 == null)
          _cgate.inp2 = boo;
          return _cgate;
        case("3"):
          if( _cgate.output == null)
          _cgate.output = boo;
          return _cgate;
        default:
          System.out.println(" Processing error: Cannot set bool value");
          return _cgate;
      }
    }

    String strToID(String gatestr){
        return  gatestr.split("\\.")[0] ;
    }

    String strToPin(String gatestr){
      return gatestr.split("\\.")[1];
    }

    boolean setBooltoPins(ArrayList<String> pinList, Boolean _bull){
      for(String pinStr: pinList ){
          setBool(_GH.MGS[ _GH.fgiByID( strToID(pinStr) )], strToPin(pinStr),_bull );
      }
      return true;
    }

    void setSecondaryTriggers(int gatind, String pin_ , Boolean _bool){
      switch(pin_){
        case("0"):
          setBooltoPins(_GH.MGS[gatind].pin0, _bool );
          break;
          case("1"):
            setBooltoPins(_GH.MGS[gatind].pin1, _bool );
            break;
            case("2"):
              setBooltoPins(_GH.MGS[gatind].pin2, _bool );
              break;
            default:
              System.out.println("Cannot set secondary triggers");
              break;
      }
    }

    boolean foutCheck(){
      for(String foutstr: _GH.foutPins){
        if( _GH.MGS[_GH.fgiByID( strToID(foutstr) )].output == null )
          return true;
      }
      return false;
    }

    void addOutput_to_TT(){
      for(String out : _GH.foutPins){
        truthTable.get(out).add(_GH.MGS[_GH.fgiByID( strToID(out) )].output );

      }
    }


  //_____________________________________________________________________________________

    void process(){

      System.out.println("Initiating Truth table");
      if( initTT() )
        System.out.println("Truth Table input initiated ");

        for( int k=0;k<total_combs;k++ ){ //loops for each input boolean combination
          _GH.resetGates();

          //setting triggers
          for(String inp : _GH.triggerPins){ //inp is like "A.1"
            // String[] _prt = inp.split("\\.");//prt[0] = gateID(A), prt[1]=pin(1)

            Gate _currGate = _GH.MGS[_GH.fgiByID( strToID(inp)) ];
            Boolean tempbull = truthTable.get(inp).get(k);
            _GH.MGS[_GH.fgiByID( strToID(inp))] = setBool(_currGate, strToPin(inp), tempbull);
            setSecondaryTriggers(_GH.fgiByID( strToID(inp) ), strToPin(inp), tempbull);
          } //trigger pins set

          //computing logic for current input combination
          while( foutCheck() ){
            System.out.println("looping cuz froutcheck is true");
            for(int n = 0;n<=_GH.MGStackPtr; n++){
              _GH.MGS[n].compute();
              setBooltoPins(_GH.MGS[n].pin2, _GH.MGS[n].output); //No NOT exception required
            }
            //printing status of GateHolder

          }
          addOutput_to_TT(); //adding output of current input combination to TT
        } //current input combination completes computing output after each loop

        TruthTeller ttelinst = new TruthTeller();
      ttelinst.tellTruth(truthTable);
    } //end of process method
} //end of processor class
