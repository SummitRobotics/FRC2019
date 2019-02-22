package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.Intake;
import frc.robot.cargointake.Intake.IntakeSpinState;
import frc.robot.robotcore.OI;

public class ActuateIntake{
    private Intake intake = Intake.getInstance();
    
    public ActuateIntake(){

    }

    public class SetIntake extends Command{
        private IntakeSpinState intakeSpin;
        private boolean timeoutFlag = false;

        public SetIntake(IntakeSpinState intakeSpin, double timeout){
            requires(intake);
            setTimeout(timeout);
            this.timeoutFlag = true;
            this.intakeSpin = intakeSpin;
        }
        public SetIntake(IntakeSpinState intakeSpin){
            requires(intake);
            this.intakeSpin = intakeSpin;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            intake.setIntakeSpin(intakeSpin);
        }
        @Override
        protected boolean isFinished() {
            if(timeoutFlag){
                return isTimedOut();
            }
            else{
                return false;
            }
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleIntake extends InstantCommand{

        private Intake.IntakeSpinState intakeSpin;
    
        public ToggleIntake(){
            requires(intake);
            setInterruptible(true);
        }
    
        @Override
        protected void initialize() {
        }
        @Override
        protected void execute() {
            this.intakeSpin = intake.toggleIntakeSpin();
            intake.setIntakeSpin(intakeSpin);
        }
        @Override
        protected void interrupted() {
            super.interrupted();
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class IntakeBall extends Command{
        private Intake.IntakeSpinState intakeSpin;

        public IntakeBall(){
            requires(intake);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            intakeSpin = Intake.IntakeSpinState.ON;
            intake.setIntakeSpin(intakeSpin);

            double POWER = 0.20;
            if(OI.getInstance().isDpadUp()){
                intake.moveIntakeArm(POWER * 1.10);
            }
            else if(OI.getInstance().isDpadDown()){
                intake.moveIntakeArm(-POWER);
            }
            else{
                intake.moveIntakeArm(0);
            }
        }
        
        @Override
        protected boolean isFinished() {
            return intake.isBallPresent();
        }
        @Override
        protected void end() {
            intake.setIntakeSpin(Intake.IntakeSpinState.OFF);
        }
    }
}