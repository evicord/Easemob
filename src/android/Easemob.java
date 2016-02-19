package com.tyrion.plugin.easemob;

import android.content.Intent;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.easeui.controller.EaseUI;
import com.evicord.panart.ChatActivity;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class Easemob extends CordovaPlugin {

    CallbackContext callback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            callback = callbackContext;

            EMChat.getInstance().init(this.cordova.getActivity().getApplication());
            EMChat.getInstance().setDebugMode(true);

            EaseUI.getInstance().init(this.cordova.getActivity().getApplication());

            PluginResult result = new PluginResult(PluginResult.Status.OK, "init");
            callback.sendPluginResult(result);

            return true;
        }

        if (action.equals("login")) {
            callback = callbackContext;

//            String userName = args.getString(0);
//            String password = args.getString(1);

            String userName = "user_e1a45f3b47344dbb87ad3963ab080601";
            String password = "TfzyGKYP9NvR4vn0CIHAdw";

            EMChatManager.getInstance().login(userName, password, new EMCallBack() {//回调
                @Override
                public void onSuccess() {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            EMGroupManager.getInstance().loadAllGroups();
//                            EMChatManager.getInstance().loadAllConversations();
//                            Log.e("main", "登陆聊天服务器成功！");
//                        }
//                    });

                    Log.e("onSuccess", "登陆聊天服务器成功！");

                }

                @Override
                public void onProgress(int progress, String status) {
                    Log.e("onProgress", "progress:"+progress);
                }

                @Override
                public void onError(int code, String message) {
                    Log.e("onError", "登陆聊天服务器失败,code=" + code + ",message=" + message);
                }
            });
            PluginResult result = new PluginResult(PluginResult.Status.OK, "userName: " + userName + "/" + "password: " + password);
            callback.sendPluginResult(result);

            return true;
        }

        if (action.equals("chat")) {
            callback = callbackContext;

            Intent intent = new Intent();
            intent.setClass(this.cordova.getActivity(), ChatActivity.class);
            this.cordova.getActivity().startActivity(intent);

            PluginResult result = new PluginResult(PluginResult.Status.OK, "chat");
            callback.sendPluginResult(result);

            return true;
        }
        return false;
    }
}
