package frc.robot.robotcore.userinput;

import edu.wpi.first.wpilibj.buttons.Button;

public class ButtonDashboard{

    private ButtonDashboardDriver dashboard;
    
    protected Button mastHigh = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastHigh();
        }
    };

    protected Button mastMid = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastMid();
        }
    };

    protected Button mastLow = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastLow();
        }
    };
    
    protected Button cargoGround = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getCargoGround();
        }
    };

    protected Button panelGround = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getPanelGround();
        }
    };

    protected Button bop = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getBop();
        }
    };

    protected Button climb = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getClimb();
        }
    };

    protected Button pos1 = new Button(){

        @Override
        public boolean get() {
            return dashboard.getPos1();
        }
    };

    protected Button pos2 = new Button(){

        @Override
        public boolean get() {
            return dashboard.getPos2();
        }
    };

    protected Button pos3 = new Button(){

        @Override
        public boolean get() {
            return dashboard.getPos3();
        }
    };

    protected Button pos4 = new Button(){

        @Override
        public boolean get() {
            return dashboard.getPos4();
        }
    };

    protected Button sideAndHeight = new Button() {

        @Override
        public boolean get() {
            return dashboard.getSideAndHeight();
        }
    };

    public ButtonDashboard(){
        dashboard = new ButtonDashboardDriver();
    }
}