package br.dev.s2w.kfoods.api.di.notificacao

import org.springframework.beans.factory.annotation.Qualifier

@Qualifier
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class TipoDoNotificador(val value: NivelUrgencia)
