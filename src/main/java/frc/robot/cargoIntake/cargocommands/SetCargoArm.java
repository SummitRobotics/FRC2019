package frc.robot.cargointake.cargocommands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.CargoIntake;

public class SetCargoArm extends Command{
    private CargoIntake cargoIntake = CargoIntake.getInstance();
    private CargoIntake.IntakeArmState armPos;
    boolean isDone;

    public SetCargoArm(CargoIntake.IntakeArmState armPos){
        requires(cargoIntake);
        this.armPos = armPos;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        isDone = cargoIntake.setIntakeArm(armPos);
    }
    @Override
    protected boolean isFinished() {
        return isDone;
    }
    @Override
    protected void end() {
        super.end();
        cargoIntake.moveIntakeArm(0);
    }
}