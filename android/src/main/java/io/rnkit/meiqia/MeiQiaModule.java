
package io.rnkit.meiqia;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.meiqia.core.MQManager;
import com.meiqia.core.callback.OnClientInfoCallback;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;

import java.math.BigDecimal;
import java.util.HashMap;

public class MeiQiaModule extends ReactContextBaseJavaModule {

    private ReactApplicationContext context;

    public MeiQiaModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return "RNKitMeiQia";
    }

    @ReactMethod
    public void init(@Nullable final String appKey, final Promise promise) {
        MQConfig.init(context, appKey, new OnInitCallback() {
            @Override
            public void onSuccess(String clientId) {
                if (promise != null) {
                    WritableMap map = Arguments.createMap();
                    map.putString("clientId", clientId);
                    promise.resolve(map);
                }
            }

            @Override
            public void onFailure(int code, String message) {
                if (promise != null) {
                    promise.reject(code + "", message);
                }
            }
        });
    }

    @ReactMethod
    public void setClientInfo(@Nullable final ReadableMap attrs, final Promise promise) {
        if (attrs == null) {
            promise.reject("1000", "attrs is null");
            return;
        }
        HashMap<String, String> clientInfo = new HashMap<>();
        ReadableMapKeySetIterator iterator = attrs.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            ReadableType type = attrs.getType(key);
            switch (type) {
                case Null:
                    clientInfo.put(key, null);
                    break;
                case Boolean:
                    clientInfo.put(key, String.valueOf(attrs.getBoolean(key)));
                    break;
                case Number:
                    System.out.println(BigDecimal.valueOf(attrs.getDouble(key)));
                    Log.d("-----", BigDecimal.valueOf(attrs.getDouble(key)).toString());
//                    clientInfo.put(key, String.valueOf());
                    break;
                case String:
                    clientInfo.put(key, attrs.getString(key));
                    break;
                case Map:
//                    clientInfo.put(key, recursivelyDeconstructReadableMap(readableMap.getMap(key)));
                    break;
                case Array:
//                    clientInfo.put(key, recursivelyDeconstructReadableArray(readableMap.getArray(key)));
                    break;
                default:
//                    throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        MQManager.getInstance(context).setClientInfo(clientInfo, new OnClientInfoCallback() {
            @Override
            public void onSuccess() {
                if (promise != null) {
                    promise.resolve(null);
                }
            }

            @Override
            public void onFailure(int code, String message) {
                if (promise != null) {
                    promise.reject(code + "", message);
                }
            }
        });
    }

    @ReactMethod
    public void open() {
        Intent intent = new MQIntentBuilder(context).build();
        context.startActivity(intent);
    }

    @ReactMethod
    public void closeService() {
        MQManager.getInstance(context).openMeiqiaService();
    }

    @ReactMethod
    public void openService() {
        MQManager.getInstance(context).closeMeiqiaService();
    }
}