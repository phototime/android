package dev.zotov.phototime.core

import io.sentry.Breadcrumb
import io.sentry.Sentry
import io.sentry.SentryLevel
import mu.KLogger
import mu.KotlinLogging

class ProjectLogger(private val kLogger: KLogger) {

    fun trace(msg: () -> Any?) = kLogger.trace(msg)

    /**
     * Lazy add a log message if isDebugEnabled is true
     */
    fun debug(msg: () -> Any?) = kLogger.debug(msg)

    /**
     * Lazy add a log message if isInfoEnabled is true
     */
    fun info(msg: () -> Any?) {
        kLogger.info(msg)
        val message = msg()
        if (message is String) Sentry.addBreadcrumb(Breadcrumb.info(message))
    }

    /**
     * Lazy add a log message if isWarnEnabled is true
     */
    fun warn(msg: () -> Any?) {
        kLogger.warn(msg)
        val message = msg()
        if (message is String) Sentry.addBreadcrumb(Breadcrumb.info(message), "warn")
    }

    /**
     * Lazy add a log message if isErrorEnabled is true
     */
    fun error(msg: () -> Any?) {
        kLogger.error(msg)
        val message = msg()
        if (message is String) Sentry.captureMessage(message, SentryLevel.ERROR)
        else if (message is Throwable) Sentry.captureException(message)
    }

    /**
     * Lazy add a log message with throwable payload if isTraceEnabled is true
     */
    fun trace(t: Throwable?, msg: () -> Any?) = kLogger.trace(t, msg)

    /**
     * Lazy add a log message with throwable payload if isDebugEnabled is true
     */
    fun debug(t: Throwable?, msg: () -> Any?) = kLogger.trace(t, msg)

    /**
     * Lazy add a log message with throwable payload if isInfoEnabled is true
     */
    fun info(t: Throwable?, msg: () -> Any?) {
        kLogger.info(t, msg)
        val message = msg()
        if (message is String) Sentry.addBreadcrumb(Breadcrumb.info(message))
    }

    /**
     * Lazy add a log message with throwable payload if isWarnEnabled is true
     */
    fun warn(t: Throwable?, msg: () -> Any?) {
        kLogger.warn(t, msg)
        val message = msg()
        if (message is String) Sentry.addBreadcrumb(Breadcrumb.info(message), "warn")
    }

    /**
     * Lazy add a log message with throwable payload if isErrorEnabled is true
     */
    fun error(t: Throwable?, msg: () -> Any?) {
        kLogger.error(msg)
        val message = msg()
        if (message is String) Sentry.captureMessage(message, SentryLevel.ERROR)
        else if (message is Throwable) Sentry.captureException(message)
        else if (t != null && message is String) Sentry.captureException(t, message)
        else if (t != null) Sentry.captureException(t)
    }
}

fun createLogger(name: String = ""): ProjectLogger{
    val kLogger = KotlinLogging.logger(name)
    return ProjectLogger(kLogger)
}