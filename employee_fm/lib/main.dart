import 'package:flutter/material.dart';
import 'package:employee_fm/staff/staff_app.dart';
import 'dart:io';
import 'package:flutter/services.dart';
import 'dart:ui';




//const EventChannel eventChannel = const EventChannel(Main_EventChannel_Name);
//
//const MethodChannel platform = const MethodChannel(Main_MethodChannel_Name);



/*
* 程序入口
* */
void main() => runApp(new StaffApp());

//void main() => runApp(MyApp());
//
//class MyApp extends StatelessWidget {
//  @override
//  Widget build(BuildContext context) {
//    return MaterialApp(
//      title: 'MediaQuery Demo',
//      theme: ThemeData(
//        platform: TargetPlatform.iOS,
//      ),
//      home: LoginWidgetController(),
//    );
//  }
//}

//void main() => runApp(_widgetForRoute(window.defaultRouteName));

//Widget _widgetForRoute(String route) {
//  switch (route) {
//    case 'merchant_diversion_fill':
//      return JSJMaterialApp(
//
//        theme: new ThemeData(
//          platform: TargetPlatform.iOS,
//        ),
//        home: new MerchantDiversionFill(),
//      );
//    case 'route2':
//      return Center(
//        child: Text('route: $route', textDirection: TextDirection.ltr),
//      );
//    default:
//      return Center(
//        child: Text('Unknown route: $route', textDirection: TextDirection.ltr),
//      );
//  }
//}

