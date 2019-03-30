package frc.robot.cargointake.cargocommands;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;

public class SetCargoArm extends Command{
    private CargoIntake cargoIntake = CargoIntake.getInstance();
    private CargoIntake.IntakeArmState armPos;
    boolean isDone = false;

    public SetCargoArm(CargoIntake.IntakeArmState armPos){
        setInterruptible(true);
        requires(cargoIntake);

        this.armPos = armPos;
    }
    @Override
    protected void initialize() {
        super.initialize();
        SmartDashboard.putBoolean("Cargo arm done", false);
    }
    @Override
    protected void execute() {
        isDone = cargoIntake.setIntakeArm(armPos.value);
    }
    
    @Override
    protected boolean isFinished() {
        return isDone;
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        cargoIntake.kill();
    }
    @Override
    protected void end() {
        super.end();
        SmartDashboard.putBoolean("Cargo arm done", true);
    }
}