package br.dev.s2w.kfoods.api.di.notification

import org.springframework.beans.factory.annotation.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NotifierType(val value: UrgencyLevel)