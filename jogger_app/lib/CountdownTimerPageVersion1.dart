import 'package:flutter/material.dart';
import 'package:quiver/async.dart';
import 'dart:async';
import 'package:jogger_app/TrainingSettingsPageVersion1.dart';
import 'package:jogger_app/BluetoothSettingsVersion1.dart';
import 'package:flutter_blue/flutter_blue.dart';

class CountdownTimerPageVersion1 extends StatefulWidget {

  BluetoothSettingsVersion1 bluetooth;

  int minutesRound;
  int secondsRound;
  int minutesPause;
  int secondsPause;
  int numOfRounds;


  CountdownTimerPageVersion1({this.minutesRound, this.secondsRound,
                              this.minutesPause, this.secondsPause,
                              this.numOfRounds, this.bluetooth});

  CountdownTimerPageStateVersion1 createState() => CountdownTimerPageStateVersion1(
      initialMinutesRound: minutesRound, initialSecondsRound: secondsRound,
      initialMinutesPause: minutesPause, initialSecondsPause: secondsPause,
      numOfRounds: numOfRounds, bluetooth: bluetooth);

}

class CountdownTimerPageStateVersion1 extends State<CountdownTimerPageVersion1> with AutomaticKeepAliveClientMixin<CountdownTimerPageVersion1> {

  int initialMinutesRound;
  int initialSecondsRound;
  int initialMinutesPause;
  int initialSecondsPause;
  int initialNumOfRounds;

  int numOfRounds;
  int currentMinutes;
  int currentSeconds;

  bool begin = true;
  bool paused = false;
  bool stopped = false;
  bool isRoundPhase = true;
  String phaseName = 'ein Satz';
  String startOrPauseOrContinue = 'Training starten';

  String remainingTime;
  CountdownTimer timer;

  BluetoothSettingsVersion1 bluetooth;


  CountdownTimerPageStateVersion1({this.initialMinutesRound, this.initialSecondsRound,
                                   this.initialMinutesPause, this.initialSecondsPause, this.numOfRounds, this.bluetooth}) {
    currentMinutes = initialMinutesRound;
    currentSeconds = initialSecondsRound;
    initialNumOfRounds = numOfRounds;
    remainingTime = '$currentMinutes:$currentSeconds';
  }


  @override
  bool get wantKeepAlive => true;


  String switchPhaseName() {
    if(isRoundPhase) phaseName = 'ein Satz';
    else phaseName = 'eine Pause';
  }

  void switchPauseContinue() {
    if(paused) startOrPauseOrContinue = 'Training fortsetzen';
    else startOrPauseOrContinue = 'Training pausieren';
  }

  void startTimer() {
    if (stopped) return;

    timer = CountdownTimer(Duration(minutes: paused ? currentMinutes: isRoundPhase ? initialMinutesRound: initialMinutesPause,
        seconds: paused ? currentSeconds: isRoundPhase ? initialSecondsRound: initialSecondsPause),
        Duration(milliseconds: 100));
    paused = false;
    timer.listen((CountdownTimer t) {
      setState(() {
        Duration duration = timer.remaining;
        remainingTime = ('${duration.inMinutes}:${duration.inSeconds % 60}');
      });
    }, onDone: () {

      if(numOfRounds == 1 && !paused && !stopped && bluetooth != null && bluetooth.deviceState == BluetoothDeviceState.connected) {
        bluetooth.notifyByVibration(1, 0, 3000, [0x00, 0x00, 0xFF, 0x00]);
      }

      if(numOfRounds > 1 && !paused && !stopped) {
        //setState(() {
        if(!isRoundPhase) {
          --numOfRounds;
          //print('Round $numOfRounds is starting, phase ${!isRoundPhase}');
        }

        //if(blueDevice == null) print('BluetoothDevice in MyCountdownTimer is null\n');
        //if(blueCharacteristic == null) print('BluetoothCharacteristic in MyCountdownTimer is null\n');

        //Wenn Bluetooth-Gerät angeschlossen ist, führe Vibrationsbenachrichtigungen aus
        if(bluetooth != null && bluetooth.deviceState == BluetoothDeviceState.connected) {
          //print('blueDevice != null && blueCharacteristic != null is true');
          if(isRoundPhase) {
            bluetooth.notifyByVibration(1, 0, 1000, [0x00, 0x00, 0xFF, 0x00]);
          }
          else {
            bluetooth.notifyByVibration(initialNumOfRounds - numOfRounds + 1, 150, 200, [0xFF, 0x00, 0x00, 0x00]);
          }
        }

        isRoundPhase = !isRoundPhase;
        switchPhaseName();
        startTimer();
        //});
      }
    });
  }


  void pauseTimer() {
    if (stopped) return;

    if (timer != null) {
      paused = true;
      timer.cancel();
      Duration duration = timer.remaining;
      currentMinutes = duration.inMinutes;
      currentSeconds = duration.inSeconds % 60;
    }
  }

  void stopTimer() {
    if(stopped) return;

    if (timer != null) {
      stopped = true;
      timer.cancel();
      currentMinutes = 0;
      currentSeconds = 0;
      //setState(() {
      //  remainingTime = 'gestoppt';
      //});
    }
  }


  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView (
        padding: EdgeInsets.fromLTRB(20, 20, 20, 20),
        //mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Container(
            padding: EdgeInsets.fromLTRB(0, 0, 0, 120),
          ),
          Text(
            remainingTime,
            textAlign: TextAlign.center,
            style: TextStyle(
                fontSize: 90,
                color: Colors.blueGrey
            ),
          ),
          Text(
            'Min : Sek',
            textAlign: TextAlign.center,
            style: TextStyle(
                fontSize: 25,
                color: Colors.blueGrey
            ),
          ),
          Container(
            //color: Colors.black12,
            padding: EdgeInsets.fromLTRB(10, 10, 10, 10),
            child: Text(
              'Runde ${initialNumOfRounds - numOfRounds + 1} von $initialNumOfRounds',
              textAlign: TextAlign.center,
              style: TextStyle(
                  color: Colors.blueGrey,
                  fontSize: 40
              ),
            ),
          ),
          Container(
            //color: Colors.black12,
            padding: EdgeInsets.fromLTRB(10, 10, 10, 10),
            child: Text(
              'Jetzt läuft $phaseName',
              textAlign: TextAlign.center,
              style: TextStyle(
                  color: Colors.blueGrey,
                  fontSize: 20
              ),
            ),
          ),
          Container(
            padding: EdgeInsets.fromLTRB(0, 20, 0, 0),
          ),
          Container(
            height: 60,
            padding: EdgeInsets.fromLTRB(35,0, 35, 0),
            child: RaisedButton(
                color: Colors.blueGrey,
                child: Text(
                  startOrPauseOrContinue,
                  style: TextStyle(
                      color: Colors.white,
                      fontSize: 18
                  ),
                ),
                onPressed: () {
                  if(begin || paused) {
                    begin = false;
                    setState(() {
                      startOrPauseOrContinue = "Training pausieren";
                    });
                    startTimer();
                  }
                  else {
                    setState(() {
                      startOrPauseOrContinue = "Training fortsetzen";
                    });
                    pauseTimer();
                  }
                }
            ),
          ),
          Container(
            height: 100,
          ),
          Container(
            height: 60,
            //width: ,
            padding: EdgeInsets.fromLTRB(35,0, 35, 0),
            child: RaisedButton(
                color: Colors.redAccent,
                child: Text(
                  'Training beenden',
                  style: TextStyle(
                      color: Colors.white,
                      fontSize: 18
                  ),
                ),
                onPressed: () {
                  stopTimer();
                  Navigator.pop(context,[bluetooth]);
                }
            ),
          ),
        ],
      ),
    );
  }



}
