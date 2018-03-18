
package ir.afshin;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class SplashViewerModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  public static final String HIDE_SPLASH_SCENE = "ir.afshin.splashViewer.HIDE_SPLASH_SCENE";
  static boolean isHideCalled = false;

  public SplashViewerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "SplashViewer";
  }

  public static void showSplash(Context context) {

    isHideCalled = false;
    Intent intent = new Intent(context, SplashActivity.class);
    context.startActivity(intent);
  }

  @ReactMethod
  public void show() {

    showSplash(reactContext);
  }

  @ReactMethod
  public void hide() {

    isHideCalled = true;
    reactContext.sendBroadcast(new Intent(HIDE_SPLASH_SCENE));
  }

}