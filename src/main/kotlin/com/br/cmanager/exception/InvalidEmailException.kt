package com.br.cmanager.exception

import java.lang.RuntimeException

class InvalidEmailException(message: String?) : RuntimeException(message)  {
}