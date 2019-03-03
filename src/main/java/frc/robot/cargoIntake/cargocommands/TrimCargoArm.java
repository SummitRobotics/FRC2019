package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.cargointake.CargoIntake;

public class TrimCargoArm extends Command{
    private CargoIntake cargoIntake = CargoIntake.getInstance();
    private final double POWER = 0.3;

    public TrimCargoArm(){
        requires(cargoIntake);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        if(Robot.gamepad.isDpadUp()){
            cargoIntake.moveIntakeArm(POWER);
        }
        else if(Robot.gamepad.isDpadDown()){
            cargoIntake.moveIntakeArm(-POWER);
        }
        else{
            cargoIntake.moveIntakeArm(0);
        }
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
        cargoIntake.moveIntakeArm(0);
    }
}