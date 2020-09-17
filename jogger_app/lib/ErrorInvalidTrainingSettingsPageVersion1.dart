import 'package:flutter/material.dart';


class ErrorInvalidTrainingSettingsPageVersion1 extends StatelessWidget {

  String errorMessage;

  ErrorInvalidTrainingSettingsPageVersion1(errorMessage) {
    this.errorMessage = errorMessage;
  }


  Widget build(BuildContext context) {

    return Scaffold(
        backgroundColor: Colors.grey,
        body: AlertDialog(
          actions: <Widget>[
            RaisedButton(
                color: Colors.blueGrey,
                child: Text('Ok',
                  style: TextStyle(color: Colors.white),),
                onPressed: () {
                  Navigator.pop(context);
                }
            )
          ],
          content: Text(errorMessage),
        )
    );
  }

}
