import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

import 'package:flutter_localizations/flutter_localizations.dart';

/*
* App启动，加载路由
* */
class StaffApp extends StatefulWidget {

  @override
  _StaffAppState createState() => _StaffAppState();

}

class _StaffAppState extends State<StaffApp> {

  @override
  void initState() {
    super.initState();
    //update

    //配置Native 与 Flutter交互的路由
    FlutterBoost.singleton.registerPageBuilders({

//      'vip_binding_state':(pageName,params, _) => VipBindingState(params: params,),//VIP经理绑定数量统计
//
//      'merchant_diversion_fill':(pageName,params, _) => MerchantDiversionFill(params: params,),//贵宾厅二维码导流设置
//
//      'cooperateCompany':(pageName,params, _) => CooperateCompanyController(params: params,),//合作单位列表
//
//      'ordering_food_activity':(pageName,params, _) => OrderingFoodActivity(userInfoParams: params,),//点单
//
//      'ordering_food_orderlist':(pageName, params, _) => OrderMealOrderListController(userInfoParams: params,),//点单订单列表
//
//      'arrivel_hall_order_controller':(pageName, params, _) => ArrivelHallOrderController(userInfoParams: params,),//在厅订单列表
//
//      'ordering_food_detail_controller':(pageName, params, _) => OrderingFoodDetailController.nativeChannel(paramers: params,),//订单详情
//
//      'pay_method_select_controller':(pageName, params, _) => SelectPayMethodController.nativeChannel(paramers: params,),//支付方式选择页面
//
//      'pay_success_controller':(pageName, params, _) => PaySuccessController.nativeChannel(paramers: params,),//支付成功页
//
//      'login_app_controller':(pageName, params, _) => LoginWidgetController(paramers: params),//登录页面
//
//      'change_service_bind_relation_controller':(pageName, params, _) => ChangeServiceBindRelationController(paramers: params),//员工绑定关系变更
//
//      'my_profit_page_widget':(pageName, params, _) => MyProfitPageWidget(paramers: params),//我的业绩
    });

  }

  @override
  Widget build(BuildContext context) {




    // TODO: implement build

    return MaterialApp(

      localizationsDelegates: [
        // ... app-specific localization delegate[s] here
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: [
//          const Locale('en'), // English
//          const Locale('he'), // Hebrew
        const Locale.fromSubtags(languageCode: 'zh'), // Chinese *See Advanced Locales below*
        // ... other locales the app supports
      ],
        theme: new ThemeData(
          platform: TargetPlatform.iOS,
        ),

        builder: FlutterBoost.init(),
        home:Container(),
        routes: {//配置Flutter内部路由
//          'merchant_diversion_fill':(context) => MerchantDiversionFill(),//贵宾厅二维码导流设置
//          'merchant_diversion_activate':(context) => MerchantDiversionActivate(),//合作单位二维码激活
//          'cooperation_unit_detail':(context) => CooperationUnitDetail(),//推广合作单位详情
//          'cooperate_company_controller' : (context) => CooperateCompanyController(),//合作公司
//          'service_city_list_controller' : (context) => ServiceCityListController(),//服务城市列表
////          'select_pay_method_controller' : (context) => SelectPayMethodController(),//支付方式
//          'ordering_food_detail_controller' : (context) => OrderingFoodDetailController(),//订单详情
//          'ordering_food_activity' : (context) => OrderingFoodActivity(),//产品列表
        },
    );

  }
}