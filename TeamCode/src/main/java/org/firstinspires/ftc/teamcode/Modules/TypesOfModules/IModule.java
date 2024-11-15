package org.firstinspires.ftc.teamcode.Modules.TypesOfModules;

import org.firstinspires.ftc.teamcode.Robot;

public interface IModule {
    void init(Robot robot);
    default void read(){}
    default void update(){}


}
