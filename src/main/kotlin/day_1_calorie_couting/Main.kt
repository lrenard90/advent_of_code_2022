package day_1_calorie_couting

import FileService
import day_1_calorie_couting.domain.port.`in`.usecase.FindElfInventoryWithMostCaloriesUseCaseImpl
import day_1_calorie_couting.domain.port.`in`.usecase.FindElvesInventoriesWithMostCaloriesUseCaseImpl
import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryRankResponse
import day_1_calorie_couting.domain.usecase.FindElvesInventoriesWithMostCaloriesUseCase

fun main() {
    val inventory = FileService().readText("src/main/resources/day_1/input.txt")
    val findElvesInventoriesWithMostCaloriesUseCase: FindElvesInventoriesWithMostCaloriesUseCase = FindElvesInventoriesWithMostCaloriesUseCaseImpl()
    val elfInventoryResponse = FindElfInventoryWithMostCaloriesUseCaseImpl(findElvesInventoriesWithMostCaloriesUseCase).handle(inventory)
    println(elfInventoryResponse)

    val topThreeElvesInventories: List<ElfInventoryRankResponse> = findElvesInventoriesWithMostCaloriesUseCase.handle(inventory, 3);
    println(topThreeElvesInventories.sumOf { it.calories })
}