package org.firstinspires.ftc.teamcode.Modules.TypesOfModules;

/*
  Writing by EgorKhvostikov
*/
public interface IModule {
    void init();
    default void read(){}
    default void update(){}


}
