package com.adyen.android.assignment.common

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultAppCoroutineDispatchers @Inject constructor() : AppCoroutineDispatchers {
    override val io get() = Dispatchers.IO
}