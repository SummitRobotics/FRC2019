package frc.robot.panelclaw.pegcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.Peg.PneumaticState;

public class BopIt{
    private Peg peg = Peg.getInstance();

    public BopIt(){

    }

    public class SetBop extends InstantCommand{
        private Peg.PneumaticState bopPos;

        public SetBop(Peg.PneumaticState bopPos){
            setInterruptible(true);
            requires(peg);
            this.bopPos = bopPos;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            peg.setBop(bopPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleBop extends InstantCommand{
        private Peg.PneumaticState bopPos;
        
        public ToggleBop(){
            setInterruptible(true);
            requires(peg);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            this.bopPos = peg.toggleBop();
            peg.setBop(bopPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
}