package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.robotcore.userinput.OI;

public class CloseLoopArcadeDrive extends Command{
    
    Drivetrain drivetrain = Drivetrain.getInstance();
    OI gamepad = OI.getInstance();

    public CloseLoopArcadeDrive(){
        setInterruptible(true);
        requires(drivetrain);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        double[] inputs = getOutputs(gamepad.fwdDrive(), gamepad.turnDrive());
        //drivetrain.leftClosedLoop(inputs[0]);
        //drivetrain.rightClosedLoop(inputs[1]);
    }
    protected double[] getOutputs(double xSpeed, double zRotation){
        //where array[0] is left, and array[1] is right.
        double[] outputArr = new double[1];
        double leftMotorOutput;
        double rightMotorOutput;
    
        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
    
        //Credit where it's due, I copied this from WPI DifferentialDrive
        if (xSpeed >= 0.0) {
          // First quadrant, else second quadrant
          if (zRotation >= 0.0) {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
          } else {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
          }
        } else {
          // Third quadrant, else fourth quadrant
          if (zRotation >= 0.0) {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
          } else {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
          }
        }
        
        outputArr[0] = leftMotorOutput;
        outputArr[1] = rightMotorOutput;
        return outputArr;
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
}