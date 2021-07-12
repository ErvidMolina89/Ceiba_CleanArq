package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.maper.publications

import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.dto.dto_publications.PublicationsDTO
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels

fun PublicationsModels.fromDTO(parentDTO: PublicationsDTO): PublicationsModels {
    return PublicationsModels().apply {
        id = parentDTO.id
        userId = parentDTO.userId
        title = parentDTO.title
        body = parentDTO.body
    }
}

fun PublicationsDTO.fromModels(parentModels: PublicationsModels): PublicationsDTO {
    return PublicationsDTO()
        .apply {
        id = parentModels.id
        userId = parentModels.userId
        title = parentModels.title
        body = parentModels.body
    }
}