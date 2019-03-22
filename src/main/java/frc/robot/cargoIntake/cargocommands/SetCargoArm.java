package frc.robot.cargointake.cargocommands;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;

public class SetCargoArm extends InstantCommand{
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
    }
    @Override
    protected void execute() {
        isDone = cargoIntake.setIntakeArm(armPos.value);
        SmartDashboard.putBoolean("Command Finished", isDone);

    }
    
    @Override
    protected void end() {
        super.end();
        SmartDashboard.putBoolean("Is At Position", true);
    }
}