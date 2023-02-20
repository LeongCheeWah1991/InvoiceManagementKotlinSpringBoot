package leongcheewah.invoicemanagement.exception

class InvoiceException : RuntimeException {
    @JvmField
    var errorMsg: String? = null

    constructor()
    constructor(errorMsg: String?) {
        this.errorMsg = errorMsg
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}