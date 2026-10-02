package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.ModalType
import com.example.model.Screen
import com.example.ui.components.*
import com.example.ui.screens.*
import com.example.ui.theme.CrownVaultTheme
import com.example.viewmodel.CrownVaultViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        setContent {
            val viewModel: CrownVaultViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            CrownVaultTheme(themeMode = uiState.themeMode) {
                val layoutDirection = if (uiState.lang.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
                CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                    CrownVaultApp(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun CrownVaultApp(viewModel: CrownVaultViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val t = uiState.t

    val hasOverlayOrBackstack = uiState.activeLegalDoc != null ||
        uiState.vaultPlusModalOpen ||
        uiState.activeSettingsModal != null ||
        uiState.activeProfileModal != null ||
        uiState.activeModal != null ||
        uiState.drawerOpen ||
        uiState.currentScreen != Screen.VAULT

    BackHandler(enabled = hasOverlayOrBackstack) {
        viewModel.navigateBack()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AmbientShellBackground(themeMode = uiState.themeMode)

        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        TopBar(
                            onOpenDrawer = { viewModel.setDrawerOpen(true) },
                            onOpenNotifications = { viewModel.openModal(ModalType.NOTIFY) }
                        )
                    }
                }
            },
            bottomBar = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        BottomNavBar(
                            currentScreen = uiState.currentScreen,
                            t = t,
                            onSelectScreen = { viewModel.navigateTo(it) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            val scrollState = rememberScrollState()
            LaunchedEffect(uiState.currentScreen, uiState.marketsTab) {
                scrollState.animateScrollTo(0)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .widthIn(max = 600.dp)
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 22.dp, vertical = 18.dp)
                ) {
                    EyebrowNetworkRow(
                        t = t,
                        networkPref = uiState.networkPref
                    )

                    AnimatedContent(
                        targetState = uiState.currentScreen,
                        transitionSpec = {
                            (fadeIn(tween(240)) + slideInVertically(tween(240)) { it / 14 })
                                .togetherWith(fadeOut(tween(180)))
                        },
                        label = "screenTransition"
                    ) { targetScreen ->
                        when (targetScreen) {
                            Screen.VAULT -> VaultScreen(
                                uiState = uiState,
                                onToggleBalanceHidden = { viewModel.toggleBalanceHidden() },
                                onAction = { viewModel.openModal(it) },
                                onNavigate = { viewModel.navigateTo(it) },
                                onAssetClick = { asset ->
                                    viewModel.openAssetInTradeView(asset)
                                }
                            )

                            Screen.MARKETS -> MarketsScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onTabChange = { viewModel.setMarketsTab(it) },
                                onSelectTradeAsset = { viewModel.selectTradeAsset(it) },
                                onTimeframeChange = { viewModel.setChartTimeframe(it) },
                                onChartStyleChange = { viewModel.setChartStyle(it) },
                                onQuickAction = { viewModel.triggerQuickTradeAction(it) },
                                onTradeSideChange = { viewModel.setTradeSide(it) },
                                onOrderTypeChange = { viewModel.setOrderType(it) },
                                onOrderAmountChange = { viewModel.setOrderAmount(it) },
                                onOrderPercentSelect = { viewModel.setOrderPercentage(it) },
                                onSubmitTrade = { viewModel.submitTradeOrder() },
                                onAddWatchlist = { viewModel.addAssetToWatchlist(it) }
                            )

                            Screen.STAKING -> StakingScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onStake = { viewModel.activateStaking() }
                            )

                            Screen.SWAP -> SwapScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onSwap = { viewModel.toggleSwap() },
                                onCycleFromAsset = { viewModel.cycleSwapFromAsset() },
                                onCycleToAsset = { viewModel.cycleSwapToAsset() }
                            )

                            Screen.CARD -> CardScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onToggleLock = { viewModel.toggleCardLock() },
                                onToggleDetails = { viewModel.toggleCardDetailsRevealed() },
                                onNotify = { viewModel.showToast(it) },
                                onViewAllActivity = { viewModel.navigateTo(Screen.ACTIVITY) }
                            )

                            Screen.PLUS -> PlusScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onOpenVaultPlusModal = { viewModel.setVaultPlusModalOpen(true) }
                            )

                            Screen.ACTIVITY -> ActivityScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onFilterChange = { viewModel.setActivityFilter(it) },
                                onToggleExpand = { viewModel.toggleExpandedActivity(it) },
                                onNotify = { viewModel.showToast(it) }
                            )

                            Screen.SYSTEM -> SystemScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onNavigateSecurity = { viewModel.navigateTo(Screen.SECURITY) },
                                onNavigateProfile = { viewModel.navigateTo(Screen.PROFILE) },
                                onOpenLangModal = { viewModel.openModal(ModalType.LANG) },
                                onOpenSettingsModal = { viewModel.openSettingsModal(it) },
                                onRunScan = { viewModel.runSystemSecurityScan() }
                            )

                            Screen.SECURITY -> SecurityScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onToggleBiometric = { viewModel.toggleSecBiometric() },
                                onToggleTwoFactor = { viewModel.toggleSecTwoFactor() },
                                onToggleTxScanning = { viewModel.toggleSecTxScanning() },
                                onRunFullScan = { viewModel.runFullSecurityScan() }
                            )

                            Screen.PROFILE -> ProfileScreen(
                                uiState = uiState,
                                onBack = { viewModel.navigateBack() },
                                onOpenLegal = { viewModel.openLegalDoc(it) },
                                onExportProfile = { viewModel.showToast(t.toast.profileExported) },
                                onOpenProfileModal = { viewModel.openProfileModal(it) }
                            )
                        }
                    }

                    CrownBrandFooter()

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        // Slide-out Navigation Drawer
        CrownDrawer(
            open = uiState.drawerOpen,
            uiState = uiState,
            onClose = { viewModel.setDrawerOpen(false) },
            onNavigate = { viewModel.navigateTo(it) },
            onNotifications = { viewModel.openModal(ModalType.NOTIFY) },
            onOpenLegal = { viewModel.openLegalDoc(it) }
        )

        // Modals
        uiState.activeModal?.let { modal ->
            ActionAndInboxModal(
                modalType = modal,
                uiState = uiState,
                onClose = { viewModel.closeModal() },
                onLangChange = { viewModel.setLanguage(it) },
                onRecipientAddressChange = { viewModel.setRecipientAddress(it) },
                onNotify = { viewModel.showToast(it) }
            )
        }

        uiState.activeLegalDoc?.let { doc ->
            LegalModal(
                doc = doc,
                t = t,
                onClose = { viewModel.closeLegalDoc() }
            )
        }

        if (uiState.vaultPlusModalOpen) {
            VaultPlusModal(
                uiState = uiState,
                onPlanChange = { viewModel.setVaultPlusPlan(it) },
                onClose = { viewModel.setVaultPlusModalOpen(false) },
                onActivate = { viewModel.activateVaultPlus() }
            )
        }

        uiState.activeSettingsModal?.let { settingsType ->
            SettingsModal(
                type = settingsType,
                uiState = uiState,
                onClose = { viewModel.closeSettingsModal() },
                onSelectTheme = { viewModel.selectThemeMode(it) },
                onSelectNetwork = { viewModel.selectNetworkPref(it) },
                onToggleSmart = { viewModel.toggleNotifSmart() },
                onTogglePrice = { viewModel.toggleNotifPrice() },
                onToggleSecurity = { viewModel.toggleNotifSecurity() },
                onSaveNotifications = { viewModel.saveNotificationSettings() }
            )
        }

        uiState.activeProfileModal?.let { profileModalType ->
            ProfileInteractiveModal(
                modalType = profileModalType,
                uiState = uiState,
                onClose = { viewModel.closeProfileModal() },
                onSaveDisplayName = { viewModel.saveDisplayName(it) },
                onNotify = { viewModel.showToast(it) }
            )
        }

        // Floating Toast Notification
        ToastOverlay(
            message = uiState.toastMessage,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
