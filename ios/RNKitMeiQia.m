#import "RNKitMeiQia.h"

#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import <MeiQiaSDK/MeiQiaSDK.h>
#import "MQChatViewManager.h"

static id stringWithFormat(id obj)
{
  if (obj == nil) {
    return [NSNull null];
  }
  return [NSString stringWithFormat:@"%@", obj];
}

@implementation RNKitMeiQia

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

- (instancetype)init
{
  self = [super init];
  if (self) {
    // for (NSString *name in @[UIApplicationDidEnterBackgroundNotification,
    //                          UIApplicationWillEnterForegroundNotification]) {
    
    //   [[NSNotificationCenter defaultCenter] addObserver:self
    //                                            selector:@selector(handleAppStateDidChange:)
    //                                                name:name
    //                                              object:nil];
    // }
  }
  return self;
}

// - (void)handleAppStateDidChange:(NSNotification *)notification
// {
//   // App 进入后台时，关闭美洽服务
//   if ([notification.name isEqualToString:UIApplicationDidEnterBackgroundNotification]) {
//     [MQManager closeMeiqiaService];

//   // App 进入前台时，开启美洽服务
//   } else if ([notification.name isEqualToString:UIApplicationWillEnterForegroundNotification]) {
//     [MQManager openMeiqiaService];
//   }
// }

// 初始化方法
RCT_EXPORT_METHOD(init:(NSString *)appKey
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject) {
  [MQManager initWithAppkey:appKey completion:^(NSString *clientId, NSError *error) {
    if (error) {
      reject([NSString stringWithFormat:@"%@", error.code], error.domain, error);
      return;
    }
    resolve(@{@"clientId": stringWithFormat(clientId)});
  }];
}

// 设置客户信息
RCT_EXPORT_METHOD(setClientInfo:(NSDictionary *)attrs
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject) {
  
  [MQManager setClientInfo:attrs completion:^(BOOL success, NSError *error) {
    if (error) {
      reject([NSString stringWithFormat:@"%ld", error.code], error.domain, error);
      return;
    }
    resolve(@{@"ok": @(1)});
  }];
}

RCT_EXPORT_METHOD(open) {
  
  UIViewController *presentingController = RCTPresentedViewController();
  if (presentingController == nil) {
    RCTLogError(@"Tried to display action sheet picker view but there is no application window.");
    return;
  }
  MQChatViewManager *chatViewManager = [[MQChatViewManager alloc] init];
  [chatViewManager enableSyncServerMessage:true];
  [chatViewManager presentMQChatViewControllerInViewController:presentingController];
}

RCT_EXPORT_METHOD(openService) {
  [MQManager openMeiqiaService];
}

RCT_EXPORT_METHOD(closeService) {
  [MQManager closeMeiqiaService];
}

- (void)dealloc
{
  // [[NSNotificationCenter defaultCenter] removeObserver:self];
}

@end

