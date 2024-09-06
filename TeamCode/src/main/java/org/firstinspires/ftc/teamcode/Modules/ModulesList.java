package org.firstinspires.ftc.teamcode.Modules;


public class ModulesList {
    public Lift lift = new Lift();
    public DriveTrain driveTrain= new DriveTrain();
    protected IModule modules[] = new IModule[]{
            lift,driveTrain
    };

}