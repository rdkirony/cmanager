package com.br.cmanager.exception

import java.lang.RuntimeException

class InvalidLoginException (message: String?) : RuntimeException(message)  {
}