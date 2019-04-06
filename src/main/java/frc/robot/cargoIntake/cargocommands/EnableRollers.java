package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;

public class EnableRollers{
    private CargoIntake cargoIntake = CargoIntake.getInstance();
    
    public EnableRollers(){

    }

    public class IntakeForTime extends Command{
        private CargoIntake.RollerState rollerSpin;

        public IntakeForTime(CargoIntake.RollerState rollerSpin, double timeout){
            setInterruptible(true);
            requires(cargoIntake);
            setTimeout(timeout);

            this.rollerSpin = rollerSpin;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            cargoIntake.setRollers(rollerSpin);
            SmartDashboard.putNumber("We got to this spot", 1);

        }
        @Override
        protected boolean isFinished() {
            return isTimedOut();
        }
        @Override
        protected void interrupted() {
            cargoIntake.kill();
            super.interrupted();
        }    
        @Override
        protected void end() {
            super.end();
            cargoIntake.setRollers(CargoIntake.RollerState.OFF);
        }
    }

    public class SetRollers extends InstantCommand{
        private CargoIntake.RollerState rollerSpin;

        public SetRollers(CargoIntake.RollerState rollerSpin){
            requires(cargoIntake);
            this.rollerSpin = rollerSpin;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            cargoIntake.setRollers(rollerSpin);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleRollers extends InstantCommand{

        private CargoIntake.RollerState rollerSpin;
    
        public ToggleRollers(){
            setInterruptible(true);
            requires(cargoIntake);
        }
    
        @Override
        protected void initialize() {
        }
        @Override
        protected void execute() {
            this.rollerSpin = cargoIntake.toggleRollers();
            cargoIntake.setRollers(rollerSpin);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleEjectionRollers extends Command{
        private CargoIntake.RollerState rollerSpin;

        public ToggleEjectionRollers(){
            setInterruptible(true);
            requires(cargoIntake);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            this.rollerSpin = cargoIntake.toggleRollers();
            cargoIntake.setRollers(CargoIntake.RollerState.ON);
        }
        @Override
        protected boolean isFinished() {
            return false;
        }
        @Override
        protected void end() {
            cargoIntake.setRollers(CargoIntake.RollerState.OFF);
        }

    }
}