package com.mik0war.complimentsApp.domain

import java.io.IOException

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()
class NoFavorites : IOException()