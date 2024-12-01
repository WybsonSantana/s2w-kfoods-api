package br.dev.s2w.kfoods.api.di.model

data class Customer(
    val name: String,
    val email: String,
    val phoneNumber: String,
    private var activated: Boolean = false
) {

    val isActivated: Boolean
        get() = activated

    fun activate() {
        activated = true
    }

}