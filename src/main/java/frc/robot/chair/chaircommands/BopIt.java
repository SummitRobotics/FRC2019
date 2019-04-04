package frc.robot.chair.chaircommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.chair.Chair;

public class BopIt{
    private Chair chair = Chair.getInstance();
    
    public BopIt(){

    }

    public class SetBop extends InstantCommand{
        private Chair.PneumaticState bopPos;

        public SetBop(Chair.PneumaticState bopPos){
            setInterruptible(true);
            requires(chair);
            this.bopPos = bopPos;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            chair.setBop(bopPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleBop extends InstantCommand{
        private Chair.PneumaticState bopPos;
        
        public ToggleBop(){
            setInterruptible(true);
            requires(chair);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            this.bopPos = chair.toggleBop();
            chair.setBop(bopPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
}