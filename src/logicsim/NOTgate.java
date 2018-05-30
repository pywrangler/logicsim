package logicsim;

public class NOTgate extends Gate{


  void compute(){
    pin2 = pin1;
    System.out.println("checking weather to compute ");
    if(inp1 != null && output == null){
      System.out.println("Computing Gate: "+this.gateid);
      System.out.println("computing as output is null");
      output = !inp1;
    }
    else if(inp1 == null){
      System.out.println("inp1 is null,cant compute ID: "+this.gateid);
    }
    else if(inp2 == null){
      System.out.println("inp2 is null,cant compute ID: "+this.gateid);
    }
    else if(output != null){
      System.out.println("already computed, ID: "+this.gateid);
    }
  }
}
