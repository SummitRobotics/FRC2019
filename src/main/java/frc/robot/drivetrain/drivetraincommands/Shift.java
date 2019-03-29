package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.drivetrain.Drivetrain;

public class Shift{

    private Drivetrain drivetrain = Drivetrain.getInstance();

    public Shift(){

    }

    public class SetShift extends InstantCommand {
        private Drivetrain.GearState gearPos;
        
        public SetShift(Drivetrain.GearState gearPos) {
            requires(drivetrain);
            this.gearPos = gearPos;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            drivetrain.shiftGear(gearPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleShift extends InstantCommand {
        private Drivetrain.GearState gearPos;

        public ToggleShift(){
            requires(drivetrain);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            gearPos = drivetrain.toggleGear();
            drivetrain.shiftGear(gearPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
}