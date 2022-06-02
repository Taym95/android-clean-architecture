package com.adyen.android.assignment.app

import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.utils.base.app.NetworkConfig

class PlanetaryNetworkConfig : NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.NASA_BASE_URL
    }

    override fun timeOut(): Long {
        return 30L
    }
}