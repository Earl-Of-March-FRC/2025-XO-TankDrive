// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
  private final DifferentialDrive drive;
  TalonFX leftBackMotor = new TalonFX(1);
  TalonFX leftFrontMotor = new TalonFX(2);
  TalonFX rightBackMotor = new TalonFX(3);
  TalonFX rightFrontMotor = new TalonFX(4);

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {
    MotorOutputConfigs leftConf = new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake);
    MotorOutputConfigs rightConf = new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake).withInverted(InvertedValue.Clockwise_Positive);
    leftBackMotor.setControl(new Follower(leftFrontMotor.getDeviceID(), false));
    rightBackMotor.setControl(new Follower(rightFrontMotor.getDeviceID(), false));

    leftFrontMotor.getConfigurator().apply(leftConf);
    leftBackMotor.getConfigurator().apply(leftConf);
    rightFrontMotor.getConfigurator().apply(rightConf);
    rightBackMotor.getConfigurator().apply(rightConf);

    leftFrontMotor.setPosition(0);
    rightFrontMotor.setPosition(0);

    // drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
    drive = new DifferentialDrive(leftFrontMotor::set, rightFrontMotor::set);
  }

  public void arcadeDrive(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
