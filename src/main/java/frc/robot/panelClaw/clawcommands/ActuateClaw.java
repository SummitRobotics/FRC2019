package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Claw.ClawState;

public class ActuateClaw{
    private Claw claw = Claw.getInstance();

    public ActuateClaw(){

    }

    public class SetClaw extends InstantCommand{

        private ClawState clawPosition;

        public SetClaw(ClawState clawPosition){
            requires(claw);
            this.clawPosition = clawPosition;
        }
        @Override
        protected void initialize() {

        }
        @Override
        protected void execute() {
            //claw.claw.set(clawPosition.value);
            claw.setClaw(clawPosition);
            SmartDashboard.putString("Claw Value", Claw.getInstance().getClawState().toString());
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleClaw extends InstantCommand{
        private Claw.ClawState clawPos;
    
        public ToggleClaw(){
            requires(claw);
        }
    
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            clawPos = claw.toggleClaw();
            claw.setClaw(clawPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
}