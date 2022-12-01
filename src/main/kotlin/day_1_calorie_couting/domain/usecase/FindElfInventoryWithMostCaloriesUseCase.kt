package day_1_calorie_couting.domain.usecase

import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryResponse

interface FindElfInventoryWithMostCaloriesUseCase {
    fun handle(inventory: String): ElfInventoryResponse
}