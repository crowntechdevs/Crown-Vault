package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.i18n.detectDefaultLang
import com.example.i18n.translations
import com.example.model.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CrownVaultUiState(
    val currentScreen: Screen = Screen.VAULT,
    val screenHistory: List<Screen> = emptyList(),
    val activeModal: ModalType? = null,
    val drawerOpen: Boolean = false,
    val activeLegalDoc: LegalDoc? = null,
    val vaultPlusModalOpen: Boolean = false,
    val vaultPlusActive: Boolean = false,
    val vaultPlusPlan: Plan = Plan.MONTHLY,
    val activeSettingsModal: SettingsModalType? = null,
    val activeProfileModal: ProfileModalType? = null,
    val displayName: String = "Traveler 07",
    val walletAddressShort: String = "0x3A91...C89F",
    val walletAddressFull: String = "0x3A9184B200E4C89F7102D4B892A1E04C62F9C89F",
    val toastMessage: String = "",
    val swapped: Boolean = false,
    val staked: Boolean = false,
    val cardLocked: Boolean = false,
    val cardDetailsRevealed: Boolean = false,
    val balanceHidden: Boolean = false,
    val lang: Lang = detectDefaultLang(),
    val themeMode: String = "midnight",
    val networkPref: String = "crownlink",
    val notifSmart: Boolean = true,
    val notifPrice: Boolean = true,
    val notifSecurity: Boolean = true,
    val assets: List<Asset> = defaultAssets,
    val watchlist: Set<String> = emptySet(),
    val marketsTab: String = "overview",
    val selectedTradeAssetIndex: Int = 0,
    val chartTimeframe: String = "1D",
    val chartStyle: String = "area",
    val tradeSide: String = "buy",
    val orderType: String = "market",
    val orderAmount: String = "",
    val swapFromAssetIndex: Int = 0,
    val swapToAssetIndex: Int = 2,
    val swapAmountInput: String = "240.00",
    val secBiometric: Boolean = true,
    val secTwoFactor: Boolean = true,
    val secTxScanning: Boolean = true,
    val systemScanning: Boolean = false,
    val systemScanned: Boolean = false,
    val securityScanning: Boolean = false,
    val securityScanned: Boolean = false,
    val securityScanProgress: Float = 0f,
    val securityScanStageText: String = "",
    val activities: List<ActivityTransaction> = defaultActivities,
    val activityFilter: String = "all",
    val expandedActivityId: String? = null,
    val recipientAddress: String = ""
) {
    val t: TranslationSet
        get() = translations[lang] ?: translations.getValue(Lang.EN)

    val selectedTradeAsset: Asset
        get() = assets.getOrElse(selectedTradeAssetIndex) { assets.first() }

    val filteredActivities: List<ActivityTransaction>
        get() = if (activityFilter == "all") {
            activities
        } else {
            activities.filter { it.type.filterKey == activityFilter }
        }
}

class CrownVaultViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CrownVaultUiState())
    val uiState: StateFlow<CrownVaultUiState> = _uiState.asStateFlow()

    private var toastJob: Job? = null
    private var systemScanJob: Job? = null
    private var securityScanJob: Job? = null

    fun navigateTo(target: Screen) {
        _uiState.update { state ->
            if (state.currentScreen == target) {
                state.copy(drawerOpen = false)
            } else {
                state.copy(
                    currentScreen = target,
                    screenHistory = (state.screenHistory + state.currentScreen).takeLast(12),
                    drawerOpen = false
                )
            }
        }
    }

    fun navigateBack(): Boolean {
        val state = _uiState.value
        if (state.activeLegalDoc != null) {
            _uiState.update { it.copy(activeLegalDoc = null) }
            return true
        }
        if (state.vaultPlusModalOpen) {
            _uiState.update { it.copy(vaultPlusModalOpen = false) }
            return true
        }
        if (state.activeSettingsModal != null) {
            _uiState.update { it.copy(activeSettingsModal = null) }
            return true
        }
        if (state.activeProfileModal != null) {
            _uiState.update { it.copy(activeProfileModal = null) }
            return true
        }
        if (state.activeModal != null) {
            _uiState.update { it.copy(activeModal = null) }
            return true
        }
        if (state.drawerOpen) {
            _uiState.update { it.copy(drawerOpen = false) }
            return true
        }
        if (state.screenHistory.isNotEmpty()) {
            val previous = state.screenHistory.last()
            _uiState.update {
                it.copy(
                    currentScreen = previous,
                    screenHistory = it.screenHistory.dropLast(1)
                )
            }
            return true
        }
        if (state.currentScreen != Screen.VAULT) {
            _uiState.update { it.copy(currentScreen = Screen.VAULT) }
            return true
        }
        return false
    }

    fun showToast(message: String) {
        toastJob?.cancel()
        _uiState.update { it.copy(toastMessage = message) }
        toastJob = viewModelScope.launch {
            delay(2600L)
            _uiState.update { it.copy(toastMessage = "") }
        }
    }

    fun openModal(modal: ModalType) {
        _uiState.update { it.copy(activeModal = modal, drawerOpen = false) }
    }

    fun closeModal() {
        _uiState.update { it.copy(activeModal = null) }
    }

    fun setDrawerOpen(open: Boolean) {
        _uiState.update { it.copy(drawerOpen = open) }
    }

    fun openLegalDoc(doc: LegalDoc) {
        _uiState.update { it.copy(activeLegalDoc = doc, drawerOpen = false) }
    }

    fun closeLegalDoc() {
        _uiState.update { it.copy(activeLegalDoc = null) }
    }

    fun setVaultPlusModalOpen(open: Boolean) {
        _uiState.update { it.copy(vaultPlusModalOpen = open) }
    }

    fun setVaultPlusPlan(plan: Plan) {
        _uiState.update { it.copy(vaultPlusPlan = plan) }
    }

    fun activateVaultPlus() {
        val msg = _uiState.value.t.toast.vaultPlusTrial
        _uiState.update {
            it.copy(
                vaultPlusActive = true,
                vaultPlusModalOpen = false
            )
        }
        showToast(msg)
    }

    fun openSettingsModal(type: SettingsModalType) {
        _uiState.update { it.copy(activeSettingsModal = type) }
    }

    fun closeSettingsModal() {
        _uiState.update { it.copy(activeSettingsModal = null) }
    }

    fun toggleBalanceHidden() {
        _uiState.update { it.copy(balanceHidden = !it.balanceHidden) }
    }

    fun activateStaking() {
        val msg = _uiState.value.t.toast.stakingActive
        _uiState.update { it.copy(staked = true) }
        showToast(msg)
    }

    fun toggleSwap() {
        val msg = _uiState.value.t.toast.rateUpdated
        _uiState.update { state ->
            state.copy(
                swapped = !state.swapped,
                swapFromAssetIndex = state.swapToAssetIndex,
                swapToAssetIndex = state.swapFromAssetIndex
            )
        }
        showToast(msg)
    }

    fun cycleSwapFromAsset() {
        _uiState.update { state ->
            val count = state.assets.size
            var next = (state.swapFromAssetIndex + 1) % count
            if (next == state.swapToAssetIndex) {
                next = (next + 1) % count
            }
            val asset = state.assets[next]
            val defaultAmt = formatQuickAmount(asset.balanceNumeric * 0.25)
            state.copy(swapFromAssetIndex = next, swapAmountInput = defaultAmt)
        }
    }

    fun cycleSwapToAsset() {
        _uiState.update { state ->
            val count = state.assets.size
            var next = (state.swapToAssetIndex + 1) % count
            if (next == state.swapFromAssetIndex) {
                next = (next + 1) % count
            }
            state.copy(swapToAssetIndex = next)
        }
    }

    fun setSwapAmountInput(amount: String) {
        _uiState.update { it.copy(swapAmountInput = amount) }
    }

    fun toggleCardLock() {
        val state = _uiState.value
        val nextLocked = !state.cardLocked
        val msg = if (nextLocked) state.t.toast.cardLocked else state.t.toast.cardUnlocked
        _uiState.update { it.copy(cardLocked = nextLocked) }
        showToast(msg)
    }

    fun toggleCardDetailsRevealed() {
        _uiState.update { it.copy(cardDetailsRevealed = !it.cardDetailsRevealed) }
    }

    fun setLanguage(lang: Lang) {
        _uiState.update { it.copy(lang = lang, activeModal = null) }
    }

    fun selectThemeMode(mode: String) {
        val msg = _uiState.value.t.system.themeApplied
        _uiState.update { it.copy(themeMode = mode, activeSettingsModal = null) }
        showToast(msg)
    }

    fun selectNetworkPref(network: String) {
        val msg = _uiState.value.t.system.networkSwitched
        _uiState.update { it.copy(networkPref = network, activeSettingsModal = null) }
        showToast(msg)
    }

    fun toggleNotifSmart() {
        _uiState.update { it.copy(notifSmart = !it.notifSmart) }
    }

    fun toggleNotifPrice() {
        _uiState.update { it.copy(notifPrice = !it.notifPrice) }
    }

    fun toggleNotifSecurity() {
        _uiState.update { it.copy(notifSecurity = !it.notifSecurity) }
    }

    fun saveNotificationSettings() {
        val msg = _uiState.value.t.system.notifSettingsSaved
        _uiState.update { it.copy(activeSettingsModal = null) }
        showToast(msg)
    }

    fun setMarketsTab(tab: String) {
        _uiState.update { it.copy(marketsTab = tab) }
    }

    fun selectTradeAsset(index: Int) {
        _uiState.update { state ->
            val clamped = index.coerceIn(0, (state.assets.size - 1).coerceAtLeast(0))
            state.copy(selectedTradeAssetIndex = clamped)
        }
    }

    fun openAssetInTradeView(asset: Asset) {
        _uiState.update { state ->
            val idx = state.assets.indexOfFirst { it.ticker.equals(asset.ticker, ignoreCase = true) }
                .takeIf { it >= 0 } ?: 0
            val updatedHistory = if (state.currentScreen != Screen.MARKETS) {
                (state.screenHistory + state.currentScreen).takeLast(12)
            } else {
                state.screenHistory
            }
            state.copy(
                currentScreen = Screen.MARKETS,
                screenHistory = updatedHistory,
                marketsTab = "trading",
                selectedTradeAssetIndex = idx,
                drawerOpen = false
            )
        }
    }

    fun openAssetInSwap(asset: Asset) {
        _uiState.update { state ->
            val fromIdx = state.assets.indexOfFirst { it.ticker.equals(asset.ticker, ignoreCase = true) }
                .takeIf { it >= 0 } ?: 0
            val usdtIdx = state.assets.indexOfFirst { it.ticker == "USDT" }.takeIf { it >= 0 } ?: 0
            val crwnIdx = state.assets.indexOfFirst { it.ticker == "CRWN" }.takeIf { it >= 0 } ?: 0
            val toIdx = if (fromIdx == usdtIdx) crwnIdx else usdtIdx
            val updatedHistory = if (state.currentScreen != Screen.SWAP) {
                (state.screenHistory + state.currentScreen).takeLast(12)
            } else {
                state.screenHistory
            }
            val quickAmt = formatQuickAmount(state.assets[fromIdx].balanceNumeric * 0.25)
            state.copy(
                currentScreen = Screen.SWAP,
                screenHistory = updatedHistory,
                swapFromAssetIndex = fromIdx,
                swapToAssetIndex = toIdx,
                swapAmountInput = quickAmt,
                drawerOpen = false
            )
        }
    }

    fun setChartTimeframe(timeframe: String) {
        _uiState.update { it.copy(chartTimeframe = timeframe) }
    }

    fun setChartStyle(style: String) {
        _uiState.update { it.copy(chartStyle = style) }
    }

    fun setTradeSide(side: String) {
        _uiState.update { it.copy(tradeSide = side) }
    }

    fun setOrderType(type: String) {
        _uiState.update { it.copy(orderType = type) }
    }

    fun setOrderAmount(amount: String) {
        _uiState.update { it.copy(orderAmount = amount) }
    }

    fun setOrderPercentage(fraction: Double) {
        val asset = _uiState.value.selectedTradeAsset
        val computed = (asset.balanceNumeric * fraction).coerceAtLeast(0.0)
        _uiState.update { it.copy(orderAmount = formatQuickAmount(computed)) }
    }

    fun triggerQuickTradeAction(action: String) {
        val state = _uiState.value
        val asset = state.selectedTradeAsset
        when (action) {
            "buy" -> {
                val prefill = if (state.orderAmount.isBlank()) {
                    formatQuickAmount((asset.balanceNumeric * 0.10).coerceAtLeast(0.01))
                } else {
                    state.orderAmount
                }
                _uiState.update { it.copy(tradeSide = "buy", orderAmount = prefill) }
                showToast("${state.t.markets.buy.uppercase()} ${asset.pairSymbol} · ${asset.value}")
            }
            "sell" -> {
                val prefill = if (state.orderAmount.isBlank()) {
                    formatQuickAmount((asset.balanceNumeric * 0.25).coerceAtLeast(0.01))
                } else {
                    state.orderAmount
                }
                _uiState.update { it.copy(tradeSide = "sell", orderAmount = prefill) }
                showToast("${state.t.markets.sell.uppercase()} ${asset.pairSymbol} · ${asset.value}")
            }
            "swap" -> {
                openAssetInSwap(asset)
            }
        }
    }

    fun submitTradeOrder() {
        val state = _uiState.value
        val asset = state.selectedTradeAsset
        val amt = state.orderAmount.ifBlank { formatQuickAmount((asset.balanceNumeric * 0.10).coerceAtLeast(0.01)) }
        val sideLabel = if (state.tradeSide == "sell") state.t.markets.sell else state.t.markets.buy
        val msg = "$sideLabel $amt ${asset.ticker} @ ${asset.value} — ${state.t.toast.orderPlaced}"
        _uiState.update { it.copy(orderAmount = "") }
        showToast(msg)
    }

    private fun formatQuickAmount(value: Double): String {
        return when {
            value >= 100.0 -> "%.2f".format(java.util.Locale.US, value)
            value >= 1.0 -> "%.3f".format(java.util.Locale.US, value)
            else -> "%.4f".format(java.util.Locale.US, value)
        }
    }

    fun addAssetToWatchlist(asset: Asset) {
        val msg = "${asset.name} ${_uiState.value.t.markets.addedToWatchlist}"
        _uiState.update { it.copy(watchlist = it.watchlist + asset.ticker) }
        showToast(msg)
    }

    fun setRecipientAddress(address: String) {
        _uiState.update { it.copy(recipientAddress = address) }
    }

    fun openProfileModal(modalType: ProfileModalType) {
        _uiState.update { it.copy(activeProfileModal = modalType, drawerOpen = false) }
    }

    fun closeProfileModal() {
        _uiState.update { it.copy(activeProfileModal = null) }
    }

    fun saveDisplayName(newName: String) {
        val trimmed = newName.trim().ifEmpty { "Traveler 07" }
        _uiState.update {
            it.copy(
                displayName = trimmed,
                activeProfileModal = null
            )
        }
        showToast("Display name updated to $trimmed")
    }

    fun toggleSecBiometric() {
        val next = !_uiState.value.secBiometric
        _uiState.update { it.copy(secBiometric = next) }
        showToast(if (next) "Biometric Lock enabled" else "Biometric Lock disabled")
    }

    fun toggleSecTwoFactor() {
        val next = !_uiState.value.secTwoFactor
        _uiState.update { it.copy(secTwoFactor = next) }
        showToast(if (next) "Two-Factor Auth enabled" else "Two-Factor Auth disabled")
    }

    fun toggleSecTxScanning() {
        val next = !_uiState.value.secTxScanning
        _uiState.update { it.copy(secTxScanning = next) }
        showToast(if (next) "Transaction Scanning enabled" else "Transaction Scanning disabled")
    }

    fun setActivityFilter(filter: String) {
        _uiState.update { it.copy(activityFilter = filter) }
    }

    fun toggleExpandedActivity(id: String) {
        _uiState.update { state ->
            state.copy(expandedActivityId = if (state.expandedActivityId == id) null else id)
        }
    }

    fun runSystemSecurityScan() {
        if (_uiState.value.systemScanning) return
        systemScanJob?.cancel()
        _uiState.update { it.copy(systemScanning = true) }
        systemScanJob = viewModelScope.launch {
            delay(1800L)
            val msg = _uiState.value.t.security.allNominal
            _uiState.update { it.copy(systemScanning = false, systemScanned = true) }
            showToast(msg)
        }
    }

    fun runFullSecurityScan() {
        if (_uiState.value.securityScanning) return
        securityScanJob?.cancel()
        _uiState.update {
            it.copy(
                securityScanning = true,
                securityScanned = false,
                securityScanProgress = 0.12f,
                securityScanStageText = "Verifying biometric enclave & hardware keys…"
            )
        }
        securityScanJob = viewModelScope.launch {
            delay(450L)
            _uiState.update {
                it.copy(
                    securityScanProgress = 0.42f,
                    securityScanStageText = "Auditing 2FA session tokens & zero-knowledge proofs…"
                )
            }
            delay(450L)
            _uiState.update {
                it.copy(
                    securityScanProgress = 0.76f,
                    securityScanStageText = "Scanning real-time transaction firewall & whitelist…"
                )
            }
            delay(450L)
            _uiState.update {
                it.copy(
                    securityScanProgress = 1.0f,
                    securityScanStageText = "Finalizing integrity check…"
                )
            }
            delay(300L)
            val msg = _uiState.value.t.security.allNominal
            _uiState.update {
                it.copy(
                    securityScanning = false,
                    securityScanned = true,
                    securityScanProgress = 1.0f,
                    securityScanStageText = ""
                )
            }
            showToast(msg)
        }
    }
}
