import 'package:flutter/material.dart';
import 'package:flutter_blue/flutter_blue.dart';
import 'package:flutter/material.dart';
import 'package:quiver/async.dart';
import 'dart:async';
import 'dart:io';


class BluetoothSettingsVersion1 {

  //Bluetooth instance
  FlutterBlue flutterBlue = FlutterBlue.instance;

  // Scanning
  StreamSubscription scanSubscription;
  Map<DeviceIdentifier, ScanResult> scanResults = new Map();
  bool isScanning = false;

  // State
  StreamSubscription stateSubscription;
  BluetoothState bluetoothState = BluetoothState.unknown;

  // Device
  BluetoothDevice device;
  String connectedDeviceName = 'Kein Gerät';

  //bool get isConnected => (device != null);
  StreamSubscription deviceConnectionSubscription;
  StreamSubscription deviceStateSubscription;
  List<BluetoothService> services = new List();

  //Map<Guid, StreamSubscription> valueChangedSubscriptions = {};
  BluetoothDeviceState deviceState = BluetoothDeviceState.disconnected;

  // Characteristic that manages vibration motors
  BluetoothCharacteristic characteristic;


  BluetoothSettingsVersion1() {
    // Immediately get the state of FlutterBlue
    flutterBlue.state.then((s) {
      bluetoothState = s;
    });
    // Subscribe to state changes
    stateSubscription = flutterBlue.onStateChanged().listen((s) {
      bluetoothState = s;
    });
  }

/*
  //Disconnect device
  void dispose() {
    // Meaning of ?: only if stateSubscription is not null call cancel, otherwise return null.
    stateSubscription?.cancel();
    stateSubscription = null;
    // Meaning of ?: only if scanSubscription is not null call cancel, otherwise return null.
    scanSubscription?.cancel();
    scanSubscription = null;
    // Meaning of ?: only if deviceConnection is not null call cancel, otherwise return null.
    deviceConnectionSubscription?.cancel();
    deviceConnectionSubscription = null;
  }
*/

  //Begin searching for bluetooth devices
  void startScan() {
    isScanning = true;
    scanSubscription =
        flutterBlue.scan(timeout: Duration(seconds: 5)).listen((
            scanResult) {
          //print('localName: ${scanResult.advertisementData.localName}');
          //print('manufacturerData: ${scanResult.advertisementData
          //    .manufacturerData}');
          //print('serviceData: ${scanResult.advertisementData.serviceData}');
          scanResults[scanResult.device.id] = scanResult;
        },
          onDone: stopScan());
  }


  //End searching for bluetooth devices
  stopScan() {
    scanSubscription?.cancel();
    scanSubscription = null;
    isScanning = false;
  }


  //Search for the characteristic with uuid: 713d0003-503e-4c75-ba94-3148f18d941e. If found, return true, else false.
  bool searchCharacteristic() {
    for (BluetoothService service in services) {
      for (BluetoothCharacteristic characteristic in service.characteristics) {
        print('Characteristc.uuid.toString is ${characteristic.uuid
            .toString()}\n');
        if (characteristic.uuid.toString().compareTo('713d0003-503e-4c75-ba94-3148f18d941e') == 0) {
          this.characteristic = characteristic;
          return true;
        }
      }
    }
    return false;
  }


  //Connect to device
  connect(BluetoothDevice d) async {
    if(device != null && device.id == d.id && deviceState == BluetoothDeviceState.connected) return;
    connectedDeviceName = d.name;
    device = d;

    deviceConnectionSubscription =
        flutterBlue.connect(device).listen(null, onDone: disconnect);

// Update the connection state immediately
    device.state.then((s) {
      deviceState = s;
    });

// Subscribe to connection changes
    deviceStateSubscription = device.onStateChanged().listen((s) {
      deviceState = s;

      if (s == BluetoothDeviceState.disconnected) {
         connectedDeviceName = 'Kein Gerät';
      }

      if (s == BluetoothDeviceState.connected) {
        //Discover services
        connectedDeviceName = device.name;
        device.discoverServices().then((s) {
          services = s;
          //Search for the characteristic
          bool found = searchCharacteristic();
          if (found)
            print('The characteristic was found');
          else
            print('The characteristic was not found');
        });
      }
    });
  }


//Disconnect device. In this case reset all listeners and 'device'.
  disconnect() {
// Remove all value changed listeners
    //valueChangedSubscriptions.forEach((uuid, sub) => sub.cancel());
    //valueChangedSubscriptions.clear();
    connectedDeviceName = 'Kein Gerät';

    deviceStateSubscription?.cancel();
    deviceStateSubscription = null;

    deviceConnectionSubscription?.cancel();
    deviceConnectionSubscription = null;

    device = null;
    deviceState = BluetoothDeviceState.disconnected;
  }

/*
  readCharacteristic(BluetoothCharacteristic c) async {
    await device.readCharacteristic(c);
  }

*/

  writeCharacteristic(BluetoothCharacteristic c, List<int> value) async {
    await device.writeCharacteristic(c, value/*, type: CharacteristicWriteType.withResponse*/);
  }


  void notifyByVibration(int numberOfNotifications, int durationPauseBetweenNotificationsMsec,
      int durationOneNotificationMsec, List <int> value) {

    for (int i = 0; i < numberOfNotifications; i++) {
      device.writeCharacteristic(characteristic, value);
      sleep(Duration(milliseconds: durationOneNotificationMsec));
      device.writeCharacteristic(characteristic, [0x00, 0x00, 0x00, 0x00]);
      sleep(Duration(milliseconds: durationPauseBetweenNotificationsMsec));
    }
  }


}


