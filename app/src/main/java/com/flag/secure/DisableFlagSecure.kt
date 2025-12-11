
package com.flag.secure

import android.view.SurfaceView
import android.view.Window
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

class DisableFlagSecure : IXposedHookLoadPackage {

    private val debug: Boolean = false

    private val mRemoveSecureFlagHook = object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            var flags: Int = param.args[0] as Int
            flags = flags and LayoutParams.FLAG_SECURE.inv()
            param.args[0] = flags
        }
    }

    private val mRemoveSetSecureHook = object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.args[0] = false
        }
    }

    private val mRemoveSecureParamHook = object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            val params = param.args[1] as LayoutParams
            params.flags = params.flags and LayoutParams.FLAG_SECURE.inv()
        }
    }

    override fun handleLoadPackage(lpparam: LoadPackageParam?) {
        XposedBridge.log("Disabled FLAG_SECURE for: " + (lpparam?.packageName ?: "null"))

        XposedHelpers.findAndHookMethod(
            Window::class.java,
            "setFlags",
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            mRemoveSecureFlagHook
        )

        XposedHelpers.findAndHookMethod(
            SurfaceView::class.java,
            "setSecure",
            Boolean::class.javaPrimitiveType,
            mRemoveSetSecureHook
        )

        try {
            val windowsState = XposedHelpers.findClass("com.android.server.wm.WindowState", lpparam?.classLoader)
            XposedHelpers.findAndHookMethod(windowsState, "isSecureLocked", XC_MethodReplacement.returnConstant(false))
        } catch (t: Throwable) {}

        try {
            val windowsState = XposedHelpers.findClass("com.android.server.wm.WindowState", lpparam?.classLoader)
            XposedHelpers.findAndHookMethod(
                "com.android.server.wm.WindowManagerService",
                lpparam?.classLoader,
                "isSecureLocked",
                windowsState,
                XC_MethodReplacement.returnConstant(false)
            )
        } catch (t: Throwable) {}

        try {
            XposedHelpers.findAndHookMethod(
                "android.view.WindowManagerGlobal",
                lpparam!!.classLoader,
                "addView",
                View::class.java,
                ViewGroup.LayoutParams::class.java,
                Display::class.java,
                Window::class.java,
                mRemoveSecureParamHook
            )
        } catch (_: Throwable) {}

        try {
            XposedHelpers.findAndHookMethod(
                "android.view.WindowManagerGlobal",
                lpparam!!.classLoader,
                "addView",
                View::class.java,
                ViewGroup.LayoutParams::class.java,
                Display::class.java,
                Window::class.java,
                Int::class.javaPrimitiveType,
                mRemoveSecureParamHook
            )
        } catch (_: Throwable) {}

        try {
            XposedHelpers.findAndHookMethod(
                "android.view.WindowManagerGlobal",
                lpparam!!.classLoader,
                "updateViewLayout",
                View::class.java,
                ViewGroup.LayoutParams::class.java,
                mRemoveSecureParamHook
            )
        } catch (_: Throwable) {}
    }
}
