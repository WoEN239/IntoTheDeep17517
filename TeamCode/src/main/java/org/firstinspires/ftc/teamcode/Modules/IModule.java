package org.firstinspires.ftc.teamcode.Modules;
import org.firstinspires.ftc.teamcode.Robot;
public interface IModule {
    void init(Robot robot);
    default void read(){}
    default void update(){}


}
