package day_1_calorie_couting.domain.port.`in`.usecase

import day_1_calorie_couting.domain.model.ElfInventory
import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryRankResponse
import day_1_calorie_couting.domain.usecase.FindElvesInventoriesWithMostCaloriesUseCase

class FindElvesInventoriesWithMostCaloriesUseCaseImpl : FindElvesInventoriesWithMostCaloriesUseCase {
    override fun handle(inventory: String, numberOfInventoriesToRank: Int): List<ElfInventoryRankResponse> {
        val elfInventories: List<ElfInventory> = extractInventories(inventory)
        val elfInventoryMaxCalories: List<ElfInventory> =
            elfInventories.sortedByDescending { elfInventory -> elfInventory.caloriesAmount }
        return elfInventoryMaxCalories.mapIndexed { index, elfInventory ->
            ElfInventoryRankResponse(
                (index + 1).toLong(),
                elfInventory.elfNumber,
                elfInventory.caloriesAmount
            )
        }.take(numberOfInventoriesToRank)
    }

    private fun extractInventories(inventory: String): List<ElfInventory> {
        val inventoryLinesGroup: List<String> = groupStringLinesByInventory(inventory)

        return inventoryLinesGroup.mapIndexed { index, inventoryGroup ->
            buildElfInventoryFromInventoryLinesGroup(inventoryGroup, index)
        }
    }

    private fun groupStringLinesByInventory(inventory: String) = inventory.replace(" ", "").split("\n\n")

    private fun buildElfInventoryFromInventoryLinesGroup(
        inventoryGroup: String,
        index: Int
    ): ElfInventory {
        val inventoryLines: List<String> = inventoryGroup.split("\n")
        val inventoryCalories = inventoryLines.sumOf { it.toLong() }
        return ElfInventory((index + 1).toLong(), inventoryCalories)
    }
}