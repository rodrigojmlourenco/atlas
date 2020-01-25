package io.procrastination.atlas.model

import io.reactivex.Single

abstract class SingleUseCase<REQ, RES>() {

    operator fun invoke(params: REQ) : Single<RES> {
        return execute(params)
    }

    abstract fun execute(params : REQ) : Single<RES>
}