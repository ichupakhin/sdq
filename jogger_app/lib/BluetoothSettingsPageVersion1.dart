import 'package:flutter/material.dart';
import 'package:flutter_blue/flutter_blue.dart';
import 'package:flutter/material.dart';
import 'package:quiver/async.dart';
import 'dart:async';
import 'package:jogger_app/BluetoothSettingsVersion1.dart';
import 'dart:io';


class BluetoothSettingsPageVersion1 extends StatefulWidget {

  BluetoothSettingsVersion1 bluetooth;

  BluetoothSettingsPageVersion1(BluetoothSettingsVersion1 bluetooth) {
    this.bluetooth = bluetooth;
  }

  @override
  BluetoothSettingsPageStateVersion1 createState() => BluetoothSettingsPageStateVersion1(this.bluetooth);

}

class BluetoothSettingsPageStateVersion1 extends State<BluetoothSettingsPageVersion1> with AutomaticKeepAliveClientMixin<BluetoothSettingsPageVersion1> {

  BluetoothSettingsVersion1 bluetooth = BluetoothSettingsVersion1();
  String searchOrStop = 'Geräte\nsuchen';
  List<Widget> devicesAsWidgets = [];
  String connectedDeviceName = 'Kein Gerät';
  StreamSubscription listenerOnDeviceStateChanges;


  @override
  bool get wantKeepAlive => true;

  BluetoothSettingsPageStateVersion1(BluetoothSettingsVersion1 bluetooth) {
    if(bluetooth != null) {
      this.bluetooth = bluetooth;
      connectedDeviceName = bluetooth.connectedDeviceName;
      if(bluetooth.device != null) {
        listenOnDeviceStateChanges(bluetooth.device);
      }
    }
  }

//Create a button of a found bluetooth bevice for ListView
  void createWidgetFromDevice(BuildContext context, BluetoothDevice device) {
    RaisedButton button = new RaisedButton(
      color: Colors.white,
      child: Text(device.name),
      onPressed: () {
        bluetooth.connect(device);
        setState(() {
          connectedDeviceName = device.name;
        });

        listenerOnDeviceStateChanges?.cancel();
        listenerOnDeviceStateChanges = null;

        Navigator.pop(context,[bluetooth]);
      }
    );

    setState(() {
      devicesAsWidgets.add(button);
    });

  }

  void listenOnDeviceStateChanges(BluetoothDevice device) async {
   // if(device != null) {
       listenerOnDeviceStateChanges = device.onStateChanged().listen((event) {
        setState(() {
          connectedDeviceName = bluetooth.connectedDeviceName;
        });
      });
    //}

  }


  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        automaticallyImplyLeading: true,//Pfeil 'Zurück' einschalten
        backgroundColor: Colors.blueGrey,
        title: Text(
          'Bluetooth-Einstellungen',
          style: TextStyle(
              color: Colors.white),
        ),
      ),
      body: /*Center(
        child:*/ ListView(
        padding: EdgeInsets.fromLTRB(10, 10, 10, 10),
        //addAutomaticKeepAlives: true,
        children: <Widget>[
          Container(
            height: 30,
            child: Text('Verbunden mit: $connectedDeviceName'),),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: <Widget>[
              Container(
                height: 45,
                width: 130,
                child: RaisedButton(
                  padding: EdgeInsets.all(2),
                  color: Colors.redAccent,
                  child: Text(
                    'Verbindung\nabbrechen',
                    style: TextStyle(
                        color: Colors.white
                    ),
                  ),
                  onPressed: () {
                    setState(() {
                      bluetooth.disconnect();
                      connectedDeviceName = bluetooth.connectedDeviceName;
                    });
                  },
                )
              ),
              Container(
                height: 45,
                width: 130,
                child: RaisedButton(
                  padding: EdgeInsets.all(2),
                  color: Colors.blueGrey,
                  child: Text(
                    searchOrStop,
                    style: TextStyle(
                        color: Colors.white),
                  ),
                  onPressed: () {
                    print('              isScanning = ${bluetooth.isScanning}\n');
                    if(!bluetooth.isScanning) {
                      setState(() {
                        devicesAsWidgets.clear();
                        searchOrStop = 'Suche\nläuft';
                      });
                      bluetooth.startScan();
                      bluetooth.scanSubscription.onDone(() {
                        setState(() {
                          searchOrStop = 'Geräte\nsuchen';
                        });
                        print('Size von scanResult ist ${bluetooth.scanResults.length}');
                        bluetooth.scanResults.forEach((DeviceIdentifier device, ScanResult result) {
                          print('Device id ist ${device.id} and deviceName ist ${result.device.name}\n');
                          //devices.add(result.device);
                          createWidgetFromDevice(context, result.device);
                          print('Länge von devicesAsWidgets: ${devicesAsWidgets.length}\n');
                          //devices.add(scanResult.device);
                        });
                      });
                    }
                  },
                ),
              )
            ],
          ),
          Container(height: 20),
          Container(
            //width: MediaQuery.of(context).size.width - 15,
              height: MediaQuery.of(context).size.height - 200,
              padding: EdgeInsets.fromLTRB(0, 0, 0, 0),
              color: Colors.black12,
              foregroundDecoration: BoxDecoration(
                  border: Border.all(
                    color: Colors.blueGrey,
                    width: 3,
                  )
              ),
              child: ListView.builder(
                  padding: EdgeInsets.fromLTRB(0, 0, 0, 0),
                  itemCount: devicesAsWidgets.length,
                  itemBuilder: (BuildContext context, int index) {
                    return devicesAsWidgets[index];
                  }
              )
          ),
        ],
      ),
    );
  }


}