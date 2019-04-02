package frc.robot.panelclaw.pegcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Peg;

public class ActuatePeg{
    private Peg peg = Peg.getInstance();

    public ActuatePeg(){

    }

    public class SetPeg extends InstantCommand{
        private Peg.PegState pegPosition;

        public SetPeg(Peg.PegState pegPosition){
            setInterruptible(true);
            requires(peg);
            this.pegPosition = pegPosition;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            peg.setPeg(pegPosition);
        }
        @Override
        protected void end() {
            super.end();
        }
    }

    public class TogglePeg extends InstantCommand{
        private Peg.PegState pegPosition;

        public TogglePeg(){
            setInterruptible(true);
            requires(peg);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            this.pegPosition = peg.togglePeg();
            peg.setPeg(pegPosition);
        }
        @Override
        protected void end() {
            super.end();
        }
    }

}