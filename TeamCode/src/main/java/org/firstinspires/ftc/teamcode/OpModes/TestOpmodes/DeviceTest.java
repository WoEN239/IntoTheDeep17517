package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.teamcode.Devices.FixColorSensor.fix;
import static org.firstinspires.ftc.teamcode.OpModes.TestOpmodes.DeviceTest.DeviceType.ANALOG_INPUT;
import static org.firstinspires.ftc.teamcode.OpModes.TestOpmodes.DeviceTest.DeviceType.BATTERY_VOLTAGE;
import static org.firstinspires.ftc.teamcode.OpModes.TestOpmodes.DeviceTest.DeviceType.DISTANCE_SENSOR;
import static org.firstinspires.ftc.teamcode.OpModes.TestOpmodes.DeviceTest.DeviceType.NONE;
import static org.firstinspires.ftc.teamcode.OpModes.TestOpmodes.DeviceTest.DeviceType.SERVO;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import java.util.Arrays;


/**
 * @author oaleksander
 * <p>
 * Тестер для Hardware девайсов.
 * Поддерживает устройства, указаннные в
 * @see DeviceType
 * Необходимо указать через Dashboard deviceName название девайса из конфига
 * и double sendValue если это мотор или сервак для отправки значения.
 */

@Config
@TeleOp
//@Disabled
public class DeviceTest extends LinearOpMode {

    public static String deviceName = "";

    public enum DeviceType {
        DC_MOTOR, DIGITAL_CHANNEL, ANALOG_INPUT, SERVO, GYRO, BATTERY_VOLTAGE, COLOR_SENSOR, DISTANCE_SENSOR, NONE
    }

    public static DeviceType deviceType = NONE;

    private DeviceType getDeviceClass(HardwareDevice hardwareDevice) {
        if (hardwareDevice instanceof DcMotorEx) return DeviceType.DC_MOTOR;
        if (hardwareDevice instanceof DigitalChannel) return DeviceType.DIGITAL_CHANNEL;
        if (hardwareDevice instanceof VoltageSensor) return BATTERY_VOLTAGE;
        if (hardwareDevice instanceof AnalogInput) return ANALOG_INPUT;
        if (hardwareDevice instanceof Servo) return SERVO;
        if (hardwareDevice instanceof IMU) return DeviceType.GYRO;
        if (hardwareDevice instanceof ColorSensor) return DeviceType.COLOR_SENSOR;
        if (hardwareDevice instanceof DistanceSensor) return DISTANCE_SENSOR;
        return NONE;
    }

    public static boolean sendValue = true;
    public static double valueToSend = 0;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardwareMap.getAll(IMU.class).forEach(imu -> imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(new Quaternion()))));
        hardwareMap.getAll(DcMotor.class).forEach(m -> {
            m.setDirection(DcMotorSimple.Direction.FORWARD);
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        });
        hardwareMap.getAll(PwmControl.class).forEach(PwmControl::setPwmDisable);
        //OpModeManagerImpl.getOpModeManagerOfActivity(AppUtil.getInstance().getActivity()).startActiveOpMode();
        telemetry.addLine("All devices:");
        telemetry.addLine(Arrays.toString(hardwareMap.getAllNames(HardwareDevice.class).toArray()));
        telemetry.update();
        waitForStart();
        telemetry.update();
        while (opModeIsActive()) {
            try {
                HardwareDevice hardwareDevice = hardwareMap.get(deviceName);
                deviceType = getDeviceClass(hardwareDevice);
                telemetry.addData("Device type", deviceType);
                telemetry.addLine(hardwareDevice.getConnectionInfo());
                telemetry.addData("send value?", sendValue);
                switch (deviceType) {
                    case DC_MOTOR:
                        DcMotorEx motor = (DcMotorEx) hardwareDevice;
                        motor.setDirection(DcMotorSimple.Direction.FORWARD);
                        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                        telemetry.addData("encoder pos", motor.getCurrentPosition());
                        telemetry.addData("encoder vel", motor.getVelocity());
                        telemetry.addData("motor current", motor.getCurrent(CurrentUnit.AMPS));
                        motor.setPower(valueToSend);
                        break;
                    case SERVO:
                        Servo servo = (Servo) hardwareDevice;
                        servo.setPosition(valueToSend);
                        break;
                    case DIGITAL_CHANNEL:
                        DigitalChannel digitalChannel = (DigitalChannel) hardwareDevice;
                        digitalChannel.setMode(DigitalChannel.Mode.INPUT);
                        telemetry.addData("State", digitalChannel.getState());
                        break;
                    case ANALOG_INPUT:
                        AnalogInput analogInput = (AnalogInput) hardwareDevice;
                        telemetry.addData("Voltage", analogInput.getVoltage());
                        break;
                    case GYRO:
                        IMU imu = (IMU) hardwareDevice;
                        YawPitchRollAngles ypra = imu.getRobotYawPitchRollAngles();
                        telemetry.addLine("NOTE: REV IMU orietnation may be off");
                        telemetry.addLine("Angle units are Degrees");
                        telemetry.addData("yaw", ypra.getYaw(DEGREES));
                        telemetry.addData("pitch", ypra.getPitch(DEGREES));
                        telemetry.addData("roll", ypra.getRoll(DEGREES));
                        break;
                    case BATTERY_VOLTAGE:
                        VoltageSensor voltageSensor = (VoltageSensor) hardwareDevice;
                        telemetry.addData("Voltage", voltageSensor.getVoltage());
                        break;
                    case COLOR_SENSOR:
                        ColorSensor colorSensor = fix((AdafruitI2cColorSensor) hardwareDevice);
                        telemetry.addData("red", colorSensor.red());
                        telemetry.addData("green", colorSensor.green());
                        telemetry.addData("blue", colorSensor.blue());
                        telemetry.addData("alpha", colorSensor.alpha());
                        telemetry.addData("argb code", Integer.toHexString(colorSensor.argb()));
                        break;
                    case DISTANCE_SENSOR:
                        DistanceSensor distanceSensor = (DistanceSensor) hardwareDevice;
                        telemetry.addData("range (cm)", distanceSensor.getDistance(DistanceUnit.CM));
                        break;
                    case NONE:
                    default:
                        break;
                }
                if (sendValue) telemetry.addData("sent value", valueToSend);
            } catch (ClassCastException e) {
                telemetry.addLine("ERR: wrong device type selected");
            } catch (IllegalArgumentException e) {
                telemetry.addLine("Device not found");
            }
            telemetry.update();
        }
    }
}
