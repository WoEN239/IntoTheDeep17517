package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.util.FieldUtil;
import com.noahbres.meepmeep.roadrunner.Constraints;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveTrainType;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MyMeepMeep {
    public static void main(String[] args) {
        FieldUtil.Companion.setFIELD_HEIGHT(3658);
        FieldUtil.Companion.setFIELD_WIDTH(3658);

        MeepMeep meepMeep = new MeepMeep(600);
        meepMeep.setAxesInterval(1000);

        RoadRunnerBotEntity myBot = new RoadRunnerBotEntity(meepMeep,
                new Constraints(600,600,3.14,3.14,300),
                300,300,
                new Pose2d(-1650,1710,0),
                meepMeep.getColorManager().getTheme(),1, DriveTrainType.MECANUM,false
        );

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-60, 60, 0))
                .splineTo(new Pose2d(0, 0, Math.toRadians(90)).component1(), 0)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
    //
}