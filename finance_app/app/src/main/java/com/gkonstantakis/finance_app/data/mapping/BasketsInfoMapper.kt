package com.gkonstantakis.finance_app.data.mapping

import com.gkonstantakis.finance_app.data.database.entities.BasketsInfo
import com.gkonstantakis.finance_app.data.models.BasketsInfoDomain

class BasketsInfoMapper() {

    fun mapToDomain(entity: BasketsInfo): BasketsInfoDomain {
        return BasketsInfoDomain(
            id = entity.id,
            baskets = entity.baskets
        )
    }

    fun mapFromDomain(entity: BasketsInfoDomain): BasketsInfo {
        return BasketsInfo(
            id = entity.id,
            baskets = entity.baskets
        )
    }

    fun mapToDomainList(entities: List<BasketsInfo>): List<BasketsInfoDomain> {
        return entities.map {
            mapToDomain(it)
        }
    }

    fun mapFromDomainList(entities: List<BasketsInfoDomain>): List<BasketsInfo> {
        return entities.map {
            mapFromDomain(it)
        }
    }
}