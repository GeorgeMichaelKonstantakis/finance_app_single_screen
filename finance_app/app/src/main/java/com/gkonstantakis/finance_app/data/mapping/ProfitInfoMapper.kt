package com.gkonstantakis.finance_app.data.mapping

import com.gkonstantakis.finance_app.data.database.entities.ProfitInfo
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain

class ProfitInfoMapper() {


    fun mapToDomain(entity: ProfitInfo): ProfitInfoDomain {
        return ProfitInfoDomain(
            id = entity.id,
            profit = entity.profit
        )
    }

    fun mapFromDomain(entity: ProfitInfoDomain): ProfitInfo {
        return ProfitInfo(
            id = entity.id,
            profit = entity.profit
        )
    }

    fun mapToDomainList(entities: List<ProfitInfo>): List<ProfitInfoDomain> {
        return entities.map {
            mapToDomain(it)
        }
    }

    fun mapFromDomainList(entities: List<ProfitInfoDomain>): List<ProfitInfo> {
        return entities.map {
            mapFromDomain(it)
        }
    }
}