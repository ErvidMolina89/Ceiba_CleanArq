package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.maper.users

import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.dto.dto_users.UserDTO
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels

fun UserModels.fromDTO(queryDTO: UserDTO): UserModels {
    return UserModels().apply {
        id = queryDTO.id
        name = queryDTO.name
        username = queryDTO.username
        email = queryDTO.email
        phone = queryDTO.phone
    }
}

fun UserDTO.fromModels(queryModels: UserModels): UserDTO {
    return UserDTO()
        .apply {
        id = queryModels.id
        name = queryModels.name
        username = queryModels.username
        email = queryModels.email
        phone = queryModels.phone
    }
}