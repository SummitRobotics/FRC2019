package frc.robot.cargointake.cargocommands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
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
        System.out.println("Set Cargo Arm Command Started at" + Timer.getFPGATimestamp());
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
        System.out.println("Set Cargo Arm Command Finished at" + Timer.getFPGATimestamp());
        super.end();
    }
}