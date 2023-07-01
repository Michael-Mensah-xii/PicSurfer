package com.example.picsurfer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**This class will be used by the Hilt library to generate all the necessary code
 *  to inject dependencies in the project ,it inherits from Application () */

@HiltAndroidApp
class MyApplication:Application() {

}