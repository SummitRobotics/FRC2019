package frc.robot.panelclaw.pegcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Peg;

public class ActuateChair{
    private Peg peg = Peg.getInstance();

    public ActuateChair(){
        
    }

public class SetChair extends InstantCommand{
    private Peg.ChairState chairPos;

        public SetChair(Peg.ChairState chairPos){
            requires(peg);
            this.chairPos = chairPos;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            peg.setChair(chairPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleChair extends InstantCommand{
        private Peg.ChairState panelPosition;
        
        public ToggleChair(){
            requires(peg);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            this.panelPosition = peg.toggleChair();
            peg.setChair(panelPosition);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
}