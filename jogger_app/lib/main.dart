
import 'package:flutter/material.dart';
import 'package:jogger_app/TrainingSettingsPageVersion1.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: TrainingSettingsPageVersion1()/*TrainingSettings()*/
    );
  }
}

