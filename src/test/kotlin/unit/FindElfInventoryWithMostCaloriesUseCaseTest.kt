package unit

import day_1_calorie_couting.domain.port.`in`.usecase.FindElfInventoryWithMostCaloriesUseCaseImpl
import day_1_calorie_couting.domain.port.`in`.usecase.FindElvesInventoriesWithMostCaloriesUseCaseImpl
import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryResponse
import day_1_calorie_couting.domain.usecase.FindElfInventoryWithMostCaloriesUseCase
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FindElfInventoryWithMostCaloriesUseCaseTest {

    private val findElfInventoryWithMostCaloriesUseCase: FindElfInventoryWithMostCaloriesUseCase = FindElfInventoryWithMostCaloriesUseCaseImpl(FindElvesInventoriesWithMostCaloriesUseCaseImpl())

    @Test
    fun `find only one elf one amount use case`() {
        val inventoryString =
            """1000"""

        val elfInventoryResponse: ElfInventoryResponse = findElfInventoryWithMostCaloriesUseCase.handle(inventoryString)
        assertEquals(1, elfInventoryResponse.elfNumber)
        assertEquals(1000, elfInventoryResponse.caloriesAmount)
    }

    @Test
    fun `find only one elf multiple amounts use case`() {
        val inventoryString =
            """1000
               2000
               3000"""

        val elfInventoryResponse: ElfInventoryResponse = findElfInventoryWithMostCaloriesUseCase.handle(inventoryString)
        assertEquals(1, elfInventoryResponse.elfNumber)
        assertEquals(6000, elfInventoryResponse.caloriesAmount)
    }

    @Test
    fun `find second elf one line use case`() {
        val inventoryString =
            """1000
               2000
               3000
               
               8000"""

        val elfInventoryResponse: ElfInventoryResponse = findElfInventoryWithMostCaloriesUseCase.handle(inventoryString)
        assertEquals(2, elfInventoryResponse.elfNumber)
        assertEquals(8000, elfInventoryResponse.caloriesAmount)
    }

}