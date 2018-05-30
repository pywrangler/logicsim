package logicsim;

public class XORgate extends Gate{


  void compute(){
    if(inp1 != null && inp2 != null && output == null){
      System.out.println("Computing Gate: "+this.gateid);
      output = (inp1 ^ inp2);
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
