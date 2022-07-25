package com.gkonstantakis.finance_app.data.mapping

import com.gkonstantakis.finance_app.data.database.entities.FinanceInfo
import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import java.util.ArrayList

class FinanceInfoMapper() {

    fun mapToDomain(entity: FinanceInfo): FinanceInfoDomain {
        return FinanceInfoDomain(
            id = entity.id,
            baskets = entity.baskets
        )
    }

    fun mapFromDomain(entity: FinanceInfoDomain): FinanceInfo {
        return FinanceInfo(
            id = entity.id,
            baskets = entity.baskets
        )
    }

    fun mapToDomainList(entities: List<FinanceInfo>): List<FinanceInfoDomain> {
        return entities.map {
            mapToDomain(it)
        }
    }

    fun mapFromDomainList(entities: List<FinanceInfoDomain>): List<FinanceInfo> {
        return entities.map {
            mapFromDomain(it)
        }
    }
}