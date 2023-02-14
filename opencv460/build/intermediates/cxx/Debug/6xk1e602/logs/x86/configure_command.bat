@echo off
"C:\\Users\\nutversionone\\AppData\\Local\\Android\\Sdk\\cmake\\3.18.1\\bin\\cmake.exe" ^
  "-HD:\\Test Projects\\nut_android_showcase\\opencv460\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=21" ^
  "-DANDROID_PLATFORM=android-21" ^
  "-DANDROID_ABI=x86" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86" ^
  "-DANDROID_NDK=C:\\Users\\nutversionone\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\nutversionone\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\nutversionone\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\nutversionone\\AppData\\Local\\Android\\Sdk\\cmake\\3.18.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\Test Projects\\nut_android_showcase\\opencv460\\build\\intermediates\\cxx\\Debug\\6xk1e602\\obj\\x86" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\Test Projects\\nut_android_showcase\\opencv460\\build\\intermediates\\cxx\\Debug\\6xk1e602\\obj\\x86" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\Test Projects\\nut_android_showcase\\opencv460\\.cxx\\Debug\\6xk1e602\\x86" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
