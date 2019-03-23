package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.cargointake.CargoIntake;
import frc.robot.robotcore.userinput.OI;

public class TrimCargoArm extends Command{
    private CargoIntake cargoIntake = CargoIntake.getInstance();
    private final double POWER = 0.3;

    public TrimCargoArm(){
        setInterruptible(true);
        requires(cargoIntake);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        //cargoIntake.moveIntakeArm(-0.8 * Math.signum(Math.pow(OI.getInstance().driver1.getRightJoystickY(), 2)));
        cargoIntake.moveIntakeArm(-0.8 * Math.signum(OI.getInstance().driver1.getRightJoystickY()) * Math.pow(OI.getInstance().driver1.getRightJoystickY(), 2));
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        cargoIntake.kill();
    }
    @Override
    protected void end() {
        super.end();
        cargoIntake.moveIntakeArm(0);
    }
}