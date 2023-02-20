package leongcheewah.invoicemanagement.exception

class ExceptionResponse {
    var errorCode: String? = null
    var errorMsg: String? = null

    constructor()
    constructor(errorCode: String?, errorMsg: String?) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
    }
}