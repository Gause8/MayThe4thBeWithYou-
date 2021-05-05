package com.example.maythefourthbewithyou.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
created this for simple use of coroutines
 */
object Coroutines {
    fun io(work:suspend (()-> Unit)) = CoroutineScope(Dispatchers.IO).launch {
        work()
    }
}