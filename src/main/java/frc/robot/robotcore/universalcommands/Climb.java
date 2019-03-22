package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.cargointake.CargoIntake;
import frc.robot.drivetrain.Drivetrain;
=
public class Climb extends PIDCommand{
    private static final double
    P = 1,
    I = 0,
    D = 0;

    private CargoIntake cargoIntake = CargoIntake.getInstance();
    private Drivetrain drivetrain = Drivetrain.getInstance();

    public Climb(){
        super(P, I, D, CargoIntake.getInstance());
        setInterruptible(true);
    }
    @Override
    protected void initialize() {
        super.initialize();
        if(!(Timer.getMatchTime() < 30)){
            cancel();
        }
        else{

        }
    }
    @Override
    protected void execute() {

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