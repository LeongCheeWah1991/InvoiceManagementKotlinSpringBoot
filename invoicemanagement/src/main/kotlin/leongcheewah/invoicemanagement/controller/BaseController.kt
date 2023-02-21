package leongcheewah.invoicemanagement.controller

import leongcheewah.invoicemanagement.exception.ExceptionResponse
import leongcheewah.invoicemanagement.exception.InvoiceException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

open class BaseController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        InvoiceException::class
    )
    fun handleEmployeeException(empEx: InvoiceException): Any {
        empEx.printStackTrace()
        return ExceptionResponse("500", empEx.errorMsg)
    }
}