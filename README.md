![Stars](https://img.shields.io/github/stars/Dev97633/DISABLE_FLAG_SECURE-next?style=flat-square)
![Downloads](https://img.shields.io/github/downloads/Dev97633/DISABLE_FLAG_SECURE-next/total?style=flat-square)
![Forks](https://img.shields.io/github/forks/Dev97633/DISABLE_FLAG_SECURE-next?style=flat-square)
![Visitors](https://komarev.com/ghpvc/?username=Dev97633&repo=DISABLE_FLAG_SECURE-next&style=flat-square)
![Release](https://img.shields.io/github/v/release/Dev97633/DISABLE_FLAG_SECURE-next?style=flat-square)






# Disable FLAG_SECURE (Xposed / LSPosed / LSPatch)

An **Xposed / LSPosed compatible module** that disables `FLAG_SECURE` for Android applications, allowing screenshots, screen recording, and mirroring in apps that normally block them.

---

## ✨ Features

- Removes `FLAG_SECURE` from app windows
- Allows screenshots & screen recording
- Works with:
  - **LSPosed**
  - **LSPatch (non-root)**


---

## 🛠 How It Works

This module hooks into Android window APIs and removes `FLAG_SECURE` flags at runtime:

- `Window.setFlags(...)`
- `SurfaceView.setSecure(false)`
- `WindowManagerGlobal.addView(...)`
- `WindowManagerGlobal.updateViewLayout(...)`

This ensures apps cannot enforce secure display restrictions.

---

## 📦 Installation

### 🔹 LSPosed (Root)

1. Install the APK
2. Open **LSPosed Manager**
3. Enable the module
4. Select target apps (scope)
5. Reboot or force-stop the target app

---

### 🔹 LSPatch (Non-Root)

1. Patch the target app using **LSPatch**
2. Enable **Disable FLAG_SECURE** in module list
3. Launch the patched app

> ⚠️ Some apps may show a black screen if they use DRM or hardware-secure rendering.

---

## 📋 Requirements

- Android 5.0+ (API 21+)
- LSPosed / LSPatch
- Xposed API ≥ 93

---

## 🧩 Module Info

- **Package:** `com.flag.secure`
- **Entry Class:** `com.flag.secure.DisableFlagSecure`
- **Type:** Xposed module (no launcher UI)

---

## ⚠️ Limitations

- DRM-based apps may still show black screen
- Some apps actively detect screen capture
- This module cannot bypass hardware-level DRM

---

## ⚠️ Disclaimer (Yes, This Is About Privacy)

This module disables `FLAG_SECURE` **only at the system UI level**.
It does not give you permission, rights, or moral superiority.

That’s not “testing” — that’s **your responsibility**.

The developer does **not** encourage:
- Privacy invasion
- Harassment
- Misuse of captured content

This module is intended for **personal experimentation, debugging, and learning**.

Respect people’s privacy.  
Use your brain before using the screenshot button.

---

## 📜 License

MIT License
