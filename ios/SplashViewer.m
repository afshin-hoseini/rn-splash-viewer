#import <Foundation/Foundation.h>
#import "SplashViewer.h"
#import <React/RCTLog.h>

@implementation SplashViewer

static UIViewController *splashViewController = nil;

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

+(void) show {
    
    
    splashViewController = [UIViewController new];
    splashViewController.view = [[[NSBundle mainBundle] loadNibNamed:@"LaunchScreen" owner:nil options:nil] objectAtIndex:0];
    
    [[[UIApplication sharedApplication].keyWindow rootViewController] presentViewController:splashViewController animated:false completion:nil];
}

RCT_EXPORT_METHOD(show) {
    
    RCTLogInfo(@"Show method called on iOS device");
    
    [SplashViewer show];
    
    RCTLogInfo(@"Show method Done");
};

RCT_EXPORT_METHOD(hide) {
    
    RCTLogInfo(@"Hide method called on iOS device");
    
    CATransition *transition = [CATransition new];
    transition.duration = 0.5;
    transition.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    transition.type = kCATransitionFade;
    transition.subtype = kCATransitionFromBottom;
    
    [[[splashViewController view] layer] addAnimation:transition forKey:nil];
    
    [splashViewController dismissViewControllerAnimated:true completion:^() {;
        
        splashViewController = nil;
    }
     ];
}

@end
  
