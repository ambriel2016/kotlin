// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JVM_IR
// TARGET_BACKEND: JVM
// WITH_COROUTINES
// WITH_REFLECT

// FILE: test.kt
import kotlin.reflect.KSuspendFunction0
import kotlin.coroutines.SuspendFunction0

class Test {
    suspend fun o() = "O"
    fun forCall(): KSuspendFunction0<String> {
        return ::o
    }

    suspend fun k() = "K"
    fun forInvoke(): SuspendFunction0<String> {
        return ::k
    }
}

// FILE: Checker.java
import helpers.*;

class Checker {
    public static String check() {
        Test test = new Test();
        return ((String) test.forCall().call(new EmptyContinuation())) +
                ((String) test.forInvoke().invoke(new EmptyContinuation()));
    }
}

// FILE: box.kt
fun box() = Checker.check()
