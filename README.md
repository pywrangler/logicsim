# logicsim
A combinatorial circuit simulation engine.
Note: When I came up with the name logicsim there were already hundreds of other repos on github with the same name but I only came to realise this much later and it was way too late to change the name.So be aware that this is not a fork of any other repository.

The idea was to create a circuit simulator engine with high level abstractions for a circuit simulator app on android.
logicsim implements logic gates such as AND,OR,NOT,XOR,OR,XNOR,NAND. These gates can be connected with each other and the truth table generated automatically for each and every combination of inputs (1's and 0's).  Circuits such as half adder,full adder,subtractor,multiplexers,demultiplexers can be built and tested.

To allow users to test the engine before building an UI, a console interface is also present.

To run logicim (requires JVM) :
 1. Download and extract the repo.
  2. Enter the main folder where you will see the file "run.sh".
((for linux debian))
 You may double click the file "run.sh" and then select "run in terminal".If that does not work follow the steps .
    >Open terminal where the file "run.sh" is located or cd to this directory.
    >Type "./run.sh"

((For Windows))
Open CMD and navigate to the extracted folder. Note: There are also subfolders with the same name logicsim, do not enter into these.
From here execute the command "java logicsim/logicsim"

To build a circuit:
 1. adds a new gate
choose the logic type
enter a gate ID i.e. a name for the gate  say "A"

now you can refer to its pins as A/0 (first input of the gate) A/1 (second input) A/2(output pin of the gate)

2. Connect pins 
 Lets say you want to connect a wire between the first input of A (A/0) to its second input (A/1)
 After choosing connect pins it will prompt you to enter the connection string
, for this enter  " A/0-A/1 " (without quotes). syntax: (gate ID)/(pin no.)-(gate ID)/(pin no.) . note that there is hyphen in between the two (gate ID)/(pin no.).
 3. Lets you see the connections you have made

 4. Set input pins. Whenever testing a combinatorial circuit on a breadboard we manually flip switches to control the inputs. Here the software performs the test with all possible compbination of inputs and prints the corresponding outputs in a truth table. 
This option lets you choose which pins are going to take the inputs.

5. Choose the pins from where you will read the outputs.

6. Generates and displays the truth table. The columns will have the names of the pins as (gate ID).(pin no.)





