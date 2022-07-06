package com.br.cmanager.exception

import java.lang.RuntimeException

class NotFoundException(message: String?) : RuntimeException(message) {
}