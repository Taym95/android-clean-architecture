package com.adyen.android.assignment.testutils

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = "AndroidManifest.xml",
    application = RobolectricTest.ApplicationStub::class,
    sdk = [Build.VERSION_CODES.O]
)
open class RobolectricTest : MockkUnitTest() {

    protected val application: Application by lazy {
        ApplicationProvider.getApplicationContext<ApplicationStub>()
    }
    protected val context: Context by lazy {
        application
    }

    internal class ApplicationStub : Application()
}
