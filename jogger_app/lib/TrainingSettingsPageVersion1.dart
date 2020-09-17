import 'package:flutter/material.dart';
import 'package:jogger_app/CountdownTimerPageVersion1.dart';
import 'package:jogger_app/BluetoothSettingsVersion1.dart';
import 'package:jogger_app/BluetoothSettingsPageVersion1.dart';
import 'package:jogger_app/ErrorInvalidTrainingSettingsPageVersion1.dart';
import 'package:flutter_blue/flutter_blue.dart';
import 'dart:async';


class TrainingSettingsPageVersion1 extends StatefulWidget {

  BluetoothSettingsVersion1 bluetooth;


  TrainingSettingsPageVersion1({this.bluetooth});

  @override
  TrainingSettingsPageStateVersion1 createState() => TrainingSettingsPageStateVersion1(bluetooth:bluetooth);

}


class TrainingSettingsPageStateVersion1 extends State<TrainingSettingsPageVersion1> with AutomaticKeepAliveClientMixin<TrainingSettingsPageVersion1> {

  BluetoothSettingsVersion1 bluetooth;
  String connectedDeviceName = 'kein Gerät';

  int minutesRound = -1;
  int secondsRound = -1;
  int minutesPause = -1;
  int secondsPause = -1;
  int numOfRounds = -1;


  TrainingSettingsPageStateVersion1({this.bluetooth}) {
    if (bluetooth != null) {
      setState(() {
        connectedDeviceName = bluetooth.connectedDeviceName;
      });
    }
  }

  @override
  bool get wantKeepAlive => true;


  void listenOnDeviceStateChanges(BluetoothDevice device) async {
    /*StreamSubscription subscription = */device.onStateChanged().listen((event) {
      setState(() {
        connectedDeviceName = bluetooth.connectedDeviceName;
      });
    });
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        automaticallyImplyLeading: false,//Pfeil 'Zurück' ausschalten
        backgroundColor: Colors.blueGrey,
        title: Text(
          'Trainingsparameter',
          style: TextStyle(
              color: Colors.white),
        ),
      ),
      body: ListView(
          padding:EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 20.0),
          children: <Widget>[
            Text(
              'Dauer einer Runde in Minuten:',
              textAlign: TextAlign.left,
              style: TextStyle(
                color:Colors.black,
              ),
            ),
            TextField(
              cursorColor: Colors.blueGrey,
              decoration: InputDecoration(
                  border: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.black
                      )
                  ),
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.blueGrey,
                          width: 2.0
                      )
                  ),
                  hintText: 'hier eingeben',
                  filled: true,
                  fillColor: Colors.black12,
                  counterStyle: TextStyle(
                      color: Colors.black
                  ),
                  hintStyle: TextStyle(
                      color:Colors.black12
                  )
              ),
              onChanged: (String str) {
                //setState(() {
                  minutesRound = int.tryParse(str);
                //});
              },
            ),
            Container(
              padding: EdgeInsets.fromLTRB(20, 6, 20, 6),
            ),
            Text('Dauer einer Runde in Sekunden:'),
            TextField(
              cursorColor: Colors.blueGrey,
              decoration: InputDecoration(
                  border: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.black
                      )
                  ),
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.blueGrey,
                          width: 2.0
                      )
                  ),
                  hintText: 'hier eingeben',
                  filled: true,
                  fillColor: Colors.black12,
                  counterStyle: TextStyle(
                      color: Colors.black
                  ),
                  hintStyle: TextStyle(
                      color:Colors.black12
                  )
              ),
              onChanged: (String str) {
                //setState(() {
                secondsRound = int.tryParse(str);
                //});
              },
            ),
            Container(
              padding: EdgeInsets.fromLTRB(20, 6, 20, 6),
            ),
            Text('Dauer einer Pause in Minuten:'),
            TextField(
              cursorColor: Colors.blueGrey,
              decoration: InputDecoration(
                  border: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.black
                      )
                  ),
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.blueGrey,
                          width: 2.0
                      )
                  ),
                  hintText: 'hier eingeben',
                  filled: true,
                  fillColor: Colors.black12,
                  counterStyle: TextStyle(
                      color: Colors.black
                  ),
                  hintStyle: TextStyle(
                      color:Colors.black12
                  )
              ),
              onChanged: (String str) {
                //setState(() {
                minutesPause = int.tryParse(str);
                //});
              },
            ),
            Container(
              padding: EdgeInsets.fromLTRB(20, 6, 20, 6),
            ),
            Text('Dauer einer Pause in Sekunden:'),
            TextField(
              cursorColor: Colors.blueGrey,
              decoration: InputDecoration(
                  border: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.black
                      )
                  ),
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.blueGrey,
                          width: 2.0
                      )
                  ),
                  hintText: 'hier eingeben',
                  filled: true,
                  fillColor: Colors.black12,
                  counterStyle: TextStyle(
                      color: Colors.black
                  ),
                  hintStyle: TextStyle(
                      color:Colors.black12
                  )
              ),
              onChanged: (String str) {
                //setState(() {
                secondsPause = int.tryParse(str);
                //});
              },
            ),
            Container(
              padding: EdgeInsets.fromLTRB(20, 6, 20, 6),
            ),
            Text('Anzahl der Runden:'),
            TextField(
              //controller: TextEditingController(text: '1'),
              cursorColor: Colors.blueGrey,
              decoration: InputDecoration(
                  border: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.black
                      )
                  ),
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(
                          color: Colors.blueGrey,
                          width: 2.0
                      )
                  ),
                  hintText: 'hier eingeben',
                  filled: true,
                  fillColor: Colors.black12,
                  counterStyle: TextStyle(
                      color: Colors.black
                  ),
                  hintStyle: TextStyle(
                      color:Colors.black12
                  )
              ),
              onChanged: (String str) {
                //setState(() {
                numOfRounds = int.tryParse(str);
                //});
              },
            ),
            Container(
              padding: EdgeInsets.fromLTRB(20, 6, 20, 6),
            ),
            Text(
                'Verbunden über Bluetooth mit folgendem Gerät:'
            ),
            Container(
              color: Colors.black12,
              padding: EdgeInsets.fromLTRB(10, 10, 10, 10),
              child: Text(
                connectedDeviceName,
                style: TextStyle(
                    color: Colors.black
                ),
              ),
            ),
            Container(
              padding: EdgeInsets.fromLTRB(35, 0, 35, 30),
              child: RaisedButton(
                  color: Colors.blueGrey,
                  //padding: EdgeInsetsDirectional.fromSTEB(10, 12, 10, 12),
                  //padding: EdgeInsets.fromLTRB(10, 12, 10, 12),
                  child:Text(
                    'Bluetooth-Verbindung einrichten',
                    style: TextStyle(
                        color: Colors.white
                    ),
                  ),
                  onPressed:() async{
                    var result = await Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => BluetoothSettingsPageVersion1(bluetooth)),
                    );
                    //print("future length is ${result.length}, the type of result is ${result.runtimeType.toString()}");
                    if(result != null) {
                      setState(() {
                        bluetooth = result[0];
                      });
                    }
                    connectedDeviceName = bluetooth.connectedDeviceName;
                    if(bluetooth.device != null) {
                      listenOnDeviceStateChanges(bluetooth.device);
                    }
                  }
              ),
            ),
            Container(
              //padding: EdgeInsets.fromLTRB(0, 0, 0, 0),
                child: RaisedButton(
                    color: Colors.blueGrey,
                    child: Text(
                      'Training erstellen',
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: 18
                      ),
                    ),
                    onPressed: () async {
                      //if(blueDevice == null) print('BluetoothDevice in TrainingSettings is null\n');
                      //if(blueCharacteristic == null) print('BluetoothCharacteristic in TrainingSettings is null\n');
                      bool invalid = false;
                      String whereInvalid = '';

                      if(minutesRound == null || minutesRound < 0) {
                        invalid = true;
                        whereInvalid = whereInvalid + 'Dauer einer Runde in Minuten\n';
                      }
                      if(secondsRound == null || secondsRound < 0) {
                        invalid = true;
                        whereInvalid = whereInvalid + 'Dauer einer Runde in Sekunden\n';
                      }
                      if(minutesPause == null || minutesPause < 0) {
                        invalid = true;
                        whereInvalid = whereInvalid + 'Dauer einer Pause in Minuten\n';
                      }
                      if(secondsPause == null || secondsPause < 0) {
                        invalid = true;
                        whereInvalid = whereInvalid + 'Dauer einer Runde in Sekunden\n';
                      }
                      if(numOfRounds == null || numOfRounds < 0) {
                        invalid = true;
                        whereInvalid = whereInvalid + 'Anzahl von Runden\n';
                      }

                      if(invalid) {
                        Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => ErrorInvalidTrainingSettingsPageVersion1('Ungültige Eingaben von\n' + whereInvalid)),
                        );
                      }
                      else {
                        var result = await Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => CountdownTimerPageVersion1(minutesRound: minutesRound, secondsRound: secondsRound,
                              minutesPause: minutesPause, secondsPause: secondsPause, numOfRounds: numOfRounds,
                              bluetooth: bluetooth)),
                        );
                        if(result != null) {
                          setState(() {
                            bluetooth = result[0];
                          });
                        }
                        connectedDeviceName = bluetooth.connectedDeviceName;
                        if(bluetooth.device != null) {
                          listenOnDeviceStateChanges(bluetooth.device);
                        }
                      }
                    })
            ),
          ]
      )
      /*)*/,
    );
  }


}