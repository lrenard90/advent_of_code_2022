package day_1_calorie_couting.domain.usecase

import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryRankResponse

interface FindElvesInventoriesWithMostCaloriesUseCase {
    fun handle(inventory: String, numberOfInventoriesToRank: Int): List<ElfInventoryRankResponse>
}