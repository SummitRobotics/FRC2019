package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.drivetrain.Drivetrain;

public class Shift extends InstantCommand{

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Drivetrain.GearState gearValue;
    private boolean toggle = false;

    public Shift(Drivetrain.GearState gearValue){
        requires(drivetrain);
        setInterruptible(false);
        this.gearValue = gearValue;
    }
    public Shift(){
        requires(drivetrain);
        toggle = true;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        if(toggle){
            this.gearValue = drivetrain.toggleGear();
        }
        drivetrain.shiftGear(gearValue);
    }
    @Override
    protected void end() {
        super.end();
    }

    public class ToggleShift extends InstantCommand{
        private Drivetrain.GearState gearValue;
        public ToggleShift(){
            requires(drivetrain);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            this.gearValue = drivetrain.toggleGear();
            drivetrain.shiftGear(gearValue);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
}