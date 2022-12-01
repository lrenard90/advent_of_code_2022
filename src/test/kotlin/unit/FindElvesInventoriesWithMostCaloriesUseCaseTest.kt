package unit

import day_1_calorie_couting.domain.port.`in`.usecase.FindElvesInventoriesWithMostCaloriesUseCaseImpl
import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryRankResponse
import day_1_calorie_couting.domain.usecase.FindElvesInventoriesWithMostCaloriesUseCase
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FindElvesInventoriesWithMostCaloriesUseCaseTest {

    private val findElvesInventoriesWithMostCaloriesUseCase: FindElvesInventoriesWithMostCaloriesUseCase =
        FindElvesInventoriesWithMostCaloriesUseCaseImpl()

    @Test
    fun `find only one elf one amount use case`() {
        val inventoryString =
            """1000"""

        val elfInventoryRankResponseList: List<ElfInventoryRankResponse> =
            findElvesInventoriesWithMostCaloriesUseCase.handle(inventoryString, 1)

        assertEquals(elfInventoryRankResponseList.size, 1)
        assertEquals(1, elfInventoryRankResponseList[0].position)
        assertEquals(1, elfInventoryRankResponseList[0].elfNumber)
        assertEquals(1000, elfInventoryRankResponseList[0].calories)
    }

    @Test
    fun `find only one elf multiple amounts use case`() {
        val inventoryString =
            """1000
               2000
               3000"""

        val elfInventoryRankResponseList: List<ElfInventoryRankResponse> =
            findElvesInventoriesWithMostCaloriesUseCase.handle(inventoryString, 1)

        assertEquals(elfInventoryRankResponseList.size, 1)
        assertEquals(1, elfInventoryRankResponseList[0].position)
        assertEquals(1, elfInventoryRankResponseList[0].elfNumber)
        assertEquals(6000, elfInventoryRankResponseList[0].calories)
    }

    @Test
    fun `find second elf one line use case`() {
        val inventoryString =
            """1000
               2000
               3000
               
               8000"""

        val elfInventoryRankResponseList: List<ElfInventoryRankResponse> =
            findElvesInventoriesWithMostCaloriesUseCase.handle(inventoryString, 2)

        assertEquals(elfInventoryRankResponseList.size, 2)
        assertEquals(1, elfInventoryRankResponseList[0].position)
        assertEquals(2, elfInventoryRankResponseList[0].elfNumber)
        assertEquals(8000, elfInventoryRankResponseList[0].calories)

        assertEquals(2, elfInventoryRankResponseList[1].position)
        assertEquals(1, elfInventoryRankResponseList[1].elfNumber)
        assertEquals(6000, elfInventoryRankResponseList[1].calories)
    }

}