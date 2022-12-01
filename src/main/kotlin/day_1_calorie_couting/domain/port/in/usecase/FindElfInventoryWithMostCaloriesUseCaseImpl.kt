package day_1_calorie_couting.domain.port.`in`.usecase

import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryResponse
import day_1_calorie_couting.domain.usecase.FindElfInventoryWithMostCaloriesUseCase
import day_1_calorie_couting.domain.usecase.FindElvesInventoriesWithMostCaloriesUseCase

class FindElfInventoryWithMostCaloriesUseCaseImpl(val findElvesInventoriesWithMostCaloriesUseCase: FindElvesInventoriesWithMostCaloriesUseCase) :
    FindElfInventoryWithMostCaloriesUseCase {

    override fun handle(inventory: String): ElfInventoryResponse {
        val numberOfInventoriesToRank = 1
        return this.findElvesInventoriesWithMostCaloriesUseCase.handle(inventory, numberOfInventoriesToRank)
            .map { elfInventoryRankResponse ->
                ElfInventoryResponse(
                    elfInventoryRankResponse.elfNumber,
                    elfInventoryRankResponse.calories
                )
            }
            .first()
    }

}