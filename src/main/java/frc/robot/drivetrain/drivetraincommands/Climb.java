package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.Drivetrain.PTOState;

public class Climb extends Command{
    private Drivetrain drivetrain = Drivetrain.getInstance();

    public Climb(){
        requires(drivetrain);
        setInterruptible(true);
    }
    @Override
    protected void initialize() {
        super.initialize();
        if(!(Timer.getMatchTime() < 30)){
            cancel();
        }
        else{
            //drivetrain.setPTO(PTOState.ENGAGED);
        }
    }
    @Override
    protected void execute() {
        if(!(Timer.getMatchTime() < 30)){
            cancel();
        }
        else{
            drivetrain.robotDrive.tankDrive(1, 1);
        }
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