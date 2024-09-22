package org.firstinspires.ftc.teamcode.Modules;
import org.firstinspires.ftc.teamcode.Robot;

public interface IModule {
    default void update(){}
    default void init(Robot robot){}

}
