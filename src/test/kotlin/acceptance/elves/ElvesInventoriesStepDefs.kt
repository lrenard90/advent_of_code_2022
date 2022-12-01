package acceptance.elves

import day_1_calorie_couting.domain.port.`in`.usecase.FindElfInventoryWithMostCaloriesUseCaseImpl
import day_1_calorie_couting.domain.port.`in`.usecase.FindElvesInventoriesWithMostCaloriesUseCaseImpl
import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryResponse
import day_1_calorie_couting.domain.port.`in`.usecase.data.ElfInventoryRankResponse
import day_1_calorie_couting.domain.usecase.FindElfInventoryWithMostCaloriesUseCase
import day_1_calorie_couting.domain.usecase.FindElvesInventoriesWithMostCaloriesUseCase
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.java8.En
import kotlin.test.assertEquals

class ElvesInventoriesStepDefs : En {

    private val findElfInventoryWithMostCaloriesUseCase: FindElfInventoryWithMostCaloriesUseCase =
        FindElfInventoryWithMostCaloriesUseCaseImpl()
    private val findElvesInventoriesWithMostCaloriesUseCase: FindElvesInventoriesWithMostCaloriesUseCase =
        FindElvesInventoriesWithMostCaloriesUseCaseImpl()

    private lateinit var inventoryListString: String
    private lateinit var elfInventoryResponse: ElfInventoryResponse;
    private lateinit var rankedInventories: List<ElfInventoryRankResponse>;

    @Given("the inventory list")
    fun givenInventory(inventoryString: String) {
        this.inventoryListString = inventoryString
    }

    @When("I ask wich elf inventory has the most calories in this list")
    fun askInventory() {
        this.elfInventoryResponse = this.findElfInventoryWithMostCaloriesUseCase.handle(this.inventoryListString)
    }

    @When("I ask the top {int} inventories with the biggest amount of calories")
    fun askNTopInventories(numberOfInventoriesToRank: Int) {
        this.rankedInventories = this.findElvesInventoriesWithMostCaloriesUseCase.handle(this.inventoryListString, numberOfInventoriesToRank)
    }

    @Then("I find this is inventory of elf number {int} with {int} calories")
    fun checkInventory(elfNumber: Long, caloriesAmount: Long) {
        assertEquals(elfNumber, this.elfInventoryResponse.elfNumber)
        assertEquals(caloriesAmount, this.elfInventoryResponse.caloriesAmount)
    }

    @Then("I have this inventory ranking")
    fun checkInventoriesRanking(dataTable: DataTable) {
        val inventoriesRanks: List<ElfInventoryRankResponse> = dataTable.asMaps().map { mutableMap ->
            ElfInventoryRankResponse(
                mutableMap["position"]!!.toLong(),
                mutableMap["elfNumber"]!!.toLong(),
                mutableMap["calories"]!!.toLong()
            )
        }

        assertEquals(inventoriesRanks.size, this.rankedInventories.size)

        inventoriesRanks.forEachIndexed { index, inventoryRankResponse ->
            assertEquals(this.rankedInventories[index], inventoryRankResponse)
        }
    }

}