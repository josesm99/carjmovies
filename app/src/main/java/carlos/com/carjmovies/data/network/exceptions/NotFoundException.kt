package carlos.com.carjmovies.data.network.exceptions

import java.io.IOException

class NotFoundException : IOException() {

    override val message: String?
        get() = "Not Found"
}