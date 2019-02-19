package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.Intake;
import frc.robot.cargointake.Intake.IntakeSpinState;

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
        protected void end() {
            super.end();
        }
    }
}