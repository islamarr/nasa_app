package com.adyen.android.assignment.common

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultAppCoroutineDispatchers @Inject constructor() : AppCoroutineDispatchers {
    override val main get() = Dispatchers.Main
    override val mainImmediate get() = Dispatchers.Main.immediate
    override val io get() = Dispatchers.IO
}
