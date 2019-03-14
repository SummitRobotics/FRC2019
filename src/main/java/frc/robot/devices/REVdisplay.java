package frc.robot.devices; 

//import edu.wpi.first.wpilibj.RobotController;

public class REVdisplay{
    /*private REVDigitBoardDriver revBoard = new REVDigitBoardDriver();
    private double battVolt;
    private final String teamNum = "5468";
    private double psi;
    private boolean buttonStatusA, previousStateA, currentStateA;
    private boolean buttonStatusB, previousStateB, currentStateB;

    private static REVdisplay instance;
    private REVdisplay(){
        
    }
    public static REVdisplay getInstance(){
        if(instance == null){
            instance = new REVdisplay();
        }
        return instance;
    }

    public void init(){
        revBoard.clear();
    }

    public void disable(){
        revBoard.clear();
    }

    public void run(){
        battVolt = RobotController.getBatteryVoltage();
        psi = PressureSensor.getInstance().getPressure();

        if(!toggleA()){
            revBoard.display(teamNum);
        }
        else if(!toggleB()){
            revBoard.display(psi);
        }
        else{
            revBoard.display(battVolt);
        }

    }

    //TODO - Proper Toggle
    public boolean toggleA(){
        previousStateA = currentStateA;
        currentStateA = revBoard.getButtonA();

        if(currentStateA && !previousStateA){
            buttonStatusA = !buttonStatusA;
        }
        return buttonStatusA;
    }

    public boolean toggleB(){
        previousStateB = currentStateB;
        currentStateB = revBoard.getButtonB();

        if(currentStateB && !previousStateB){
            buttonStatusB = !buttonStatusB;
        }
        return buttonStatusB;
    }*/

}