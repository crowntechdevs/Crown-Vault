package com.example

import com.example.model.Lang
import com.example.model.LegalDoc
import com.example.model.ModalType
import com.example.model.Plan
import com.example.model.Screen
import com.example.viewmodel.CrownVaultViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CrownVaultViewModelTest {

    @Test
    fun `navigation and backstack pop work as expected`() {
        val viewModel = CrownVaultViewModel()
        assertEquals(Screen.VAULT, viewModel.uiState.value.currentScreen)

        viewModel.navigateTo(Screen.MARKETS)
        assertEquals(Screen.MARKETS, viewModel.uiState.value.currentScreen)

        viewModel.navigateTo(Screen.STAKING)
        assertEquals(Screen.STAKING, viewModel.uiState.value.currentScreen)

        val poppedToMarkets = viewModel.navigateBack()
        assertTrue(poppedToMarkets)
        assertEquals(Screen.MARKETS, viewModel.uiState.value.currentScreen)

        val poppedToVault = viewModel.navigateBack()
        assertTrue(poppedToVault)
        assertEquals(Screen.VAULT, viewModel.uiState.value.currentScreen)
    }

    @Test
    fun `swap card lock and language updates mutate state`() {
        val viewModel = CrownVaultViewModel()
        assertFalse(viewModel.uiState.value.swapped)
        viewModel.toggleSwap()
        assertTrue(viewModel.uiState.value.swapped)

        assertFalse(viewModel.uiState.value.cardLocked)
        viewModel.toggleCardLock()
        assertTrue(viewModel.uiState.value.cardLocked)

        viewModel.setLanguage(Lang.AR)
        assertEquals(Lang.AR, viewModel.uiState.value.lang)
        assertTrue(viewModel.uiState.value.lang.isRtl)
    }

    @Test
    fun `modals and legal sheets open and close via back navigation`() {
        val viewModel = CrownVaultViewModel()
        viewModel.openModal(ModalType.RECEIVE)
        assertEquals(ModalType.RECEIVE, viewModel.uiState.value.activeModal)
        assertTrue(viewModel.navigateBack())
        assertNull(viewModel.uiState.value.activeModal)

        viewModel.openLegalDoc(LegalDoc.TERMS)
        assertEquals(LegalDoc.TERMS, viewModel.uiState.value.activeLegalDoc)
        assertTrue(viewModel.navigateBack())
        assertNull(viewModel.uiState.value.activeLegalDoc)

        viewModel.setVaultPlusPlan(Plan.ANNUAL)
        assertEquals(Plan.ANNUAL, viewModel.uiState.value.vaultPlusPlan)
    }
}
